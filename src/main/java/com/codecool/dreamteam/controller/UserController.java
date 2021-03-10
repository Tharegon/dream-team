package com.codecool.dreamteam.controller;


import com.codecool.dreamteam.entity.Card;
import com.codecool.dreamteam.entity.PageUser;
import com.codecool.dreamteam.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class UserController {

    private final CardService cardService;

    public UserController(CardService cardService) {
        this.cardService = cardService;
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/ledger")
    public List<PageUser> ledger(){
        return cardService.ledger();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/open-small-pack/{userId}")
    public List<Card> openSmallPack(@PathVariable Long userId){
        return  cardService.openSmallPack(userId);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/open-medium-pack/{userId}")
    public List<Card> openMediumPack(@PathVariable Long userId){
        return  cardService.openMediumPack(userId);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/open-large-pack/{userId}")
    public List<Card> openLargePack(@PathVariable Long userId){
        return  cardService.openLargePack(userId);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/buy-small-pack/{userId}")
    public void buySmallPack(@PathVariable Long userId){
        cardService.buySmallPack(userId);
    }
    @CrossOrigin(origins = "*")
    @GetMapping("/buy-medium-pack/{userId}")
    public void buyMediumPack(@PathVariable Long userId){
        cardService.buyMediumPack(userId);
    }
    @CrossOrigin(origins = "*")
    @GetMapping("/buy-large-pack/{userId}")
    public void buyLargePack(@PathVariable Long userId){
        cardService.buyLargePack(userId);
    }
}
