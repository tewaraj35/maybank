package com.maybank.core.service.impl;

import com.maybank.core.domain.Book;
import com.maybank.core.domain.Borrower;
import com.maybank.core.domain.enums.BookStatus;
import com.maybank.core.endpoint.request.BookRequest;
import com.maybank.core.endpoint.request.BorrowBookRequest;
import com.maybank.core.repositories.BookRepository;
import com.maybank.core.repositories.BorrowerRepository;
import com.maybank.core.service.IBookService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Tewaraj
 */
@Service
@Transactional
public class BookService implements IBookService {

    private final BookRepository bookRepository;

    private final BorrowerRepository borrowerRepository;

    @Autowired
    public BookService(BookRepository bookRepository, BorrowerRepository borrowerRepository) {
        this.bookRepository = bookRepository;
        this.borrowerRepository = borrowerRepository;
    }

    @Override
    public String registration(BookRequest request) throws Exception {
        if (StringUtils.isBlank(request.getIsbnNo())) {
            throw new Exception("ISBN Number is required.");
        }
        validateISBN13Format(request.getIsbnNo());
        if (StringUtils.isBlank(request.getAuthor())) {
            throw new Exception("Author is required.");
        }
        if (StringUtils.isBlank(request.getTitle())) {
            throw new Exception("Title is required.");
        }

        List<Book> books = bookRepository.findAllByIsbnNo(request.getIsbnNo());

        if (CollectionUtils.isNotEmpty(books)) {
            for (Book book : books) {
                if (!StringUtils.equalsIgnoreCase(book.getTitle(), request.getTitle())) {
                    throw new Exception("Book with same ISBN number must have the same title.");
                }
                if (!StringUtils.equalsIgnoreCase(book.getAuthor(), request.getAuthor())) {
                    throw new Exception("Book with same ISBN number must have the same author.");
                }
            }
        }

        bookRepository.save(Book.Builder.instance()
                .withId(UUID.randomUUID())
                .withAuthor(request.getAuthor())
                .withTitle(request.getTitle())
                .withIsbnNo(request.getIsbnNo())
                .withStatus(BookStatus.AVAILABLE)
                .build());

        return "Successfully registered book " + request.getTitle();
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public String borrowBook(UUID bookId, BorrowBookRequest request) throws Exception {
        if (ObjectUtils.isEmpty(request.getBorrowerId())) {
            throw new Exception("Borrow id is required.");
        }
        Borrower borrower = borrowerRepository.findById(request.getBorrowerId())
                .orElseThrow(() -> new Exception("Borrower not found."));

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new Exception("Book not found."));

        if (book.getStatus() == BookStatus.BORROWED) {
            throw new Exception("Book has already been borrowed.");
        }

        book.setBorrower(borrower);
        book.setStatus(BookStatus.BORROWED);

        bookRepository.save(book);

        return "Book successfully borrowed!";
    }

    @Override
    public String returnBook(UUID bookId) throws Exception {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new Exception("Book not found."));

        if (book.getStatus() == BookStatus.AVAILABLE) {
            throw new Exception("Book is not borrowed to be returned.");
        }

        book.setBorrower(null);
        book.setStatus(BookStatus.AVAILABLE);

        bookRepository.save(book);

        return "Book successfully returned!";
    }

    @Override
    public Page<Book> criteriaSearch(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    @Override
    public String callThirdParty() {
        WebClient webclient = WebClient.builder().build();
        return webclient.get().uri("https://pokeapi.co/api/v2/pokemon/ditto")
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    // Functions
    /**
     * Validates ISBN 13 Format.
     * Valid ISBN 13 Format:
     * ISBN 978-0-596-52068-7 = TRUE
     * ISBN-13: 978-0-596-52068-7 = TRUE
     * 978 0 596 52068 7 = TRUE
     * 9780596520687 = TRUE
     * Invalid ISBN 13 Format:
     * ISBN 11978-0-596-52068-7 = FALSE
     * ISBN-12: 978-0-596-52068-7 = FALSE
     * 978 10 596 52068 7 = FALSE
     * 119780596520687 = FALSE
     *
     * @param isbn - string to be validated
     * @throws Exception if invalid ISBN 13 Format
     * */
    private void validateISBN13Format(String isbn) throws Exception {
        String regex = "^(?:ISBN(?:-13)?:? )?(?=[0-9]{13}$|(?=(?:[0-9]+[- ]){4})[- 0-9]{17}$)97[89][- ]?[0-9]{1,5}[- ]?[0-9]+[- ]?[0-9]+[- ]?[0-9]$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(isbn);
        if (!matcher.matches()) {
            throw new Exception("Invalid ISBN-13 format.");
        }
    }
}
