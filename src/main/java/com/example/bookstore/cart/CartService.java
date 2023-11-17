package com.example.bookstore.cart;

import com.example.bookstore.book.BooksRepository;
import com.example.bookstore.user.Users;
import com.example.bookstore.user.UsersRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final UsersRepository usersRepository;
    private final BooksRepository booksRepository;
    @Transactional
    public List<Cart> getCart() {
        return cartRepository.findAll();
    }

    public List<Cart> getCart(long userId) {
        return List.of(cartRepository.findCartByUser(usersRepository.findById(userId).orElseThrow(
                ()->new UsernameNotFoundException("user not found")
        )).orElseThrow(
                ()-> new IllegalStateException("cart not found")
        ));
    }

    public void createCart(long userId){
        Users user = usersRepository.findById(userId).orElseThrow(
                () -> new UsernameNotFoundException("User with id "+userId+" not found!!!")
        );
        List<CartItem> empty = new ArrayList<>();
        cartRepository.save(new Cart(user,empty));
    }

    public void addBookToCart(AddToCartRequest request) {
        Optional<Cart> cartByUser = cartRepository.findCartByUser(usersRepository.findById(request.getUserId()).orElseThrow(
                () -> new UsernameNotFoundException("user with id "+request.getUserId()+" not found")
        ));
        Cart cart;
        if(cartByUser.isEmpty()){
            this.createCart(request.getUserId());
        }
        cart = getCart(request.getUserId()).get(0);
        cart.getItems().add(new CartItem(
                booksRepository.findById(request.getBookId()).orElseThrow(
                        () -> new IllegalStateException("book with id "+request.getBookId()+" not found")
                ),
                request.getQuantity(),
                cart
        ));
        cartRepository.save(cart);
    }
}
