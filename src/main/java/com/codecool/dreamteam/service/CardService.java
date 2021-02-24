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

    @Autowired
    private PageUserRepository pageUserRepository;
    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private CardCreator cardCreator;
    @Autowired
    private TeamRepository teamRepository;

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
}

