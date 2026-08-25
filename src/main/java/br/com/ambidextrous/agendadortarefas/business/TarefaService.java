package br.com.ambidextrous.agendadortarefas.business;

import br.com.ambidextrous.agendadortarefas.business.dto.TarefaDTO;
import br.com.ambidextrous.agendadortarefas.business.mapper.TarefaConverter;
import br.com.ambidextrous.agendadortarefas.business.mapper.TarefaUpdateConverter;
import br.com.ambidextrous.agendadortarefas.exception.ResourceNotFoundException;
import br.com.ambidextrous.agendadortarefas.infrastructure.entity.Tarefa;
import br.com.ambidextrous.agendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import br.com.ambidextrous.agendadortarefas.infrastructure.repository.TarefaRepository;
import br.com.ambidextrous.agendadortarefas.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefaService {

    private final TarefaRepository tarefaRepository;
    private final TarefaConverter tarefaConverter;
    private final JwtUtil jwtUtil;
    private final TarefaUpdateConverter tarefaUpdateConverter;

    public TarefaDTO gravarTarefa(String token, TarefaDTO tarefaDTO) {
        try {
            String email = jwtUtil.extractUsername(token.substring(7));
            tarefaDTO.setDataCriacao(LocalDateTime.now());
            tarefaDTO.setStatus(StatusNotificacaoEnum.PENDENTE);
            tarefaDTO.setEmail(email);
            Tarefa tarefa = tarefaConverter.paraTarefa(tarefaDTO);
            Tarefa salva =  tarefaRepository.save(tarefa);
            TarefaDTO retorno = tarefaConverter.paraTarefaDTO(salva);

            return retorno;

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public List<TarefaDTO> buscarTarefasAgendadasPorPeriodo(LocalDate dataInicio, LocalDate dataFim) {
        return tarefaConverter.paraListaTarefaDTO(tarefaRepository.findByDataEventoBetween(dataInicio, dataFim));
    }

    public List<TarefaDTO> buscarTarefeasPorEmail(String token) {

        String email = jwtUtil.extractUsername(token.substring(7));
        List<Tarefa> listaTarefas = tarefaRepository.findByEmail(email);

        return tarefaConverter.paraListaTarefaDTO(listaTarefas);

    }

    public void deleteTarefaPorId(String id) {
        try {
            tarefaRepository.deleteById(id);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Erro ao deletar tarefa por id, id inexistente " + id, e.getCause());
        }
    }

    public TarefaDTO alterarStatus(String id, StatusNotificacaoEnum status) {
        try {
            Tarefa tarefa = tarefaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada " + id));
            tarefa.setStatus(status);
            return tarefaConverter.paraTarefaDTO(tarefaRepository.save(tarefa));
        } catch (ResourceNotFoundException e) {
            throw new RuntimeException("Erro ao alterar o status da tarefa " + e.getCause());
        }
    }

    public TarefaDTO updateTarefa(String id, TarefaDTO tarefaDTO) {
        try {
            Tarefa tarefa = tarefaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Tarefa não  encontrada " + id));
            tarefaUpdateConverter.updateTarefa(tarefaDTO, tarefa);
            return tarefaConverter.paraTarefaDTO(tarefaRepository.save(tarefa));
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Erro ao atualizar tarefa " + e.getCause());
        }
    }

}
