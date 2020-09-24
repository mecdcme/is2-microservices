package it.istat.is2.dataset.controller;

import it.istat.is2.dataset.domain.DatasetColumn;
import it.istat.is2.dataset.dto.DatasetColumnDTO;
import it.istat.is2.dataset.service.DatasetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/v${api.version}/dataset")
public class DataSetController {

    private final DatasetService datasetService;

    @Autowired
    public DataSetController(DatasetService datasetService) {
        this.datasetService = datasetService;
    }

    @GetMapping("/datasetcolonnasql/{dfile}/{rigainf}/{rigasup}")
    @ResponseBody
    public ResponseEntity<List<DatasetColumnDTO>> loadDataSetColonnaSql(
            @PathVariable("dfile") Long dfile,
            @PathVariable("rigainf") Integer rigainf,
            @PathVariable("rigasup") Integer rigasup)
    {
        List<DatasetColumn> df = datasetService.findAllDatasetColumnSQL(dfile, rigainf, rigasup);
        return ResponseEntity.ok(df.stream().map(x -> new DatasetColumnDTO.Builder().fromEntity(x).build()).collect(Collectors.toList()));
    }
}
