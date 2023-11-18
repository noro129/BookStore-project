package com.example.bookstore.cart;

import com.example.bookstore.book.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CartItemRepository extends JpaRepository<CartItem,Long> {
    @Query("select ci from CartItem ci where ci.book=?1 and ci.cart=?2")
    CartItem findCartItemByUserBook(Books book, Cart cart);
}
