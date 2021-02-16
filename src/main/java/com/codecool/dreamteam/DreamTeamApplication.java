package com.codecool.dreamteam;

import com.codecool.dreamteam.entity.Card;
import com.codecool.dreamteam.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.util.List;

@SpringBootApplication
public class DreamTeamApplication {

    public static void main(String[] args) {
        SpringApplication.run(DreamTeamApplication.class, args);
    }

    /*@Autowired
    CardRepository cardRepository;

    @Bean
    @Profile("production")
    public CommandLineRunner init() {
        return args -> {
            Card card = Card.builder().build();
            cardRepository.save(card);
            List<Card> cardList = cardRepository.findAll();
            System.out.println(cardList.size());
        };

    }*/

}
