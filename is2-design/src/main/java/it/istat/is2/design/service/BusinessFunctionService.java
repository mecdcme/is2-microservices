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
package it.istat.is2.design.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.istat.is2.commons.dto.design.domain.BusinessFunctionDTO;
import it.istat.is2.commons.request.business.BusinessServiceRequestForm;
import it.istat.is2.design.dao.BusinessFunctionDao;
import it.istat.is2.design.domain.BusinessFunction;
import it.istat.is2.design.exceptions.NoDataException;
import it.istat.is2.design.translators.Translators;

@Service
public class BusinessFunctionService {

	@Autowired
	BusinessFunctionDao businessFunctionDao;

	public List<BusinessFunction> findBFunctions() {
		return businessFunctionDao.findAll();
	}

	public BusinessFunctionDTO findBFunctionById(long idfunction) {

		if (!businessFunctionDao.findById(idfunction).isPresent())
			throw new NoDataException("Business Function no present");
		return Translators.translate(businessFunctionDao.findById(idfunction).get());
	}

	public BusinessFunction updateBFunction(BusinessFunction function) {

		return businessFunctionDao.save(function);
	}

	public BusinessFunction deleteBFunction(BusinessFunction funzione) {

		businessFunctionDao.delete(funzione);

		return funzione;
	}

	public void deleteBFunction(Long id) {

		businessFunctionDao.deleteById(id);

	}

	public BusinessFunction findBFunctionByName(String name) {

		return businessFunctionDao.findBFunctionByName(name);
	}

	public Long create(String jwt, BusinessServiceRequestForm request) {
		// TODO Auto-generated method stub
		return null;
	}

}
