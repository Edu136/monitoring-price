package com.moniter.price.domain.vo;

import com.moniter.price.domain.Exceptions.InvalidEmailException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class Email {
    @Column(name = "user_email")
    private String address;

    protected Email() {}

    public Email(String address) {
        if (address == null) {
            throw new InvalidEmailException("Email vazio não é permitido.");
        }
        if(!address.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            throw new InvalidEmailException("Email inválido: " + address);
        }
        this.address = address;
    }
}
