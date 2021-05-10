package com.zmslabs.springboot.leaderboard;

import com.zmslabs.springboot.leaderboard.entity.Player;
import com.zmslabs.springboot.leaderboard.repository.PlayerRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PlayerRepositoryTests {

    @Autowired
    private PlayerRepository playerRepository;


    private int testPlayerID;

    @BeforeAll
    public void setup() {
        testPlayerID = 7091;
    }


    @Test
    @DisplayName("Get All Player Names Test")
    public void getAllPlayerNamesTest() {
        List<Player> dbPlayersList = playerRepository.findAll();
        // extract only players name
        List<String> dbPlayersNameList = dbPlayersList.stream()
                .map(Player::getPlayerName)
                .collect(Collectors.toList());
        // custom query method list
        List<String> queryPlayersNameList = playerRepository.findAllPlayerNames();

        // test for both are equal
        assertEquals(queryPlayersNameList, dbPlayersNameList);
    }

    @Test
    @DisplayName("Add New Player Test")
    public void addNewPlayerTest() {
        Player freshPlayer = new Player("Virat Kohli");
        Player addedPlayer = playerRepository.save(freshPlayer);

        assertEquals(freshPlayer, addedPlayer);

    }

    @Test
    @DisplayName("Delete Player By ID")
    public void deletePlayerTest() {
        assertNotNull(getPlayerByID());

        playerRepository.deleteById(testPlayerID);

        assertNull(getPlayerByID());
    }

    // helper methods
    public Player getPlayerByID() {
        Optional<Player> result = playerRepository.findById(testPlayerID);
        return result.orElse(null);
    }


}
