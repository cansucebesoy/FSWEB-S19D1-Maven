package com.workintech.s18d2.services;

import com.workintech.s18d2.repository.FruitRepository;
import com.workintech.s18d2.entity.Fruit;
import com.workintech.s18d2.exceptions.PlantException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FruitServiceImpl implements FruitService {

    private FruitRepository fruitRepository;

    @Autowired
    public FruitServiceImpl(FruitRepository fruitRepository) {
        this.fruitRepository = fruitRepository;
    }

    @Override
    public List<Fruit> getByPriceAsc() {
        return fruitRepository.getByPriceAsc();
    }

    @Override
    public List<Fruit> getByPriceDesc() {
        return fruitRepository.getByPriceDesc();
    }


    @Override
    public Fruit getById(Long id) {
        return fruitRepository.findById(id)
                .orElseThrow(() -> new PlantException("Fruit not found", HttpStatus.NOT_FOUND)); // Fruit bulunamazsa exception fırlat
    }


    @Override
    public List<Fruit> searchByName(String name) {
        if(name == null){
            throw new PlantException("Name field cannot be empty" , HttpStatus.BAD_REQUEST);
        }
        return fruitRepository.searchByName(name);
    }


    @Override
    public Fruit save(Fruit fruit) {
        return fruitRepository.save(fruit);
    }

    @Override
    public Fruit delete(long id) {
        Optional<Fruit> foundFruit = fruitRepository.findById(id);
        if(foundFruit.isPresent()){
            fruitRepository.delete(foundFruit.get());
            return foundFruit.get();
        }

        throw new PlantException("There is no fruit with this id" , HttpStatus.NOT_FOUND);
    }


}

