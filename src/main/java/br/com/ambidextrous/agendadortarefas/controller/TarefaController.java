package br.com.ambidextrous.agendadortarefas.controller;

import br.com.ambidextrous.agendadortarefas.business.TarefaService;
import br.com.ambidextrous.agendadortarefas.business.dto.TarefaDTO;
import br.com.ambidextrous.agendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
public class TarefaController {

    private final TarefaService  tarefaService;

    @PostMapping
    public ResponseEntity<TarefaDTO> gravarTarefas(@RequestBody TarefaDTO tarefaDTO, @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(tarefaService.gravarTarefa(token, tarefaDTO));
    }

    @GetMapping("/eventos")
    public ResponseEntity<List<TarefaDTO>> buscarTarefaEventos(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate dataInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate dataFim) {
        return ResponseEntity.ok(tarefaService.buscarTarefasAgendadasPorPeriodo(dataInicio, dataFim));
    }

    @GetMapping
    public ResponseEntity<List<TarefaDTO>> buscarTarefasPorEmail(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(tarefaService.buscarTarefeasPorEmail(token));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteTarefa(@RequestParam String id) {
        tarefaService.deleteTarefaPorId(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping
    public ResponseEntity<TarefaDTO> alterarStatus(@RequestParam StatusNotificacaoEnum status, @RequestParam String id) {
        return ResponseEntity.ok(tarefaService.alterarStatus(id, status));
    }

    @PutMapping
    public ResponseEntity<TarefaDTO> atualizarTarefa(@RequestBody TarefaDTO tarefaDTO, @RequestParam String id) {
        return ResponseEntity.ok(tarefaService.updateTarefa(id, tarefaDTO));
    }

}
