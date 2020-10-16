/**
 * Copyright 2019 ISTAT
 *
 * Licensed under the EUPL, Version 1.1 or – as soon they will be approved by
 * the European Commission - subsequent versions of the EUPL (the "Licence");
 * You may not use this work except in compliance with the Licence. You may
 * obtain a copy of the Licence at:
 *
 * http://ec.europa.eu/idabc/eupl5
 *
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
package it.istat.is2.worksession.converter;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONException;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Converter
public class ListToStringConverter implements AttributeConverter<List<String>, String> {

	@Override
	public String convertToDatabaseColumn(List<String> data) {
		String value = null;

		if(data!=null) {
		JSONArray allDataArray = new JSONArray();

		for (int index = 0; index < data.size(); index++) {

			allDataArray.put(data.get(index) != null ? data.get(index) : "");
		}

		value = allDataArray.toString();
		}
		
		return value;
	}

	@Override
	public List<String> convertToEntityAttribute(String data) {
		List<String> listValue = new ArrayList<String>();
		try {
			if(data!=null) {
			JSONArray jsonArray = new JSONArray(data);
			for (int i = 0; i < jsonArray.length(); i++) {
		  	listValue.add(jsonArray.get(i).toString());
				}
			}
		} catch (JSONException e) {

			log.error("{}", e);
			log.error(data);
		}
		return listValue;
	}

}
