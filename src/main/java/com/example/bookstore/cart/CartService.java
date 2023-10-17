package com.example.bookstore.cart;

import com.example.bookstore.user.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final UsersRepository usersRepository;
    public List<Cart> getCart() {
        return cartRepository.findAll();
    }

    public List<Cart> getCart(long userId) {
        return List.of(cartRepository.findByUser(usersRepository.findById(userId).orElseThrow(
                ()->new UsernameNotFoundException("user not found")
        )).orElseThrow(
                ()->new IllegalStateException("cart not found")
        ));
    }


    public void addBookToCart(AddToCartRequest request) {
    }
}
