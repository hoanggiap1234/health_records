package com.viettel.etc.kafka.service;
import static org.hamcrest.MatcherAssert.assertThat;
import com.google.code.beanmatchers.BeanMatchers;
import com.google.code.beanmatchers.ValueGenerator;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.hamcrest.Matchers;
import static org.hamcrest.CoreMatchers.allOf;
import static com.google.code.beanmatchers.BeanMatchers.*;
import org.junit.jupiter.api.Test;

class KafkaConfigTest {

	@BeforeEach
	void setUp() {
	}

	@Test
	void KafkaConfig (){
		assertThat(KafkaConfig.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(KafkaConfig.ToppicName.values().length, Matchers.greaterThanOrEqualTo(1));
	}

}