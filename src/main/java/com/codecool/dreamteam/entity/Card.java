package com.codecool.dreamteam.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Card {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String nickname;
    private Role role;
    private Quality quality;
    private String bio;
    private int earlyGameSkill;
    private int midGameSkill;
    private int lateGameSkill;
}
