package com.example.bookstore.cart;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping
    public List<Cart> getCart(@RequestParam(required = false) long userId){
        if(userId==-1) return cartService.getCart();
        return cartService.getCart(userId);
    }

    @PostMapping
    public void addBookToCart(@RequestBody AddToCartRequest request){
        cartService.addBookToCart(request);
    }

}
