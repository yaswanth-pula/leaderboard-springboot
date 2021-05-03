package com.zmslabs.springboot.leaderboard.validation;

import com.zmslabs.springboot.leaderboard.repository.TeamRepository;
import com.zmslabs.springboot.leaderboard.service.LeaderboardService;
import com.zmslabs.springboot.leaderboard.service.LeaderboardServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;


public class DbUniqueTeamNameValidator implements ConstraintValidator<DbUniqueTeamName, String> {

    @Autowired
    private LeaderboardService leaderboardService;

    @Override
    public boolean isValid(String fieldValue, ConstraintValidatorContext constraintValidatorContext) {
        List<String>  teamNameList = leaderboardService.getTeamNameList();
        // empty data
        if(teamNameList.size() == 0) return true;

        for(String dbName : teamNameList) {
            if (dbName.equalsIgnoreCase(fieldValue.trim()))
                return false;
        }
        return true;
    }

    @Override
    public void initialize(DbUniqueTeamName constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
}
