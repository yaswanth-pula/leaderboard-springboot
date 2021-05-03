package com.zmslabs.springboot.leaderboard.validation;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = DbUniqueTeamNameValidator.class)
@Target({ElementType.FIELD,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DbUniqueTeamName {

    public String value() default "";

    // error message
    public String message() default "Team Already Exist";

    // default groups
    public Class<?>[] groups() default {};

    // default payloads
    public Class<? extends Payload>[] payload() default {};

}
