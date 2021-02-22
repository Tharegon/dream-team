package com.codecool.dreamteam.service;


import com.codecool.dreamteam.entity.Card;
import com.codecool.dreamteam.repository.PageUserRepository;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CardService {


    private PageUserRepository pageUserRepository;

    public Set<Card> getMyCard(Long id){
        return pageUserRepository.findById(id).get().getMyCards();
    }
}
