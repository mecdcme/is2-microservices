package it.istat.is2.dataset.services;

import it.istat.is2.dataset.domain.DatasetColumn;
import it.istat.is2.dataset.domain.StatisticalVariableCls;
import it.istat.is2.dataset.request.LoadInputDataRequest;
import it.istat.is2.dataset.request.LoadTableRequest;
import it.istat.is2.dataset.request.SetVariabileSumRequest;
import it.istat.is2.dataset.service.DatasetService;
import it.istat.is2.dataset.utils.FileHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
@Slf4j
public class MessageListener {


    private final DatasetService datasetService;

    @Autowired
    public MessageListener(DatasetService datasetService) {
        this.datasetService = datasetService;
    }

    @RabbitListener(queues = "${queue.set_variable}")
    public void setVariable(SetVariabileSumRequest request) {
        log.info("SetVariable : {}", request);

        DatasetColumn dcol = datasetService.findOneColonna(request.getIdcol());
        StatisticalVariableCls sum = new StatisticalVariableCls(request.getIdva());
        dcol.setVariabileType(sum);
        datasetService.salvaColonna(dcol);

        log.info("Done");
    }

    @RabbitListener(queues = "${queue.loadInputData}")
    public void loadInputData(LoadInputDataRequest request) throws Exception {
        log.info("LoadInputDataRequest : {}", request);

        String labelFile  = request.getLabelFile();
        Long tipoDato     = request.getFileType();
        String separatore = request.getDelimiter();
        String idsessione = request.getIdsessione();

        File f = FileHandler.convertMultipartFileToFile(request.getFileName());
        String pathTmpFile = f.getAbsolutePath().replace("\\", "/");

        var valoriHeaderNum = FileHandler.getCampiHeaderNumIndex(pathTmpFile, separatore.toCharArray()[0]);
        var campiL = FileHandler.getArrayListFromCsv2(pathTmpFile, request.getNumeroCampi(), separatore.toCharArray()[0], valoriHeaderNum);

        datasetService.save(campiL, valoriHeaderNum, labelFile, tipoDato, separatore, request.getDescrizione(), idsessione);

        log.info("Done");
    }

    @RabbitListener(queues = "${queue.loadTable}")
    public void loadTable(LoadTableRequest request) {
        log.info("loadTable : {}", request);
        datasetService.loadDatasetFromTable(request.getIdsessione(), request.getDbschema(), request.getTablename(), request.getFields());
        log.info("Done");
    }

    @RabbitListener(queues = "${queue.deleteDataset}")
    public void deleteDataset(Long idDataset) {
        log.info("deleteDataset : {}", idDataset);
        datasetService.deleteDataset(idDataset);
        log.info("Done");
    }
}
