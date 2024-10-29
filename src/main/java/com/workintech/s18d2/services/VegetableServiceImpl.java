package com.workintech.s18d2.services;

import com.workintech.s18d2.repository.VegetableRepository;
import com.workintech.s18d2.dto.ApiResponse;
import com.workintech.s18d2.entity.Vegetable;
import com.workintech.s18d2.exceptions.PlantException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VegetableServiceImpl implements VegetableService {

    private VegetableRepository vegetableRepository;

    @Autowired
    public VegetableServiceImpl(VegetableRepository vegetableRepository){
        this.vegetableRepository=vegetableRepository;
    }


    @Override
    public ApiResponse<List<Vegetable>> getAllVegetablesInAscendingOrder() {
        List<Vegetable> vegetables = vegetableRepository.getByPriceAsc();
        return new ApiResponse<>("Vegetables retrieved in ascending order", vegetables);
    }

    @Override
    public ApiResponse<List<Vegetable>> getAllVegetablesInDescendingOrder() {
        List<Vegetable> vegetables = vegetableRepository.getByPriceDesc();
        return new ApiResponse<>("Vegetables retrieved in descending order", vegetables);
    }

    @Override
    public ApiResponse<List<Vegetable>> searchVegetablesByName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new PlantException("Name field cannot be empty", HttpStatus.BAD_REQUEST);
        }
        List<Vegetable> vegetables = vegetableRepository.searchByName(name);
        return new ApiResponse<>("Vegetables retrieved containing name: " + name, vegetables);
    }

    @Override
    public ApiResponse<Vegetable> getVegetableById(Long id) {
        Vegetable vegetable = vegetableRepository.findById(id)
                .orElseThrow(() -> new PlantException("Vegetable not found with ID: " + id, HttpStatus.NOT_FOUND));
        return new ApiResponse<>("Vegetable retrieved successfully", vegetable);
    }

    @Override
    public ApiResponse<Vegetable> saveOrUpdateVegetable(Vegetable vegetable) {
        Vegetable savedVegetable = vegetableRepository.save(vegetable);
        return new ApiResponse<>("Vegetable saved/updated successfully", savedVegetable);
    }

    @Override
    public ApiResponse<Void> deleteVegetable(Long id) {
        if (!vegetableRepository.existsById(id)) {
            throw new PlantException("Vegetable not found with ID: " + id, HttpStatus.NOT_FOUND);
        }
        vegetableRepository.deleteById(id);
        return new ApiResponse<>("Vegetable deleted successfully", null);
    }
}
