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

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class Cart extends RepresentationModel<Cart> {
    @Id 
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Getter
    private Integer id;

    @Getter
    @ElementCollection
    @CollectionTable(name= "cart_item_mapping",
    joinColumns = {@JoinColumn(name= "cart_id", referencedColumnName= "id")})
    @MapKeyColumn(name = "desc")
    @Column(name = "qtd")
    private Map<Item, Integer> item = new HashMap<Item, Integer>();

    @Getter private Double total;

    public Cart setTotal(Double value){
        this.total = value;
        return this;
    }

    public Double CalculateTotal(){
        total = .0;
        for(Map.Entry<Item,Integer> entry : item.entrySet()){
            total += entry.getKey().getPrice() * entry.getValue();
        }
        return total;
    }
}
