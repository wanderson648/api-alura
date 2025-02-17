package med.voll.api.domain.paciente.dto;

import med.voll.api.domain.endereco.Endereco;
import med.voll.api.domain.paciente.Paciente;

public record DadosDetalhamentoPacienteDTO(
        String nome,
        String email,
        String telefone,
        String cpf,
        Endereco endereco) {

    public DadosDetalhamentoPacienteDTO(Paciente paciente) {
        this(paciente.getNome(), paciente.getEmail(), paciente.getTelefone(), paciente.getCpf(), paciente.getEndereco());
    }
}