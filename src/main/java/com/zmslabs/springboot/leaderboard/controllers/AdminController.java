package com.zmslabs.springboot.leaderboard.controllers;

import com.zmslabs.springboot.leaderboard.dto.TeamDTO;
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
@RequestMapping("/admin")
public class AdminController {

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
    private final LeaderboardService leaderboardService;

    @Autowired
    public AdminController(LeaderboardService leaderboardService) {
        logger.info("Initialized Admin Controller");
        this.leaderboardService = leaderboardService;
    }

    @GetMapping("/add-team")
    public String renderAddNewTeam(Model model) {
        model.addAttribute("freshTeam", new TeamDTO());
        logger.info("Operation Add New Team");
        return "team-add";
    }

    @PostMapping("/save-team")
    public String saveTeam(@Valid @ModelAttribute("freshTeam") TeamDTO team, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            logger.error("New Team Validation Error" + bindingResult);
            return "team-add";
        }
        // no validation errors
        logger.info("Adding New Team" + team);
        leaderboardService.addNewTeam(team);
        return "redirect:/leaderboard";
    }

    @GetMapping("/delete-team")
    public String deleteTeam(@RequestParam("teamID") int deleteTeamID) {
        logger.info("Deleting Team of ID" + deleteTeamID);
        leaderboardService.deleteTeamByID(deleteTeamID);
        return "redirect:/leaderboard";
    }

    @GetMapping("/player-detail/{teamID}")
    public String renderPlayerList(@PathVariable("teamID") int playerTeamID, Model model) {
        List<Player> teamPlayers = leaderboardService.getPlayerList(playerTeamID);
        model.addAttribute("playersList", teamPlayers);
        model.addAttribute("currentTeamID", playerTeamID);
        logger.info("Displaying Players Details");
        return "players-detail";
    }

    @GetMapping("{teamID}/add-player")
    public String addPlayer(Model model, @PathVariable("teamID") int playerTeamID) {
        Player player = new Player();
        model.addAttribute("freshPlayer", player);
        model.addAttribute("currentTeamID", playerTeamID);
        logger.info("Adding a New Player");
        return "add-player";
    }

    @PostMapping("/save-player/{teamID}")
    public String savePlayer(@Valid @ModelAttribute("freshPlayer") Player player, BindingResult br,
                             @PathVariable("teamID") int playerTeamID, Model model) {
        if (br.hasErrors()) {
            model.addAttribute("freshPlayer", player);
            model.addAttribute("currentTeamID", playerTeamID);
            logger.error("New Player Validation Error" + br);
            return "add-player";
        }
        leaderboardService.addTeamPlayer(player.getPlayerName(), playerTeamID);
        return "redirect:/admin/player-detail/" + playerTeamID;
    }


    @GetMapping("{teamID}/delete-player/{playerID}")
    public String deletePlayer(@PathVariable("playerID") int deletePlayerID, @PathVariable("teamID") int deletePlayerTeamID) {
        logger.info("Deleting Player of ID" + deletePlayerID);
        leaderboardService.deletePlayer(deletePlayerID);
        return "redirect:/admin/player-detail/" + deletePlayerTeamID;
    }
}