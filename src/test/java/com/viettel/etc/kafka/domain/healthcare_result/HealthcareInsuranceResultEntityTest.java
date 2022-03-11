package com.viettel.etc.kafka.domain.healthcare_result;import com.fasterxml.jackson.annotation.JsonIgnoreProperties;import com.fasterxml.jackson.annotation.JsonProperty;import lombok.Data;import lombok.NoArgsConstructor;import javax.validation.Valid;import java.util.List;import com.viettel.etc.kafka.domain.healthcare_result.HealthcareInsuranceResultEntity;
import static org.hamcrest.MatcherAssert.assertThat;
import com.google.code.beanmatchers.BeanMatchers;
import com.google.code.beanmatchers.ValueGenerator;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.hamcrest.Matchers;
import static org.hamcrest.CoreMatchers.allOf;
import static com.google.code.beanmatchers.BeanMatchers.*;
import org.junit.jupiter.api.Test;

class HealthcareInsuranceResultEntityTest {

	@BeforeEach
	void setUp() {
	}

	@Test
	void HealthcareInsuranceResultEntity (){
		assertThat(HealthcareInsuranceResultEntity.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
	}

}