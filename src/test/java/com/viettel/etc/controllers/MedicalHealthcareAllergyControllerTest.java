package com.viettel.etc.controllers;import com.viettel.etc.dto.MedicalHealthcareAllergyDTO;import com.viettel.etc.services.MedicalHealthcareAllergyService;import com.viettel.etc.services.tables.MedicalHealthcareAllergyServiceJPA;import com.viettel.etc.utils.FnCommon;import com.viettel.etc.utils.TeleCareException;import com.viettel.etc.xlibrary.core.constants.FunctionCommon;import com.viettel.etc.xlibrary.core.entities.CoreErrorApp;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.http.HttpStatus;import org.springframework.http.MediaType;import org.springframework.http.ResponseEntity;import org.springframework.security.core.Authentication;import org.springframework.security.core.annotation.AuthenticationPrincipal;import org.springframework.web.bind.annotation.*;import javax.validation.Valid;import com.viettel.etc.controllers.MedicalHealthcareAllergyController;
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
class MedicalHealthcareAllergyControllerTest {

	private MockMvc mvc;
	@Mock
	private MedicalHealthcareAllergyService medicalHealthcareAllergyService;
	@Mock
	private MedicalHealthcareAllergyServiceJPA medicalHealthcareAllergyServiceJPA;
	@InjectMocks 	MedicalHealthcareAllergyController MedicalHealthcareAllergyController;	@BeforeEach
	void setUp() {
		mvc = MockMvcBuilders.standaloneSetup(MedicalHealthcareAllergyController).build();
		JacksonTester.initFields(this, new ObjectMapper());
	}

	@Test
	void getMedicalHealthcareAllergy2() throws Exception {
		Authentication authentication = null;
		MedicalHealthcareAllergyDTO dataParams = new MedicalHealthcareAllergyDTO();

		//mock method
		ResultSelectEntity resultData1 = new ResultSelectEntity();
		when(medicalHealthcareAllergyService.getMedicalHealthcareAllergy(Mockito.any())).thenReturn(resultData1);
		MockHttpServletResponse responseActual = mvc.perform(
			get("/"+"medical-healthcare-allergy")
			.accept(MediaType.APPLICATION_JSON)
			.contentType(MediaType.APPLICATION_JSON))
			.andReturn().getResponse();

		// assert result
		assertThat(responseActual.getStatus(), Matchers.equalTo(HttpStatus.OK.value()));
	}

	@Test
	void getMedicalHealthcareAllergy2ThrowException2() throws Exception {
		Authentication authentication = null;
		MedicalHealthcareAllergyDTO dataParams = new MedicalHealthcareAllergyDTO();

		//mock method
		ResultSelectEntity resultData1 = new ResultSelectEntity();
		when(medicalHealthcareAllergyService.getMedicalHealthcareAllergy(Mockito.any())).thenReturn(resultData1);
		MockHttpServletResponse responseActual = mvc.perform(
			get("/"+"medical-healthcare-allergy")
			.accept(MediaType.APPLICATION_JSON)
			.contentType(MediaType.APPLICATION_JSON))
			.andReturn().getResponse();

		// assert result
		assertThat(responseActual.getStatus(), Matchers.equalTo(HttpStatus.OK.value()));
	}

	@Test
	void createPatientAllergy1() throws Exception {
		Authentication authentication = null;
		MedicalHealthcareAllergyDTO dataParams = new MedicalHealthcareAllergyDTO();
		// TODO sua cac tham so sau de khop voi dieu kien trong DTO
		Integer patientId = 0;

		//mock method
		ResultSelectEntity resultData1 = new ResultSelectEntity();
		when(medicalHealthcareAllergyServiceJPA.createPatientAllergy(Mockito.any())).thenReturn(resultData1);
		MockHttpServletResponse responseActual = mvc.perform(
			post("/"+patientId+"/prehistoric/allergy")
			.accept(MediaType.APPLICATION_JSON)
			.content((new ObjectMapper()).writeValueAsString(dataParams))
			.contentType(MediaType.APPLICATION_JSON))
			.andReturn().getResponse();

		// assert result
		assertThat(responseActual.getStatus(), Matchers.equalTo(HttpStatus.OK.value()));
	}

	@Test
	void createPatientAllergy1ThrowException3() throws Exception {
		Authentication authentication = null;
		MedicalHealthcareAllergyDTO dataParams = new MedicalHealthcareAllergyDTO();
		// TODO sua cac tham so sau de khop voi dieu kien trong DTO
		Integer patientId = 0;

		//mock method
		when(medicalHealthcareAllergyServiceJPA.createPatientAllergy(Mockito.any())).thenThrow(new TeleCareException(ErrorApp.ERROR_INPUTPARAMS));
		MockHttpServletResponse responseActual = mvc.perform(
			post("/"+patientId+"/prehistoric/allergy")
			.accept(MediaType.APPLICATION_JSON)
			.content((new ObjectMapper()).writeValueAsString(dataParams))
			.contentType(MediaType.APPLICATION_JSON))
			.andReturn().getResponse();

		// assert result
		assertThat(responseActual.getStatus(), Matchers.equalTo(HttpStatus.OK.value()));
	}

	@Test
	void updatePatientAllergy3() throws Exception {
		Authentication authentication = null;
		MedicalHealthcareAllergyDTO dataParams = new MedicalHealthcareAllergyDTO();
		// TODO sua cac tham so sau de khop voi dieu kien trong DTO
		Integer patientId = 0;
		Integer allergyId = 0;

		//mock method
		ResultSelectEntity resultData1 = new ResultSelectEntity();
		when(medicalHealthcareAllergyServiceJPA.updatePatientAllergy(Mockito.any(),Mockito.any())).thenReturn(resultData1);
		MockHttpServletResponse responseActual = mvc.perform(
			post("/"+patientId+"/prehistoric/allergy/"+allergyId+"")
			.accept(MediaType.APPLICATION_JSON)
			.content((new ObjectMapper()).writeValueAsString(dataParams))
			.contentType(MediaType.APPLICATION_JSON))
			.andReturn().getResponse();

		// assert result
		assertThat(responseActual.getStatus(), Matchers.equalTo(HttpStatus.OK.value()));
	}

	@Test
	void updatePatientAllergy3ThrowException4() throws Exception {
		Authentication authentication = null;
		MedicalHealthcareAllergyDTO dataParams = new MedicalHealthcareAllergyDTO();
		// TODO sua cac tham so sau de khop voi dieu kien trong DTO
		Integer patientId = 0;
		Integer allergyId = 0;

		//mock method
		when(medicalHealthcareAllergyServiceJPA.updatePatientAllergy(Mockito.any(),Mockito.any())).thenThrow(new TeleCareException(ErrorApp.ERROR_INPUTPARAMS));
		MockHttpServletResponse responseActual = mvc.perform(
			post("/"+patientId+"/prehistoric/allergy/"+allergyId+"")
			.accept(MediaType.APPLICATION_JSON)
			.content((new ObjectMapper()).writeValueAsString(dataParams))
			.contentType(MediaType.APPLICATION_JSON))
			.andReturn().getResponse();

		// assert result
		assertThat(responseActual.getStatus(), Matchers.equalTo(HttpStatus.OK.value()));
	}

	@Test
	void deletePatientAllergy0() throws Exception {
		Authentication authentication = null;
		MedicalHealthcareAllergyDTO dataParams = new MedicalHealthcareAllergyDTO();
		Integer patientId = 0;
		Integer allergyId = 0;

		//mock method
		Mockito.doNothing().when(medicalHealthcareAllergyServiceJPA).deletePatientAllergy(Mockito.any(),Mockito.any());
		MockHttpServletResponse responseActual = mvc.perform(
			delete("/"+patientId+"/prehistoric/allergy/"+allergyId+"")
			.accept(MediaType.APPLICATION_JSON)
			.contentType(MediaType.APPLICATION_JSON))
			.andReturn().getResponse();

		// assert result
		assertThat(responseActual.getStatus(), Matchers.equalTo(HttpStatus.OK.value()));
	}

	@Test
	void deletePatientAllergy0ThrowException4() throws Exception {
		Authentication authentication = null;
		MedicalHealthcareAllergyDTO dataParams = new MedicalHealthcareAllergyDTO();
		Integer patientId = 0;
		Integer allergyId = 0;

		//mock method
		Mockito.doThrow(new TeleCareException(ErrorApp.ERROR_INPUTPARAMS)).when(medicalHealthcareAllergyServiceJPA).deletePatientAllergy(Mockito.any(),Mockito.any());
		MockHttpServletResponse responseActual = mvc.perform(
			delete("/"+patientId+"/prehistoric/allergy/"+allergyId+"")
			.accept(MediaType.APPLICATION_JSON)
			.contentType(MediaType.APPLICATION_JSON))
			.andReturn().getResponse();

		// assert result
		assertThat(responseActual.getStatus(), Matchers.equalTo(HttpStatus.OK.value()));
	}

}