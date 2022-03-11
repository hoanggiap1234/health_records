package com.viettel.etc.dto;import com.fasterxml.jackson.annotation.JsonInclude;import com.fasterxml.jackson.annotation.JsonInclude.Include;import java.util.Date;import lombok.Data;import lombok.NoArgsConstructor;import com.viettel.etc.dto.MedicalHealthcareServiceResultDTO;
import static org.hamcrest.MatcherAssert.assertThat;
import com.google.code.beanmatchers.BeanMatchers;
import com.google.code.beanmatchers.ValueGenerator;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.hamcrest.Matchers;
import static org.hamcrest.CoreMatchers.allOf;
import static com.google.code.beanmatchers.BeanMatchers.*;
import org.junit.jupiter.api.Test;

class MedicalHealthcareServiceResultDTOTest {

	@BeforeEach
	void setUp() {
	}

	@Test
	void MedicalHealthcareServiceResultDTO (){
		assertThat(MedicalHealthcareServiceResultDTO.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
	}

}