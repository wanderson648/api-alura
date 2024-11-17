package med.voll.api.model.medico;

import jakarta.persistence.*;
import lombok.*;
import med.voll.api.endereco.Endereco;
import med.voll.api.model.medico.dto.AtualizaMedicoDTO;
import med.voll.api.model.medico.dto.MedicoDTO;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "medicos")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Embedded
    private Endereco endereco;

    private Boolean ativo;

    public Medico(MedicoDTO dados) {
        this.ativo = true;
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.crm = dados.crm();
        this.especialidade = dados.especialidade();
        this.endereco = new Endereco(dados.endereco());
    }

    public void atualizaInformacoes(AtualizaMedicoDTO dto) {
        if(dto.nome() != null) {
            this.nome = dto.nome();
        }
        if(dto.telefone() != null) {
            this.telefone = dto.telefone();
        }
        if(dto.endereco() != null) {
            this.endereco.atualizarInformacoes(dto.endereco());
        }

    }

    public void excluir() {
        this.ativo = false;
    }
}
