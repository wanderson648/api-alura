package med.voll.api.model.medico.dto;

import jakarta.validation.constraints.NotNull;
import med.voll.api.endereco.DadosEndereco;

public record AtualizaMedicoDTO(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco
) {
}
