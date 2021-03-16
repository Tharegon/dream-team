package com.codecool.dreamteam.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;
import java.util.Date;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class CombatLog {

    @Id
    @GeneratedValue
    private Long combatId;
    private LocalDateTime date;
    private String winner;
    private String loser;
    private String text;
    private String earlyGameText;
    private String midGameText;
    private String lateGameText;
    private int blueScore;
    private int redScore;
    private Integer pointLoss;
    private Integer pointGain;
    @OneToOne
    @JsonIgnore
    private PageUser blue;
    @OneToOne
    @JsonIgnore
    private PageUser red;
}
