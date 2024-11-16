package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.model.paciente.dto.DadosListagemPaciente;
import med.voll.api.model.paciente.dto.PacienteDTO;
import med.voll.api.model.paciente.Paciente;
import med.voll.api.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid PacienteDTO dto) {
        repository.save(new Paciente(dto));
    }

    @GetMapping
    public Page<DadosListagemPaciente> listar(
            @PageableDefault(page = 0, size = 10, sort = {"nome"}) Pageable paginacao) {
        return repository.findAll(paginacao).map(DadosListagemPaciente::new);
    }
}
