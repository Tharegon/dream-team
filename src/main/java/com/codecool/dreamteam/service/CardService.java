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
        PageUser user = pageUserRepository.findById(userId).get();
        if (user.getNumberOfSmallPacks()>0){
        int small=5;

        List<Card> cards = cardCreator.createPack(small,user);
        cardRepository.saveAll(cards);
        user.setNumberOfSmallPacks(user.getNumberOfSmallPacks()-1);
        pageUserRepository.save(user);
        return cards;
        }
        return null;
    }
    public void deleteCard(Long id) {
        Card card = cardRepository.findById(id).get();
        Long userId = card.getPageUser().getUserId();
        PageUser user = pageUserRepository.getOne(userId);
        user.setSilverCoin(user.getSilverCoin()+25);
        cardRepository.delete(card);
    }
}
