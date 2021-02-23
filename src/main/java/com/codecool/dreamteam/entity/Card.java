package com.codecool.dreamteam.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable=false)
    private Long id;
    private String name;
    private String nickname;
    private Role role;
    private Quality quality;
    private String bio;
    private int earlyGameSkill;
    private int midGameSkill;
    private int lateGameSkill;
    private int allSkill;
    private String picture;
    private String teamPicture;
    private String teamName;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private PageUser pageUser;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Team team;
}
