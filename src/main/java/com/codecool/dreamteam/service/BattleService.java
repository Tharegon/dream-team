package com.codecool.dreamteam.service;

import com.codecool.dreamteam.entity.Card;
import com.codecool.dreamteam.entity.CombatLog;
import com.codecool.dreamteam.entity.PageUser;
import com.codecool.dreamteam.repository.CombatLogRepository;
import com.codecool.dreamteam.repository.PageUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;

@Service
public class BattleService {
    private final PageUserRepository pageUserRepository;
    private final CombatLogRepository logRepository;

    public BattleService(PageUserRepository pageUserRepository, CombatLogRepository logRepository) {
        this.pageUserRepository = pageUserRepository;
        this.logRepository = logRepository;
    }

    public CombatLog battle(Long blueId, Long redId) {
        PageUser blue = pageUserRepository.getOne(blueId);
        PageUser red = pageUserRepository.getOne(redId);
        System.out.println("blue points " + blue.getPoint());
        System.out.println("red points " + red.getPoint());
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
            blue.setPoint(blue.getPoint() + pointDiff);
            log.setPointGain(pointDiff);
            log.setWinner("blue");

            red.setLose(red.getLose() + 1);
            red.setSilverCoin(red.getSilverCoin() + 5);
            red.setPoint(red.getPoint() - pointDiff / 2);
            log.setPointLoss(pointDiff / 2);

            log.setLoser("red");

        } else {
            red.setWin(red.getWin() + 1);
            red.setSilverCoin(red.getSilverCoin() + 25);
            red.setPoint(red.getPoint() + pointDiff);
            log.setPointGain(pointDiff);
            log.setWinner("red");

            blue.setSilverCoin(blue.getSilverCoin() + 5);
            blue.setLose(blue.getLose() + 1);
            blue.setPoint(blue.getPoint() - pointDiff / 2);
            log.setPointLoss(pointDiff / 2);
            log.setLoser("blue");

        }
        System.out.println("blue point: " + blue.getPoint());
        System.out.println("red point: " + red.getPoint());
        System.out.println("point loss in combat log: " + log.getPointLoss());
        System.out.println("point gain in combat log: " + log.getPointGain());
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

    private int calculateEarlySkill(Set<Card> team) {
        int skill = 0;
        for (Card card : team) {

            skill = skill + card.getEarlyGameSkill();
        }
        return skill;
    }

    private int calculateMidSkill(Set<Card> team) {
        int skill = 0;
        for (Card card : team) {

            skill = skill + card.getMidGameSkill();
        }
        return skill;
    }

    private void calculateEarlyGameWinner(Set<Card> blueTeam, Set<Card> redTeam, CombatLog combatLog) {
        int blueEarlySkill = calculateEarlySkill(blueTeam);
        int redEarlySkill = calculateEarlySkill(redTeam);
        if (blueEarlySkill >= redEarlySkill) {
            combatLog.setEarlyGameText("Blue side had won in the Early Game by: Blue: " + blueEarlySkill + " Red: " + redEarlySkill);
            combatLog.setBlueScore(+1);
        } else {
            combatLog.setEarlyGameText("Red side had won in the Early Game by: Red: " + redEarlySkill + " Blue: " + blueEarlySkill);
            combatLog.setRedScore(+1);
        }
    }
    private void calculateMidGameWinner(Set<Card> blueTeam, Set<Card> redTeam, CombatLog combatLog){
        int blueMidSkill = calculateMidSkill(blueTeam);
        int redMidSkill = calculateMidSkill(redTeam);
        if (blueMidSkill>=redMidSkill){
            combatLog.setMidGameText("Blue side had won in the Early Game by: Blue: " + blueMidSkill + " Red: " + redMidSkill);
            combatLog.setBlueScore(+1);
        }else{
            combatLog.setMidGameText("Red side had won in the Early Game by: Red: " + redMidSkill + " Blue: " + blueMidSkill);
            combatLog.setRedScore(+1);
        }
    }
}
