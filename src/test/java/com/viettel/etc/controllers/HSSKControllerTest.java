//package com.viettel.etc.controllers;
//import com.viettel.etc.dto.PatientDTO;
//import com.viettel.etc.dto.VaccinationsDTO;
//import com.viettel.etc.services.HSSKService;
//import com.viettel.etc.services.VaccinationsService;
//import com.viettel.etc.utils.Constants;
//import com.viettel.etc.utils.ErrorApp;
//import com.viettel.etc.utils.FnCommon;
//import com.viettel.etc.utils.TeleCareException;
//import com.viettel.etc.xlibrary.core.constants.FunctionCommon;
//import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestHeader;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//import java.util.Optional;
//import com.viettel.etc.controllers.HSSKController;
//import org.hamcrest.MatcherAssert;
//import com.viettel.etc.utils.ErrorApp;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import com.viettel.etc.utils.TeleCareException;
//import org.hamcrest.MatcherAssert;
//import static org.hamcrest.MatcherAssert.assertThat;
//import org.hamcrest.Matchers;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.boot.test.json.JacksonTester;
//import org.springframework.mock.web.MockHttpServletResponse;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.mockito.Mockito.when;
//import org.junit.jupiter.api.extension.ExtendWith;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//
//@ExtendWith(MockitoExtension.class)
//class HSSKControllerTest {
//
//	private MockMvc mvc;
//	@Mock
//	private HSSKService hsskService;
//	@InjectMocks
//	HSSKController HSSKController;
//
//	@BeforeEach
//	void setUp() {
//		mvc = MockMvcBuilders.standaloneSetup(HSSKController).build();
//		JacksonTester.initFields(this, new ObjectMapper());
//	}
//
//	@Test
//	void getDataHSSK0() throws Exception {
//		Authentication authentication = null;
//		Optional lang = Optional.empty();
//
//		//mock method
//		PatientDTO resultData1 = new PatientDTO();
//		resultData1.setPid("1");
//		resultData1.setPatientId(1);
//		when(hsskService.getPatientInfo(Mockito.any(),Mockito.any())).thenReturn(resultData1);
//		List resultData2 = null;
//		when(hsskService.getDiseaseHistoryInfo(Mockito.any(),Mockito.any())).thenReturn(resultData2);
//		List resultData3 = null;
//		when(hsskService.getDiseaseHistoryFamilyInfo(Mockito.any(),Mockito.any())).thenReturn(resultData3);
//		List resultData4 = null;
//		when(hsskService.getMedicalAllergyInfo(Mockito.any(),Mockito.any())).thenReturn(resultData4);
//		List resultData5 = null;
//		when(hsskService.getPresurgeryInfo(Mockito.any(),Mockito.any())).thenReturn(resultData5);
//		List resultData6 = null;
//		when(hsskService.getImmunizationInfo(Mockito.any(),Mockito.any())).thenReturn(resultData6);
//		ResultSelectEntity resultData7 = new ResultSelectEntity();
//		when(hsskService.getPatientHistoryInfo(Mockito.any(),Mockito.any())).thenReturn(resultData7);
//		MockHttpServletResponse responseActual = mvc.perform(
//			get("/getDataHSSK")
//			.accept(MediaType.APPLICATION_JSON)
//			.contentType(MediaType.APPLICATION_JSON))
//			.andReturn().getResponse();
//
//		// assert result
//		assertThat(responseActual.getStatus(), Matchers.equalTo(HttpStatus.OK.value()));
//	}
//
//	@Test
//	void getDataHSSK0ThrowException2() throws Exception {
//		Authentication authentication = null;
//		Optional lang = Optional.empty();
//
//		//mock method
//		when(hsskService.getPatientInfo(Mockito.any(),Mockito.any())).thenThrow(new TeleCareException(ErrorApp.ERR_USER_NOT_PERMISSION));
//		MockHttpServletResponse responseActual = mvc.perform(
//			get("/getDataHSSK")
//			.accept(MediaType.APPLICATION_JSON)
//			.contentType(MediaType.APPLICATION_JSON))
//			.andReturn().getResponse();
//
//		// assert result
//		assertThat(responseActual.getStatus(), Matchers.equalTo(HttpStatus.BAD_REQUEST.value()));
//	}
//
//}