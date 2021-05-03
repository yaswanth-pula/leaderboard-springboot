package com.zmslabs.springboot.leaderboard.controllers;

import com.zmslabs.springboot.leaderboard.service.LeaderboardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/fan")
public class FantasyController {

    private final LeaderboardService leaderboardService;
    private static final Logger logger = LoggerFactory.getLogger(FantasyController.class);

    @Autowired
    public FantasyController(LeaderboardService leaderboardService) {
        logger.info("Initialized Fantasy Controller");
        this.leaderboardService = leaderboardService;
    }

    @GetMapping("/support-team")
    public String supportTeam(@RequestParam("teamID") int supportTeamID) {
        logger.info("Operation Support Team of ID" + supportTeamID);
        leaderboardService.updateTeamSupport(supportTeamID);
        return "redirect:/leaderboard";
    }
}
