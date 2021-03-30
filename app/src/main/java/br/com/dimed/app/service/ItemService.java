package br.com.dimed.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.dimed.app.persistence.ItemRepository;

@RestController
@RequestMapping("/v1/item-service")
public class ItemService {

    @Autowired
    private ItemRepository ItemRepository;

    @GetMapping("/item")
    ResponseEntity<?> getItems(){
        return ResponseEntity.ok(ItemRepository.findAll());
    }
}
