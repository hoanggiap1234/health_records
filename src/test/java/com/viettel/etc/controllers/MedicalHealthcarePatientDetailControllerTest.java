package com.viettel.etc.controllers;
import com.viettel.etc.dto.MedicalHealthcarePatientDetailDTO;
import com.viettel.etc.repositories.tables.entities.MedicalHealthcarePatientDetailEntity;
import com.viettel.etc.services.MedicalHealthcarePatientDetailService;
import com.viettel.etc.utils.ErrorApp;
import com.viettel.etc.utils.FnCommon;
import com.viettel.etc.utils.TeleCareException;
import com.viettel.etc.xlibrary.core.constants.FunctionCommon;
import com.viettel.etc.xlibrary.core.entities.CoreErrorApp;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import com.viettel.etc.controllers.MedicalHealthcarePatientDetailController;
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
class MedicalHealthcarePatientDetailControllerTest {

	private MockMvc mvc;
	@Mock
	private MedicalHealthcarePatientDetailService medicalHealthcarePatientDetailService;
	@InjectMocks 
	MedicalHealthcarePatientDetailController MedicalHealthcarePatientDetailController;

	@BeforeEach
	void setUp() {
		mvc = MockMvcBuilders.standaloneSetup(MedicalHealthcarePatientDetailController).build();
		JacksonTester.initFields(this, new ObjectMapper());
	}

	@Test
	void getMedicalHealthcarePatientDetailHistory0() throws Exception {
		Authentication authentication = null;
		Integer historiesId = 0;
		MedicalHealthcarePatientDetailDTO dataParams = new MedicalHealthcarePatientDetailDTO();

		//mock method
		Optional resultData1 = Optional.empty();
		when(medicalHealthcarePatientDetailService.getMedicalHealthcarePatientDetailHistory(Mockito.any())).thenReturn(resultData1);
		MockHttpServletResponse responseActual = mvc.perform(
			get("/patient-detail/"+historiesId+"")
			.accept(MediaType.APPLICATION_JSON)
			.contentType(MediaType.APPLICATION_JSON))
			.andReturn().getResponse();

		// assert result
		assertThat(responseActual.getStatus(), Matchers.equalTo(HttpStatus.NO_CONTENT.value()));
	}

	@Test
	void getMedicalHealthcarePatientDetailHistory0ThrowException3() throws Exception {
		Authentication authentication = null;
		Integer historiesId = 0;
		MedicalHealthcarePatientDetailDTO dataParams = new MedicalHealthcarePatientDetailDTO();

		//mock method
		Optional resultData1 = Optional.empty();
		when(medicalHealthcarePatientDetailService.getMedicalHealthcarePatientDetailHistory(Mockito.any())).thenReturn(resultData1);
		MockHttpServletResponse responseActual = mvc.perform(
			get("/patient-detail/"+historiesId+"")
			.accept(MediaType.APPLICATION_JSON)
			.contentType(MediaType.APPLICATION_JSON))
			.andReturn().getResponse();

		// assert result
		assertThat(responseActual.getStatus(), Matchers.equalTo(HttpStatus.NO_CONTENT.value()));
	}

	@Test
	void getHealthIndexLatest4() throws Exception {
		Authentication authentication = null;
		Integer patientId = 0;
		MedicalHealthcarePatientDetailDTO dataParams = new MedicalHealthcarePatientDetailDTO();

		//mock method
		ResultSelectEntity resultData1 = null;
		when(medicalHealthcarePatientDetailService.getHealthIndexLatest(Mockito.any())).thenReturn(resultData1);
		MockHttpServletResponse responseActual = mvc.perform(
			get("/"+patientId+"/health-index/latest")
			.accept(MediaType.APPLICATION_JSON)
			.contentType(MediaType.APPLICATION_JSON))
			.andReturn().getResponse();

		// assert result
		assertThat(responseActual.getStatus(), Matchers.equalTo(HttpStatus.OK.value()));
	}

	@Test
	void getHealthIndexLatest4ThrowException3() throws Exception {
		Authentication authentication = null;
		Integer patientId = 0;
		MedicalHealthcarePatientDetailDTO dataParams = new MedicalHealthcarePatientDetailDTO();

		//mock method
		when(medicalHealthcarePatientDetailService.getHealthIndexLatest(Mockito.any())).thenThrow(new TeleCareException(ErrorApp.ERROR_INPUTPARAMS));
		MockHttpServletResponse responseActual = mvc.perform(
			get("/"+patientId+"/health-index/latest")
			.accept(MediaType.APPLICATION_JSON)
			.contentType(MediaType.APPLICATION_JSON))
			.andReturn().getResponse();

		// assert result
		assertThat(responseActual.getStatus(), Matchers.equalTo(HttpStatus.OK.value()));
	}

	@Test
	void getHealthIndexList5() throws Exception {
		Authentication authentication = null;
		Integer patientId = 0;
		MedicalHealthcarePatientDetailDTO dataParams = new MedicalHealthcarePatientDetailDTO();

		//mock method
		ResultSelectEntity resultData1 = null;
		when(medicalHealthcarePatientDetailService.getHealthIndexLatest(Mockito.any())).thenReturn(resultData1);
		MockHttpServletResponse responseActual = mvc.perform(
			get("/"+patientId+"/health-index")
			.accept(MediaType.APPLICATION_JSON)
			.contentType(MediaType.APPLICATION_JSON))
			.andReturn().getResponse();

		// assert result
		assertThat(responseActual.getStatus(), Matchers.equalTo(HttpStatus.OK.value()));
	}

	@Test
	void getHealthIndexList5ThrowException3() throws Exception {
		Authentication authentication = null;
		Integer patientId = 0;
		MedicalHealthcarePatientDetailDTO dataParams = new MedicalHealthcarePatientDetailDTO();

		//mock method
		when(medicalHealthcarePatientDetailService.getHealthIndexLatest(Mockito.any())).thenThrow(new TeleCareException(ErrorApp.ERROR_INPUTPARAMS));
		MockHttpServletResponse responseActual = mvc.perform(
			get("/"+patientId+"/health-index")
			.accept(MediaType.APPLICATION_JSON)
			.contentType(MediaType.APPLICATION_JSON))
			.andReturn().getResponse();

		// assert result
		assertThat(responseActual.getStatus(), Matchers.equalTo(HttpStatus.OK.value()));
	}

	@Test
	void deleteHealthIndex3() throws Exception {
		Authentication authentication = null;
		Integer patientId = 0;
		Integer detailId = 0;
		MedicalHealthcarePatientDetailDTO dataParams = new MedicalHealthcarePatientDetailDTO();

		//mock method
		MedicalHealthcarePatientDetailEntity resultData1 = null;
		when(medicalHealthcarePatientDetailService.delete(Mockito.any(),Mockito.any())).thenReturn(resultData1);
		MockHttpServletResponse responseActual = mvc.perform(
			delete("/"+patientId+"/health-index/"+detailId+"")
			.accept(MediaType.APPLICATION_JSON)
			.contentType(MediaType.APPLICATION_JSON))
			.andReturn().getResponse();

		// assert result
		assertThat(responseActual.getStatus(), Matchers.equalTo(HttpStatus.OK.value()));
	}

	@Test
	void deleteHealthIndex3ThrowException4() throws Exception {
		Authentication authentication = null;
		Integer patientId = 0;
		Integer detailId = 0;
		MedicalHealthcarePatientDetailDTO dataParams = new MedicalHealthcarePatientDetailDTO();

		//mock method
		when(medicalHealthcarePatientDetailService.delete(Mockito.any(),Mockito.any())).thenThrow(new TeleCareException(ErrorApp.ERROR_INPUTPARAMS));
		MockHttpServletResponse responseActual = mvc.perform(
			delete("/"+patientId+"/health-index/"+detailId+"")
			.accept(MediaType.APPLICATION_JSON)
			.contentType(MediaType.APPLICATION_JSON))
			.andReturn().getResponse();

		// assert result
		assertThat(responseActual.getStatus(), Matchers.equalTo(HttpStatus.OK.value()));
	}

	@Test
	void saveHealthIndex1() throws Exception {
		Authentication authentication = null;
		MedicalHealthcarePatientDetailDTO dataParams = new MedicalHealthcarePatientDetailDTO();
		Integer patientId = 0;
		Optional indexType = Optional.empty();

		//mock method
		MedicalHealthcarePatientDetailEntity resultData1 = null;
		when(medicalHealthcarePatientDetailService.createHealthIndex(Mockito.any(),Mockito.any())).thenReturn(resultData1);
		MockHttpServletResponse responseActual = mvc.perform(
			post("/"+patientId+"/health-index")
			.accept(MediaType.APPLICATION_JSON)
			.content((new ObjectMapper()).writeValueAsString(dataParams))
			.contentType(MediaType.APPLICATION_JSON))
			.andReturn().getResponse();

		// assert result
		assertThat(responseActual.getStatus(), Matchers.equalTo(HttpStatus.OK.value()));
	}

	@Test
	void saveHealthIndex1ThrowException4() throws Exception {
		Authentication authentication = null;
		MedicalHealthcarePatientDetailDTO dataParams = new MedicalHealthcarePatientDetailDTO();
		Integer patientId = 0;
		Optional indexType = Optional.empty();

		//mock method
		when(medicalHealthcarePatientDetailService.createHealthIndex(Mockito.any(),Mockito.any())).thenThrow(new TeleCareException(ErrorApp.ERROR_INPUTPARAMS));
		MockHttpServletResponse responseActual = mvc.perform(
			post("/"+patientId+"/health-index")
			.accept(MediaType.APPLICATION_JSON)
			.content((new ObjectMapper()).writeValueAsString(dataParams))
			.contentType(MediaType.APPLICATION_JSON))
			.andReturn().getResponse();

		// assert result
		assertThat(responseActual.getStatus(), Matchers.equalTo(HttpStatus.OK.value()));
	}

	@Test
	void updateHealthIndex2() throws Exception {
		Authentication authentication = null;
		MedicalHealthcarePatientDetailDTO dataParams = new MedicalHealthcarePatientDetailDTO();
		Integer patientId = 0;
		Integer detailId = 0;
		Optional indexType = Optional.empty();

		//mock method
		MedicalHealthcarePatientDetailEntity resultData1 = null;
		when(medicalHealthcarePatientDetailService.updateHealthIndex(Mockito.any(),Mockito.any())).thenReturn(resultData1);
		MockHttpServletResponse responseActual = mvc.perform(
			post("/"+patientId+"/health-index/"+detailId+"")
			.accept(MediaType.APPLICATION_JSON)
			.content((new ObjectMapper()).writeValueAsString(dataParams))
			.contentType(MediaType.APPLICATION_JSON))
			.andReturn().getResponse();

		// assert result
		assertThat(responseActual.getStatus(), Matchers.equalTo(HttpStatus.OK.value()));
	}

	@Test
	void updateHealthIndex2ThrowException5() throws Exception {
		Authentication authentication = null;
		MedicalHealthcarePatientDetailDTO dataParams = new MedicalHealthcarePatientDetailDTO();
		Integer patientId = 0;
		Integer detailId = 0;
		Optional indexType = Optional.empty();

		//mock method
		when(medicalHealthcarePatientDetailService.updateHealthIndex(Mockito.any(),Mockito.any())).thenThrow(new TeleCareException(ErrorApp.ERROR_INPUTPARAMS));
		MockHttpServletResponse responseActual = mvc.perform(
			post("/"+patientId+"/health-index/"+detailId+"")
			.accept(MediaType.APPLICATION_JSON)
			.content((new ObjectMapper()).writeValueAsString(dataParams))
			.contentType(MediaType.APPLICATION_JSON))
			.andReturn().getResponse();

		// assert result
		assertThat(responseActual.getStatus(), Matchers.equalTo(HttpStatus.OK.value()));
	}

}