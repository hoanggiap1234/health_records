package com.viettel.etc.kafka.domain;import com.fasterxml.jackson.annotation.JsonCreator;import com.fasterxml.jackson.annotation.JsonProperty;import lombok.Data;import lombok.NoArgsConstructor;import com.viettel.etc.kafka.domain.ConsumerModel;
import static org.hamcrest.MatcherAssert.assertThat;
import com.google.code.beanmatchers.BeanMatchers;
import com.google.code.beanmatchers.ValueGenerator;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.hamcrest.Matchers;
import static org.hamcrest.CoreMatchers.allOf;
import static com.google.code.beanmatchers.BeanMatchers.*;
import org.junit.jupiter.api.Test;

class ConsumerModelTest {

	@BeforeEach
	void setUp() {
	}

	@Test
	void ConsumerModel (){
		assertThat(ConsumerModel.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
	}

}