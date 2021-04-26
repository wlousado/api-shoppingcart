package br.com.dimed.app.business.dto;

import br.com.dimed.app.business.model.Item;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ItemDto {
    private String desc;
    private Double price;

    public ItemDto(Item item) {
        this.desc = item.getDesc();
        this.price = item.getPrice();
    }

    public Item convert(){
        return new Item(desc, price);
    }

    public static Object convert(Item item) {
        return new ItemDto(item);
    }
}
