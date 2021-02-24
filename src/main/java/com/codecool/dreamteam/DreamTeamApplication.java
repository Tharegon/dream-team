package com.codecool.dreamteam;

import com.codecool.dreamteam.entity.Card;
import com.codecool.dreamteam.entity.PageUser;
import com.codecool.dreamteam.entity.Team;
import com.codecool.dreamteam.repository.CardRepository;
import com.codecool.dreamteam.repository.PageUserRepository;
import com.codecool.dreamteam.repository.TeamRepository;
import com.codecool.dreamteam.service.CardCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class DreamTeamApplication {

    public static void main(String[] args) {
        SpringApplication.run(DreamTeamApplication.class, args);
    }

    @Autowired
    CardRepository cardRepository;

    @Autowired
    PageUserRepository pageUserRepository;

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    CardCreator cardCreator;

    @Bean
    @Profile("production")
    public CommandLineRunner init() {
        return args -> {
            PageUser user = PageUser.builder().name("Pista").numberOfSmallPacks(1).email("pista@gaming.com").silverCoin(1000).build();
            Card card = cardCreator.createRandomPlayer(user);

            Set<Card> myTeam=new HashSet<>();
            myTeam.add(card);

            Team team = Team.builder().myTeam(myTeam).build();

            card.setTeam(team);
            user.setTeam(team);
            teamRepository.save(team);
        };

    }

}
