package br.com.farmacia.rotina;

import br.com.farmacia.anotacao.Boleano;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

//    http://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/scheduling/support/CronSequenceGenerator.html
//
//    Example patterns:
//
//    "0 0 * * * *" = the top of every hour of every day.
//    "*/10 * * * * *" = every ten seconds.
//    "0 0 8-10 * * *" = 8, 9 and 10 o'clock of every day.
//    "0 0/30 8-10 * * *" = 8:00, 8:30, 9:00, 9:30 and 10 o'clock every day.
//    "0 0 9-17 * * MON-FRI" = on the hour nine-to-five weekdays
//    "0 0 0 25 12 ?" = every Christmas Day at midnight

@Component
public class Agendador {

    @Autowired private RotinaConsultaSPC rotinaConsultaSPC;
    private Boolean controle = false;

    @Scheduled(cron = "*/30 * * * * *") // a cada 30 segundos
    public void runTrintaSegundos() {
//        rotinaConsultaSPC.consultar();
    }
}
