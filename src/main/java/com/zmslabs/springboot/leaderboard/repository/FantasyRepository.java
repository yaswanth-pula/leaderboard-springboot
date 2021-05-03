package com.zmslabs.springboot.leaderboard.repository;

import com.zmslabs.springboot.leaderboard.entity.Fantasy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FantasyRepository extends JpaRepository<Fantasy, Integer> {

}
