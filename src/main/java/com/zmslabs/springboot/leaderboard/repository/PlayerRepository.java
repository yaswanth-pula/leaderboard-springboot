package com.zmslabs.springboot.leaderboard.repository;

import com.zmslabs.springboot.leaderboard.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Integer> {
    @Query("SELECT player.playerName from Player player")
    List<String> findAllPlayerNames();
}
