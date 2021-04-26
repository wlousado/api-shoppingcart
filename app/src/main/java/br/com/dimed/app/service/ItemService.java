package br.com.dimed.app.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.dimed.app.business.dto.ItemDto;
import br.com.dimed.app.business.model.Item;
import br.com.dimed.app.persistence.ItemRepository;

@RestController
@RequestMapping("/v1/item-service")
public class ItemService {

    @Autowired
    private ItemRepository ItemRepository;

    @GetMapping("")
    ResponseEntity<?> getItems(){
        List<Item> items = ItemRepository.findAll();
        return ResponseEntity.ok(items);
    }

    @GetMapping("/{id}")
    ResponseEntity<?> getItem(@PathVariable Integer id){
        Optional<Item> item = ItemRepository.findById(id);

        if(item.isPresent()){
            createHateoas(item.get(), id);
            return ResponseEntity.ok(item.get());
        }
        throw new RuntimeException("Item não encontrado");
    }


    @PostMapping("")
    ResponseEntity<?> createItem(@RequestBody @Valid ItemDto body){
        Optional<Item> search = ItemRepository.findByDesc(body.getDesc());
        if(search.isPresent()){
            createHateoas(search.get(), 0);
            return ResponseEntity.status(HttpStatus.CONFLICT)
            .body(search.get());
        }

        Item item = ItemRepository.save(body.convert());
        createHateoas(item, item.getId());
        //item.add(linkTo(methodOn(ItemService.class)).withRel("POST"));
        return ResponseEntity.status(HttpStatus.CREATED)
        .body(item);
    }

    @PutMapping("/{id}")
    @Transactional
    ResponseEntity<?> attItem(@PathVariable Integer id, 
    @RequestBody @Valid ItemDto itemDto){

        Optional<Item> search = ItemRepository.findById(id);

        if(search.isPresent()){
            Item item = ItemRepository.getOne(id);
            item.setDesc(itemDto.getDesc());
            item.setPrice(itemDto.getPrice());
            createHateoas(item, id);
            return ResponseEntity.ok(item);
        }
        throw new RuntimeException("Item não encontrado");
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteItem(@PathVariable Integer id){

        Optional<Item> search = ItemRepository.findById(id);

        if(search.isPresent()){
            createHateoas(search.get(), search.get().getId());
            ItemRepository.delete(search.get());
            return ResponseEntity.ok(search.get());
        }
        throw new RuntimeException("Item não encontrado");
    }

    private void createHateoas(Item item, Integer id){
        if(id == 0){
            id = 1;
        }
        item.add(
            linkTo(methodOn(ItemService.class).getItem(id)).withRel("GET")
        );
        item.add(
            linkTo(methodOn(ItemService.class).getItems()).withRel("GET")
        );
        item.add(
            linkTo(methodOn(ItemService.class)).withRel("POST")
        );
        item.add(
            linkTo(methodOn(ItemService.class).attItem(id, null)).withRel("PUT")
        );
        item.add(
            linkTo(methodOn(ItemService.class).deleteItem(id)).withRel("DELETE")
        );
    }
}
