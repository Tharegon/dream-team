package com.codecool.dreamteam.repository;

import com.codecool.dreamteam.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card,Long> {
}
