package com.zmslabs.springboot.leaderboard.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ExceptionResponse {
    private int statusCode;
    private String message;
    private long timeStamp;

}
