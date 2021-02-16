package com.codecool.dreamteam;

import com.codecool.dreamteam.entity.Card;
import com.codecool.dreamteam.repository.CardRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
class DreamTeamApplicationTests {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void saveOneEntity() {
        Card card = Card.builder().build();
        cardRepository.save(card);
        List<Card> result = cardRepository.findAll();
        assertThat(result).hasSizeGreaterThan(0);
    }

    @Test
    void saveWithName(){
        String name = "Hegyi Pista";
        Card card = Card.builder().name(name).build();
        cardRepository.save(card);
        assertThat(cardRepository.findByName(name).getName()).isEqualTo(name);
    }
}
