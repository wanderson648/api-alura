package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.consulta.dto.DadosAgendamentoConsultaDTO;
import med.voll.api.domain.consulta.dto.DadosCancelamentoConsultaDTO;
import med.voll.api.domain.consulta.dto.DadosDetalhamentoConsultaDTO;
import med.voll.api.service.AgendaDeConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private AgendaDeConsultaService agendaDeConsultaService;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoConsultaDTO> agendar(@RequestBody @Valid DadosAgendamentoConsultaDTO dto) {
        var dados = agendaDeConsultaService.agendar(dto);
        return ResponseEntity.ok(dados);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity<DadosCancelamentoConsultaDTO> cancelar(@RequestBody @Valid DadosCancelamentoConsultaDTO dto) {
        agendaDeConsultaService.cancelar(dto);
        return ResponseEntity.noContent().build();
    }

}
