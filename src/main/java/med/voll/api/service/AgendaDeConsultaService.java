package med.voll.api.service;


import med.voll.api.domain.ValidationException;
import med.voll.api.domain.consulta.Consulta;
import med.voll.api.domain.consulta.dto.DadosAgendamentoConsultaDTO;
import med.voll.api.domain.consulta.dto.DadosCancelamentoConsultaDTO;
import med.voll.api.domain.medico.Medico;
import med.voll.api.repository.ConsultaRepository;
import med.voll.api.repository.MedicoRepository;
import med.voll.api.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgendaDeConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    public void agendar(DadosAgendamentoConsultaDTO dto) {
        if(!pacienteRepository.existsById(dto.idPaciente())) {
            throw new ValidationException("Id do paciente informado não existe!");
        }

        if(dto.idMedico() != null && !medicoRepository.existsById(dto.idMedico())) {
            throw new ValidationException("Id do médico informado não existe!");
        }

        var paciente = pacienteRepository.getReferenceById(dto.idPaciente());
        var medico = escolherMedico(dto);

        var consulta = new Consulta(null, medico, paciente, dto.data(), null);
        consultaRepository.save(consulta);
    }

    private Medico escolherMedico(DadosAgendamentoConsultaDTO dto) {
        if(dto.idMedico() != null) {
            return medicoRepository.getReferenceById(dto.idMedico());
        }

        if(dto.especialidade() == null) {
            throw new ValidationException("Especialidade é ogrigatória quando médico não for escolhido");
        }

        return  medicoRepository.escolherMedicoAleatorioLivreNaData(dto.especialidade(), dto.data());
    }

    public void cancelar(DadosCancelamentoConsultaDTO dto) {
        if (!consultaRepository.existsById(dto.idConsulta())) {
            throw new ValidationException("Id da consulta informado não existe!");
        }

        var consulta = consultaRepository.getReferenceById(dto.idConsulta());
        consulta.cancelar(dto.motivo());
    }
}
