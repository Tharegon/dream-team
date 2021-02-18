package com.codecool.dreamteam.controller;


import com.codecool.dreamteam.entity.Card;
import com.codecool.dreamteam.entity.PageUser;
import com.codecool.dreamteam.repository.CardRepository;
import com.codecool.dreamteam.repository.PageUserRepository;
import com.codecool.dreamteam.service.CardCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping
public class CardController {

    final
    CardRepository cardRepository;

    final
    PageUserRepository pageUserRepository;

    final
    CardCreator cardCreator;

    public CardController(CardRepository cardRepository, PageUserRepository pageUserRepository, CardCreator cardCreator) {
        this.cardRepository = cardRepository;
        this.pageUserRepository = pageUserRepository;
        this.cardCreator = cardCreator;
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/myCards/{id}")
    public Set<Card> getMyCards(@PathVariable Long id){
       return pageUserRepository.findById(id).get().getMyCards();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/card/{id}")
    public Object getCard(@PathVariable Long id){
        return cardRepository.findById(id);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/openSmallPack/{userId}")
    public List<Card> openSmallPack(@PathVariable Long userId){
        int small=5;
        PageUser user = pageUserRepository.findById(userId).get();
        List<Card> cards = cardCreator.createPack(small,user);
        cardRepository.saveAll(cards);
        return cards;

    }
}
