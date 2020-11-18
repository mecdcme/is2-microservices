package it.istat.is2.design.translators;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import it.istat.is2.commons.dto.design.domain.BusinessFunctionDTO;
import it.istat.is2.commons.dto.design.domain.BusinessProcessDTO;
import it.istat.is2.commons.dto.design.domain.BusinessServiceDTO;
import it.istat.is2.commons.dto.design.domain.GsbpmProcessDTO;
import it.istat.is2.commons.dto.design.domain.ProcessStepDTO;
import it.istat.is2.commons.dto.worksession.domain.WorkSessionDTO;
import it.istat.is2.design.domain.BusinessFunction;
import it.istat.is2.design.domain.BusinessProcess;
import it.istat.is2.design.domain.BusinessService;
import it.istat.is2.design.domain.GsbpmProcess;
import it.istat.is2.design.domain.ProcessStep;
import it.istat.is2.design.domain.WorkSession;

@Component
public class Translators {

	public static WorkSessionDTO translate(final WorkSession workSession) {

		final ModelMapper modelMapper = new ModelMapper();
		final WorkSessionDTO workSessionDTO = modelMapper.map(workSession, WorkSessionDTO.class);

		return workSessionDTO;

	}

	public static List<WorkSessionDTO> translate(final List<WorkSession> workSessions) {
		final ModelMapper modelMapper = new ModelMapper();
		return mapList(workSessions, WorkSessionDTO.class, modelMapper);

	}

	public static BusinessServiceDTO translate(BusinessService x) {

		final ModelMapper modelMapper = new ModelMapper();
		final BusinessServiceDTO dTO = modelMapper.map(x, BusinessServiceDTO.class);
		return dTO;
	}

	public static BusinessFunctionDTO translate(BusinessFunction x) {
		final ModelMapper modelMapper = new ModelMapper();
		final BusinessFunctionDTO dTO = modelMapper.map(x, BusinessFunctionDTO.class);
		return dTO;
	}

	public static GsbpmProcessDTO translate(GsbpmProcess x) {
		final ModelMapper modelMapper = new ModelMapper();
		
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		modelMapper.typeMap(GsbpmProcess.class, GsbpmProcessDTO.class)
				.addMappings(mapper -> mapper.skip(GsbpmProcessDTO::setGsbpmProcessParent))	.addMappings(mapper -> mapper.skip(GsbpmProcessDTO::setBusinessServices));
		final GsbpmProcessDTO dTO = modelMapper.map(x, GsbpmProcessDTO.class);
		return dTO;
	}

	public static BusinessProcessDTO translate(BusinessProcess x) {
		final ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		modelMapper.typeMap(BusinessProcess.class, BusinessProcessDTO.class)
				.addMappings(mapper -> mapper.skip(BusinessProcessDTO::setBusinessProcessParent));
		final BusinessProcessDTO dTO = modelMapper.map(x, BusinessProcessDTO.class);
		return dTO;
	}

	
	public static ProcessStepDTO translate(ProcessStep x) {
		final ModelMapper modelMapper = new ModelMapper();

		final ProcessStepDTO dTO = modelMapper.map(x, ProcessStepDTO.class);
		return dTO;
	}

	public static <S, T> List<T> mapList(List<S> source, Class<T> targetClass, final ModelMapper modelMapper) {

		return source.stream().map(element -> modelMapper.map(element, targetClass)).collect(Collectors.toList());
	}
}
