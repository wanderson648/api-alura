package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.ValidationException;
import med.voll.api.domain.consulta.dto.DadosAgendamentoConsultaDTO;
import med.voll.api.repository.MedicoRepository;
import med.voll.api.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteAtivo implements ValidadorAgendamentoDeConsulta {

    @Autowired
    private PacienteRepository repository;


    public void validar(DadosAgendamentoConsultaDTO dto) {
        var pacienteEstaAtivo = repository.findAtivoById(dto.idPaciente());
        if (!pacienteEstaAtivo) {
            throw new ValidationException("Consulta não pode ser agendada, pois o paciente não existe");
        }
    }

}
