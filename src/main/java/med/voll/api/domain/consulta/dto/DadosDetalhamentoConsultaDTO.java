package med.voll.api.domain.consulta.dto;

import java.time.LocalDateTime;

public record DadosDetalhamentoConsultaDTO(
        Long id,
        Long idMedico,
        Long idPaciente,
        LocalDateTime data) {
}
