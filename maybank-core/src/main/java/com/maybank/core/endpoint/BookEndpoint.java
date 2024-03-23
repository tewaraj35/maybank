package com.maybank.core.endpoint;


import com.maybank.core.domain.Book;
import com.maybank.core.endpoint.base.BaseEndpoint;
import com.maybank.core.endpoint.request.BookRequest;
import com.maybank.core.endpoint.request.BorrowBookRequest;
import com.maybank.core.endpoint.request.PageableRequest;
import com.maybank.core.endpoint.response.PageableResponse;
import com.maybank.core.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.MessageFormat;
import java.util.List;
import java.util.UUID;

/**
 * @author Tewaraj
 */
@RestController
public class BookEndpoint extends BaseEndpoint {

    private final IBookService bookService;

    @Autowired
    public BookEndpoint(IBookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/book/registration")
    public String registration(@RequestBody BookRequest request) throws Exception {
       return bookService.registration(request);
    }

    @GetMapping("/books")
    public List<Book> findAll() {
        return bookService.findAll();
    }

    @PutMapping("/book/{bookId}/borrow")
    public String borrowBook(@PathVariable UUID bookId, @RequestBody BorrowBookRequest request) throws Exception {
        LOGGER.info("Received borrow book {} request.", bookId);
        return bookService.borrowBook(bookId, request);
    }

    @PutMapping("/book/{bookId}/return")
    public String returnBook(@PathVariable UUID bookId) throws Exception {
        LOGGER.info("Received return book {} request.", bookId);
        return bookService.returnBook(bookId);
    }

    @PostMapping("/books/search")
    public ResponseEntity<PageableResponse<List<Book>>> criteriaSearch(
            @RequestBody(required = false) PageableRequest request) {

        Pageable pageable = request != null ? request.getPageRequest() : Pageable.unpaged();

        Page<Book> responses = bookService.criteriaSearch(pageable);

        return pageableSuccessResponse(responses, "200", responses.hasContent() ?
                MessageFormat.format("{0} record(s) found.", responses.getNumberOfElements()) : "No result found.");
    }

    @GetMapping("/call_third_party")
    public String callThirdParty() {
        return bookService.callThirdParty();
    }
}
