package br.com.dimed.app.service;

import java.util.Optional;

import javax.transaction.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.dimed.app.business.dto.CartDto;
import br.com.dimed.app.business.model.Cart;
import br.com.dimed.app.business.model.Item;
import br.com.dimed.app.persistence.CartRepository;
import br.com.dimed.app.persistence.ItemRepository;


@RestController
@RequestMapping("/v1/cart-service")
@ExposesResourceFor(Cart.class)
public class CartService {
    
    @Autowired
    private CartRepository cartRepository;
    
    @Autowired
    private ItemRepository itemRepository;

    @GetMapping("")
    public ResponseEntity<?> all(){

        return ResponseEntity.ok("OK");
    }

    @PostMapping("/{id_cart}/{id_item}/{qtd}")
    @Transactional
    public ResponseEntity<?> addToCart(
        @PathVariable Integer id_cart, 
        @PathVariable Integer id_item,
        @PathVariable Integer qtd) throws JsonProcessingException{
        if(qtd > 0){
            Cart cart = cartRepository.getOne(id_cart);
            Optional<Item> item = itemRepository.findById(id_item);
            if(cart != null && item.isPresent()){
                cart.getItem().put(item.get(), qtd);
                cart.CalculateTotal();
                return ResponseEntity.ok(CartDto.convert(cart));
            }else if(cart == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cart is invalid");
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item is invalid");
            }
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("qtd not acceptable, < 0");
        }
    }

    @DeleteMapping("/{id_cart}/{id_item}/{qtd}")
    @Transactional
    public ResponseEntity<?> rmToCart(
        @PathVariable Integer id_cart, 
        @PathVariable Integer id_item,
        @PathVariable Integer qtd   
    ){
        if(qtd > 0){
            Cart cart = cartRepository.getOne(id_cart);
            Optional<Item> item = itemRepository.findById(id_item);
            if(cart != null && item.isPresent()){
                cart.getItem().put(item.get(), qtd);
                cart.CalculateTotal();
                return ResponseEntity.ok(CartDto.convert(cart));
            }else if(cart == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cart is invalid");
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item is invalid");
            }
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("qtd not acceptable, < 0");
        }
    }

    @PostMapping("")
    public ResponseEntity<?> createCart(){
        return ResponseEntity.ok(cartRepository.save(new Cart()));
    }
}