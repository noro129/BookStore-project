package com.example.bookstore.cart;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    @GetMapping(path = "/containsBook")
    public Map<String, Boolean> containsBook(@RequestParam long userId,
                                             @RequestParam long bookId){
        Map<String, Boolean> object = new HashMap<>();
        object.put("containsBook",cartService.containsBook(userId, bookId));
        return object;
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

    @GetMapping(path = "/total")
    public Map<String, Double> getUserCartTotal(@RequestParam long userId){
        return cartService.getUserCartTotal(userId);
    }

    @DeleteMapping
    public void deleteCart(@RequestParam long userId){
        cartService.deleteCart(userId);
    }

//    @DeleteMapping(path = "/removeBook")
//    public void removeBookFromCart(@RequestParam long userId, @RequestParam long bookId){
//        cartService.removeBookFromCart(userId, bookId);
//    }
}
