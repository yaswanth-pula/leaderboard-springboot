package com.zmslabs.springboot.leaderboard.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ControllerExceptionHandler {

    private static Logger logger = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    // handle resource not found exception
    @ExceptionHandler
    public ModelAndView render404ExceptionPage(ResourceNotFoundException exception) {
        logger.error("404 Exception Cause :" + exception.getCause());
        ExceptionResponse errorResponse = new ExceptionResponse(HttpStatus.NOT_FOUND.value()
                , exception.getMessage(), System.currentTimeMillis());
        return new ModelAndView("error-template", "errorContent", errorResponse);
    }

//     handles unique key constraint
//    @ExceptionHandler
//    public ModelAndView handleUniqueKeyException(DataIntegrityViolationException exception) {
//        logger.info(exception.getCause().toString());
//        ExceptionResponse errorResponse = new ExceptionResponse(HttpStatus.CONFLICT.value()
//                , "Given Team Name Already Exist", System.currentTimeMillis());
//        return new ModelAndView("error-template", "errorContent", errorResponse);
//    }

    // handle bad request Exception
    @ExceptionHandler
    public ModelAndView renderBadRequestException(Exception exception) {

        logger.error("400 Exception Cause :" + exception.getCause());
        ExceptionResponse errorResponse = new ExceptionResponse(HttpStatus.BAD_REQUEST.value()
                , exception.getMessage(), System.currentTimeMillis());

        return new ModelAndView("error-template", "errorContent", errorResponse);
    }


}
