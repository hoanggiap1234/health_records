package com.viettel.etc.kafka.multipleconsumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.viettel.etc.controllers.MedicalHealthcareAllergyController;
import com.viettel.etc.kafka.domain.ConsultantResultDTO;
import com.viettel.etc.kafka.domain.TopicInfor;
import com.viettel.etc.services.tables.MedicalHealthcareHistoriesServiceJPA;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SaveConsultantResultConsummer extends ConsumerThread {
	@Autowired
	MedicalHealthcareHistoriesServiceJPA medicalHealthcareHistoriesServiceJPA;

	private static final Logger LOGGER = Logger.getLogger(MedicalHealthcareAllergyController.class);

	@Override
	public void setVariable(TopicInfor topicInfor, String bootstrapServer) throws IOException {
		super.setVariable(topicInfor, bootstrapServer);
	}

	@Override
	public void handleRecords(ConsumerRecords<String, String> records) {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		for (ConsumerRecord<String, String> record : records) {
			System.out.println("==========Save health care consultant=========" + record.key());
			try {
				ConsultantResultDTO bookingInformationsEntity = objectMapper.readValue(record.value(), ConsultantResultDTO.class);
				medicalHealthcareHistoriesServiceJPA.saveHealthcareHistories(Integer.parseInt(record.key()), bookingInformationsEntity);
			} catch (JsonProcessingException e) {
				LOGGER.info(e);
				e.printStackTrace();
			} catch (Exception e) {
				LOGGER.info(e);
				e.printStackTrace();
			}
		}
	}
}
