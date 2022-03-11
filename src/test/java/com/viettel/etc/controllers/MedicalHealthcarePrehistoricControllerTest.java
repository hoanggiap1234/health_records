package com.viettel.etc.controllers;
import com.viettel.etc.dto.MedicalHealthcarePrehistoricDTO;
import com.viettel.etc.services.MedicalHealthcarePrehistoricService;
import com.viettel.etc.services.tables.MedicalHealthcarePrehistoricServiceJPA;
import com.viettel.etc.utils.FnCommon;
import com.viettel.etc.utils.TeleCareException;
import com.viettel.etc.xlibrary.core.constants.FunctionCommon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.viettel.etc.controllers.MedicalHealthcarePrehistoricController;
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
class MedicalHealthcarePrehistoricControllerTest {

	private MockMvc mvc;
	@Mock
	private MedicalHealthcarePrehistoricService medicalHealthcarePrehistoricService;
	@Mock
	private MedicalHealthcarePrehistoricServiceJPA medicalHealthcarePrehistoricServiceJPA;
	@InjectMocks 
	MedicalHealthcarePrehistoricController MedicalHealthcarePrehistoricController;

	@BeforeEach
	void setUp() {
		mvc = MockMvcBuilders.standaloneSetup(MedicalHealthcarePrehistoricController).build();
		JacksonTester.initFields(this, new ObjectMapper());
	}

	@Test
	void getPatientPrehistoric1() throws Exception {
		Authentication authentication = null;
		MedicalHealthcarePrehistoricDTO dataParams = new MedicalHealthcarePrehistoricDTO();
		Integer patientId = 0;

		//mock method
		ResultSelectEntity resultData1 = new ResultSelectEntity();
		when(medicalHealthcarePrehistoricService.getPatientPrehistoric(Mockito.any(),Mockito.any(),Mockito.any())).thenReturn(resultData1);
		MockHttpServletResponse responseActual = mvc.perform(
			get("/"+patientId+"/prehistoric")
			.accept(MediaType.APPLICATION_JSON)
			.contentType(MediaType.APPLICATION_JSON))
			.andReturn().getResponse();

		// assert result
		assertThat(responseActual.getStatus(), Matchers.equalTo(HttpStatus.OK.value()));
	}

	@Test
	void getPatientPrehistoric1ThrowException3() throws Exception {
		Authentication authentication = null;
		MedicalHealthcarePrehistoricDTO dataParams = new MedicalHealthcarePrehistoricDTO();
		Integer patientId = 0;

		//mock method
		when(medicalHealthcarePrehistoricService.getPatientPrehistoric(Mockito.any(),Mockito.any(),Mockito.any())).thenThrow(new TeleCareException(ErrorApp.ERROR_INPUTPARAMS));
		MockHttpServletResponse responseActual = mvc.perform(
			get("/"+patientId+"/prehistoric")
			.accept(MediaType.APPLICATION_JSON)
			.contentType(MediaType.APPLICATION_JSON))
			.andReturn().getResponse();

		// assert result
		assertThat(responseActual.getStatus(), Matchers.equalTo(HttpStatus.OK.value()));
	}

	@Test
	void createPatientPrehistoric2() throws Exception {
		Authentication authentication = null;
		MedicalHealthcarePrehistoricDTO dataParams = new MedicalHealthcarePrehistoricDTO();
		// TODO sua cac tham so sau de khop voi dieu kien trong DTO
		Integer patientId = 0;

		//mock method
		MedicalHealthcarePrehistoricDTO resultData1 = null;
		when(medicalHealthcarePrehistoricServiceJPA.createPatientPrehistoric(Mockito.any(),Mockito.any(),Mockito.any())).thenReturn(resultData1);
		MockHttpServletResponse responseActual = mvc.perform(
			post("/"+patientId+"/prehistoric")
			.accept(MediaType.APPLICATION_JSON)
			.content((new ObjectMapper()).writeValueAsString(dataParams))
			.contentType(MediaType.APPLICATION_JSON))
			.andReturn().getResponse();

		// assert result
		assertThat(responseActual.getStatus(), Matchers.equalTo(HttpStatus.OK.value()));
	}

	@Test
	void createPatientPrehistoric2ThrowException3() throws Exception {
		Authentication authentication = null;
		MedicalHealthcarePrehistoricDTO dataParams = new MedicalHealthcarePrehistoricDTO();
		// TODO sua cac tham so sau de khop voi dieu kien trong DTO
		Integer patientId = 0;

		//mock method
		when(medicalHealthcarePrehistoricServiceJPA.createPatientPrehistoric(Mockito.any(),Mockito.any(),Mockito.any())).thenThrow(new TeleCareException(ErrorApp.ERROR_INPUTPARAMS));
		MockHttpServletResponse responseActual = mvc.perform(
			post("/"+patientId+"/prehistoric")
			.accept(MediaType.APPLICATION_JSON)
			.content((new ObjectMapper()).writeValueAsString(dataParams))
			.contentType(MediaType.APPLICATION_JSON))
			.andReturn().getResponse();

		// assert result
		assertThat(responseActual.getStatus(), Matchers.equalTo(HttpStatus.BAD_REQUEST.value()));
	}

	@Test
	void updatePatientPrehistoric0() throws Exception {
		Authentication authentication = null;
		Integer prehistoricId = 0;
		Integer patientId = 0;
		MedicalHealthcarePrehistoricDTO dataParams = new MedicalHealthcarePrehistoricDTO();

		//mock method
		MedicalHealthcarePrehistoricDTO resultData1 = null;
		when(medicalHealthcarePrehistoricServiceJPA.updatePatientPrehistoric(Mockito.any(),Mockito.any())).thenReturn(resultData1);
		MockHttpServletResponse responseActual = mvc.perform(
			post("/"+""+patientId+"/prehistoric/"+prehistoricId+"")
			.accept(MediaType.APPLICATION_JSON)
			.content((new ObjectMapper()).writeValueAsString(dataParams))
			.contentType(MediaType.APPLICATION_JSON))
			.andReturn().getResponse();

		// assert result
		assertThat(responseActual.getStatus(), Matchers.equalTo(HttpStatus.OK.value()));
	}

	@Test
	void updatePatientPrehistoric0ThrowException4() throws Exception {
		Authentication authentication = null;
		Integer prehistoricId = 0;
		Integer patientId = 0;
		MedicalHealthcarePrehistoricDTO dataParams = new MedicalHealthcarePrehistoricDTO();

		//mock method
		when(medicalHealthcarePrehistoricServiceJPA.updatePatientPrehistoric(Mockito.any(),Mockito.any())).thenThrow(new TeleCareException(ErrorApp.ERROR_INPUTPARAMS));
		MockHttpServletResponse responseActual = mvc.perform(
			post("/"+""+patientId+"/prehistoric/"+prehistoricId+"")
			.accept(MediaType.APPLICATION_JSON)
			.content((new ObjectMapper()).writeValueAsString(dataParams))
			.contentType(MediaType.APPLICATION_JSON))
			.andReturn().getResponse();

		// assert result
		assertThat(responseActual.getStatus(), Matchers.equalTo(HttpStatus.BAD_REQUEST.value()));
	}

	@Test
	void deletePatientPrehistoric3() throws Exception {
		Authentication authentication = null;
		Integer prehistoricId = 0;
		Integer patientId = 0;
		MedicalHealthcarePrehistoricDTO dataParams = new MedicalHealthcarePrehistoricDTO();

		//mock method
		Mockito.doNothing().when(medicalHealthcarePrehistoricServiceJPA).deletePatientPrehistoric(Mockito.any(),Mockito.any());
		MockHttpServletResponse responseActual = mvc.perform(
			delete("/"+""+patientId+"/prehistoric/"+prehistoricId+"")
			.accept(MediaType.APPLICATION_JSON)
			.contentType(MediaType.APPLICATION_JSON))
			.andReturn().getResponse();

		// assert result
		assertThat(responseActual.getStatus(), Matchers.equalTo(HttpStatus.OK.value()));
	}

	@Test
	void deletePatientPrehistoric3ThrowException4() throws Exception {
		Authentication authentication = null;
		Integer prehistoricId = 0;
		Integer patientId = 0;
		MedicalHealthcarePrehistoricDTO dataParams = new MedicalHealthcarePrehistoricDTO();

		//mock method
		Mockito.doThrow(new TeleCareException(ErrorApp.ERROR_INPUTPARAMS)).when(medicalHealthcarePrehistoricServiceJPA).deletePatientPrehistoric(Mockito.any(),Mockito.any());
		MockHttpServletResponse responseActual = mvc.perform(
			delete("/"+""+patientId+"/prehistoric/"+prehistoricId+"")
			.accept(MediaType.APPLICATION_JSON)
			.contentType(MediaType.APPLICATION_JSON))
			.andReturn().getResponse();

		// assert result
		assertThat(responseActual.getStatus(), Matchers.equalTo(HttpStatus.BAD_REQUEST.value()));
	}

}