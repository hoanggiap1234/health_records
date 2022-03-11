package com.viettel.etc.kafka.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import com.viettel.etc.kafka.domain.ConsultantResultDTO;
import static org.hamcrest.MatcherAssert.assertThat;
import com.google.code.beanmatchers.BeanMatchers;
import com.google.code.beanmatchers.ValueGenerator;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.hamcrest.Matchers;
import static org.hamcrest.CoreMatchers.allOf;
import static com.google.code.beanmatchers.BeanMatchers.*;
import org.junit.jupiter.api.Test;

class ConsultantResultDTOTest {

	@BeforeEach
	void setUp() {
	}

	@Test
	void ConsultantResultDTO (){
		assertThat(ConsultantResultDTO.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(ConsultantResultDTO.LivingFunction.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSettersExcluding(new String[]{"bmi"})));
		assertThat(ConsultantResultDTO.Drug.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(ConsultantResultDTO.Attacments.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(ConsultantResultDTO.Diagnostic.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(ConsultantResultDTO.Diseases.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(ConsultantResultDTO.ServiceIndex.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(ConsultantResultDTO.Subclinical.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
	}

}