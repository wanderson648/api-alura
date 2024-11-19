package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.ValidationException;
import med.voll.api.domain.consulta.dto.DadosAgendamentoConsultaDTO;
import med.voll.api.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidadorMedicoAtivo {

    @Autowired
    private MedicoRepository repository;


    public void validar(DadosAgendamentoConsultaDTO dto) {
        if(dto.idMedico() == null) {
            return;
        }

        var medicosEstaAtivo = repository.findAtivoById(dto.idMedico());
        if (!medicosEstaAtivo) {
            throw new ValidationException("Consulta não pode ser agendada, pois o médico não faz parte da clínica");
        }
    }

}
