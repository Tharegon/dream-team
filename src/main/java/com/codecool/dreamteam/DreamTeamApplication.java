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

    public DreamTeamApplication(CardRepository cardRepository, PageUserRepository pageUserRepository, TeamRepository teamRepository, CardCreator cardCreator) {
        this.cardRepository = cardRepository;
        this.pageUserRepository = pageUserRepository;
        this.teamRepository = teamRepository;
        this.cardCreator = cardCreator;
    }

    public static void main(String[] args) {
        SpringApplication.run(DreamTeamApplication.class, args);
    }

    final
    CardRepository cardRepository;

    final
    PageUserRepository pageUserRepository;

    final
    TeamRepository teamRepository;

    final
    CardCreator cardCreator;

    @Bean
    @Profile("production")
    public CommandLineRunner init() {
        return args -> {
            PageUser user = PageUser.builder().name("Pista")
                    .numberOfSmallPacks(1).numberOfLargePacks(1).numberOfMediumPacks(1).email("pista@gaming.com")
                    .matchPlayed(0).lose(0).win(0)
                    .silverCoin(1000).build();
            Card card = cardCreator.createRandomPlayer(user);

            Set<Card> myTeam=new HashSet<>();
            myTeam.add(card);

            Team team = Team.builder().myTeam(myTeam).build();

            card.setTeam(team);
            user.setTeam(team);
            teamRepository.save(team);

            PageUser user2 = PageUser.builder().name("Gyuri")
                    .numberOfSmallPacks(1).numberOfLargePacks(1).numberOfMediumPacks(1).email("gyuri@gaming.com")
                    .matchPlayed(0).lose(0).win(0)
                    .silverCoin(1000).build();
            Card card2 = cardCreator.createRandomPlayer(user2);

            Set<Card> myTeam2=new HashSet<>();
            myTeam2.add(card2);

            Team team2 = Team.builder().myTeam(myTeam2).build();

            card2.setTeam(team2);
            user2.setTeam(team2);
            teamRepository.save(team2);
        };

    }

}

