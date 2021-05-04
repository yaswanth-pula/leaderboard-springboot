package com.zmslabs.springboot.leaderboard.dto;

import com.zmslabs.springboot.leaderboard.validation.DbUniqueTeamName;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Data
public class TeamDTO {

    private int teamId;

    @DbUniqueTeamName
    @Size(min = 1, message = "Team Name Should be atleast 1 char")
    private String teamName;

    @Min(value = 0)
    private int matchesPlayed;

    @Min(value = 0)
    private int matchesWon;

    @Min(value = 0)
    private int matchesLost;

    @Min(value = 0)
    private int leaguePoints;
}
