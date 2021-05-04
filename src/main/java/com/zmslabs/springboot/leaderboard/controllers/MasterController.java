package com.zmslabs.springboot.leaderboard.controllers;

import com.zmslabs.springboot.leaderboard.dto.DtoConverter;
import com.zmslabs.springboot.leaderboard.dto.TeamDTO;
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



@Controller
@RequestMapping("/master")
public class MasterController {

    private final LeaderboardService leaderboardService;
    private final DtoConverter dtoConverter;
    private static final Logger logger = LoggerFactory.getLogger(MasterController.class);

    @Autowired
    public MasterController(LeaderboardService leaderboardService,DtoConverter dtoConverter) {
        logger.info("Initialized Master Controller");
        this.leaderboardService = leaderboardService;
        this.dtoConverter = dtoConverter;

    }

    @GetMapping("/update-team")
    public String updateTeam(@RequestParam("teamID") int updateTeamID, Model model) {
        logger.info("Updating Team of " + updateTeamID);
        Team currentTeam = leaderboardService.findTeamById(updateTeamID);

        TeamDTO teamDTO = dtoConverter.getTeamDtoFromTeamEntity(currentTeam);
        teamDTO.setTeamId(currentTeam.getTeamId());

        model.addAttribute("oldTeam", teamDTO);
        logger.info("Before update"+currentTeam);
        return "team-update";
    }

    @PostMapping("/save-update")
    public String saveUpdatedTeam(@Valid @ModelAttribute("oldTeam") TeamDTO team, BindingResult br) {
        if (br.hasErrors() && (br.getErrorCount()>1)) {
            logger.info("Update Team Validation Error" + br);
            return "team-update";
        }

        leaderboardService.updateTeam(team);
        logger.info("After update :"+team);
        return "redirect:/leaderboard";
    }


}
