package com.zmslabs.springboot.leaderboard;

import com.zmslabs.springboot.leaderboard.entity.Player;
import com.zmslabs.springboot.leaderboard.service.LeaderboardService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(roles = "ADMIN")
public class AdminControllerTest {
    @MockBean
    private LeaderboardService leaderboardService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Render Add New Team Page Test")
    public void renderAddNewTeamPageTest() {
        try {
            mockMvc.perform(get("/admin/add-team"))
                    .andExpect(status().isOk())
                    .andExpect(view().name("team-add"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Delete Team will redirect Leaderboard Test")
    public void redirectLeaderboardDeleteTeamTest() {
        int testTeamId = 180;
        try {
            mockMvc.perform(get("/admin/delete-team")
                    .param("teamID", String.valueOf(testTeamId)))
                    .andExpect(status().is(302))
                    .andExpect(redirectedUrl("/leaderboard"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Render Players Detail Page Test")
    public void renderPlayerDetailPageTest() {
        int testTeamId = 180;
        List<Player> testPlayerList = new ArrayList<>();
        testPlayerList.add(new Player("virat"));
        testPlayerList.add(new Player("dhoni"));

        Mockito.when(leaderboardService.getPlayerList(testTeamId))
                .thenReturn(testPlayerList);

        try {
            mockMvc.perform(get("/admin/player-detail/" + testTeamId))
                    .andExpect(status().isOk())
                    .andExpect(view().name("players-detail"))
                    .andExpect(model().attribute("playersList", testPlayerList));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Render Add Player Page Test")
    public void renderAddPlayerPageTest() {
        int testTeamId = 180;
        try {
            mockMvc.perform(get("/admin/" + testTeamId + "/add-player"))
                    .andExpect(status().isOk())
                    .andExpect(view().name("add-player"))
                    .andExpect(model().attribute("currentTeamID", testTeamId));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Delete Player will redirect Player Detail Page Test")
    public void afterDeletePlayerRedirectPlayerDetailTest() {
        int testTeamId = 180;
        int testPlayerId = 7091;
        try {
            mockMvc.perform(get("/admin/" + testTeamId + "/delete-player/" + testPlayerId))
                    .andExpect(status().is(302))
                    .andExpect(redirectedUrl("/admin/player-detail/" + testTeamId));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
