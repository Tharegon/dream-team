package com.codecool.dreamteam.service;


import com.codecool.dreamteam.entity.Card;
import com.codecool.dreamteam.repository.CardRepository;
import com.codecool.dreamteam.repository.PageUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CardService {

    @Autowired
    private PageUserRepository pageUserRepository;
    @Autowired
    private CardRepository cardRepository;

    public Set<Card> getMyCard(Long id){
        return pageUserRepository.findById(id).get().getMyCards();
    }

    public Card getCard(Long id){
        return cardRepository.findById(id).get();
    }
}
