package com.workintech.zoo.controller;

import com.workintech.zoo.entity.Kangaroo;
import com.workintech.zoo.entity.Koala;
import com.workintech.zoo.validations.KoalaValid;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/koalas")
public class KoalaController {

    private Map<Integer, Koala> koalas;

    @PostConstruct
    public void init(){koalas = new HashMap<>();}

    @GetMapping
    public List<Koala> find(){return this.koalas.values().stream().toList();}

    @GetMapping("/{id}")
    public Koala findById(@PathVariable("id") Integer id){
        KoalaValid.idValid(id);
        KoalaValid.checkKoalaExistence(koalas, id, true);

        return koalas.get(id);
    }

    @PostMapping
    public Koala save(@RequestBody Koala koala){
        KoalaValid.checkKoalaExistence(koalas,koala.getId(),false);
        KoalaValid.checkKoalaWeight(koala.getWeight());
        koalas.put(koala.getId(),koala);
        return koalas.get(koala.getId());
    }

    @PutMapping("/{id}")
    public Koala update(@PathVariable("id") Integer id, @RequestBody Koala koala){
        KoalaValid.idValid(id);
        KoalaValid.checkKoalaWeight(koala.getWeight());

        koala.setId(id);
        if (koalas.containsKey(id)){
            koalas.put(id,koala);
            return koalas.get(id);
        }
        else {
            return save(koala);
        }
    }

    @DeleteMapping("/{id}")
    public Koala delete(@PathVariable("id") Integer id){
        KoalaValid.idValid(id);
        KoalaValid.checkKoalaExistence(koalas, id,true);

        return koalas.remove(id);
    }

}
