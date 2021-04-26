package br.com.dimed.app.business.model;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;

import org.springframework.hateoas.RepresentationModel;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Cart extends RepresentationModel<Cart> {
    @Id 
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @ElementCollection
    @CollectionTable(name= "cart_item_mapping",
    joinColumns = {@JoinColumn(name= "cart_id", referencedColumnName= "id")})
    @MapKeyColumn(name = "desc")
    @Column(name = "qtd")
    private Map<Item, Integer> item = new HashMap<Item, Integer>();

    private Double total;

    public Double CalculateTotal(){
        total = .0;
        for(Map.Entry<Item,Integer> entry : item.entrySet()){
            total += entry.getKey().getPrice() * entry.getValue();
        }
        return total;
    }
}
