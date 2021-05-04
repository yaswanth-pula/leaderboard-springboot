package com.zmslabs.springboot.leaderboard.service;

import com.zmslabs.springboot.leaderboard.dto.TeamDTO;
import com.zmslabs.springboot.leaderboard.entity.Player;
import com.zmslabs.springboot.leaderboard.entity.Team;

import java.util.List;

public interface LeaderboardService {

    List<Team> getAllTeams();

    Team addNewTeam(TeamDTO freshTeam);
    void updateTeam(TeamDTO oldTeam);

    Team findTeamById(int teamID);

    void deleteTeamByID(int teamID);

    void updateTeamSupport(int supportTeamID);

    void addTeamPlayer(String playerName, int playerTeamID);

    List<Player> getPlayerList(int playerTeamID);

    void deletePlayer(int deletePlayerID);

    List<String> getTeamNameList();

    List<String> getPlayerNameList();
}
