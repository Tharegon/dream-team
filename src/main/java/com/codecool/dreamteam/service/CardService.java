package com.codecool.dreamteam.service;


import com.codecool.dreamteam.entity.Card;
import com.codecool.dreamteam.entity.PageUser;
import com.codecool.dreamteam.repository.CardRepository;
import com.codecool.dreamteam.repository.PageUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Set;

@Service
public class CardService {

    @Autowired
    private PageUserRepository pageUserRepository;
    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private CardCreator cardCreator;
    public Set<Card> getMyCard(Long id){
        return pageUserRepository.findById(id).get().getMyCards();
    }

    public Card getCard(Long id){
        return cardRepository.findById(id).get();
    }

    public List<Card> openSmallPack(Long userId){
        int small=5;
        PageUser user = pageUserRepository.findById(userId).get();
        List<Card> cards = cardCreator.createPack(small,user);
        cardRepository.saveAll(cards);
        return cards;
    }
    public void deleteCard(Long id) {
        cardRepository.deleteById(id);
    }
}
