package com.zmslabs.springboot.leaderboard;


import com.zmslabs.springboot.leaderboard.service.LeaderboardService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@DisplayName("Fantasy Controller Test")
@WithMockUser(roles = "FAN")
public class FantasyControllerTest {

    @MockBean
    private LeaderboardService leaderboardService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Update Team Fan Support Test")
    public void updateTeamFanSupportTest() {
        int testTeamId = 180;
        try {
            mockMvc.perform(get("/fan/support-team")
                    .param("teamID", String.valueOf(testTeamId)))
                    .andExpect(status().is(302))
                    .andExpect(redirectedUrl("/leaderboard"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
