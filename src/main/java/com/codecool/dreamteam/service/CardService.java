package com.codecool.dreamteam.service;


import com.codecool.dreamteam.entity.Card;
import com.codecool.dreamteam.entity.CombatLog;
import com.codecool.dreamteam.entity.PageUser;
import com.codecool.dreamteam.entity.Team;
import com.codecool.dreamteam.repository.CardRepository;
import com.codecool.dreamteam.repository.CombatLogRepository;
import com.codecool.dreamteam.repository.PageUserRepository;
import com.codecool.dreamteam.repository.TeamRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
public class CardService {

    private final CombatLogRepository logRepository;
    private final PageUserRepository pageUserRepository;
    private final CardRepository cardRepository;
    private final CardCreator cardCreator;
    private final TeamRepository teamRepository;

    public CardService(PageUserRepository pageUserRepository, CardRepository cardRepository, CardCreator cardCreator, TeamRepository teamRepository, CombatLogRepository logRepository) {
        this.pageUserRepository = pageUserRepository;
        this.cardRepository = cardRepository;
        this.cardCreator = cardCreator;
        this.teamRepository = teamRepository;
        this.logRepository = logRepository;
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
        if (user.getNumberOfMediumPacks() > 0) {
            int medium = 10;

            List<Card> cards = cardCreator.createPack(medium, user);
            cardRepository.saveAll(cards);
            user.setNumberOfMediumPacks(user.getNumberOfMediumPacks() - 1);
            pageUserRepository.save(user);
            return cards;
        }
        return null;
    }

    public List<Card> openLargePack(Long userId) {
        PageUser user = pageUserRepository.findById(userId).get();
        if (user.getNumberOfLargePacks() > 0) {
            int large = 20;

            List<Card> cards = cardCreator.createPack(large, user);
            cardRepository.saveAll(cards);
            user.setNumberOfLargePacks(user.getNumberOfLargePacks() - 1);
            pageUserRepository.save(user);
            return cards;
        }
        return null;
    }

    public void addToMyTeam(Long id) {
        Card card = cardRepository.findById(id).get();
        Long userId = card.getPageUser().getUserId();
        PageUser user = pageUserRepository.getOne(userId);
        Set<Card> teamSet = user.getTeam().getMyTeam();
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
            if (user.getSilverCoin() > 0) {
                user.setSilverCoin(user.getSilverCoin() - 50);
                user.setNumberOfSmallPacks(user.getNumberOfSmallPacks() + 1);
                pageUserRepository.save(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void buyMediumPack(Long userId) {
        try {
            PageUser user = pageUserRepository.getOne(userId);
            if (user.getSilverCoin() > 0) {
                user.setSilverCoin(user.getSilverCoin() - 100);
                user.setNumberOfMediumPacks(user.getNumberOfMediumPacks() + 1);
                pageUserRepository.save(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void buyLargePack(Long userId) {
        try {
            PageUser user = pageUserRepository.getOne(userId);
            if (user.getSilverCoin() > 0) {
                user.setSilverCoin(user.getSilverCoin() - 100);
                user.setNumberOfLargePacks(user.getNumberOfLargePacks() + 1);
                pageUserRepository.save(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public List<PageUser> ledger() {
        List<PageUser> users = pageUserRepository.findAll();
        Collections.sort(users, Collections.reverseOrder());
        return users;
    }
}

