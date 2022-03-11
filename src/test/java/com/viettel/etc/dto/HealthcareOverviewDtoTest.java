package com.viettel.etc.dto;import lombok.Data;import java.util.Date;import java.util.HashMap;import com.viettel.etc.dto.HealthcareOverviewDto;
import static org.hamcrest.MatcherAssert.assertThat;
import com.google.code.beanmatchers.BeanMatchers;
import com.google.code.beanmatchers.ValueGenerator;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.hamcrest.Matchers;
import static org.hamcrest.CoreMatchers.allOf;
import static com.google.code.beanmatchers.BeanMatchers.*;
import org.junit.jupiter.api.Test;

class HealthcareOverviewDtoTest {

	@BeforeEach
	void setUp() {
	}

	@Test
	void HealthcareOverviewDto (){
		assertThat(HealthcareOverviewDto.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(HealthcareOverviewDto.HealthcareIndex.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(HealthcareOverviewDto.Patient.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
	}

}