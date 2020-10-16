/**
 * Copyright 2019 ISTAT
 * <p>
 * Licensed under the EUPL, Version 1.1 or – as soon they will be approved by
 * the European Commission - subsequent versions of the EUPL (the "Licence");
 * You may not use this work except in compliance with the Licence. You may
 * obtain a copy of the Licence at:
 * <p>
 * http://ec.europa.eu/idabc/eupl5
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the Licence is distributed on an "AS IS" basis, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * Licence for the specific language governing permissions and limitations under
 * the Licence.
 *
 * @author Francesco Amato <framato @ istat.it>
 * @author Mauro Bruno <mbruno @ istat.it>
 * @author Paolo Francescangeli  <pafrance @ istat.it>
 * @author Renzo Iannacone <iannacone @ istat.it>
 * @author Stefano Macone <macone @ istat.it>
 * @version 1.0
 */
package it.istat.is2.worksession.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.istat.is2.commons.dto.worksession.domain.WorkSessionDTO;
import it.istat.is2.worksession.dao.UserDao;
import it.istat.is2.worksession.dao.WorkSessionDao;
import it.istat.is2.worksession.domain.BusinessFunction;
import it.istat.is2.worksession.domain.User;
import it.istat.is2.worksession.domain.WorkSession;
import it.istat.is2.worksession.translators.Translators;

@Service
public class WorkSessionService {

	@Autowired
	private WorkSessionDao sessioneDao;
	@Autowired
	private UserDao userDao;

	public WorkSession getSessione(Long id) {
		return sessioneDao.findById(id).orElse(null);
	}

	public List<WorkSession> getSessioneList(String username) {
		User user = userDao.findByEmail(username);
		return sessioneDao.findByUserOrderByLastUpdateDesc(user.getId());
	}

	public List<WorkSessionDTO> getAllWSessioneList() {

		return sessioneDao.findAll().stream().map(x -> Translators.translate(x)).collect(Collectors.toList());
	}

	public List<WorkSessionDTO> getSessioneList(Long userId, Long busineFunctionId) {
		BusinessFunction businessFunction = new BusinessFunction(busineFunctionId);
		List<WorkSession> ret = sessioneDao.findByUserAndBusinessFunctionOrderByLastUpdateDesc(userId,
				businessFunction);
		return ret.stream().map(x -> Translators.translate(x)).collect(Collectors.toList());
	}

	public WorkSession newWorkSession(Long userId, String descr, String name, Long idBusinessFunction) {
		WorkSession sl = new WorkSession();
		BusinessFunction businessFunction = new BusinessFunction(idBusinessFunction);
		sl.setLastUpdate(new Date());
		sl.setDescr(descr);
		sl.setBusinessFunction(businessFunction);
		sl.setName(name);
		sl.setUser(userId);
		return sessioneDao.save(sl);
	}

	public boolean deleteWorkSession(Long idsessione) {
		sessioneDao.deleteById(idsessione);
		return true;
	}

}
