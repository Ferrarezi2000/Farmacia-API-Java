package br.com.farmacia.builder;

import br.com.farmacia.dto.PlantaoDTO;
import br.com.farmacia.model.Farmacia;
import br.com.farmacia.model.Plantao;
import br.com.farmacia.repository.FarmaciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.GregorianCalendar;

@Component
public class PlantaoBuild {

    @Autowired private FarmaciaRepository farmaciaRepository;

    public Plantao build(Plantao plantao, PlantaoDTO dto) {
        Farmacia farmacia = farmaciaRepository.findOne(dto.getFarmaciaId());


        GregorianCalendar dataCal = new GregorianCalendar();
        dataCal.setTime(dto.getData());

        plantao.setFarmacia(farmacia);
        plantao.setData(dto.getData());
        plantao.setDia(dataCal.get(Calendar.DATE));
        plantao.setAno(dataCal.get(Calendar.YEAR));

        switch (dto.getData().getMonth()) {
            case 0:
                plantao.setMes("Janeiro");
                break;
            case 1:
                plantao.setMes("Fevereiro");
                break;
            case 2:
                plantao.setMes("Março");
                break;
            case 3:
                plantao.setMes("Abril");
                break;
            case 4:
                plantao.setMes("Maio");
                break;
            case 5:
                plantao.setMes("Junho");
                break;
            case 6:
                plantao.setMes("Julho");
                break;
            case 7:
                plantao.setMes("Agosto");
                break;
            case 8:
                plantao.setMes("Setembro");
                break;
            case 9:
                plantao.setMes("Outubro");
                break;
            case 10:
                plantao.setMes("Novembro");
                break;
            case 11:
                plantao.setMes("Dezembro");
                break;
        }

        return plantao;
    }

}
