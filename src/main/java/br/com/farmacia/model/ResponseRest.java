package br.com.farmacia.model;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.*;

public class ResponseRest {
    public ResponseRest() {
    }

    public static ResponseEntity ok(String message) {
        return new ResponseEntity(MapBuilder.build("mensagem", message), HttpStatus.OK);
    }

    public static ResponseEntity created(String message) {
        return new ResponseEntity(MapBuilder.build("mensagem", message), HttpStatus.CREATED);
    }

    public static ResponseEntity list(Collection list) {
        return new ResponseEntity(list, HttpStatus.OK);
    }

    public static ResponseEntity list(Object... item) {
        return list((Collection) Arrays.asList(item));
    }

    public static ResponseEntity object(Object obj) {
        return new ResponseEntity(obj, HttpStatus.OK);
    }

    private static ResponseEntity exception(IllegalArgumentException ex) {
        return new ResponseEntity(MapBuilder.build("mensagem", ex.getMessage()).add("exception", ex.getStackTrace()), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    private static ResponseEntity exception(ConstraintViolationException ex) {
        Set violations = ex.getConstraintViolations();
        String message = "";

        ConstraintViolation v;
        String f;
        for(Iterator var3 = violations.iterator(); var3.hasNext(); message = message + f.substring(0, 1).toUpperCase() + f.substring(1) + ": " + v.getMessage() + "\n") {
            v = (ConstraintViolation)var3.next();
            f = v.getPropertyPath().toString();
        }

        return new ResponseEntity(MapBuilder.build("mensagem", message).add("exception", ex.getStackTrace()), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    private static ResponseEntity exception(MethodArgumentNotValidException ex) {
        List erros = ex.getBindingResult().getAllErrors();
        String message = "";

        ObjectError e;
        String f;
        for(Iterator var3 = erros.iterator(); var3.hasNext(); message = message + f.substring(0, 1).toUpperCase() + f.substring(1) + ": " + e.getDefaultMessage() + "\n") {
            e = (ObjectError)var3.next();
            f = ((FieldError)e).getField();
        }

        return new ResponseEntity(MapBuilder.build("mensagem", message).add("erros", erros).add("exception", ex.getStackTrace()), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    public static ResponseEntity exception(Exception ex) {
        if(ex instanceof IllegalArgumentException) {
            return exception((IllegalArgumentException)ex);
        } else if(ex instanceof ConstraintViolationException) {
            return exception((ConstraintViolationException)ex);
        } else if(ex instanceof MethodArgumentNotValidException) {
            return exception((MethodArgumentNotValidException)ex);
        } else {
            HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
            return new ResponseEntity(MapBuilder.build("mensagem", ex.getMessage()).add("exception", ex.getStackTrace()), status);
        }
    }
}

