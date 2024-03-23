package com.maybank.core.endpoint.base;

import com.maybank.core.endpoint.response.PageableResponse;
import com.maybank.core.endpoint.response.StatusResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;
import java.util.Collection;

/**
 * @author Tewaraj
 */
@SuppressWarnings("unchecked")
public abstract class BaseEndpoint implements Serializable {

    protected static final long serialVersionUID = 1L;

    protected static final Logger LOGGER = LoggerFactory.getLogger(BaseEndpoint.class);

    protected <T extends Collection<?>> ResponseEntity<PageableResponse<T>> pageableSuccessResponse(Page<?> page, String code, String message) {
        return new ResponseEntity<>(PageableResponse.Builder.instance()
                .withObject(page.getContent())
                .withSlice(page.getNumber())
                .withSize(page.getNumberOfElements())
                .withTotal(page.getTotalElements())
                .withPages(page.getTotalPages())
                .withSuccess(StatusResponse.Builder.instance().withCode(code).withMessage(message).build())
                .build(), HttpStatus.OK);
    }

}
