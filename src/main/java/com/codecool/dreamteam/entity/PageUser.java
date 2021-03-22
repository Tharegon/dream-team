package com.codecool.dreamteam.entity;


import lombok.*;
import org.springframework.context.annotation.Bean;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class PageUser implements Comparable{

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
    private Integer point;
    @OneToOne
    private Team team;
    @OneToMany(mappedBy = "pageUser", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @Singular
    @EqualsAndHashCode.Exclude
    private Set<Card> myCards;

    @Override
    public int compareTo(Object o){
        return this.getPoint().compareTo(((PageUser) o).getPoint());
    }
}
