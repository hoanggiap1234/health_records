package com.viettel.etc.kafka.service;import com.viettel.etc.utils.FnCommon;import org.apache.kafka.clients.admin.NewTopic;import org.apache.kafka.clients.consumer.ConsumerConfig;import org.apache.kafka.clients.consumer.KafkaConsumer;import org.apache.kafka.clients.producer.ProducerConfig;import org.springframework.context.annotation.Bean;import org.springframework.context.annotation.Configuration;import java.util.ArrayList;import java.util.Properties;import com.viettel.etc.kafka.service.KafkaConfig;
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