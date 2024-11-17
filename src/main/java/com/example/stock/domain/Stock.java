package com.example.stock.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productId;
    private Long quantity;

    public Stock(Long productId, Long quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public void decreaseQuantity(Long quantity) {
        if (this.quantity - quantity < 0) {
            throw new IllegalArgumentException("재고가 부족합니다.");
        }

        this.quantity -= quantity;
    }
}
