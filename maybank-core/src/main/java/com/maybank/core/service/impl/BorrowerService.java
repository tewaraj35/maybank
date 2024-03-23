package com.maybank.core.service.impl;

import com.maybank.core.domain.Borrower;
import com.maybank.core.endpoint.request.BorrowerRequest;
import com.maybank.core.repositories.BorrowerRepository;
import com.maybank.core.service.IBorrowerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Tewaraj
 */
@Service
@Transactional
public class BorrowerService implements IBorrowerService {

    private final BorrowerRepository borrowerRepository;

    @Autowired
    public BorrowerService(BorrowerRepository borrowerRepository) {
        this.borrowerRepository = borrowerRepository;
    }

    @Override
    public String registration(BorrowerRequest request) throws Exception {
        if (StringUtils.isBlank(request.getEmail())) {
            throw new Exception("Email is required.");
        }
        if (!isValidEmail(request.getEmail())) {
            throw new Exception("Email format is invalid.");
        }
        if (StringUtils.isBlank(request.getName())) {
            throw new Exception("Name is required.");
        }
        Optional<Borrower> existingBorrower = borrowerRepository.findByEmail(request.getEmail());
        if (existingBorrower.isPresent()) {
            throw new Exception("Borrower email already exist.");
        }

        borrowerRepository.save(Borrower.Builder.instance()
                .withId(UUID.randomUUID())
                .withEmail(request.getEmail())
                .withName(request.getName()).build());

        return "Successfully registered borrower " + request.getEmail();
    }

    // Functions
    /**
     * Email alice@example.com is valid
     * Email alice.bob@example.co.in is valid
     * Email alice@example.me.org is valid
     * Email alice.example.com is invalid
     * Email alice#example.com is invalid
     * Email @example.me.org is invalid
     * */
    public boolean isValidEmail(String email) {
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    @Override
    public List<Borrower> findAll() {
        return borrowerRepository.findAll();
    }


}
