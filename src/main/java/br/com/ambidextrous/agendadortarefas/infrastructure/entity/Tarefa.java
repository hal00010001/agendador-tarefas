package br.com.ambidextrous.agendadortarefas.infrastructure.entity;

import br.com.ambidextrous.agendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Document("tarefa")
public class Tarefa {

    @Id
    private String id;
    private String nome;
    private String descricao;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;
    private LocalDate dataEvento;
    private String email;
    private StatusNotificacaoEnum status;

}
