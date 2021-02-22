package com.codecool.dreamteam.controller;


import com.codecool.dreamteam.entity.Card;
import com.codecool.dreamteam.entity.PageUser;
import com.codecool.dreamteam.repository.CardRepository;
import com.codecool.dreamteam.repository.PageUserRepository;
import com.codecool.dreamteam.service.CardCreator;
import com.codecool.dreamteam.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping
public class CardController {

    @Autowired
    CardService cardService;

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
        return cardService.getMyCard(id);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/card/{id}")
    public Card getCard(@PathVariable Long id){
        return cardService.getCard(id);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/openSmallPack/{userId}")
    public List<Card> openSmallPack(@PathVariable Long userId){
        return  cardService.openSmallPack(userId);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/deleteCard/{id}", method = RequestMethod.DELETE)
    //TODO add currency
    public void deleteCard(@PathVariable Long id){
        cardService.deleteCard(id);
    }
}
