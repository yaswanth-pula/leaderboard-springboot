package com.zmslabs.springboot.leaderboard.controllers;

import com.zmslabs.springboot.leaderboard.service.LeaderboardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class LeaderboardController {

    private static final Logger logger = LoggerFactory.getLogger(LeaderboardController.class);
    private final LeaderboardService leaderboardService;

    @Autowired
    public LeaderboardController(LeaderboardService leaderboardService) {
        logger.info("Initialized Leaderboard Controller");
        this.leaderboardService = leaderboardService;
    }

    @GetMapping("/leaderboard")
    public String renderLeaderboard(Model model) {
        logger.info("Displaying Leaderboard");
        model.addAttribute("teamList", leaderboardService.getAllTeams());
        return "leaderboard";
    }


}
