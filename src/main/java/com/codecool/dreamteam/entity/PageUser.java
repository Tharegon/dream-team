package com.codecool.dreamteam.entity;


import lombok.*;
import org.springframework.context.annotation.Bean;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class PageUser {

    @Id
    @GeneratedValue
    @Column(nullable=false)
    private Long userId;
    private String name;
    private String email;
    private Integer numberOfSmallPacks;
    private Integer numberOfMediumPacks;
    private Integer numberOfLargePacks;
    private Integer silverCoin;
    private Integer win;
    private Integer lose;
    private Integer matchPlayed;
    @OneToOne
    private Team team;
    @OneToMany(mappedBy = "pageUser", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @Singular
    @EqualsAndHashCode.Exclude
    private Set<Card> myCards;
}
