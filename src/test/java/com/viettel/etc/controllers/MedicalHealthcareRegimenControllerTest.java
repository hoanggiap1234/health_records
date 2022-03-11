package com.viettel.etc.controllers;
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
class MedicalHealthcareRegimenControllerTest {

	private MockMvc mvc;
	@Mock
	private MedicalHealthcareRegimenService medicalHealthcareRegimenService;
	@Mock
	private MedicalHealthcareRegimensServiceJPA medicalHealthcareRegimenServiceJPA;
	@InjectMocks 
	void setUp() {
		mvc = MockMvcBuilders.standaloneSetup(MedicalHealthcareRegimenController).build();
		JacksonTester.initFields(this, new ObjectMapper());
	}

	@Test
	void getHealthcareRegimens1() throws Exception {
		Authentication authentication = null;
		Integer patientId = 0;
		MedicalHealthcareRegimenDTO dataParams = new MedicalHealthcareRegimenDTO();

		//mock method
		ResultSelectEntity resultData1 = new ResultSelectEntity();
		when(medicalHealthcareRegimenService.getHealthcareRegimens(Mockito.any(),Mockito.any())).thenReturn(resultData1);
		MockHttpServletResponse responseActual = mvc.perform(
			get("/"+patientId+"/healthcare-regimens")
			.accept(MediaType.APPLICATION_JSON)
			.contentType(MediaType.APPLICATION_JSON))
			.andReturn().getResponse();

		// assert result
		assertThat(responseActual.getStatus(), Matchers.equalTo(HttpStatus.OK.value()));
	}

	@Test
	void getHealthcareRegimens1ThrowException3() throws Exception {
		Authentication authentication = null;
		Integer patientId = 0;
		MedicalHealthcareRegimenDTO dataParams = new MedicalHealthcareRegimenDTO();

		//mock method
		when(medicalHealthcareRegimenService.getHealthcareRegimens(Mockito.any(),Mockito.any())).thenThrow(new TeleCareException(ErrorApp.ERROR_INPUTPARAMS));
		MockHttpServletResponse responseActual = mvc.perform(
			get("/"+patientId+"/healthcare-regimens")
			.accept(MediaType.APPLICATION_JSON)
			.contentType(MediaType.APPLICATION_JSON))
			.andReturn().getResponse();

		// assert result
		assertThat(responseActual.getStatus(), Matchers.equalTo(HttpStatus.OK.value()));
	}

	@Test
	void createHealthcareRegimens0() throws Exception {
		Authentication authentication = null;
		Integer patientId = 0;
		RegimenRequestDTO dataParams = new RegimenRequestDTO();

		//mock method
		ResultSelectEntity resultData1 = new ResultSelectEntity();
		when(medicalHealthcareRegimenServiceJPA.createHealthcareRegimens(Mockito.any(),Mockito.any())).thenReturn(resultData1);
		MockHttpServletResponse responseActual = mvc.perform(
			post("/"+patientId+"/healthcare-regimens")
			.accept(MediaType.APPLICATION_JSON)
			.content((new ObjectMapper()).writeValueAsString(dataParams))
			.contentType(MediaType.APPLICATION_JSON))
			.andReturn().getResponse();

		// assert result
		assertThat(responseActual.getStatus(), Matchers.equalTo(HttpStatus.OK.value()));
	}

	@Test
	void createHealthcareRegimens0ThrowException3() throws Exception {
		Authentication authentication = null;
		Integer patientId = 0;
		RegimenRequestDTO dataParams = new RegimenRequestDTO();

		//mock method
		when(medicalHealthcareRegimenServiceJPA.createHealthcareRegimens(Mockito.any(),Mockito.any())).thenThrow(new TeleCareException(ErrorApp.ERROR_INPUTPARAMS));
		MockHttpServletResponse responseActual = mvc.perform(
			post("/"+patientId+"/healthcare-regimens")
			.accept(MediaType.APPLICATION_JSON)
			.content((new ObjectMapper()).writeValueAsString(dataParams))
			.contentType(MediaType.APPLICATION_JSON))
			.andReturn().getResponse();

		// assert result
		assertThat(responseActual.getStatus(), Matchers.equalTo(HttpStatus.OK.value()));
	}

	@Test
	void updateHealthcareRegimens2() throws Exception {
		Authentication authentication = null;
		Integer patientId = 0;
		Integer patientRegimenId = 0;
		MedicalHealthcareRegimenDTO dataParams = new MedicalHealthcareRegimenDTO();

		//mock method
		ResultSelectEntity resultData1 = new ResultSelectEntity();
		when(medicalHealthcareRegimenServiceJPA.updateHealthcareRegimens(Mockito.any(),Mockito.any())).thenReturn(resultData1);
		MockHttpServletResponse responseActual = mvc.perform(
			put("/"+patientId+"/healthcare-regimens/"+patientRegimenId+"")
			.accept(MediaType.APPLICATION_JSON)
			.content((new ObjectMapper()).writeValueAsString(dataParams))
			.contentType(MediaType.APPLICATION_JSON))
			.andReturn().getResponse();

		// assert result
		assertThat(responseActual.getStatus(), Matchers.equalTo(HttpStatus.OK.value()));
	}

	@Test
	void updateHealthcareRegimens2ThrowException4() throws Exception {
		Authentication authentication = null;
		Integer patientId = 0;
		Integer patientRegimenId = 0;
		MedicalHealthcareRegimenDTO dataParams = new MedicalHealthcareRegimenDTO();

		//mock method
		when(medicalHealthcareRegimenServiceJPA.updateHealthcareRegimens(Mockito.any(),Mockito.any())).thenThrow(new TeleCareException(ErrorApp.ERROR_INPUTPARAMS));
		MockHttpServletResponse responseActual = mvc.perform(
			put("/"+patientId+"/healthcare-regimens/"+patientRegimenId+"")
			.accept(MediaType.APPLICATION_JSON)
			.content((new ObjectMapper()).writeValueAsString(dataParams))
			.contentType(MediaType.APPLICATION_JSON))
			.andReturn().getResponse();

		// assert result
		assertThat(responseActual.getStatus(), Matchers.equalTo(HttpStatus.OK.value()));
	}

	@Test
	void deletePatientRegimen3() throws Exception {
		Authentication authentication = null;
		Integer patientId = 0;
		Integer patientRegimenId = 0;
		MedicalHealthcareRegimenDTO dataParams = new MedicalHealthcareRegimenDTO();

		//mock method
		ResultSelectEntity resultData1 = new ResultSelectEntity();
		when(medicalHealthcareRegimenServiceJPA.deletePatientRegimen(Mockito.any(),Mockito.any())).thenReturn(resultData1);
		MockHttpServletResponse responseActual = mvc.perform(
			delete("/"+patientId+"/healthcare-regimens/"+patientRegimenId+"")
			.accept(MediaType.APPLICATION_JSON)
			.contentType(MediaType.APPLICATION_JSON))
			.andReturn().getResponse();

		// assert result
		assertThat(responseActual.getStatus(), Matchers.equalTo(HttpStatus.OK.value()));
	}

	@Test
	void deletePatientRegimen3ThrowException4() throws Exception {
		Authentication authentication = null;
		Integer patientId = 0;
		Integer patientRegimenId = 0;
		MedicalHealthcareRegimenDTO dataParams = new MedicalHealthcareRegimenDTO();

		//mock method
		when(medicalHealthcareRegimenServiceJPA.deletePatientRegimen(Mockito.any(),Mockito.any())).thenThrow(new TeleCareException(ErrorApp.ERROR_INPUTPARAMS));
		MockHttpServletResponse responseActual = mvc.perform(
			delete("/"+patientId+"/healthcare-regimens/"+patientRegimenId+"")
			.accept(MediaType.APPLICATION_JSON)
			.contentType(MediaType.APPLICATION_JSON))
			.andReturn().getResponse();

		// assert result
		assertThat(responseActual.getStatus(), Matchers.equalTo(HttpStatus.OK.value()));
	}

}