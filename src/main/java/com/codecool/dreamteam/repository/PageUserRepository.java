package com.codecool.dreamteam.repository;

import com.codecool.dreamteam.entity.Card;
import com.codecool.dreamteam.entity.PageUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface PageUserRepository extends JpaRepository<PageUser,Long> {
    PageUser findByName(String name);
}
