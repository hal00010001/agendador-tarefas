package br.com.ambidextrous.agendadortarefas.business.mapper;

import br.com.ambidextrous.agendadortarefas.business.dto.TarefaDTO;
import br.com.ambidextrous.agendadortarefas.infrastructure.entity.Tarefa;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TarefaUpdateConverter {

    void updateTarefa(TarefaDTO tarefaDTO, @MappingTarget Tarefa tarefa);

}
