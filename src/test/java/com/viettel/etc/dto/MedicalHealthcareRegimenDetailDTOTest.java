package com.viettel.etc.dto;import com.fasterxml.jackson.annotation.JsonInclude;import com.viettel.etc.utils.ErrorApp;import com.viettel.etc.utils.FnCommon;import com.viettel.etc.utils.TeleCareException;import lombok.Data;import lombok.NoArgsConstructor;import java.util.Arrays;import java.util.Date;import java.util.List;import com.viettel.etc.dto.MedicalHealthcareRegimenDetailDTO;
import static org.hamcrest.MatcherAssert.assertThat;
import com.google.code.beanmatchers.BeanMatchers;
import com.google.code.beanmatchers.ValueGenerator;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.hamcrest.Matchers;
import static org.hamcrest.CoreMatchers.allOf;
import static com.google.code.beanmatchers.BeanMatchers.*;
import org.junit.jupiter.api.Test;

class MedicalHealthcareRegimenDetailDTOTest {

	@BeforeEach
	void setUp() {
	}

	@Test
	void MedicalHealthcareRegimenDetailDTO (){
		assertThat(MedicalHealthcareRegimenDetailDTO.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
	}

}