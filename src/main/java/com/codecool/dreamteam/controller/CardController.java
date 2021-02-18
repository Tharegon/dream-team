package com.codecool.dreamteam.controller;


import com.codecool.dreamteam.entity.Card;
import com.codecool.dreamteam.repository.CardRepository;
import com.codecool.dreamteam.repository.PageUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping
public class CardController {

    @Autowired
    CardRepository cardRepository;

    @Autowired
    PageUserRepository pageUserRepository;


    @CrossOrigin(origins = "*")
    @GetMapping("/myCards/{id}")
    public Set<Card> getMyCards(@PathVariable Long id){
       return pageUserRepository.findById(id).get().getMyCards();
    }


}
