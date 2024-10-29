package com.workintech.s18d2.controller;

import com.workintech.s18d2.entity.Fruit;

import com.workintech.s18d2.services.FruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/fruit")
public class FruitController {

    private FruitService fruitService;

    @Autowired
    public FruitController(FruitService fruitService) {
        this.fruitService = fruitService;
    }

    @GetMapping
    public List<Fruit> getByAsc() {
        return fruitService.getByPriceAsc();
    }

    @GetMapping("/desc")
    public List<Fruit> getByDesc() {
        return fruitService.getByPriceDesc();
    }

    @GetMapping("/{id}")
    public Fruit getById(@PathVariable Long id) {
        return fruitService.getById(id);
    }

    @PostMapping
    public Fruit saveOrUpdateFruit(@RequestBody Fruit fruit) {
        return fruitService.save(fruit);
    }

    @DeleteMapping("/{id}")
    public Fruit deleteFruit(@PathVariable Long id) {
        return fruitService.delete(id);
    }

    @GetMapping("/name/{name}")
    public List<Fruit> getFruitByName(@PathVariable String name) {
        return fruitService.searchByName(name);
    }

}