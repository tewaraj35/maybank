package com.maybank.core.endpoint;

import com.maybank.core.domain.Borrower;
import com.maybank.core.endpoint.request.BorrowerRequest;
import com.maybank.core.service.IBorrowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.List;

/**
 * @author Tewaraj
 */
@RestController
public class BorrowerEndpoint implements Serializable {

    protected static final long serialVersionUID = 1L;

    private final IBorrowerService borrowService;

    @Autowired
    public BorrowerEndpoint(IBorrowerService borrowService) {
        this.borrowService = borrowService;
    }

    @PostMapping("/borrower/registration")
    public String registration(@RequestBody BorrowerRequest request) throws Exception {
       return borrowService.registration(request);
    }

    @GetMapping("/borrowers")
    public List<Borrower> findAll() {
        return borrowService.findAll();
    }

}
