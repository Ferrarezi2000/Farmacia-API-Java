package br.com.farmacia.controller;

import br.com.farmacia.model.Messages;
import br.com.farmacia.model.ResponseRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.NoSuchMessageException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.logging.Logger;

public class AbstractRestController {

    private static final Logger log = Logger.getLogger(AbstractRestController.class.getName());
    @Autowired
    private Messages messages;

    public AbstractRestController() {
    }

    public String buildMensagem(String message) {
        try {
            return this.messages.get(message);
        } catch (NoSuchMessageException var3) {
            return message;
        }
    }

    @ExceptionHandler({Exception.class})
    protected ResponseEntity<?> exception(Exception ex) {
        return ResponseRest.exception(ex);
    }
}

