package com.viettel.etc.dto;
import static org.hamcrest.MatcherAssert.assertThat;
import com.google.code.beanmatchers.BeanMatchers;
import com.google.code.beanmatchers.ValueGenerator;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.hamcrest.Matchers;
import static org.hamcrest.CoreMatchers.allOf;
import static com.google.code.beanmatchers.BeanMatchers.*;
import org.junit.jupiter.api.Test;

class PatientRequestDTOTest {

	@BeforeEach
	void setUp() {
	}

	@Test
	void PatientRequestDTO (){
		assertThat(PatientRequestDTO.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
	}

}