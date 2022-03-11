package com.viettel.etc.controllers;
import com.viettel.etc.dto.MedicalHealthcareHistoriesDTO;
import com.viettel.etc.services.MedicalHealthcareHistoriesService;
import com.viettel.etc.utils.ErrorApp;
import com.viettel.etc.utils.FnCommon;
import com.viettel.etc.utils.TeleCareException;
import com.viettel.etc.xlibrary.core.constants.FunctionCommon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;
import com.viettel.etc.controllers.MedicalHealthcareHistoriesController;
import org.hamcrest.MatcherAssert;
import com.viettel.etc.utils.ErrorApp;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import com.viettel.etc.utils.TeleCareException;
import org.hamcrest.MatcherAssert;
import static org.hamcrest.MatcherAssert.assertThat;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.extension.ExtendWith;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

@ExtendWith(MockitoExtension.class)
class MedicalHealthcareHistoriesControllerTest {

	private MockMvc mvc;
	@Mock
	private MedicalHealthcareHistoriesService medicalHealthcareHistoriesService;
	@InjectMocks 
	MedicalHealthcareHistoriesController MedicalHealthcareHistoriesController;

	@BeforeEach
	void setUp() {
		mvc = MockMvcBuilders.standaloneSetup(MedicalHealthcareHistoriesController).build();
		JacksonTester.initFields(this, new ObjectMapper());
	}

	@Test
	void getHealthcareDrugs1() throws Exception {
		Authentication authentication = null;
		MedicalHealthcareHistoriesDTO dataParams = new MedicalHealthcareHistoriesDTO();
		Integer patientId = 0;

		//mock method
		ResultSelectEntity resultData1 = null;
		when(medicalHealthcareHistoriesService.getHealthcareDrugs(Mockito.any())).thenReturn(resultData1);
		MockHttpServletResponse responseActual = mvc.perform(
			get("/"+patientId+"/healthcare-drugs")
			.accept(MediaType.APPLICATION_JSON)
			.contentType(MediaType.APPLICATION_JSON))
			.andReturn().getResponse();

		// assert result
		assertThat(responseActual.getStatus(), Matchers.equalTo(HttpStatus.OK.value()));
	}

	@Test
	void getHealthcareDrugs1ThrowException3() throws Exception {
		Authentication authentication = null;
		MedicalHealthcareHistoriesDTO dataParams = new MedicalHealthcareHistoriesDTO();
		Integer patientId = 0;

		//mock method
		ResultSelectEntity resultData1 = null;
		when(medicalHealthcareHistoriesService.getHealthcareDrugs(Mockito.any())).thenReturn(resultData1);
		MockHttpServletResponse responseActual = mvc.perform(
			get("/"+patientId+"/healthcare-drugs")
			.accept(MediaType.APPLICATION_JSON)
			.contentType(MediaType.APPLICATION_JSON))
			.andReturn().getResponse();

		// assert result
		assertThat(responseActual.getStatus(), Matchers.equalTo(HttpStatus.OK.value()));
	}

	@Test
	void getDetailHealthcareDrug0() throws Exception {
		Authentication authentication = null;
		MedicalHealthcareHistoriesDTO dataParams = new MedicalHealthcareHistoriesDTO();
		Integer patientId = 0;
		Integer historiesId = 0;

		//mock method
		Optional resultData1 = Optional.empty();
		when(medicalHealthcareHistoriesService.getDetailHealthcareDrug(Mockito.any())).thenReturn(resultData1);
		MockHttpServletResponse responseActual = mvc.perform(
			get("/"+patientId+"/healthcare-drugs/"+historiesId+"")
			.accept(MediaType.APPLICATION_JSON)
			.contentType(MediaType.APPLICATION_JSON))
			.andReturn().getResponse();

		// assert result
		assertThat(responseActual.getStatus(), Matchers.equalTo(HttpStatus.NOT_FOUND.value()));
	}

	@Test
	void getDetailHealthcareDrug0ThrowException4() throws Exception {
		Authentication authentication = null;
		MedicalHealthcareHistoriesDTO dataParams = new MedicalHealthcareHistoriesDTO();
		Integer patientId = 0;
		Integer historiesId = 0;

		//mock method
		Optional resultData1 = Optional.empty();
		when(medicalHealthcareHistoriesService.getDetailHealthcareDrug(Mockito.any())).thenReturn(resultData1);
		MockHttpServletResponse responseActual = mvc.perform(
			get("/"+patientId+"/healthcare-drugs/"+historiesId+"")
			.accept(MediaType.APPLICATION_JSON)
			.contentType(MediaType.APPLICATION_JSON))
			.andReturn().getResponse();

		// assert result
		assertThat(responseActual.getStatus(), Matchers.equalTo(HttpStatus.NOT_FOUND.value()));
	}

	@Test
	void getReExaminations0() throws Exception {
		Authentication authentication = null;
		MedicalHealthcareHistoriesDTO dataParams = new MedicalHealthcareHistoriesDTO();
		Integer patientId = 0;

		//mock method
		ResultSelectEntity resultData1 = null;
		when(medicalHealthcareHistoriesService.getReExaminations(Mockito.any(),Mockito.any())).thenReturn(resultData1);
		MockHttpServletResponse responseActual = mvc.perform(
				get("/"+patientId+"/re-examination")
						.accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON))
				.andReturn().getResponse();

		// assert result
		assertThat(responseActual.getStatus(), Matchers.equalTo(HttpStatus.OK.value()));
	}

	@Test
	void getReExaminations0ThrowException3() throws Exception {
		Authentication authentication = null;
		MedicalHealthcareHistoriesDTO dataParams = new MedicalHealthcareHistoriesDTO();
		Integer patientId = 0;

		//mock method
		ResultSelectEntity resultData1 = null;
		when(medicalHealthcareHistoriesService.getReExaminations(Mockito.any(),Mockito.any())).thenReturn(resultData1);
		MockHttpServletResponse responseActual = mvc.perform(
				get("/"+patientId+"/re-examination")
						.accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON))
				.andReturn().getResponse();

		// assert result
		assertThat(responseActual.getStatus(), Matchers.equalTo(HttpStatus.OK.value()));
	}
}