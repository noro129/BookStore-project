package com.example.bookstore.cart;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping
    public List<Cart> getCart(@RequestParam(name = "userId", required = false) Optional<Long> userId){
        if(userId.isEmpty()) return cartService.getCart();
        else {
            return cartService.getCart(userId.get());
        }
    }

    @PostMapping
    public void addBookToCart(@RequestBody AddToCartRequest request){
        if(request.getQuantity()>5){
            throw new IllegalStateException("can not add more than 5 books");
        }
        cartService.addBookToCart(request);
    }

    @PostMapping( path = "/create")
    public void createCart(@RequestParam long userId){
        cartService.createCart(userId);
    }

}
