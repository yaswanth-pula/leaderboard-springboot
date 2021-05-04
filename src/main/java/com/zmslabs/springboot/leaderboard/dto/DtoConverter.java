package com.zmslabs.springboot.leaderboard.dto;

import com.zmslabs.springboot.leaderboard.entity.Team;
import org.springframework.stereotype.Component;

@Component
public class DtoConverter {

    public TeamDTO getTeamDtoFromTeamEntity(Team teamEntity){
        TeamDTO teamDTO = new TeamDTO();
        teamDTO.setTeamName(teamEntity.getTeamName());
        teamDTO.setMatchesPlayed(teamDTO.getMatchesPlayed());
        teamDTO.setMatchesWon(teamEntity.getMatchesWon());
        teamDTO.setMatchesLost(teamEntity.getMatchesLost());
        teamDTO.setLeaguePoints(teamEntity.getLeaguePoints());

        return teamDTO;
    }

    public Team getTeamEntityFromTeamDto(TeamDTO teamDto){
        // dto conversion
        Team teamEntity = new Team();
        teamEntity.setTeamId(teamDto.getTeamId());
        teamEntity.setTeamName(teamDto.getTeamName());
        teamEntity.setMatchesPlayed(teamDto.getMatchesPlayed());
        teamEntity.setMatchesWon(teamDto.getMatchesWon());
        teamEntity.setMatchesLost(teamDto.getMatchesLost());
        teamEntity.setLeaguePoints(teamDto.getLeaguePoints());
//        teamEntity.stream().map(tm ->getTeamEntityFromTeamDto(tm)).collect()
        return teamEntity;
    }

}
