package br.com.farmacia.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Locale;

@Component
public class Messages {
    @Autowired
    private MessageSource messageSource;
    private MessageSourceAccessor accessor;

    public Messages() {
    }

    @PostConstruct
    private void init() {
        this.accessor = new MessageSourceAccessor(this.messageSource, new Locale("pt", "BR"));
    }

    public String get(String code) {
        return this.accessor.getMessage(code);
    }
}