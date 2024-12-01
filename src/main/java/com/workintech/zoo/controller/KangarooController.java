package com.workintech.zoo.controller;

import com.workintech.zoo.entity.Kangaroo;
import com.workintech.zoo.validations.KangarooValid;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/kangaroos")
public class KangarooController {

    private Map<Integer, Kangaroo> kangaroos;

    @PostConstruct
    public void init(){
        kangaroos = new HashMap<>();
    }

    @GetMapping
    public List<Kangaroo> find(){
        return this.kangaroos.values().stream().toList();
    }

    @GetMapping("/{id}")
    public Kangaroo findById(@PathVariable("id") Integer id){
        KangarooValid.idValid(id);
        KangarooValid.checkKangarooExistence(kangaroos,id, true);

        return kangaroos.get(id);
    }

    @PostMapping
    public Kangaroo save(@RequestBody Kangaroo kangaroo){
        KangarooValid.checkKangarooExistence(kangaroos, kangaroo.getId(), false);
        KangarooValid.checkKangarooWeight(kangaroo.getWeight());
        kangaroos.put(kangaroo.getId(), kangaroo);
        return kangaroos.get(kangaroo.getId());
    }

    @PutMapping("/{id}")
    public Kangaroo update(@PathVariable("id") Integer id, @RequestBody Kangaroo kangaroo){
       KangarooValid.idValid(id);
       KangarooValid.checkKangarooWeight(kangaroo.getWeight());

       kangaroo.setId(id);
       if (kangaroos.containsKey(id)){
           kangaroos.put(id,kangaroo);
           return kangaroos.get(id);

       }
       else {
           return save(kangaroo);
       }
    }

    @DeleteMapping("/{id}")
    public Kangaroo delete(@PathVariable("id") Integer id){
        KangarooValid.idValid(id);
        KangarooValid.checkKangarooExistence(kangaroos,id,true);

        return kangaroos.remove(id);
    }
}
