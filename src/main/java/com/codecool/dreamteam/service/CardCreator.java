package com.codecool.dreamteam.service;

import com.codecool.dreamteam.entity.Card;
import com.codecool.dreamteam.entity.PageUser;
import com.codecool.dreamteam.entity.Quality;
import com.codecool.dreamteam.entity.Role;
import com.codecool.dreamteam.util.Util;
import lombok.*;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


@Service
public class CardCreator {
    private List<String[]> players;

    public CardCreator() {
        try {
            List<String[]> customPlayers = new ArrayList<>();
            File myFile = new File("src/main/resources/players.txt");
            Scanner myReader = new Scanner(myFile);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] player = data.split(",");
                customPlayers.add(player);
            }
            myReader.close();
            this.players=customPlayers;
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public Card createRandomPlayer(PageUser user){
        int early=Util.randomSkill();
        int mid=Util.randomSkill();
        int late=Util.randomSkill();
        int allSkill=early+mid+late;
        String[] player = getRandomPlayer();
        Quality quality= getQuality(allSkill);
        Role role= getRole(player[2]);
        return Card.builder()
                .name(player[0])
                .nickname(player[1])
                .role(role)
                .bio(player[3])
                .picture(player[4])
                .teamPicture(player[5])
                .teamName(player[6])
                .earlyGameSkill(early)
                .midGameSkill(mid)
                .lateGameSkill(late)
                .allSkill(allSkill)
                .quality(quality)
                .pageUser(user)
                .build();
    }

    public List<Card> createPack(int packSize, PageUser user){
        List<Card> pack = new ArrayList<>();
        for (int i = 0; i < packSize; i++) {
            pack.add(createRandomPlayer(user));
        }
        return pack;
    }

    private String[] getRandomPlayer() {
        return this.players.get(Util.randomNumMax(this.players.size()));
    }

    private Role getRole(String role){
        Role result = Role.JGL;
        switch (role) {
            case "MID":
                result=Role.MID;
                break;
            case "TOP":
                result=Role.TOP;
                break;
            case "BOT":
                result=Role.BOT;
                break;
            case "JGL":
                result=Role.JGL;
                break;
            case "SUP":
                result=Role.SUP;
                break;
        }
        return result;
    }

    private Quality getQuality(int num){
        Quality result;
        if(num<50) return result= Quality.COMMON;
        else if(num>50 && num<100) return result = Quality.UNCOMMON;
        else if(num>100 && num<150) return result = Quality.EPIC;
        else if(num>150 && num<200) return result = Quality.LEGENDARY;
        else if(num>200 && num<250) return result = Quality.MYTICH;
        else if(num>250) return result = Quality.ULTIMATE;
        return Quality.COMMON;
    }
}
