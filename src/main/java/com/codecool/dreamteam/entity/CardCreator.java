package com.codecool.dreamteam.entity;

import com.codecool.dreamteam.util.Util;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
@Component
public class CardCreator {
    private List<String[]> players;

    public CardCreator() {
        List<String[]> customPlayers = new ArrayList<>();
        String[] caps = {"Rasmus Winther","Caps", "MID", "text","link","link","G2 Esport"};
        String[] rekkles = {"Martin","Rekkles", "BOT", "text","link","link","G2 Esport"};
        String[] wunder = {"Martin","Wunder", "TOP", "text","link","link","G2 Esport"};
        String[] jankos = {"jankowski","Jankos", "JGL", "text","link","link","G2 Esport"};
        String[] mikyx = {"Miky","Mikyx", "SUP", "text","link","link","G2 Esport"};
        customPlayers.add(caps);
        customPlayers.add(rekkles);
        customPlayers.add(wunder);
        customPlayers.add(jankos);
        customPlayers.add(mikyx);
        this.players = customPlayers;
    }

    public Card createRandomPlayer(){
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
                .build();
    }

    public List<Card> createPack(int packSize){
        List<Card> pack = new ArrayList<>();
        for (int i = 0; i < packSize; i++) {
            pack.add(createRandomPlayer());
        }
        return pack;
    }

    private String[] getRandomPlayer() {
        return players.get(Util.randomNumMax(this.players.size()));
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
