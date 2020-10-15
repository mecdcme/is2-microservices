package it.istat.is2.logger.controller;

import it.istat.is2.logger.domain.WorkSessionEntity;
import it.istat.is2.logger.model.LogCreateRequest;
import it.istat.is2.logger.model.LogDTO;
import it.istat.is2.logger.model.LogDeleteRequest;
import it.istat.is2.logger.repository.LogRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/log")
@Slf4j
public class LoggerController {

    private static final String Q_CREATE = "createLogQueue";
    private static final String Q_DELETE = "deleteLogQueue";


    private LogRepository logRepository;
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public LoggerController(LogRepository logRepository, RabbitTemplate rabbitTemplate) {
        this.logRepository = logRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    @GetMapping("/")
    public ResponseEntity<List<LogDTO>> findAll() {
        return ResponseEntity.ok(logRepository.findAll()
                .stream()
                .map( x -> new LogDTO.Builder().fromEntity(x).build() )
                .collect(Collectors.toList()));
    }

    @GetMapping("/{idSessione}")
    public ResponseEntity<List<LogDTO>> findByIdSessione(@PathVariable("idSessione") Long idSessione) {
        WorkSessionEntity id = new WorkSessionEntity();
        id.setId(idSessione);
        return ResponseEntity.ok(logRepository.findByWorkSessionOrderByIdAsc(id)
                .stream()
                .map( x -> new LogDTO.Builder().fromEntity(x).build() )
                .collect(Collectors.toList()));
    }

    @GetMapping("/{idSessione}/{tipo}")
    public ResponseEntity<List<LogDTO>> findByIdSessioneAndTipo(@PathVariable("idSessione") Long idSessione, @PathVariable("idSessione") String tipo) {
        WorkSessionEntity id = new WorkSessionEntity();
        id.setId(idSessione);
        return ResponseEntity.ok(logRepository.findByWorkSessionAndTypeOrderByIdAsc(id, tipo)
                .stream()
                .map( x -> new LogDTO.Builder().fromEntity(x).build() )
                .collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<Boolean> create(@RequestBody LogCreateRequest request) {
        rabbitTemplate.convertAndSend(Q_CREATE, request);
        return ResponseEntity.ok(Boolean.TRUE);
    }

    @DeleteMapping("/{idSessione}")
    public ResponseEntity<Void> deleteByIdSessione(@PathVariable("idSessione") Long idSessione) {
        LogDeleteRequest request = LogDeleteRequest.builder().sessioneId(idSessione).build();
        rabbitTemplate.convertAndSend(Q_DELETE, request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{idSessione}/{tipo}")
    public ResponseEntity<Void> deleteByIdSessioneAndTipo(@PathVariable("idSessione") Long idSessione, @PathVariable("tipo") String tipo) {
        LogDeleteRequest request = LogDeleteRequest.builder().sessioneId(idSessione).type(tipo).build();
        rabbitTemplate.convertAndSend(Q_DELETE, request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/public/test")
    public ResponseEntity<String> testPublic() {
        return ResponseEntity.ok("public method test");
    }

}
