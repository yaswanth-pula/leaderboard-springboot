package com.zmslabs.springboot.leaderboard.controllers;

import com.zmslabs.springboot.leaderboard.entity.Player;
import com.zmslabs.springboot.leaderboard.entity.Team;
import com.zmslabs.springboot.leaderboard.service.LeaderboardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping("/master")
public class MasterController {

    private final LeaderboardService leaderboardService;
    private static final Logger logger = LoggerFactory.getLogger(MasterController.class);

    @Autowired
    public MasterController(LeaderboardService leaderboardService) {
        logger.info("Initialized Master Controller");
        this.leaderboardService = leaderboardService;
    }

    @GetMapping("/update-team")
    public String updateTeam(@RequestParam("teamID") int updateTeamID, Model model) {
        logger.info("Updating Team of " + updateTeamID);
        Team currentTeam = leaderboardService.findTeamById(updateTeamID);

        model.addAttribute("oldTeam", currentTeam);
        logger.info("Before update"+currentTeam);
        return "team-update";
    }

    @PostMapping("/save-update")
    public String saveUpdatedTeam(@Valid @ModelAttribute("oldTeam") Team team, BindingResult br) {
        if (br.hasErrors() && (br.getErrorCount()>1)) {
            logger.info("Update Team Validation Error" + br);
            return "team-update";
        }

        leaderboardService.updateTeam(team);
        return "redirect:/leaderboard";
    }


}
