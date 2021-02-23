package com.codecool.dreamteam.repository;

import com.codecool.dreamteam.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team,Long> {
}
