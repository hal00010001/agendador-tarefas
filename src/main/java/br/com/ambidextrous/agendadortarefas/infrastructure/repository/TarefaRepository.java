package br.com.ambidextrous.agendadortarefas.infrastructure.repository;

import br.com.ambidextrous.agendadortarefas.infrastructure.entity.Tarefa;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TarefaRepository extends MongoRepository<Tarefa, String> {

    List<Tarefa> findByDataEventoBetween(LocalDate dataInicio, LocalDate dataFim);

    List<Tarefa> findByEmail(String email);

}
