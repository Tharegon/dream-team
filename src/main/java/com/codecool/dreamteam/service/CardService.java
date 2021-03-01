package com.codecool.dreamteam.service;


import com.codecool.dreamteam.entity.Card;
import com.codecool.dreamteam.entity.PageUser;
import com.codecool.dreamteam.entity.Team;
import com.codecool.dreamteam.repository.CardRepository;
import com.codecool.dreamteam.repository.PageUserRepository;
import com.codecool.dreamteam.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CardService {

    private final PageUserRepository pageUserRepository;
    private final CardRepository cardRepository;
    private final CardCreator cardCreator;
    private final TeamRepository teamRepository;

    public CardService(PageUserRepository pageUserRepository, CardRepository cardRepository, CardCreator cardCreator, TeamRepository teamRepository) {
        this.pageUserRepository = pageUserRepository;
        this.cardRepository = cardRepository;
        this.cardCreator = cardCreator;
        this.teamRepository = teamRepository;
    }

    public Set<Card> getMyCard(Long id) {
        return pageUserRepository.findById(id).get().getMyCards();
    }

    public Card getCard(Long id) {
        return cardRepository.findById(id).get();
    }

    public List<Card> openSmallPack(Long userId) {
        PageUser user = pageUserRepository.findById(userId).get();
        if (user.getNumberOfSmallPacks() > 0) {
            int small = 5;

            List<Card> cards = cardCreator.createPack(small, user);
            cardRepository.saveAll(cards);
            user.setNumberOfSmallPacks(user.getNumberOfSmallPacks() - 1);
            pageUserRepository.save(user);
            return cards;
        }
        return null;
    }

    public void deleteCard(Long id) {
        Card card = cardRepository.findById(id).get();
        Long userId = card.getPageUser().getUserId();
        PageUser user = pageUserRepository.getOne(userId);
        user.setSilverCoin(user.getSilverCoin() + 25);
        cardRepository.delete(card);
    }

    public List<Card> openMediumPack(Long userId) {
        PageUser user = pageUserRepository.findById(userId).get();
        if (user.getNumberOfSmallPacks() > 0) {
            int medium = 10;

            List<Card> cards = cardCreator.createPack(medium, user);
            cardRepository.saveAll(cards);
            user.setNumberOfSmallPacks(user.getNumberOfSmallPacks() - 1);
            pageUserRepository.save(user);
            return cards;
        }
        return null;
    }

    public List<Card> openLargePack(Long userId) {
        PageUser user = pageUserRepository.findById(userId).get();
        if (user.getNumberOfSmallPacks() > 0) {
            int large = 20;

            List<Card> cards = cardCreator.createPack(large, user);
            cardRepository.saveAll(cards);
            user.setNumberOfSmallPacks(user.getNumberOfSmallPacks() - 1);
            pageUserRepository.save(user);
            return cards;
        }
        return null;
    }

    public void addToMyTeam(Long id) {
        Card card = cardRepository.findById(id).get();
        Long userId = card.getPageUser().getUserId();
        PageUser user = pageUserRepository.getOne(userId);
        Set<Card> teamSet =  user.getTeam().getMyTeam();
        user.getTeam().setMyTeam(teamSet);
        Team userTeam = user.getTeam();
        userTeam.setMyTeam(teamSet);
        card.setTeam(userTeam);
        user.setTeam(userTeam);
        teamRepository.save(userTeam);
    }


    public Set<Card> getMyTeam(Long userId) {
        PageUser user = pageUserRepository.getOne(userId);
        return user.getTeam().getMyTeam();
    }

    public void buySmallPack(Long userId) {
        try {
            PageUser user = pageUserRepository.getOne(userId);
            if (user.getSilverCoin()>0){
                user.setSilverCoin(user.getSilverCoin()-50);
                user.setNumberOfSmallPacks(user.getNumberOfSmallPacks()+1);
                pageUserRepository.save(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void battle(Long blueId, Long redId) {
        PageUser blue = pageUserRepository.getOne(blueId);
        PageUser red = pageUserRepository.getOne(redId);
        Set<Card> blueTeam = blue.getTeam().getMyTeam();
        Set<Card> redTeam = red.getTeam().getMyTeam();
        int blueSkill=0;
        int redSkill=0;
        for (Card bcard: blueTeam) {

            blueSkill = blueSkill + bcard.getAllSkill();
        }
        for (Card rcard: redTeam) {

            redSkill = redSkill + rcard.getAllSkill();
        }
        System.out.println(blueSkill+"  "+redSkill);
        if (blueSkill>=redSkill){
            blue.setWin(blue.getWin()+1);
            blue.setSilverCoin(blue.getSilverCoin()+25);
            red.setLose(red.getLose()+1);
            red.setSilverCoin(red.getSilverCoin()+5);

        }
        else {
            red.setWin(red.getWin()+1);
            red.setSilverCoin(red.getSilverCoin()+25);
            blue.setSilverCoin(blue.getSilverCoin()+5);
            blue.setLose(blue.getLose()+1);
        }
        red.setMatchPlayed(red.getMatchPlayed()+1);
        blue.setMatchPlayed(blue.getMatchPlayed()+1);
        pageUserRepository.save(blue);
        pageUserRepository.save(red);
    }
}

