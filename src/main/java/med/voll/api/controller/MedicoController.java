package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.PastOrPresent;
import med.voll.api.model.medico.Medico;
import med.voll.api.model.medico.dto.AtualizaMedicoDTO;
import med.voll.api.model.medico.dto.DadosListagemMedicosDTO;
import med.voll.api.model.medico.dto.MedicoDTO;
import med.voll.api.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid MedicoDTO dto) {
        repository.save(new Medico(dto));
    }

    @GetMapping
    public Page<DadosListagemMedicosDTO> lsitar(
            @PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        return repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedicosDTO::new);
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid AtualizaMedicoDTO dto) {
        var medico = repository.getReferenceById(dto.id());
        medico.atualizaInformacoes(dto);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void excluir(@PathVariable Long id) {
        var medico = repository.getReferenceById(id);
        medico.excluir();
    }
}
