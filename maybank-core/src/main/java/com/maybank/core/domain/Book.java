package com.maybank.core.domain;

import com.maybank.core.domain.enums.BookStatus;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.UUID;

/**
 * @author Tewaraj
 */
@Entity
@Table(name = "BOOK")
public class Book implements Serializable {

    @Id
    @Column(name = "BOOK_ID", nullable = false, columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(name = "ISBN_NO")
    private String isbnNo;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "AUTHOR")
    private String author;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS", length = 32, nullable = false)
    private BookStatus status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "BORROWER_ID", referencedColumnName = "BORROWER_ID",
            foreignKey = @ForeignKey(name = "FK_BK_BORROWER"))
    private Borrower borrower;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

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

    public BookStatus getStatus() {
        return status;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }

    public Borrower getBorrower() {
        return borrower;
    }

    public void setBorrower(Borrower borrower) {
        this.borrower = borrower;
    }

    public static final class Builder {
        private UUID id;
        private String isbnNo;
        private String title;
        private String author;
        private BookStatus status;
        private Borrower borrower;

        private Builder() {
        }

        public static Builder instance() {
            return new Builder();
        }

        public Builder withId(UUID id) {
            this.id = id;
            return this;
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

        public Builder withStatus(BookStatus status) {
            this.status = status;
            return this;
        }

        public Builder withBorrower(Borrower borrower) {
            this.borrower = borrower;
            return this;
        }

        public Book build() {
            Book book = new Book();
            book.setId(id);
            book.setIsbnNo(isbnNo);
            book.setTitle(title);
            book.setAuthor(author);
            book.setStatus(status);
            book.setBorrower(borrower);
            return book;
        }
    }
}
