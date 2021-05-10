package com.zmslabs.springboot.leaderboard;

import com.zmslabs.springboot.leaderboard.entity.Team;
import com.zmslabs.springboot.leaderboard.repository.TeamRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//@SpringBootTest
public class TeamRepositoryTests {

    @Autowired
    private TeamRepository teamRepository;

    private MockMvc mockMvc;


    private int testTeamID;

    @BeforeAll
    public void setup() {
        testTeamID = 210;
    }

    @Test
    @DisplayName("Get All Teams Sorted ByPoints DESC Test")
    @Order(1)
    public void getAllTeamsByPointsDESCTest() {
        List<Team> dbTeamList = teamRepository.findAll();
        List<Team> sortedDBTeamList = teamRepository.findAllByOrderByLeaguePointsDesc();
        // desc sort db list based on League Points
        List<Team> dbPointsList = dbTeamList.stream().
                sorted(Comparator.comparingInt(Team::getLeaguePoints).reversed())
                .collect(Collectors.toList());
        // test for both are equal
        assertEquals(sortedDBTeamList, dbPointsList);

    }

    @Test
    @DisplayName("Insert New Team Test")
    @Order(2)
    public void insertNewTeamTest() {
        Team team = new Team("england", 2, 0, 2, 2);
        Team testTeam = teamRepository.save(team);

        assertEquals(team, testTeam);

    }


    @Test
    @DisplayName("Find A Team By valid Id")
    @Order(3)
    public void findTeamByIdTest() {
        assertNotNull(getTestTeamByID());
    }


    @Test
    @DisplayName("Update a Team Test")
    @Order(4)
    public void updateTeamByTest() {
        findTeamByIdTest();
        Team existingTeam = getTestTeamByID();

        existingTeam.setMatchesPlayed(8);
        existingTeam.setMatchesWon(6);
        existingTeam.setMatchesLost(2);
        existingTeam.setLeaguePoints(12);

        // save updated Team
        Team updatedTeam = teamRepository.save(existingTeam);

        // assert values
        assertEquals(updatedTeam.getMatchesPlayed(), 8);
        assertEquals(updatedTeam.getMatchesWon(), 6);
        assertEquals(updatedTeam.getMatchesLost(), 2);
        assertEquals(updatedTeam.getLeaguePoints(), 12);

    }

    @Test
    @DisplayName("Delete a Team Test")
    @Order(5)
    public void deleteTeamTest() {
        assertNotNull(getTestTeamByID());
        teamRepository.deleteById(testTeamID);
        assertNull(getTestTeamByID());
    }

    @Test
    @DisplayName("Get All Team Names Test")
    @Order(6)
    public void getAllTeamNamesTest() {
        List<Team> dbTeamList = teamRepository.findAll();
        // extract team name
        List<String> dbTeamNameList = dbTeamList.stream()
                .map(Team::getTeamName)
                .collect(Collectors.toList());
        // custom query method list
        List<String> queryTeamNameList = teamRepository.findAllByTeamName();

        // test for both are equal
        assertEquals(dbTeamNameList, queryTeamNameList);

    }


    // helper methods
    public Team getTestTeamByID() {
        Optional<Team> result = teamRepository.findById(testTeamID);
        return result.orElse(null);
    }

}
