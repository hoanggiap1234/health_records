package com.viettel.etc.dto;import com.fasterxml.jackson.annotation.JsonInclude;import lombok.Data;import lombok.NoArgsConstructor;import com.viettel.etc.dto.VaccineResponseDTO;
import static org.hamcrest.MatcherAssert.assertThat;
import com.google.code.beanmatchers.BeanMatchers;
import com.google.code.beanmatchers.ValueGenerator;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.hamcrest.Matchers;
import static org.hamcrest.CoreMatchers.allOf;
import static com.google.code.beanmatchers.BeanMatchers.*;
import org.junit.jupiter.api.Test;

class VaccineResponseDTOTest {

	@BeforeEach
	void setUp() {
	}

	@Test
	void VaccineResponseDTO (){
		assertThat(VaccineResponseDTO.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(VaccineResponseDTO.VaccineSubDTO.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
	}

}