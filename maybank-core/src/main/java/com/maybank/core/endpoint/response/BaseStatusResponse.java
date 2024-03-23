package com.maybank.core.endpoint.response;

/**
 * @author Tewaraj
 */
public abstract class BaseStatusResponse extends BaseResponse {

    protected StatusResponse success;
    protected StatusResponse error;

    protected BaseStatusResponse() {
    }

    protected BaseStatusResponse(StatusResponse success, StatusResponse error) {
        this.success = success;
        this.error = error;
    }

    public StatusResponse getSuccess() {
        return success;
    }

    public void setSuccess(StatusResponse success) {
        this.success = success;
    }

    public StatusResponse getError() {
        return error;
    }

    public void setError(StatusResponse error) {
        this.error = error;
    }
}
