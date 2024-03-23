package com.maybank.core.endpoint.request;

import java.io.Serializable;

/**
 * @author Tewaraj
 */
public class BorrowerRequest implements Serializable {

    protected static final long serialVersionUID = 1L;

    private String name;

    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static final class Builder {
        private String name;
        private String email;

        private Builder() {
        }

        public static Builder instance() {
            return new Builder();
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public BorrowerRequest build() {
            BorrowerRequest borrowerRequest = new BorrowerRequest();
            borrowerRequest.setName(name);
            borrowerRequest.setEmail(email);
            return borrowerRequest;
        }
    }
}
