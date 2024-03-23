package com.maybank.core.endpoint.response;

import java.io.Serializable;

/**
 * @author Tewaraj
 */
public class StatusResponse implements Serializable {

    protected static final long serialVersionUID = 1L;

    private String code;
    private String message;

    public StatusResponse() {
    }

    /**
     * Use {@link Builder} to build this class.
     *
     * @param code    status code.
     * @param message explanation of this status.
     */
    public StatusResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static final class Builder {
        private String code;
        private String message;

        private Builder() {
        }

        public static Builder instance() {
            return new Builder();
        }

        public Builder withCode(String code) {
            this.code = code;
            return this;
        }

        public Builder withMessage(String message) {
            this.message = message;
            return this;
        }

        public StatusResponse build() {
            return new StatusResponse(code, message);
        }
    }

}
