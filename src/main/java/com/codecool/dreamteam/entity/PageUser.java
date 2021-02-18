package com.codecool.dreamteam.entity;


import lombok.*;

import javax.persistence.*;
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
    private String password;
    private String email;
    @OneToMany(mappedBy = "pageUser", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @Singular
    @EqualsAndHashCode.Exclude
    private Set<Card> myCards;
}