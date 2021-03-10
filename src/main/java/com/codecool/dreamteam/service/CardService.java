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

    public CombatLog battle(Long blueId, Long redId) {
        PageUser blue = pageUserRepository.getOne(blueId);
        PageUser red = pageUserRepository.getOne(redId);
        System.out.println("blue points "+blue.getPoint());
        System.out.println("red points " +red.getPoint());
        System.out.println("---------------");
        Set<Card> blueTeam = blue.getTeam().getMyTeam();
        Set<Card> redTeam = red.getTeam().getMyTeam();
        int pointDiff = calculatePointDifference(blue, red);
        CombatLog log = CombatLog.builder().date(LocalDateTime.now()).blue(blue).red(red).build();
        int blueSkill = calculateSkill(blueTeam);
        int redSkill = calculateSkill(redTeam);
        System.out.println("Blue side points: " + blueSkill + " Red Side points: " + redSkill);
        log.setText("Blue side points: " + blueSkill + " Red Side points: " + redSkill);
        if (blueSkill >= redSkill) {
            blue.setWin(blue.getWin() + 1);
            blue.setSilverCoin(blue.getSilverCoin() + 25);
            blue.setPoint(blue.getPoint()+pointDiff);
            log.setPointGain(pointDiff);
            log.setWinner("blue");

            red.setLose(red.getLose() + 1);
            red.setSilverCoin(red.getSilverCoin() + 5);
            red.setPoint(red.getPoint()-pointDiff/2);
            log.setPointLoss( pointDiff/2);

            log.setLoser("red");

        } else {
            red.setWin(red.getWin() + 1);
            red.setSilverCoin(red.getSilverCoin() + 25);
            red.setPoint(red.getPoint()+pointDiff);
            log.setPointGain(pointDiff);
            log.setWinner("red");

            blue.setSilverCoin(blue.getSilverCoin() + 5);
            blue.setLose(blue.getLose() + 1);
            blue.setPoint(blue.getPoint()-pointDiff/2);
            log.setPointLoss( pointDiff/2);
            log.setLoser("blue");

        }
        System.out.println("blue point: "+blue.getPoint());
        System.out.println("red point: "+red.getPoint());
        System.out.println("point loss in combat log: "+log.getPointLoss());
        System.out.println("point gain in combat log: "+log.getPointGain());
        red.setMatchPlayed(red.getMatchPlayed() + 1);
        blue.setMatchPlayed(blue.getMatchPlayed() + 1);
        pageUserRepository.save(blue);
        pageUserRepository.save(red);
        logRepository.save(log);
        System.out.println(log.getCombatId());
        return log;
    }

        private int calculatePointDifference(PageUser blue, PageUser red) {
        int value = blue.getPoint() - red.getPoint();
        if (value < 0) value *= -1;
        return (int) (value + 0.1 * value);
    }

    private int calculateSkill(Set<Card> team) {
        int skill = 0;
        for (Card card : team) {

            skill = skill + card.getAllSkill();
        }
        return skill;
    }

    public List<PageUser> ledger() {
        List<PageUser> users = pageUserRepository.findAll();
        Collections.sort(users,Collections.reverseOrder());
        return users;
    }
}

