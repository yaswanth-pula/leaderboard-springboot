package com.zmslabs.springboot.leaderboard;

import com.zmslabs.springboot.leaderboard.entity.Team;
import com.zmslabs.springboot.leaderboard.service.LeaderboardService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class LeaderboardControllerTest {

    @MockBean
    private LeaderboardService leaderboardService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Render Leaderboard")
    public void renderLeaderboardPageTest() {
        List<Team> testTeamList = new ArrayList<>();
        testTeamList.add(new Team("india", 2, 0, 2, 0));
        Mockito.when(leaderboardService.getAllTeams()).thenReturn(testTeamList);

        try {
            mockMvc.perform(get("/leaderboard"))
                    .andExpect(status().isOk())
                    .andExpect(view().name("leaderboard"))
                    .andExpect(model().attribute("teamList", testTeamList));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
