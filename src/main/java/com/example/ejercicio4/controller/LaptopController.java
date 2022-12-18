package com.example.ejercicio4.controller;

import com.example.ejercicio4.entity.Laptop;
import com.example.ejercicio4.repository.LaptopRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class LaptopController {

    private LaptopRepository laptopRepository;

    public LaptopController(LaptopRepository laptopRepository) {
        this.laptopRepository = laptopRepository;
    }

    @Value("${app.message}")
    String message;

    @GetMapping("/laptops")
    public List<Laptop> findAll(){
        System.out.println(message);
        return laptopRepository.findAll();
    }


    @GetMapping("/laptops/{id}")
    public ResponseEntity<Laptop> findOne(@PathVariable Long id){
        Optional<Laptop> laptopOpt = laptopRepository.findById(id);

        if(laptopOpt.isPresent()){
            return ResponseEntity.ok(laptopOpt.get());
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/laptops")
    public ResponseEntity<Laptop> create(@RequestBody Laptop laptop){
        if(laptop.getId() != null){
            return ResponseEntity.badRequest().build();
        }
        laptopRepository.save(laptop);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/laptops")
    public ResponseEntity<Laptop> update(@RequestBody Laptop laptop){

        if(laptop.getId() == null){
            return ResponseEntity.badRequest().build();
        }

        if(!laptopRepository.existsById(laptop.getId())){
            return ResponseEntity.notFound().build();
        }

        laptopRepository.save(laptop);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/laptops/{id}")
    public ResponseEntity<Laptop> deleteOne(@PathVariable Long id){

        if(!laptopRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        laptopRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/laptops")
    public ResponseEntity<Laptop> deleteAll(){
        laptopRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }

}
