package com.viettel.etc.controllers;import com.viettel.etc.dto.MedicalHealthcarePatientSummaryDTO;import com.viettel.etc.services.MedicalHealthcarePatientSummaryService;import com.viettel.etc.utils.ErrorApp;import com.viettel.etc.utils.FnCommon;import com.viettel.etc.utils.TeleCareException;import com.viettel.etc.xlibrary.core.constants.FunctionCommon;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.http.HttpStatus;import org.springframework.http.MediaType;import org.springframework.http.ResponseEntity;import org.springframework.security.core.Authentication;import org.springframework.security.core.annotation.AuthenticationPrincipal;import org.springframework.web.bind.annotation.*;import javax.validation.Valid;import com.viettel.etc.controllers.MedicalHealthcarePatientSummaryController;
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
class MedicalHealthcarePatientSummaryControllerTest {

	private MockMvc mvc;
	@Mock
	private MedicalHealthcarePatientSummaryService medicalHealthcarePatientSummaryService;
	@InjectMocks 	MedicalHealthcarePatientSummaryController MedicalHealthcarePatientSummaryController;	@BeforeEach
	void setUp() {
		mvc = MockMvcBuilders.standaloneSetup(MedicalHealthcarePatientSummaryController).build();
		JacksonTester.initFields(this, new ObjectMapper());
	}

	@Test
	void getPatientOverview0() throws Exception {
		Authentication authentication = null;
		Integer patientId = 0;
		MedicalHealthcarePatientSummaryDTO dataParams = new MedicalHealthcarePatientSummaryDTO();

		//mock method
		ResultSelectEntity resultData1 = new ResultSelectEntity();
		when(medicalHealthcarePatientSummaryService.getPatientOverview(Mockito.any(),Mockito.any())).thenReturn(resultData1);
		MockHttpServletResponse responseActual = mvc.perform(
			get("/"+patientId+"/overview")
			.accept(MediaType.APPLICATION_JSON)
			.contentType(MediaType.APPLICATION_JSON))
			.andReturn().getResponse();

		// assert result
		assertThat(responseActual.getStatus(), Matchers.equalTo(HttpStatus.OK.value()));
	}

	@Test
	void getPatientOverview0ThrowException3() throws Exception {
		Authentication authentication = null;
		Integer patientId = 0;
		MedicalHealthcarePatientSummaryDTO dataParams = new MedicalHealthcarePatientSummaryDTO();

		//mock method
		when(medicalHealthcarePatientSummaryService.getPatientOverview(Mockito.any(),Mockito.any())).thenThrow(new TeleCareException(ErrorApp.ERROR_INPUTPARAMS));
		MockHttpServletResponse responseActual = mvc.perform(
			get("/"+patientId+"/overview")
			.accept(MediaType.APPLICATION_JSON)
			.contentType(MediaType.APPLICATION_JSON))
			.andReturn().getResponse();

		// assert result
		assertThat(responseActual.getStatus(), Matchers.equalTo(HttpStatus.OK.value()));
	}

	@Test
	void createPatientSummary1() throws Exception {
		Authentication authentication = null;
		Integer patientId = 0;
		MedicalHealthcarePatientSummaryDTO dataParams = new MedicalHealthcarePatientSummaryDTO();
		// TODO sua cac tham so sau de khop voi dieu kien trong DTO

		//mock method
		ResultSelectEntity resultData1 = new ResultSelectEntity();
		when(medicalHealthcarePatientSummaryService.createPatientSummary(Mockito.any(),Mockito.any())).thenReturn(resultData1);
		MockHttpServletResponse responseActual = mvc.perform(
			post("/"+patientId+"/healthcare_summary")
			.accept(MediaType.APPLICATION_JSON)
			.content((new ObjectMapper()).writeValueAsString(dataParams))
			.contentType(MediaType.APPLICATION_JSON))
			.andReturn().getResponse();

		// assert result
		assertThat(responseActual.getStatus(), Matchers.equalTo(HttpStatus.OK.value()));
	}

	@Test
	void createPatientSummary1ThrowException3() throws Exception {
		Authentication authentication = null;
		Integer patientId = 0;
		MedicalHealthcarePatientSummaryDTO dataParams = new MedicalHealthcarePatientSummaryDTO();
		// TODO sua cac tham so sau de khop voi dieu kien trong DTO

		//mock method
		when(medicalHealthcarePatientSummaryService.createPatientSummary(Mockito.any(),Mockito.any())).thenThrow(new TeleCareException(ErrorApp.ERROR_INPUTPARAMS));
		MockHttpServletResponse responseActual = mvc.perform(
			post("/"+patientId+"/healthcare_summary")
			.accept(MediaType.APPLICATION_JSON)
			.content((new ObjectMapper()).writeValueAsString(dataParams))
			.contentType(MediaType.APPLICATION_JSON))
			.andReturn().getResponse();

		// assert result
		assertThat(responseActual.getStatus(), Matchers.equalTo(HttpStatus.OK.value()));
	}

}