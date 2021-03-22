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
        PageUser blue = pageUserRepository.findById(blueId).get();
        PageUser red = pageUserRepository.findById(redId).get();

        Set<Card> blueTeam = blue.getTeam().getMyTeam();
        Set<Card> redTeam = red.getTeam().getMyTeam();

        CombatLog log = CombatLog.builder()
                .date(LocalDateTime.now())
                .blue(blue).red(red)
                .build();

        calculateEarlyGameWinner(blueTeam, redTeam, log);
        calculateMidGameWinner(blueTeam, redTeam, log);
        calculateLateGameWinner(blueTeam, redTeam, log);

        winning(log, getWinner(log));
        losing(log, getLoser(log));


        pageUserRepository.save(blue);
        pageUserRepository.save(red);
        logRepository.save(log);
        return log;
    }

    private int eloToUser(int win, int lose){
        return  (win*400 - lose*400)/win+lose;
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

    private int calculateLateSkill(Set<Card> team) {
        int skill = 0;
        for (Card card : team) {

            skill = skill + card.getLateGameSkill();
        }
        return skill;
    }

    private PageUser getWinner(CombatLog combatLog) {
        if (combatLog.getBlueScore() >= 2) return combatLog.getBlue();
        return combatLog.getRed();
    }

    private PageUser getLoser(CombatLog combatLog) {
        if (combatLog.getBlueScore() >= 2) return combatLog.getRed();
        return combatLog.getBlue();
    }

    private void calculateEarlyGameWinner(Set<Card> blueTeam, Set<Card> redTeam, CombatLog combatLog) {
        int blueEarlySkill = calculateEarlySkill(blueTeam);
        int redEarlySkill = calculateEarlySkill(redTeam);
        if (blueEarlySkill >= redEarlySkill) {
            combatLog.setEarlyGameText("Blue side had won in the Early Game by: Blue: " + blueEarlySkill + " Red: " + redEarlySkill);
            combatLog.setBlueScore(combatLog.getBlueScore() + 1);
        } else {
            combatLog.setEarlyGameText("Red side had won in the Early Game by: Red: " + redEarlySkill + " Blue: " + blueEarlySkill);
            combatLog.setRedScore(combatLog.getRedScore() + 1);
        }
    }

    private void calculateMidGameWinner(Set<Card> blueTeam, Set<Card> redTeam, CombatLog combatLog) {
        int blueMidSkill = calculateMidSkill(blueTeam);
        int redMidSkill = calculateMidSkill(redTeam);
        if (blueMidSkill >= redMidSkill) {
            combatLog.setMidGameText("Blue side had won in the Mid Game by: Blue: " + blueMidSkill + " Red: " + redMidSkill);
            combatLog.setBlueScore(combatLog.getBlueScore() + 1);
        } else {
            combatLog.setMidGameText("Red side had won in the Mid Game by: Red: " + redMidSkill + " Blue: " + blueMidSkill);
            combatLog.setRedScore(combatLog.getRedScore() + 1);
        }
    }

    private void calculateLateGameWinner(Set<Card> blueTeam, Set<Card> redTeam, CombatLog combatLog) {
        int blueLateSkill = calculateLateSkill(blueTeam);
        int redLateSkill = calculateLateSkill(redTeam);
        if (blueLateSkill >= redLateSkill) {
            combatLog.setLateGameText("Blue side had won in the Late Game by: Blue: " + blueLateSkill + " Red: " + redLateSkill);
            combatLog.setBlueScore(combatLog.getBlueScore() + 1);
        } else {
            combatLog.setLateGameText("Red side had won in the Late Game by: Red: " + redLateSkill + " Blue: " + blueLateSkill);
            combatLog.setRedScore(combatLog.getRedScore() + 1);
        }
    }

    private void winning(CombatLog combatLog, PageUser winner) {
        combatLog.setWinner(winner.getName());
        combatLog.setPointGain(400);
        winner.setMatchPlayed(winner.getMatchPlayed() + 1);
        winner.setPoint(winner.getPoint() + combatLog.getPointGain());
        winner.setWin(winner.getWin() + 1);
    }

    private void losing(CombatLog combatLog, PageUser loser) {
        combatLog.setLoser(loser.getName());
        combatLog.setPointLoss(400);
        loser.setMatchPlayed(loser.getMatchPlayed() + 1);
        loser.setPoint(loser.getPoint()-combatLog.getPointLoss());
        loser.setLose(loser.getLose() + 1);
    }
}
