package com.codecool.dreamteam.controller;


import com.codecool.dreamteam.entity.Card;
import com.codecool.dreamteam.entity.CombatLog;
import com.codecool.dreamteam.entity.PageUser;
import com.codecool.dreamteam.service.CardService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping
public class CardController {

    final
    CardService cardService;

    public CardController(CardService cardService) {

        this.cardService = cardService;
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
    @RequestMapping(value = "/delete-card/{id}", method = RequestMethod.DELETE)
    public void deleteCard(@PathVariable Long id){
        cardService.deleteCard(id);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/add-to-my-team/{id}")
    public void addToMyTeam(@PathVariable Long id){
        cardService.addToMyTeam(id);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/get-my-team/{userId}")
    public Set<Card> getMyTeam(@PathVariable Long userId){
        return cardService.getMyTeam(userId);
    }


    @CrossOrigin(origins = "*")
    @GetMapping("/battle/{blueId}/{redId}")
    public CombatLog battle(@PathVariable Long blueId, @PathVariable Long redId){
        return cardService.battle(blueId, redId);
    }
}
