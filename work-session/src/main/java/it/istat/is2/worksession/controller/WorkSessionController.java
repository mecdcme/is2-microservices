package it.istat.is2.worksession.controller;

import java.util.List;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.discovery.EurekaClient;

import it.istat.is2.commons.dto.worksession.domain.WorkSessionDTO;
import it.istat.is2.commons.dto.worksession.request.CreateWorkSessionRequest;
import it.istat.is2.worksession.service.WorkSessionService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/worksession")
public class WorkSessionController {

	private static final String Q_CREATE = "worksession_create";
	private static final String Q_DELETE = "worksession_delete";

	private final RabbitTemplate rabbitTemplate;

	private WorkSessionService workSessionService;

	
	private RestTemplate restTemplate;

	
	private EurekaClient eurekaClient;

	@Value("${is2-services.dataset-id}")
	private String datasetServiceId;

	@Autowired
	public WorkSessionController(WorkSessionService workSessionService, RabbitTemplate rabbitTemplate, RestTemplate restTemplate,EurekaClient eurekaClient) {
		this.workSessionService = workSessionService;
		this.rabbitTemplate = rabbitTemplate;
	}
	
	@GetMapping(value = "/")
	public ResponseEntity<List<WorkSessionDTO>> getAllWSession() {

		return ResponseEntity.ok(workSessionService.getAllWSessioneList());

	}

	@GetMapping(value = "/users/{user}/business-functions/{businessFunction}")
	public ResponseEntity<List<WorkSessionDTO>> getSession(@PathVariable("user") Long user,
			@PathVariable("businessFunction") Long businessFunction) {

		return ResponseEntity.ok(workSessionService.getSessioneList(user, businessFunction));

	}

	@PostMapping
	public ResponseEntity<Boolean> create(@RequestBody CreateWorkSessionRequest request) {
		rabbitTemplate.convertAndSend(Q_CREATE, request);
		return ResponseEntity.ok(Boolean.TRUE);
	}

	@DeleteMapping("/{workSessionId}")
	public ResponseEntity<Void> deleteWorkSession(@PathVariable("workSessionId") Long workSessionId) {

		rabbitTemplate.convertAndSend(Q_DELETE, workSessionId);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/public/test")
	public ResponseEntity<String> testPublic() {
		return ResponseEntity.ok("public method test");
	}
	/*
	 * @PostMapping(value = "/sessione/nuovasessione") public String
	 * nuovaSessione(HttpSession session, RedirectAttributes ra, Model model,
	 * 
	 * @AuthenticationPrincipal Principal user, @RequestParam("descrizione") String
	 * descrizione,
	 * 
	 * @RequestParam("nome") String nome, @RequestParam("idBusinessFunction") Long
	 * idBusinessFunction) { notificationService.removeAllMessages();
	 * NotificationMessage message; try { WorkSession workSession =
	 * workSessionService.newWorkSession(user.getName(), descrizione, nome,
	 * idBusinessFunction); message = new
	 * NotificationMessage(NotificationMessage.TYPE_SUCCESS,
	 * messages.getMessage("session.created.success", new
	 * Object[]{workSession.getName()}, LocaleContextHolder.getLocale()));
	 * 
	 * } catch (Exception e) { message = new
	 * NotificationMessage(NotificationMessage.TYPE_ERROR,
	 * messages.getMessage("session.created.error", null,
	 * LocaleContextHolder.getLocale()), e.getMessage()); }
	 * ra.addFlashAttribute("message", message); return
	 * "redirect:/sessione/mostraSessioni/" + idBusinessFunction; }
	 * 
	 * @GetMapping(value = "/sessione/apriseselab/{idSessione}/{idElaborazione}")
	 * public String apriSesElab(HttpSession session, Model
	 * model, @AuthenticationPrincipal Principal user,
	 * 
	 * @PathVariable("idSessione") Long idSessione, @PathVariable("idElaborazione")
	 * Long idElaborazione) {
	 * 
	 * WorkSession workSession = workSessionService.getSessione(idSessione); if
	 * (workSession.getDatasetFiles() != null) {
	 * session.setAttribute(IS2Const.SESSION_DATASET, true); }
	 * session.setAttribute(IS2Const.SESSION_BEAN, new SessionBean(idSessione,
	 * workSession.getName()));
	 * 
	 * return "redirect:/ws/home/" + idElaborazione; }
	 * 
	 * @GetMapping(value = "/sessione/apri/{id}") public String
	 * apriSessione(HttpSession session, Model model, @PathVariable("id") Long id) {
	 * 
	 * // notificationService.removeAllMessages();
	 * 
	 * List<Log> logs = logService.findByIdSessione(id);
	 * 
	 * WorkSession workSession = workSessionService.getSessione(id); SessionBean
	 * sessionBean;
	 * 
	 * List<DataProcessing> listaElaborazioni =
	 * dataProcessingService.getDataProcessingList(workSession);
	 * List<BusinessProcess> processesList = businessProcessService
	 * .findBProcessByIdFunction(workSession.getBusinessFunction().getId());
	 * 
	 * sessionBean = (SessionBean) session.getAttribute(IS2Const.SESSION_BEAN);
	 * sessionBean.setId(id); sessionBean.setName(workSession.getName());
	 * 
	 * List<String> files; List<String> rulesets;
	 * 
	 * if (workSession.getBusinessFunction().getViewDataType() .contains(new
	 * ViewDataType(IS2Const.VIEW_DATATYPE_DATASET))) { files = new
	 * ArrayList<String>(); if (workSession.getDatasetFiles() != null) {
	 * session.setAttribute(IS2Const.SESSION_DATASET, true); for (DatasetFile
	 * datasetFile : workSession.getDatasetFiles()) {
	 * files.add(datasetFile.getFileName()); } } sessionBean.setFile(files); } if
	 * (workSession.getBusinessFunction().getViewDataType() .contains(new
	 * ViewDataType(IS2Const.VIEW_DATATYPE_RULESET))) { rulesets = new
	 * ArrayList<String>(); if (workSession.getRuleSets() != null) { for (Ruleset
	 * ruleset : workSession.getRuleSets()) { rulesets.add(ruleset.getFileName()); }
	 * } sessionBean.setRuleset(rulesets); }
	 * 
	 * session.setAttribute(IS2Const.SESSION_BEAN, sessionBean);
	 * 
	 * model.addAttribute("processesList", processesList);
	 * model.addAttribute("listaElaborazioni", listaElaborazioni);
	 * model.addAttribute("logs", logs);
	 * 
	 * return "worksession/home"; }
	 * 
	 * @PostMapping(value = "/sessione/nuovoworkingset") public String
	 * nuovoWorkingSet(HttpSession session, RedirectAttributes ra, Model model,
	 * 
	 * @AuthenticationPrincipal Principal user,
	 * 
	 * @ModelAttribute("elaborazioneFormBean") ElaborazioneFormBean form) {
	 * notificationService.removeAllMessages();
	 * 
	 * session.setAttribute(IS2Const.WORKINGSET, "workingset"); WorkSession
	 * workSession = workSessionService.getSessione(form.getIdsessione()); try {
	 * DataProcessing elaborazione = new DataProcessing();
	 * elaborazione.setWorkSession(workSession);
	 * elaborazione.setDescr(form.getDescrizione());
	 * elaborazione.setName(form.getNome()); elaborazione.setLastUpdate(new Date());
	 * elaborazione.setBusinessProcess(businessProcessService.findBProcessById(form.
	 * getIdfunzione()));
	 * 
	 * dataProcessingService.saveDataProcessing(elaborazione);
	 * 
	 * notificationService.addInfoMessage(
	 * messages.getMessage("creation.process.success", null,
	 * LocaleContextHolder.getLocale()));
	 * 
	 * logService.save("Elaborazione " + elaborazione.getName() +
	 * " creata con successo");
	 * 
	 * } catch (Exception e) {
	 * 
	 * notificationService.addErrorMessage(
	 * messages.getMessage("process.create.error", null,
	 * LocaleContextHolder.getLocale()), e.getMessage()); }
	 * 
	 * return "redirect:/sessione/apri/" + workSession.getId(); }
	 * 
	 * @GetMapping(value = "/sessione/workingset/{id}") public String
	 * workingSet(HttpSession session, Model model, @PathVariable("id") Long id) {
	 * session.setAttribute(IS2Const.WORKINGSET, "workingset"); return
	 * "elaborazione/nuovo_ws"; }
	 * 
	 * @GetMapping(value = "/sessione/chiudisessione/{idBusinessFunction}") public
	 * String chiudiSessione(HttpSession
	 * session, @PathVariable("idBusinessFunction") Long idBusinessFunction) {
	 * session.removeAttribute(IS2Const.SESSION_BEAN);
	 * session.removeAttribute(IS2Const.SESSION_DATASET);
	 * session.removeAttribute(IS2Const.SESSION_DATAPROCESSING); return
	 * "redirect:/sessione/mostraSessioni/" + idBusinessFunction; }
	 * 
	 * @GetMapping(value = "/sessione/elimina/{idsessione}") public String
	 * eliminaWS(HttpSession session, Model model, RedirectAttributes ra,
	 * 
	 * @PathVariable("idsessione") Long idsessione) {
	 * notificationService.removeAllMessages(); NotificationMessage message;
	 * WorkSession workSession = workSessionService.getSessione(idsessione); if
	 * (workSessionService.deleteWorkSession(idsessione)) { message = new
	 * NotificationMessage(NotificationMessage.TYPE_SUCCESS,
	 * messages.getMessage("session.removed.success", new
	 * Object[]{workSession.getName()}, LocaleContextHolder.getLocale())); } else {
	 * message = new NotificationMessage(NotificationMessage.TYPE_ERROR,
	 * messages.getMessage("session.removed.success", null,
	 * LocaleContextHolder.getLocale())); } SessionBean sessionBean = (SessionBean)
	 * session.getAttribute(IS2Const.SESSION_BEAN);
	 * session.removeAttribute(IS2Const.SESSION_DATAPROCESSING);
	 * ra.addFlashAttribute("message", message); return
	 * "redirect:/sessione/mostraSessioni/" +
	 * sessionBean.getBusinessFunction().getId(); }
	 */
}
