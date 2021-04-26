package br.com.dimed.app.business.model;

public class CartExample {

    Database db;

    public CartExample(Database db){
        this.db = db;
    }

    public void AddItemToCart(Item item){
        db.save(item);
    }
}

