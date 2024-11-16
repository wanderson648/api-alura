package med.voll.api.model.medico.dto;

import med.voll.api.model.medico.Especialidade;
import med.voll.api.model.medico.Medico;

public record DadosListagemMedicosDTO(
        Long id,
        String nome,
        String email,
        String crm,
        Especialidade especialidade
) {

    public DadosListagemMedicosDTO(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
}
