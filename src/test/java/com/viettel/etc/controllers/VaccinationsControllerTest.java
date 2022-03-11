package com.viettel.etc.controllers;
import com.viettel.etc.dto.VaccinationsDTO;
import com.viettel.etc.services.VaccinationsService;
import com.viettel.etc.utils.ErrorApp;
import com.viettel.etc.utils.TeleCareException;
import com.viettel.etc.xlibrary.core.constants.FunctionCommon;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
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
import com.viettel.etc.controllers.VaccinationsController;
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
class VaccinationsControllerTest {

	private MockMvc mvc;
	@Mock
	private VaccinationsService vaccinationsService;
	@InjectMocks 
	VaccinationsController VaccinationsController;

	@BeforeEach
	void setUp() {
		mvc = MockMvcBuilders.standaloneSetup(VaccinationsController).build();
		JacksonTester.initFields(this, new ObjectMapper());
	}

	@Test
	void getVaccinationsHistory0() throws Exception {
		Authentication authentication = null;
		Long patientId = 0L;
		VaccinationsDTO dataParams = new VaccinationsDTO();

		//mock method
		ResultSelectEntity resultData1 = null;
		when(vaccinationsService.getVaccinationsHistory(Mockito.any(),Mockito.any(), Mockito.any())).thenReturn(resultData1);
		MockHttpServletResponse responseActual = mvc.perform(
			get("/"+patientId+"/vaccinations")
			.accept(MediaType.APPLICATION_JSON)
			.contentType(MediaType.APPLICATION_JSON))
			.andReturn().getResponse();

		// assert result
		assertThat(responseActual.getStatus(), Matchers.equalTo(HttpStatus.OK.value()));
	}

	@Test
	void getVaccinationsHistory0ThrowException3() throws Exception {
		Authentication authentication = null;
		Long patientId = 0L;
		VaccinationsDTO dataParams = new VaccinationsDTO();

		//mock method
		ResultSelectEntity resultData1 = null;
		when(vaccinationsService.getVaccinationsHistory(Mockito.any(),Mockito.any(), Mockito.any())).thenReturn(resultData1);
		MockHttpServletResponse responseActual = mvc.perform(
			get("/"+patientId+"/vaccinations")
			.accept(MediaType.APPLICATION_JSON)
			.contentType(MediaType.APPLICATION_JSON))
			.andReturn().getResponse();

		// assert result
		assertThat(responseActual.getStatus(), Matchers.equalTo(HttpStatus.OK.value()));
	}

	@Test
	void getDetailVaccinationsHistory1() throws Exception {
		Authentication authentication = null;
		VaccinationsDTO dataParams = new VaccinationsDTO();
		Integer patientId = 0;
		Integer vaccinationsId = 0;

		//mock method
		Optional resultData1 = Optional.empty();
		when(vaccinationsService.getDetailVaccinationsHistory(Mockito.any())).thenReturn(resultData1);
		MockHttpServletResponse responseActual = mvc.perform(
			get("/"+patientId+"/vaccinations/"+vaccinationsId+"")
			.accept(MediaType.APPLICATION_JSON)
			.contentType(MediaType.APPLICATION_JSON))
			.andReturn().getResponse();

		// assert result
		assertThat(responseActual.getStatus(), Matchers.equalTo(HttpStatus.OK.value()));
	}

	@Test
	void getDetailVaccinationsHistory1ThrowException4() throws Exception {
		Authentication authentication = null;
		VaccinationsDTO dataParams = new VaccinationsDTO();
		Integer patientId = 0;
		Integer vaccinationsId = 0;

		//mock method
		Optional resultData1 = Optional.empty();
		when(vaccinationsService.getDetailVaccinationsHistory(Mockito.any())).thenReturn(resultData1);
		MockHttpServletResponse responseActual = mvc.perform(
			get("/"+patientId+"/vaccinations/"+vaccinationsId+"")
			.accept(MediaType.APPLICATION_JSON)
			.contentType(MediaType.APPLICATION_JSON))
			.andReturn().getResponse();

		// assert result
		assertThat(responseActual.getStatus(), Matchers.equalTo(HttpStatus.OK.value()));
	}

}