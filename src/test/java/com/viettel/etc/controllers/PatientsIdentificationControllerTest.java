package com.viettel.etc.controllers;import com.viettel.etc.dto.PatientsIdentificationDTO;import com.viettel.etc.services.PatientsIdentificationService;import com.viettel.etc.utils.TeleCareException;import com.viettel.etc.xlibrary.core.constants.FunctionCommon;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.http.HttpStatus;import org.springframework.http.MediaType;import org.springframework.http.ResponseEntity;import org.springframework.security.core.Authentication;import org.springframework.security.core.annotation.AuthenticationPrincipal;import org.springframework.web.bind.annotation.GetMapping;import org.springframework.web.bind.annotation.PathVariable;import org.springframework.web.bind.annotation.RestController;import java.util.Optional;import com.viettel.etc.controllers.PatientsIdentificationController;
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
class PatientsIdentificationControllerTest {

	private MockMvc mvc;
	@Mock
	private PatientsIdentificationService patientsIdentificationService;
	@InjectMocks 	PatientsIdentificationController PatientsIdentificationController;	@BeforeEach
	void setUp() {
		mvc = MockMvcBuilders.standaloneSetup(PatientsIdentificationController).build();
		JacksonTester.initFields(this, new ObjectMapper());
	}

	@Test
	void autofillHealthInsuranceInfo0() throws Exception {
		Authentication authentication = null;
		String healthInsuranceNumber = new String("test");
		PatientsIdentificationDTO dataParams = new PatientsIdentificationDTO();

		//mock method
		Optional resultData1 = Optional.empty();
		when(patientsIdentificationService.autofillHealthInsuranceInfo(Mockito.any())).thenReturn(resultData1);
		MockHttpServletResponse responseActual = mvc.perform(
			get("/health-insurances/"+healthInsuranceNumber+"")
			.accept(MediaType.APPLICATION_JSON)
			.contentType(MediaType.APPLICATION_JSON))
			.andReturn().getResponse();

		// assert result
		assertThat(responseActual.getStatus(), Matchers.equalTo(HttpStatus.OK.value()));
	}

	@Test
	void autofillHealthInsuranceInfo0ThrowException3() throws Exception {
		Authentication authentication = null;
		String healthInsuranceNumber = new String("test");
		PatientsIdentificationDTO dataParams = new PatientsIdentificationDTO();

		//mock method
		when(patientsIdentificationService.autofillHealthInsuranceInfo(Mockito.any())).thenThrow(new TeleCareException(ErrorApp.ERROR_INPUTPARAMS));
		MockHttpServletResponse responseActual = mvc.perform(
			get("/health-insurances/"+healthInsuranceNumber+"")
			.accept(MediaType.APPLICATION_JSON)
			.contentType(MediaType.APPLICATION_JSON))
			.andReturn().getResponse();

		// assert result
		assertThat(responseActual.getStatus(), Matchers.equalTo(HttpStatus.OK.value()));
	}

}