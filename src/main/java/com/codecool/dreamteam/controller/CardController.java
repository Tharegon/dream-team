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
    @GetMapping("/openSmallPack/{userId}")
    public List<Card> openSmallPack(@PathVariable Long userId){
        return  cardService.openSmallPack(userId);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/openMediumPack/{userId}")
    public List<Card> openMediumPack(@PathVariable Long userId){
        return  cardService.openMediumPack(userId);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/openLargePack/{userId}")
    public List<Card> openLargePack(@PathVariable Long userId){
        return  cardService.openLargePack(userId);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/deleteCard/{id}", method = RequestMethod.DELETE)
    public void deleteCard(@PathVariable Long id){
        cardService.deleteCard(id);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/addToMyTeam/{id}")
    public void addToMyTeam(@PathVariable Long id){
        cardService.addToMyTeam(id);
    }

    /*@CrossOrigin(origins = "*")
    @GetMapping("/getMyTeam/{userId}")
    public Set<Card> getMyTeam(@PathVariable Long userId){
        return cardService.getMyTeam(userId);
    }*/
}
