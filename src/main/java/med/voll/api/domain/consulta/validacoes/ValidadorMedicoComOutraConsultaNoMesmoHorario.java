package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.ValidationException;
import med.voll.api.domain.consulta.dto.DadosAgendamentoConsultaDTO;
import med.voll.api.repository.ConsultaRepository;
import med.voll.api.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidadorMedicoComOutraConsultaNoMesmoHorario {

    @Autowired
    private ConsultaRepository repository;


    public void validar(DadosAgendamentoConsultaDTO dto) {

        var medicoPossuiOutraConsultaNoMesmoHorario = repository.existsByMedicoIdAndData(dto.idMedico(), dto.data());

        if (medicoPossuiOutraConsultaNoMesmoHorario) {
            throw new ValidationException("Médico já possui outra mesma consulta já agendada nesse mesmo horário");
        }
    }

}
