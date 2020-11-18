package it.istat.is2.runtime.translators;

import org.modelmapper.ModelMapper;

import it.istat.is2.commons.dto.worksession.domain.WorkSessionDTO;
import it.istat.is2.runtime.domain.WorkSession;

public class Translators {

	public static WorkSessionDTO translate(final WorkSession workSession) {

		final ModelMapper modelMapper = new ModelMapper();
		final WorkSessionDTO workSessionDTO = modelMapper.map(workSession, WorkSessionDTO.class);

		return workSessionDTO;

	}
}
