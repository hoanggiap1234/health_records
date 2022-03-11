package com.viettel.etc.controllers;import com.viettel.etc.dto.MedicalHealthcarePresurgeryDTO;import com.viettel.etc.services.tables.MedicalHealthcarePresurgeryServiceJPA;import com.viettel.etc.utils.FnCommon;import com.viettel.etc.utils.TeleCareException;import com.viettel.etc.xlibrary.core.constants.FunctionCommon;import com.viettel.etc.xlibrary.core.entities.CoreErrorApp;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.http.HttpStatus;import org.springframework.http.MediaType;import org.springframework.http.ResponseEntity;import org.springframework.security.core.Authentication;import org.springframework.security.core.annotation.AuthenticationPrincipal;import org.springframework.web.bind.annotation.*;import javax.validation.Valid;import com.viettel.etc.controllers.MedicalHealthcarePresurgeryController;
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
class MedicalHealthcarePresurgeryControllerTest {

	private MockMvc mvc;
	@Mock
	private MedicalHealthcarePresurgeryServiceJPA medicalHealthcarePresurgeryServiceJPA;
	@InjectMocks 	MedicalHealthcarePresurgeryController MedicalHealthcarePresurgeryController;	@BeforeEach
	void setUp() {
		mvc = MockMvcBuilders.standaloneSetup(MedicalHealthcarePresurgeryController).build();
		JacksonTester.initFields(this, new ObjectMapper());
	}

	@Test
	void saveMedicalHealthcarePresurgery0() throws Exception {
		Authentication authentication = null;
		MedicalHealthcarePresurgeryDTO dataParams = new MedicalHealthcarePresurgeryDTO();
		// TODO sua cac tham so sau de khop voi dieu kien trong DTO
		Integer patientId = 0;

		//mock method
		ResultSelectEntity resultData1 = new ResultSelectEntity();
		when(medicalHealthcarePresurgeryServiceJPA.saveMedicalHealthcarePresurgery(Mockito.any(),Mockito.any())).thenReturn(resultData1);
		MockHttpServletResponse responseActual = mvc.perform(
			post("/"+patientId+"/presurgery")
			.accept(MediaType.APPLICATION_JSON)
			.content((new ObjectMapper()).writeValueAsString(dataParams))
			.contentType(MediaType.APPLICATION_JSON))
			.andReturn().getResponse();

		// assert result
		assertThat(responseActual.getStatus(), Matchers.equalTo(HttpStatus.OK.value()));
	}

	@Test
	void saveMedicalHealthcarePresurgery0ThrowException3() throws Exception {
		Authentication authentication = null;
		MedicalHealthcarePresurgeryDTO dataParams = new MedicalHealthcarePresurgeryDTO();
		// TODO sua cac tham so sau de khop voi dieu kien trong DTO
		Integer patientId = 0;

		//mock method
		when(medicalHealthcarePresurgeryServiceJPA.saveMedicalHealthcarePresurgery(Mockito.any(),Mockito.any())).thenThrow(new TeleCareException(ErrorApp.ERROR_INPUTPARAMS));
		MockHttpServletResponse responseActual = mvc.perform(
			post("/"+patientId+"/presurgery")
			.accept(MediaType.APPLICATION_JSON)
			.content((new ObjectMapper()).writeValueAsString(dataParams))
			.contentType(MediaType.APPLICATION_JSON))
			.andReturn().getResponse();

		// assert result
		assertThat(responseActual.getStatus(), Matchers.equalTo(HttpStatus.OK.value()));
	}

	@Test
	void updatePatientPresurgery2() throws Exception {
		Authentication authentication = null;
		MedicalHealthcarePresurgeryDTO dataParams = new MedicalHealthcarePresurgeryDTO();
		// TODO sua cac tham so sau de khop voi dieu kien trong DTO
		Integer patientId = 0;
		Integer presurgeryId = 0;

		//mock method
		ResultSelectEntity resultData1 = new ResultSelectEntity();
		when(medicalHealthcarePresurgeryServiceJPA.updatePatientPresurgery(Mockito.any(),Mockito.any())).thenReturn(resultData1);
		MockHttpServletResponse responseActual = mvc.perform(
			post("/"+patientId+"/presurgery/"+presurgeryId+"")
			.accept(MediaType.APPLICATION_JSON)
			.content((new ObjectMapper()).writeValueAsString(dataParams))
			.contentType(MediaType.APPLICATION_JSON))
			.andReturn().getResponse();

		// assert result
		assertThat(responseActual.getStatus(), Matchers.equalTo(HttpStatus.OK.value()));
	}

	@Test
	void updatePatientPresurgery2ThrowException4() throws Exception {
		Authentication authentication = null;
		MedicalHealthcarePresurgeryDTO dataParams = new MedicalHealthcarePresurgeryDTO();
		// TODO sua cac tham so sau de khop voi dieu kien trong DTO
		Integer patientId = 0;
		Integer presurgeryId = 0;

		//mock method
		when(medicalHealthcarePresurgeryServiceJPA.updatePatientPresurgery(Mockito.any(),Mockito.any())).thenThrow(new TeleCareException(ErrorApp.ERROR_INPUTPARAMS));
		MockHttpServletResponse responseActual = mvc.perform(
			post("/"+patientId+"/presurgery/"+presurgeryId+"")
			.accept(MediaType.APPLICATION_JSON)
			.content((new ObjectMapper()).writeValueAsString(dataParams))
			.contentType(MediaType.APPLICATION_JSON))
			.andReturn().getResponse();

		// assert result
		assertThat(responseActual.getStatus(), Matchers.equalTo(HttpStatus.OK.value()));
	}

	@Test
	void deletePatientPresurgery1() throws Exception {
		Authentication authentication = null;
		MedicalHealthcarePresurgeryDTO dataParams = new MedicalHealthcarePresurgeryDTO();
		Integer patientId = 0;
		Integer presurgeryId = 0;

		//mock method
		Mockito.doNothing().when(medicalHealthcarePresurgeryServiceJPA).deletePatientPresurgery(Mockito.any(),Mockito.any());
		MockHttpServletResponse responseActual = mvc.perform(
			delete("/"+patientId+"/presurgery/"+presurgeryId+"")
			.accept(MediaType.APPLICATION_JSON)
			.contentType(MediaType.APPLICATION_JSON))
			.andReturn().getResponse();

		// assert result
		assertThat(responseActual.getStatus(), Matchers.equalTo(HttpStatus.OK.value()));
	}

	@Test
	void deletePatientPresurgery1ThrowException4() throws Exception {
		Authentication authentication = null;
		MedicalHealthcarePresurgeryDTO dataParams = new MedicalHealthcarePresurgeryDTO();
		Integer patientId = 0;
		Integer presurgeryId = 0;

		//mock method
		Mockito.doThrow(new TeleCareException(ErrorApp.ERROR_INPUTPARAMS)).when(medicalHealthcarePresurgeryServiceJPA).deletePatientPresurgery(Mockito.any(),Mockito.any());
		MockHttpServletResponse responseActual = mvc.perform(
			delete("/"+patientId+"/presurgery/"+presurgeryId+"")
			.accept(MediaType.APPLICATION_JSON)
			.contentType(MediaType.APPLICATION_JSON))
			.andReturn().getResponse();

		// assert result
		assertThat(responseActual.getStatus(), Matchers.equalTo(HttpStatus.OK.value()));
	}

}