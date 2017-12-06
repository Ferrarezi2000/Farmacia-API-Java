package br.com.farmacia.rotina;

import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

@Log
@Service
public class RotinaConsultaSPC {

    public void consultar() {
        log.info("Consultando SPC...");
    }
}
