package com.zmslabs.springboot.leaderboard.validation;

import com.zmslabs.springboot.leaderboard.service.LeaderboardService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class DbUniquePlayerNameValidator implements ConstraintValidator<DbUniquePlayerName, String> {

    @Autowired
    private LeaderboardService leaderboardService;

    @Override
    public boolean isValid(String fieldValue, ConstraintValidatorContext constraintValidatorContext) {
        List<String>  playerNameList = leaderboardService.getPlayerNameList();
        // empty data
        if(playerNameList.size() == 0) return true;

        for(String dbName : playerNameList) {
               if (dbName.equalsIgnoreCase(fieldValue.trim()))
                   return false;
        }
        return true;
    }

    @Override
    public void initialize(DbUniquePlayerName constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
}
