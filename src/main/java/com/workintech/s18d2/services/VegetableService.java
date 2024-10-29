package com.workintech.s18d2.services;


import com.workintech.s18d2.dto.ApiResponse;
import com.workintech.s18d2.entity.Vegetable;

import java.util.List;

public interface VegetableService {

    ApiResponse<List<Vegetable>> getAllVegetablesInAscendingOrder();
    ApiResponse<List<Vegetable>> getAllVegetablesInDescendingOrder();
    ApiResponse<List<Vegetable>> searchVegetablesByName(String name);
    ApiResponse<Vegetable> getVegetableById(Long id);
    ApiResponse<Vegetable> saveOrUpdateVegetable(Vegetable vegetable);
    ApiResponse<Void> deleteVegetable(Long id);
}
