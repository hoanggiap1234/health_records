package com.viettel.etc.kafka.common;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.viettel.etc.kafka.domain.ConsumerModel;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.hamcrest.MatcherAssert;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import com.viettel.etc.kafka.common.KafkaUtils;
import static org.hamcrest.MatcherAssert.assertThat;
import com.google.code.beanmatchers.BeanMatchers;
import com.google.code.beanmatchers.ValueGenerator;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.hamcrest.Matchers;
import static org.hamcrest.CoreMatchers.allOf;
import static com.google.code.beanmatchers.BeanMatchers.*;
import org.junit.jupiter.api.Test;

class KafkaUtilsTest {

	KafkaUtils kafkaUtils = new KafkaUtils();

	@BeforeEach
	void setUp() {
	}

	@Test
	void getConfigYaml() {
	}

	@Test
	void createConsumerConfig() throws IOException {
		String nameFile = "configTopics/consummer";
		String bootstrapServer = "localhost:9093";
		MatcherAssert.assertThat(kafkaUtils.createConsumerConfig(nameFile,bootstrapServer).getProperty(ConsumerConfig.GROUP_ID_CONFIG), Matchers.equalTo("health_records"));
	}
}