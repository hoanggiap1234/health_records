package com.viettel.etc.kafka.multipleconsumer;


import com.viettel.etc.kafka.common.FileUtils;
import com.viettel.etc.kafka.domain.TopicInfor;
import com.viettel.etc.kafka.domain.TopicModel;
import com.viettel.etc.kafka.service.KafkaConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MultipleConsumerMain {

	private static final Logger LOGGER = LogManager.getLogger(MultipleConsumerMain.class);
	@Value("${fileConfigTopic}")
	private String fileConfigTopic;
	@Value("${kafka.bootstrap-servers}")
	private String bootstrapServer;
	@Autowired
	private ThreadPoolTaskExecutor taskExecutor;
	@Autowired
	private ApplicationContext context;
	@Value("${importer.numberMinThread}")
	private Integer numberMinThread;
	@Value("${importer.numberMaxThread}")
	private Integer numberMaxThread;

	public void run() {
		taskExecutor.setCorePoolSize(numberMinThread);
		taskExecutor.setMaxPoolSize(numberMaxThread);
		taskExecutor.afterPropertiesSet();
		try {
			TopicModel topicModel = (TopicModel) FileUtils.getObjectFromJsonFile(fileConfigTopic, TopicModel.class);

			for (TopicInfor topicInfor : topicModel.getConfigInfor()) {
				ConsumerThread t;
				try {
					t = getConsumer(topicInfor, bootstrapServer);
					if (t != null) {
						t.setVariable(topicInfor, bootstrapServer);
						taskExecutor.execute(t);
					}

				} catch (IOException e) {
					LOGGER.error("Read file failed: " + e);
				} catch (Exception e) {
					LOGGER.error("Errors: " + e);
				}
			}
		} catch (IOException e) {
			LOGGER.error("Read file failed: " + e);
		}
	}

	public ConsumerThread getConsumer(TopicInfor topicInfor, String bootstrapServer) throws IOException {
		if (topicInfor.getTopicName().equals(KafkaConfig.ToppicName.BookingToHealthRecord.val())) {
			return (SaveConsultantResultConsummer) context.getBean("saveConsultantResultConsummer");
		} else if (topicInfor.getTopicName().equals(KafkaConfig.ToppicName.BookingInsuranceToHealthRecord.val())) {
			return (SaveHealthcareResultConsummer) context.getBean("saveHealthcareResultConsummer");
		} else if (topicInfor.getTopicName().equals(KafkaConfig.ToppicName.SavePatientCallBackHealthRecord.val())) {
			return (SavePatientSummaryResultConsummer) context.getBean("savePatientSummaryResultConsummer");
		} else if (topicInfor.getTopicName().equals(KafkaConfig.ToppicName.BookingInsuranceBhytToHealthRecord.val())) {
			return (SaveHealthcareResultBhxhConsummer) context.getBean("saveHealthcareResultBhxhConsummer");
		}
		else if (topicInfor.getTopicName().equals(KafkaConfig.ToppicName.SavePatientPreHistoricCallBackHealthRecord.val())) {
			return (SavePatientPrehistoricResultConsumer) context.getBean("savePatientPrehistoricResultConsumer");
		}
		return null;
	}
}
