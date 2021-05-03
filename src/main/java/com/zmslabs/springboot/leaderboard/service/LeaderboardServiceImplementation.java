package com.zmslabs.springboot.leaderboard.service;

import com.zmslabs.springboot.leaderboard.entity.Fantasy;
import com.zmslabs.springboot.leaderboard.entity.Player;
import com.zmslabs.springboot.leaderboard.entity.Team;
import com.zmslabs.springboot.leaderboard.exception.ResourceNotFoundException;
import com.zmslabs.springboot.leaderboard.repository.FantasyRepository;
import com.zmslabs.springboot.leaderboard.repository.PlayerRepository;
import com.zmslabs.springboot.leaderboard.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LeaderboardServiceImplementation implements LeaderboardService {

    private TeamRepository teamRepository;
    private FantasyRepository fantasyRepository;
    private PlayerRepository playerRepository;


    @Autowired
    public LeaderboardServiceImplementation(TeamRepository teamRepository,
                                            FantasyRepository fantasyRepository,
                                            PlayerRepository playerRepository) {
        this.teamRepository = teamRepository;
        this.fantasyRepository = fantasyRepository;
        this.playerRepository = playerRepository;
    }

    public LeaderboardServiceImplementation() {

    }

    @Override
    public List<Team> getAllTeams() {
        return teamRepository.findAllByOrderByLeaguePointsDesc();
    }

    @Override
    public Team addNewTeam(Team freshTeam) {
        return teamRepository.save(freshTeam);
    }

    @Override
    public void updateTeam(Team oldTeam) {
        int teamID = oldTeam.getTeamId();
        List<Player> teamPlayerList =  this.getPlayerList(teamID);
        oldTeam.setPlayers(teamPlayerList);

        // no validation errors
        this.addNewTeam(oldTeam);
    }

    @Override
    public Team findTeamById(int teamID) {
        Optional<Team> dbResult = teamRepository.findById(teamID);
        if (dbResult.isEmpty())
            throw new ResourceNotFoundException("Team with ID " + teamID + " Not Found");

        return dbResult.get();
    }

    @Override
    public void deleteTeamByID(int teamID) {
        Optional<Team> dbResult = teamRepository.findById(teamID);
        if (dbResult.isEmpty())
            throw new ResourceNotFoundException("Team with ID " + teamID + " Not Found");

        teamRepository.deleteById(teamID);
    }

    @Override
    public List<String> getTeamNameList() {
        return teamRepository.findAllByTeamName();
    }

    @Override
    public void updateTeamSupport(int supportTeamID) {
        Team currentTeam = this.findTeamById(supportTeamID);

        Fantasy fantasy = currentTeam.getFantasy();
        if (fantasy == null) {
            fantasy = new Fantasy(0, 0, currentTeam);
        }
        int presentCount = fantasy.getFanSupportCount();
        fantasy.setFanSupportCount(presentCount + 100);

        fantasyRepository.save(fantasy);
    }

    @Override
    public void addTeamPlayer(String playerName, int playerTeamID) {
        Team currentTeam = this.findTeamById(playerTeamID);
        Player freshPlayer = new Player(playerName);
        currentTeam.addPlayers(freshPlayer);
        playerRepository.save(freshPlayer);
    }

    @Override
    public List<Player> getPlayerList(int playerTeamID) {
        Team currentTeam = this.findTeamById(playerTeamID);
        return currentTeam.getPlayers();
    }

    @Override
    public void deletePlayer(int deletePlayerID) {
        Optional<Player> dbResult = playerRepository.findById(deletePlayerID);
        if (dbResult.isEmpty())
            throw new ResourceNotFoundException("Player With ID" + deletePlayerID + " Not Found");

        playerRepository.deleteById(deletePlayerID);
    }

    @Override
    public List<String> getPlayerNameList() {
        return playerRepository.findAllPlayerNames();
    }
}
