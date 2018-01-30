package com.example.Fibonacci.conrollers;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {
    public static final String DEFAULT_ERROR_VIEW = "error";

    @ExceptionHandler(value = Exception.class)
    public ModelAndView
    defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {

        if (AnnotationUtils.findAnnotation
                (e.getClass(), ResponseStatus.class) != null)
            throw e;

        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e);
        mav.addObject("url", req.getRequestURL());
        mav.setViewName(DEFAULT_ERROR_VIEW);
        System.out.println();
        System.out.println("!!! EXCEPTION !!!");
        System.out.println(req.getRequestURL() + " " + e);
        System.out.println();
        return mav;
    }

    @ExceptionHandler({ SQLException.class, DataAccessException.class })
    public String databaseError(Exception e) {

        System.out.println();
        System.out.println("!!! EXCEPTION !!!");
        System.out.println("databaseError " + e);

        return "databaseError";
    }
}
