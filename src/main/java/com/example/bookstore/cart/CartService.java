package com.example.bookstore.cart;

import com.example.bookstore.book.BooksRepository;
import com.example.bookstore.user.Users;
import com.example.bookstore.user.UsersRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
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

    public boolean containsBook(long userId, long bookId){
        Cart cart = cartRepository.findCartByUser(usersRepository.findById(userId).orElseThrow(
                () -> new UsernameNotFoundException("no user with id "+userId+" was found")
        )).orElseThrow(
                () -> new IllegalStateException("no cart was found for user with id "+userId)
        );
        for(CartItem item : cart.getItems()){
            if(item.getBook().getId()==bookId){
                return true;
            }
        }
        return false;
    }

    public void addBookToCart(AddToCartRequest request) {
        Optional<Cart> cartByUser = cartRepository.findCartByUser(usersRepository.findById(request.getUserId()).orElseThrow(
                () -> new UsernameNotFoundException("user with id "+request.getUserId()+" not found")
        ));
        Cart cart;
        if(cartByUser.isEmpty()){
            this.createCart(request.getUserId());
        }
        if(containsBook(request.getUserId(), request.getBookId())){
            throw new IllegalStateException("book with id "+request.getBookId()+" already added to cart");
        }
        cart = getCart(request.getUserId()).get(0);
        cartItemRepository.save(new CartItem(
                booksRepository.findById(request.getBookId()).orElseThrow(
                        () -> new IllegalStateException("book with id "+request.getBookId()+" not found")
                ),
                request.getQuantity(),
                cart
        ));
    }

    public Map<String,Double> getUserCartTotal(long userId) {
        Cart cartByUser = cartRepository.findCartByUser(usersRepository.findById(userId).orElseThrow(
                () -> new UsernameNotFoundException("user with id "+userId+" not found")
        )).orElseThrow(
                () -> new IllegalStateException("no cart found for user with id "+userId)
        );
        double price = 0;
        for(CartItem cartItem : cartByUser.getItems()){
            price+=cartItem.getBook().getPrice()*cartItem.getQuantity();
        }
        Map<String, Double> object = new HashMap<>();
        object.put("total price", price);
        return object;
    }

    public void deleteCart(long userId) {
        cartRepository.deleteById(cartRepository.findCartByUser(usersRepository.findById(userId).orElseThrow(
                () -> new UsernameNotFoundException("no user with id "+userId+" exists")
        )).orElseThrow(
                () -> new IllegalStateException("no cart found for user with id "+userId)
        ).getId());
    }

    public void removeBookFromCart(long userId, long bookId) {
        Cart cart = cartRepository.findCartByUser(usersRepository.findById(userId).orElseThrow(
                () -> new UsernameNotFoundException("no user with id "+userId+" was found")
        )).orElseThrow(
                () -> new IllegalStateException("no cart was found for user with id "+userId)
        );
        if(!containsBook(userId, bookId))
            throw new IllegalStateException("book with id " + bookId + " is not in the cart");
        for(CartItem item : cart.getItems()){
            if(item.getBook().getId()==bookId){
                cart.getItems().remove(item);
                cartRepository.save(cart);
                return;
            }
        }
    }
}
