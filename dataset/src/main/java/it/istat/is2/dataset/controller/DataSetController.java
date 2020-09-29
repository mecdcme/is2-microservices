package it.istat.is2.dataset.controller;

import it.istat.is2.dataset.domain.DatasetColumn;
import it.istat.is2.dataset.domain.DatasetFile;
import it.istat.is2.dataset.domain.StatisticalVariableCls;
import it.istat.is2.dataset.dto.DataSetDTO;
import it.istat.is2.dataset.dto.DatasetColumnDTO;
import it.istat.is2.dataset.dto.DatasetDxDTO;
import it.istat.is2.dataset.dto.MetadataDatasetDTO;
import it.istat.is2.dataset.request.LoadInputDataRequest;
import it.istat.is2.dataset.request.LoadTableRequest;
import it.istat.is2.dataset.request.SetVariabileSumRequest;
import it.istat.is2.dataset.service.DatasetService;
import it.istat.is2.dataset.utils.FileHandler;
import it.istat.is2.dataset.utils.Utility;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
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
    public ResponseEntity<List<DatasetColumnDTO>> loadDataSetColonnaSql(@PathVariable("dfile") Long dfile,
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

        HashMap<String, String> parameters = parseParameters(parametri);
        String dtb = datasetService.loadDatasetValori(dfile, length, start, draw, parameters, nameColumnToOrder, dirColumnOrder);

        return ResponseEntity.ok(dtb);
    }



    @PostMapping(value = "/setvariabilesum")
    public ResponseEntity<?> setVarSum(@RequestBody SetVariabileSumRequest request)  {

        DatasetColumn dcol = datasetService.findOneColonna(request.getIdcol());
        StatisticalVariableCls sum = new StatisticalVariableCls(request.getIdva());
        dcol.setVariabileType(sum);
        dcol = datasetService.salvaColonna(dcol);

        return ResponseEntity.status(HttpStatus.CREATED).body(dcol);
    }

    @GetMapping(value = "/download/dataset/{tipoFile}/{dfile}")
    public ResponseEntity<Resource> downloadWorkset(@PathVariable("tipoFile") String tipoFile, @PathVariable("dfile") Long dfile, HttpServletResponse response) throws IOException {

        String fileName = "";
        String contentType = "";
        switch (tipoFile) {
            case "pdf":
                fileName = "dataset.pdf";
                contentType = "application/pdf";
                break;
            case "excel":
                fileName = "dataset.xlsx";
                contentType = "application/vnd.ms-excel";
                break;
            default:
                fileName = "dataset.csv";
                contentType = "text/csv";
                break;
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        headers.set(HttpHeaders.CONTENT_TYPE, contentType);
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        PrintWriter pw = new PrintWriter(byteStream);
        Map<String, List<String>> dataMap = datasetService.loadDatasetValori(dfile);
        Utility.writeObjectToCSV(pw, dataMap);


        InputStreamResource resource = new InputStreamResource( new ByteArrayInputStream(byteStream.toByteArray()));

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(byteStream.size())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

    @PutMapping(value = "/updaterowlist")
    public ResponseEntity<DatasetColumnDTO> updateOrdineRighe(@RequestParam("ordineIds") String ordineIds) throws Exception {

        StringTokenizer stringTokenizerElements = new StringTokenizer(ordineIds, "|");
        String element = null;
        String ordine = null;
        String idcol = null;
        DatasetColumn datasetCol =null;
        while (stringTokenizerElements.hasMoreElements()) {
            element = stringTokenizerElements.nextElement().toString();
            StringTokenizer stringTokenizerValues = new StringTokenizer(element, "=");
            while (stringTokenizerValues.hasMoreElements()) {
                ordine = stringTokenizerValues.nextElement().toString();
                idcol = stringTokenizerValues.nextElement().toString();
            }
            Long idc = Long.parseLong(idcol);
            Short ordineC = Short.parseShort(ordine);
            datasetCol = datasetService.findOneColonna(idc);

            datasetCol.setOrderCode(ordineC);
            datasetService.salvaColonna(datasetCol);
        }

        return ResponseEntity.ok(new DatasetColumnDTO.Builder().fromEntity(datasetCol).build());
    }

    @GetMapping(value = "tables/{db}")
    public ResponseEntity<List<String>> getTablesDB(@PathVariable("db") String db) {
        return ResponseEntity.ok(datasetService.findTablesDB(db));
    }

    @GetMapping(value = "fields/{db}/{table}")
    public ResponseEntity<List<String>> getFieldsTableDB(@PathVariable("db") String db, @PathVariable("table") String table) {
        return ResponseEntity.ok(datasetService.findFieldsTableDB(db, table));
    }

    @GetMapping("getDatasetDx/{idfile}")
    public ResponseEntity<DatasetDxDTO> getDatasetDx(@PathVariable("idfile") Long idfile) {

        List<String> columns = datasetService.getDatasetColumns(idfile);
        List<Object[]> data = datasetService.getDataset(idfile);

        int offset = 2;

        List<Map<String, String>> dataDx = new ArrayList<>();
        for (Object[] row : data) {
            Map<String, String> rowDx = new LinkedHashMap<>();
            for (int i = 0; i < columns.size(); i++) {
                rowDx.put(columns.get(i), String.valueOf(row[i + offset]));
            }
            dataDx.add(rowDx);
        }
        return ResponseEntity.ok(DatasetDxDTO.builder().columns(columns).data(dataDx).build());
    }

    @GetMapping("/viewDataset/{idfile}/{idWorkSession}")
    public ResponseEntity<DataSetDTO> caricafile(@PathVariable("idfile") Long idfile, @PathVariable("idWorkSession") Long idWorkSession) {

        DatasetFile dfile = datasetService.findDataSetFile(idfile);
        List<DatasetColumn> colonne = datasetService.findAllNameColum(idfile);
        List<StatisticalVariableCls> variabiliSum = datasetService.findAllVariabiliSum();
        Integer numRighe = dfile.getTotalRows();

        List<DatasetFile> files = null;
        if (idWorkSession != null) {
            files = datasetService.findDatasetFilesByIdWorkSession(idWorkSession);
        }

        var builder = new DataSetDTO.Builder();

        builder.colonneFromEntity(colonne);
        builder.idfile(idfile);
        builder.variabiliSumFromEntity(variabiliSum);
        builder.dfileFromEntity(dfile);
        builder.numRighe(numRighe);
        builder.filesFromEntity(files);

        return ResponseEntity.ok(builder.build());
    }

    @GetMapping("/metadatiDataset/{idfile}")
    public ResponseEntity<MetadataDatasetDTO> caricaMetadati(Model model, @PathVariable("idfile") Long idfile) {

        DatasetFile dfile = datasetService.findDataSetFile(idfile);

        List<DatasetColumn> colonne = datasetService.findAllNameColum(idfile);
        List<StatisticalVariableCls> variabiliSum = datasetService.findAllVariabiliSum();

        var builder = new MetadataDatasetDTO.Builder();

        builder.colonneFromEntity(colonne);
        builder.idfile(idfile);
        builder.variabiliSumFromEntity(variabiliSum);
        builder.dfileFromEntity(dfile);

        return ResponseEntity.ok(builder.build());
    }

    @PutMapping(value = "/associaVarSum")
    public ResponseEntity<MetadataDatasetDTO> caricaMetadati(String idfile, String idvar, String idsum) {

        DatasetFile dfile = datasetService.findDataSetFile(Long.valueOf(idfile));
        DatasetColumn dcol = datasetService.findOneColonna(Long.parseLong(idvar));
        StatisticalVariableCls sum = new StatisticalVariableCls(Integer.parseInt(idsum));

        dcol.setVariabileType(sum);

        datasetService.salvaColonna(dcol);

        List<DatasetColumn> colonne = datasetService.findAllNameColum(Long.parseLong(idfile));
        List<StatisticalVariableCls> variabiliSum = datasetService.findAllVariabiliSum();

        var builder = new MetadataDatasetDTO.Builder();

        builder.colonneFromEntity(colonne);
        builder.idfile(Long.parseLong(idfile));
        builder.variabiliSumFromEntity(variabiliSum);
        builder.dfileFromEntity(dfile);

        return ResponseEntity.ok(builder.build());
    }

    @PostMapping(value = "/loadInputData")
    public ResponseEntity<Boolean> loadInputData(@RequestBody LoadInputDataRequest form) throws Exception {

        String labelFile  = form.getLabelFile();
        Long tipoDato     = form.getFileType();
        String separatore = form.getDelimiter();
        String idsessione = form.getIdsessione();

        File f = FileHandler.convertMultipartFileToFile(form.getFileName());
        String pathTmpFile = f.getAbsolutePath().replace("\\", "/");

        var valoriHeaderNum = FileHandler.getCampiHeaderNumIndex(pathTmpFile, separatore.toCharArray()[0]);
        var campiL = FileHandler.getArrayListFromCsv2(pathTmpFile, form.getNumeroCampi(), separatore.toCharArray()[0], valoriHeaderNum);

        datasetService.save(campiL, valoriHeaderNum, labelFile, tipoDato, separatore, form.getDescrizione(), idsessione);

        return ResponseEntity.status(HttpStatus.CREATED).body(Boolean.TRUE);
    }

    @DeleteMapping(value = "/{datasetId}")
    public ResponseEntity<Boolean> deleteDataset(@PathVariable("datasetId") Long idDataset) {

        datasetService.deleteDataset(idDataset);

        return ResponseEntity.ok(Boolean.TRUE);
    }

    @PostMapping(value = "/dataset/loadtable")
    public ResponseEntity<Boolean> loadDatasetFromTable(@RequestBody LoadTableRequest request) {
        datasetService.loadDatasetFromTable(request.getIdsessione(), request.getDbschema(), request.getTablename(), request.getFields());
        return ResponseEntity.status(HttpStatus.CREATED).body(Boolean.TRUE);
    }




    private HashMap<String, String> parseParameters(String parametri) {
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
        return parameters;
    }
}
