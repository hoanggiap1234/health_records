package com.viettel.etc.controllers;
import com.viettel.etc.dto.BookingInformationResultDTO;
import com.viettel.etc.services.BookingInformationResultService;
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
import com.viettel.etc.controllers.BookingInformationResultController;
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
class BookingInformationResultControllerTest {

	private MockMvc mvc;
	@Mock
	private BookingInformationResultService bookingInformationResultService;
	@InjectMocks 
	BookingInformationResultController BookingInformationResultController;

	@BeforeEach
	void setUp() {
		mvc = MockMvcBuilders.standaloneSetup(BookingInformationResultController).build();
		JacksonTester.initFields(this, new ObjectMapper());
	}

	@Test
	void getBookingInformationResult0() throws Exception {
		Authentication authentication = null;
		Integer patientId = 0;
		BookingInformationResultDTO dataParams = new BookingInformationResultDTO();

		//mock method
		ResultSelectEntity resultData1 = new ResultSelectEntity();
		when(bookingInformationResultService.getBookingInformationResult(Mockito.any(),Mockito.any())).thenReturn(resultData1);
		MockHttpServletResponse responseActual = mvc.perform(
			get("/"+patientId+"/booking-information-results")
			.accept(MediaType.APPLICATION_JSON)
			.contentType(MediaType.APPLICATION_JSON))
			.andReturn().getResponse();

		// assert result
		assertThat(responseActual.getStatus(), Matchers.equalTo(HttpStatus.OK.value()));
	}

	@Test
	void getBookingInformationResult0ThrowException3() throws Exception {
		Authentication authentication = null;
		Integer patientId = 0;
		BookingInformationResultDTO dataParams = new BookingInformationResultDTO();

		//mock method
		when(bookingInformationResultService.getBookingInformationResult(Mockito.any(),Mockito.any())).thenThrow(new TeleCareException(ErrorApp.ERROR_INPUTPARAMS));
		MockHttpServletResponse responseActual = mvc.perform(
			get("/"+patientId+"/booking-information-results")
			.accept(MediaType.APPLICATION_JSON)
			.contentType(MediaType.APPLICATION_JSON))
			.andReturn().getResponse();

		// assert result
		assertThat(responseActual.getStatus(), Matchers.equalTo(HttpStatus.BAD_REQUEST.value()));
	}

}