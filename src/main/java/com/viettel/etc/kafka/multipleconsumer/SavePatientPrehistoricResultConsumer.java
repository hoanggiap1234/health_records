package com.viettel.etc.kafka.multipleconsumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.viettel.etc.controllers.MedicalHealthcareAllergyController;
import com.viettel.etc.dto.MedicalHealthcarePatientSummaryDTO;
import com.viettel.etc.kafka.domain.TopicInfor;
import com.viettel.etc.repositories.tables.entities.MedicalHealthcarePrehistoricEntity;
import com.viettel.etc.services.tables.MedicalHealthcarePatientSummaryServiceJPA;
import com.viettel.etc.services.tables.MedicalHealthcarePrehistoricServiceJPA;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SavePatientPrehistoricResultConsumer extends ConsumerThread {
    @Autowired
    MedicalHealthcarePrehistoricServiceJPA medicalHealthcarePrehistoricServiceJPA;

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
            try {
                MedicalHealthcarePrehistoricEntity medicalHealthcarePrehistoricEntity = objectMapper.readValue(record.value(), MedicalHealthcarePrehistoricEntity.class);
                medicalHealthcarePrehistoricServiceJPA.save(medicalHealthcarePrehistoricEntity);
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
