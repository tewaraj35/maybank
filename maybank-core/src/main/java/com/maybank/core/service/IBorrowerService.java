package com.maybank.core.service;

import com.maybank.core.domain.Borrower;
import com.maybank.core.endpoint.request.BorrowerRequest;

import java.util.List;

/**
 * @author Tewaraj
 */
public interface IBorrowerService {

    String registration(BorrowerRequest request) throws Exception;

    List<Borrower> findAll();
}
