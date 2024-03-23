package com.maybank.core.endpoint.request;

import java.io.Serializable;
import java.util.UUID;

/**
 * @author Tewaraj
 */
public class BorrowBookRequest implements Serializable {

    protected static final long serialVersionUID = 1L;

    private UUID borrowerId;

    public UUID getBorrowerId() {
        return borrowerId;
    }

    public void setBorrowerId(UUID borrowerId) {
        this.borrowerId = borrowerId;
    }

    public static final class Builder {
        private UUID borrowerId;

        private Builder() {
        }

        public static Builder instance() {
            return new Builder();
        }

        public Builder withBorrowerId(UUID borrowerId) {
            this.borrowerId = borrowerId;
            return this;
        }

        public BorrowBookRequest build() {
            BorrowBookRequest borrowBookRequest = new BorrowBookRequest();
            borrowBookRequest.setBorrowerId(borrowerId);
            return borrowBookRequest;
        }
    }
}
