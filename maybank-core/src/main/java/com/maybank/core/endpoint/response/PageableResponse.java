package com.maybank.core.endpoint.response;

import java.util.Collection;

/**
 * @author Tewaraj
 */
public class PageableResponse<T extends Collection<?>> extends BaseStatusResponse {

    /**
     * current page slice. e.g. pagination page 2, slice should return 1.
     */
    private int slice;

    /**
     * total number of pages
     */
    private int pages;

    /**
     * object size (the fetched size)
     */
    private long size;

    /**
     * total records
     */
    private long total;

    /**
     * list of objects
     */
    private T object;

    public PageableResponse() {}

    /**
     * Use {@link Builder} to build this class.
     *
     * @param object  list of objects.
     * @param pages   total number of pages in pagination.
     * @param size    total of the {@link #object}
     * @param success success description
     * @param error   failure description
     */
    private PageableResponse(T object, int slice, int pages, long size, long total, StatusResponse success, StatusResponse error) {
        super(success, error);
        this.slice = slice;
        this.object = object;
        this.pages = pages;
        this.size = size;
        this.total = total;
    }

    public int getSlice() {
        return slice;
    }

    public void setSlice(int slice) {
        this.slice = slice;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }

    public static final class Builder<T extends Collection<?>> {
        private T object;
        private int slice;
        private int pages;
        private long size;
        private long total;
        private StatusResponse success;
        private StatusResponse error;

        private Builder() {
        }

        @SuppressWarnings("rawtypes")
        public static Builder instance() {
            return new Builder<>();
        }

        public Builder<T> withObject(T object) {
            this.object = object;
            return this;
        }

        public Builder<T> withSlice(int slice) {
            this.slice = slice;
            return this;
        }

        public Builder<T> withPages(int pages) {
            this.pages = pages;
            return this;
        }

        public Builder<T> withSize(long size) {
            this.size = size;
            return this;
        }

        public Builder<T> withTotal(long total) {
            this.total = total;
            return this;
        }

        public Builder<T> withSuccess(StatusResponse success) {
            this.success = success;
            return this;
        }

        public Builder<T> withError(StatusResponse error) {
            this.error = error;
            return this;
        }

        public PageableResponse<T> build() {
            return new PageableResponse<>(object, slice, pages, size, total, success, error);
        }
    }
}
