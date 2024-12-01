package com.workintech.zoo.validations;

import com.workintech.zoo.entity.Kangaroo;
import com.workintech.zoo.exceptions.ZooException;
import org.springframework.http.HttpStatus;

import java.util.Map;

public class KangarooValid {
    public static void idValid(Integer id){

        if(id==null || id < 0){
            throw new ZooException("id is not valid: " + id, HttpStatus.BAD_REQUEST);
        }

    }

    public static void checkKangarooExistence(Map<Integer, Kangaroo> kangaroos, Integer id, boolean existence) {
        if (existence){
            if (!kangaroos.containsKey(id)){
                throw new ZooException("Record is not exits: " + id, HttpStatus.NOT_FOUND);
            }
        } else {
            if (kangaroos.containsKey(id)){
                throw new ZooException("Record is already exist: " + id, HttpStatus.BAD_REQUEST);
            }
        }
    }

    public static void checkKangarooWeight(Double weight){
        if (weight==null || weight<=0){
            throw new ZooException("This weight is not possible for create ->> " + weight, HttpStatus.BAD_REQUEST);
        }
    }
}
