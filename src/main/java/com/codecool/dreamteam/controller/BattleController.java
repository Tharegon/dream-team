package com.codecool.dreamteam.controller;

import com.codecool.dreamteam.entity.CombatLog;
import com.codecool.dreamteam.service.BattleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BattleController {

    private final BattleService battleService;

    public BattleController(BattleService battleService) {
        this.battleService = battleService;
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/battle/{blueId}/{redId}")
    public CombatLog battle(@PathVariable Long blueId, @PathVariable Long redId){
        return battleService.battle(blueId, redId);
    }
}
