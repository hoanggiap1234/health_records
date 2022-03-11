package com.viettel.etc.controllers;import com.viettel.etc.dto.MedicalHealthcareServiceResultDTO;import com.viettel.etc.services.MedicalHealthcareServiceResultService;import com.viettel.etc.xlibrary.core.constants.FunctionCommon;import com.viettel.etc.xlibrary.core.entities.UserSystemEntity;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.http.HttpStatus;import org.springframework.http.MediaType;import org.springframework.security.core.Authentication;import org.springframework.http.ResponseEntity;import org.springframework.web.bind.annotation.*;import com.viettel.etc.utils.*;import org.springframework.security.core.annotation.AuthenticationPrincipal;import com.viettel.etc.controllers.MedicalHealthcareServiceResultController;
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
class MedicalHealthcareServiceResultControllerTest {

	private MockMvc mvc;
	@Mock
	private MedicalHealthcareServiceResultService medicalHealthcareServiceResultService;
	@InjectMocks 	MedicalHealthcareServiceResultController MedicalHealthcareServiceResultController;	@BeforeEach
	void setUp() {
		mvc = MockMvcBuilders.standaloneSetup(MedicalHealthcareServiceResultController).build();
		JacksonTester.initFields(this, new ObjectMapper());
	}

	@Test
	void getMedicalHealthcareServiceResult0() throws Exception {
		Authentication authentication = null;
		MedicalHealthcareServiceResultDTO dataParams = new MedicalHealthcareServiceResultDTO();

		//mock method
		ResultSelectEntity resultData1 = new ResultSelectEntity();
		when(medicalHealthcareServiceResultService.getMedicalHealthcareServiceResult(Mockito.any())).thenReturn(resultData1);
		MockHttpServletResponse responseActual = mvc.perform(
			get("/services_results")
			.accept(MediaType.APPLICATION_JSON)
			.contentType(MediaType.APPLICATION_JSON))
			.andReturn().getResponse();

		// assert result
		assertThat(responseActual.getStatus(), Matchers.equalTo(HttpStatus.OK.value()));
	}

	@Test
	void getMedicalHealthcareServiceResult0ThrowException2() throws Exception {
		Authentication authentication = null;
		MedicalHealthcareServiceResultDTO dataParams = new MedicalHealthcareServiceResultDTO();

		//mock method
		ResultSelectEntity resultData1 = new ResultSelectEntity();
		when(medicalHealthcareServiceResultService.getMedicalHealthcareServiceResult(Mockito.any())).thenReturn(resultData1);
		MockHttpServletResponse responseActual = mvc.perform(
			get("/services_results")
			.accept(MediaType.APPLICATION_JSON)
			.contentType(MediaType.APPLICATION_JSON))
			.andReturn().getResponse();

		// assert result
		assertThat(responseActual.getStatus(), Matchers.equalTo(HttpStatus.OK.value()));
	}

}