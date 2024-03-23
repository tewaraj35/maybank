package com.maybank.core.endpoint.request;

import java.io.Serializable;

/**
 * @author Tewaraj
 */
public class BookRequest implements Serializable {

    protected static final long serialVersionUID = 1L;

    private String isbnNo;

    private String title;

    private String author;

    public String getIsbnNo() {
        return isbnNo;
    }

    public void setIsbnNo(String isbnNo) {
        this.isbnNo = isbnNo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public static final class Builder {
        private String isbnNo;
        private String title;
        private String author;

        private Builder() {
        }

        public static Builder instance() {
            return new Builder();
        }

        public Builder withIsbnNo(String isbnNo) {
            this.isbnNo = isbnNo;
            return this;
        }

        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder withAuthor(String author) {
            this.author = author;
            return this;
        }

        public BookRequest build() {
            BookRequest bookRequest = new BookRequest();
            bookRequest.setIsbnNo(isbnNo);
            bookRequest.setTitle(title);
            bookRequest.setAuthor(author);
            return bookRequest;
        }
    }
}
