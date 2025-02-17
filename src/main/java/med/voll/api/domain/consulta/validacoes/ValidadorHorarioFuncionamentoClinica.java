package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.ValidationException;
import med.voll.api.domain.consulta.dto.DadosAgendamentoConsultaDTO;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidadorHorarioFuncionamentoClinica implements ValidadorAgendamentoDeConsulta {

    public void validar(DadosAgendamentoConsultaDTO dto) {
        var dataConsulta = dto.data();

        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var antesDaaberturaDaClinica = dataConsulta.getHour() < 7;
        var dopoisDoEncerramentoDaClinica = dataConsulta.getHour() > 18;
        if(domingo || antesDaaberturaDaClinica || dopoisDoEncerramentoDaClinica) {
            throw new ValidationException("Consulta fora do horário de funcionamento da clínica");
        }
    }
}
