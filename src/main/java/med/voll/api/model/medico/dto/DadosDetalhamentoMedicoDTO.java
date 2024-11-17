package med.voll.api.model.medico.dto;

import med.voll.api.endereco.Endereco;
import med.voll.api.model.medico.Especialidade;
import med.voll.api.model.medico.Medico;

public record DadosDetalhamentoMedicoDTO(
        Long id,
        String nome,
        String email,
        String crm,
        Especialidade especialidade,
        Endereco endereco
) {
    public DadosDetalhamentoMedicoDTO(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(),
                medico.getEspecialidade(), medico.getEndereco());
    }


}
