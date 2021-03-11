package com.codecool.dreamteam;

import com.codecool.dreamteam.entity.Card;
import com.codecool.dreamteam.entity.PageUser;
import com.codecool.dreamteam.entity.Quality;
import com.codecool.dreamteam.entity.Role;
import com.codecool.dreamteam.repository.CardRepository;
import com.codecool.dreamteam.repository.PageUserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
class DreamTeamApplicationTests {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private PageUserRepository pageUserRepository;

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
        Card card = Card.builder()
                .name(name)
                .build();
        cardRepository.save(card);
        assertThat(cardRepository.findByName(name).getName()).isEqualTo(name);
    }

    @Test
    void saveWithNickname(){
        String name = "HegyiMEDVE";
        Card card = Card.builder()
                .nickname(name)
                .build();
        cardRepository.save(card);
        assertThat(cardRepository.findByNickname(name).getNickname()).isEqualTo(name);
    }
    @Test
    void saveWithQualityAndRole() {
        Card card = Card.builder()
                .quality(Quality.EPIC)
                .role(Role.BOT)
                .name("Pista")
                .build();
        cardRepository.save(card);
        assertThat(cardRepository.findByName("Pista").getQuality()).isEqualTo(Quality.EPIC);
        assertThat(cardRepository.findByName("Pista").getRole()).isEqualTo(Role.BOT);
    }

    @Test
    void saveSkills(){
        Card card = Card.builder()
                .earlyGameSkill(23)
                .midGameSkill(32)
                .lateGameSkill(23)
                .name("Béla")
                .build();
        cardRepository.save(card);
        Card bela = cardRepository.findByName("Béla");
        assertThat(bela.getEarlyGameSkill()).isEqualTo(23);
        assertThat(bela.getMidGameSkill()).isEqualTo(32);
        assertThat(bela.getLateGameSkill()).isEqualTo(23);
    }

    @Test
    void savePageUser(){
        PageUser user = PageUser.builder()
                .build();
        pageUserRepository.save(user);

        assertThat(pageUserRepository.findAll().size()).isEqualTo(1);


    }
}
