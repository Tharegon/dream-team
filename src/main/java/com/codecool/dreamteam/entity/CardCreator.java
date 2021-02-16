package com.codecool.dreamteam.entity;

import com.codecool.dreamteam.util.Util;
import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
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
        String[] mikyx = {"Mikyx","Jankos", "SUP", "text","link","link","G2 Esport"};
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
        String[] player = getRandomPlayer();
        Quality quality= getQuality(early+mid+late);
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
                .quality(quality)
                .build();
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
        Quality result = Quality.COMMON;
        return result;
    }
}
