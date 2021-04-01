package br.com.dimed.app.business.dto;

import java.util.HashMap;
import java.util.Map;

import br.com.dimed.app.business.model.Cart;
import br.com.dimed.app.business.model.Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CartDto {
    private Integer id;
    private Map<Item, Integer> item = new HashMap<Item, Integer>();
    private Double total;

    public CartDto(Cart cart){
        id = cart.getId();
        
        item = cart.getItem();
        total = cart.getTotal();
    }

    public static CartDto convert(Cart cart){
        return new CartDto(cart);
    }
}