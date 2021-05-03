package com.zmslabs.springboot.leaderboard.repository;

import com.zmslabs.springboot.leaderboard.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<Team, Integer> {
    List<Team> findAllByOrderByLeaguePointsDesc();

    @Query("SELECT team.teamName from Team team")
    List<String> findAllByTeamName();
}
