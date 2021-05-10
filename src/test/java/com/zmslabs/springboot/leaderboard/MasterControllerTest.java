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
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
@DisplayName("Master Controller Test")
public class MasterControllerTest {

    @MockBean
    private LeaderboardService leaderboardService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Render Update Team page Test")
    @WithMockUser(roles = "MASTER")
    public void renderUpdateTeamPageTest() {
        int testTeamId = 180;
        Team testTeam = new Team("india", 2, 2, 0, 4);
        Mockito.when(leaderboardService.findTeamById(testTeamId)).thenReturn(testTeam);

        try {
            mockMvc.perform(get("/master/update-team")
                    .queryParam("teamID", String.valueOf(testTeamId)))
                    .andExpect(status().isOk())
                    .andExpect(view().name("team-update"))
                    .andExpect(model().attribute("oldTeam", testTeam));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
