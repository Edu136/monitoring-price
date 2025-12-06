package com.moniter.price.domain.Entity;

import com.moniter.price.domain.vo.Email;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProdutoTrack {

    @Id @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Embedded
    private Email userEmail;

    private String url;
    private Double targetPrice;
    private Double lastCheckedPrice;
    private Boolean active;
    private LocalDate lastCheckedDate;

    private ProdutoTrack(String url, Double targetPrice, Email userEmail) {
        this.url = url;
        this.targetPrice = targetPrice;
        this.userEmail = userEmail;
        this.active = true;
    }

    public static ProdutoTrack create(String url, Double targetPrice, Email userEmail) {
        if (targetPrice <= 0) throw new IllegalArgumentException("Preço inválido");
        if (userEmail == null) throw new IllegalArgumentException("Email obrigatório");

        return new ProdutoTrack(url, targetPrice, userEmail);
    }

    public void updateLastPrice(Double newPrice) {
        this.lastCheckedPrice = newPrice;
        this.lastCheckedDate = LocalDate.now();
    }
}
