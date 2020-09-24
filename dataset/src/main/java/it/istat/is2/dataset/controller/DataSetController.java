package it.istat.is2.dataset.controller;

import it.istat.is2.dataset.domain.DatasetColumn;
import it.istat.is2.dataset.domain.StatisticalVariableCls;
import it.istat.is2.dataset.dto.DatasetColumnDTO;
import it.istat.is2.dataset.request.SetVariabileSumRequest;
import it.istat.is2.dataset.request.ValoriRequest;
import it.istat.is2.dataset.service.DatasetService;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;
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

    @GetMapping("/colonnasql/{dfile}/{rigainf}/{rigasup}")
    @ResponseBody
    public ResponseEntity<List<DatasetColumnDTO>> loadDataSetColonnaSql(
            @PathVariable("dfile") Long dfile,
            @PathVariable("rigainf") Integer rigainf,
            @PathVariable("rigasup") Integer rigasup)
    {
        List<DatasetColumn> df = datasetService.findAllDatasetColumnSQL(dfile, rigainf, rigasup);
        return ResponseEntity.ok(df.stream().map(x -> new DatasetColumnDTO.Builder().fromEntity(x).build()).collect(Collectors.toList()));
    }


    @GetMapping(value = "/rest/datasetvalori/{dfile}/{parametri:.+}")
    public ResponseEntity<String> loadDatasetValori(@PathVariable("dfile") Long dfile,
                                                    @PathVariable("parametri") String parametri,
                                                    @RequestParam("length") Integer length,
                                                    @RequestParam("start") Integer start,
                                                    @RequestParam("draw") Integer draw,
                                                    @RequestParam Map<String, String> allParams) throws JSONException {

        String indexColunmToOrder = allParams.get("order[0][column]");
        String nameColumnToOrder = allParams.get("columns[" + indexColunmToOrder + "][data]");
        String dirColumnOrder = allParams.get("order[0][dir]");

        HashMap<String, String> parameters = null;
        String noparams = "noparams";
        if (!noparams.equals(parametri)) {
            StringTokenizer st = new StringTokenizer(parametri, "&");
            StringTokenizer st2 = null;
            parameters = new HashMap<>();

            ArrayList<String> nomeValore = null;
            while (st.hasMoreTokens()) {
                st2 = new StringTokenizer(st.nextToken(), "=");
                nomeValore = new ArrayList<>();
                while (st2.hasMoreTokens()) {
                    nomeValore.add(st2.nextToken());
                }

                parameters.put(nomeValore.get(0), nomeValore.get(1));
            }
        }
        String dtb = datasetService.loadDatasetValori(dfile, length, start, draw, parameters, nameColumnToOrder, dirColumnOrder);

        return ResponseEntity.ok(dtb);
    }

    @PostMapping(value = "/setvariabilesum")
    public ResponseEntity<?> setVarSum(@RequestBody SetVariabileSumRequest request) throws IOException {

        DatasetColumn dcol = datasetService.findOneColonna(request.getIdcol());
        StatisticalVariableCls sum = new StatisticalVariableCls(request.getIdva());
        dcol.setVariabileType(sum);
        try {
            dcol = datasetService.salvaColonna(dcol);
        } catch (Exception e) {
            //TODO: Inviare a notificatione service
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(dcol);
    }
}
