package com.codecool.dreamteam.entity;


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
    @OneToOne
    private PageUser blue;
    @OneToOne
    private PageUser red;
}
