package com.zmslabs.springboot.leaderboard;

import com.zmslabs.springboot.leaderboard.entity.Player;
import com.zmslabs.springboot.leaderboard.entity.Team;
import com.zmslabs.springboot.leaderboard.repository.PlayerRepository;
import com.zmslabs.springboot.leaderboard.repository.TeamRepository;
import com.zmslabs.springboot.leaderboard.service.LeaderboardServiceImplementation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@ExtendWith({MockitoExtension.class, SpringExtension.class})
public class LeaderboardServiceImplTests {

    @MockBean
    private TeamRepository teamRepository;
    @MockBean
    private PlayerRepository playerRepository;

    @Autowired
    @InjectMocks
    private LeaderboardServiceImplementation leaderboardService;


    @Test
    @DisplayName("Get All Teams Service Test")
    public void getAllTeamsTest() {
        List<Team> testTeamList = new ArrayList<>();
        testTeamList.add(new Team("india", 6, 4, 2, 8));
        Mockito.when(teamRepository.findAllByOrderByLeaguePointsDesc()).thenReturn(testTeamList);

        List<Team> serviceTeamList = leaderboardService.getAllTeams();

        assertEquals(serviceTeamList, testTeamList);
    }


    @Test
    @DisplayName("Save a new Team Test")
    public void saveNewTeamTest() {
        Team testTeam = new Team("india", 6, 4, 2, 8);
        Mockito.when(teamRepository.save(testTeam)).thenReturn(testTeam);

        Team serviceTeam = leaderboardService.addNewTeam(testTeam);

        assertEquals(serviceTeam, testTeam);

    }

    @Test
    @DisplayName("Find A Team By ID Test")
    public void getTeamByIdTest() {
        Team testTeam = new Team("india", 6, 4, 2, 8);
        Mockito.when(teamRepository.findById(100)).thenReturn(java.util.Optional.of(testTeam));
//        Mockito.when(teamRepository.findById(200)).thenReturn(java.util.Optional.empty());

        assertEquals(testTeam, leaderboardService.findTeamById(100));
//        assertNull(leaderboardService.findTeamById(200));
    }

    @Test
    @DisplayName("Get All Teams Names Test")
    public void getAllTeamNamesTest() {
        List<String> dbTeamNameList = new ArrayList<>();
        dbTeamNameList.add("india");
        Mockito.when(teamRepository.findAllByTeamName()).thenReturn(dbTeamNameList);
        List<String> serviceTeamNameList = leaderboardService.getTeamNameList();

        assertEquals(serviceTeamNameList, dbTeamNameList);
    }


    @Test
    @DisplayName("Get Team Player List Test")
    public void getPlayerListTest() {
        List<Player> dbPlayerList = new ArrayList<>();
        dbPlayerList.add(new Player("Virat Kohli"));
        Team testTeam = new Team();
        testTeam.setPlayers(dbPlayerList);
        Mockito.when(teamRepository.findById(100))
                .thenReturn(java.util.Optional.of(testTeam));

        List<Player> servicePlayerList = leaderboardService.getPlayerList(100);

        assertEquals(servicePlayerList, dbPlayerList);

    }

    @Test
    @DisplayName("Get All Player Names Test")
    public void getAllPlayerNamesTest() {
        List<String> dbPlayersNameList = new ArrayList<>();
        dbPlayersNameList.add("Virat Kohli");
        dbPlayersNameList.add("MS Dhoni");

        Mockito.when(playerRepository.findAllPlayerNames())
                .thenReturn(dbPlayersNameList);

        List<String> servicePlayerNameList = leaderboardService.getPlayerNameList();

        assertEquals(servicePlayerNameList, dbPlayersNameList);
    }

}
