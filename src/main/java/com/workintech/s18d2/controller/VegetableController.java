package com.workintech.s18d2.controller;

import com.workintech.s18d2.dto.ApiResponse;
import com.workintech.s18d2.entity.Vegetable;
import com.workintech.s18d2.services.VegetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vegetables")
public class VegetableController {

    private VegetableService vegetableService;

    @Autowired
    public VegetableController(VegetableService vegetableService){
        this.vegetableService=vegetableService;
    }

    @GetMapping
    public ApiResponse<List<Vegetable>> getAllVegetablesInAscendingOrder() {
        return vegetableService.getAllVegetablesInAscendingOrder();
    }

    @GetMapping("/desc")
    public ApiResponse<List<Vegetable>> getAllVegetablesInDescendingOrder() {
        return vegetableService.getAllVegetablesInDescendingOrder();
    }

    @GetMapping("/{id}")
    public ApiResponse<Vegetable> getVegetableById(@PathVariable Long id) {
        return vegetableService.getVegetableById(id);
    }

    @PostMapping
    public ApiResponse<Vegetable> saveOrUpdateVegetable(@RequestBody Vegetable vegetable) {
        return vegetableService.saveOrUpdateVegetable(vegetable);
    }

    @PostMapping("/{name}")
    public ApiResponse<List<Vegetable>> searchVegetablesByName(@PathVariable String name) {
        return vegetableService.searchVegetablesByName(name);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteVegetable(@PathVariable Long id) {
        return vegetableService.deleteVegetable(id);
    }
}
