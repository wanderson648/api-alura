package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.model.medico.Medico;
import med.voll.api.model.medico.dto.AtualizaMedicoDTO;
import med.voll.api.model.medico.dto.DadosDetalhamentoMedicoDTO;
import med.voll.api.model.medico.dto.DadosListagemMedicosDTO;
import med.voll.api.model.medico.dto.MedicoDTO;
import med.voll.api.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoMedicoDTO> cadastrar(@RequestBody @Valid MedicoDTO dto,
                                                                UriComponentsBuilder uriBuilder) {
        var medico = new Medico(dto);
        repository.save(medico);

        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoMedicoDTO(medico));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemMedicosDTO>> lsitar(
            @PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedicosDTO::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoMedicoDTO> atualizar(@RequestBody @Valid AtualizaMedicoDTO dto) {
        var medico = repository.getReferenceById(dto.id());
        medico.atualizaInformacoes(dto);
        return ResponseEntity.ok(new DadosDetalhamentoMedicoDTO(medico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        var medico = repository.getReferenceById(id);
        medico.excluir();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoMedicoDTO> detalhar(@PathVariable Long id) {
        var medico = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoMedicoDTO(medico));
    }
}
