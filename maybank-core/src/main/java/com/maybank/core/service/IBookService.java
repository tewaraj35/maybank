package com.maybank.core.service;

import com.maybank.core.domain.Book;
import com.maybank.core.endpoint.request.BookRequest;
import com.maybank.core.endpoint.request.BorrowBookRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

/**
 * @author Tewaraj
 */
public interface IBookService {

    String registration(BookRequest request) throws Exception;

    List<Book> findAll();

    String borrowBook(UUID bookId, BorrowBookRequest request) throws Exception;

    String returnBook(UUID bookId) throws Exception;

    Page<Book> criteriaSearch(Pageable pageable);

    String callThirdParty();
}
