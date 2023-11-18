package com.example.bookstore.cart;

import com.example.bookstore.book.Books;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "cart_item")
public class CartItem {

    @Id
    @SequenceGenerator(
            name = "cart_sequence",
            sequenceName = "cart_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "cart_sequence"
    )
    private long id;
    @ManyToOne
    private Books book;

    private int quantity;

    @ManyToOne
    @JsonIgnore
    private Cart cart;

    public CartItem(Books book, int quantity, Cart cart) {
        this.book = book;
        this.quantity = quantity;
        this.cart = cart;
    }
}
