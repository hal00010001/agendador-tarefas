package br.com.ambidextrous.agendadortarefas.business.mapper;

import br.com.ambidextrous.agendadortarefas.business.dto.TarefaDTO;
import br.com.ambidextrous.agendadortarefas.infrastructure.entity.Tarefa;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TarefaConverter {

    @Mapping(source = "id", target = "id")
//    @Mapping(source = "dataCriacao", target = "dataCriacao")
//    @Mapping(source = "dataAtualizacao", target = "dataAtualizacao")
    Tarefa paraTarefa(TarefaDTO tarefaDTO);
    TarefaDTO paraTarefaDTO(Tarefa tarefa);
    List<Tarefa>  paraListaTarefa(List<TarefaDTO> tarefaDTO);
    List<TarefaDTO>  paraListaTarefaDTO(List<Tarefa> tarefa);

}
