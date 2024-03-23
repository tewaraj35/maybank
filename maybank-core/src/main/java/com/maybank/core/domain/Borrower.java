package com.maybank.core.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.util.UUID;

/**
 * @author Tewaraj
 */
@Entity
@Table(name = "BORROWER")
public class Borrower implements Serializable {

    @Id
    @Column(name = "BORROWER_ID", nullable = false, columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "EMAIL")
    private String email;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

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
        private UUID id;
        private String name;
        private String email;

        private Builder() {
        }

        public static Builder instance() {
            return new Builder();
        }

        public Builder withId(UUID id) {
            this.id = id;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public Borrower build() {
            Borrower borrower = new Borrower();
            borrower.setId(id);
            borrower.setName(name);
            borrower.setEmail(email);
            return borrower;
        }
    }
}
