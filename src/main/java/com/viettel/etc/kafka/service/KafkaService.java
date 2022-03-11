package com.viettel.etc.kafka.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.viettel.etc.dto.CovidNotificationDTO;
import com.viettel.etc.dto.HealthRecordCallbackDTO;
import com.viettel.etc.utils.TeleCareException;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class KafkaService {

	private static Logger log = LoggerFactory.getLogger(KafkaService.class);
	@Autowired
	private Properties kafkaProducerStringProperties;

	public void sendString(String topic, Object key, Object value, Callback callBack) {
		ObjectMapper objectMapper = new ObjectMapper();
		KafkaProducer<String, String> kafkaProducer = new KafkaProducer(kafkaProducerStringProperties);
		try {
			kafkaProducer.send(new ProducerRecord(topic, (key instanceof String) ? key : objectMapper.writeValueAsString(key),
					(value instanceof String) ? value : objectMapper.writeValueAsString(value)), callBack);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			log.error("error convert object json ", e);
		}
		kafkaProducer.close();
	}

	public void healthRecordCallbackBooking(Integer bookingId, Integer historyId, Integer actionBy) throws JsonProcessingException {
		System.out.println("Push to toppic " + KafkaConfig.ToppicName.SaveHealthRecordCallbackBooking.val() + "========" + bookingId);
		HealthRecordCallbackDTO dto = new HealthRecordCallbackDTO(bookingId, historyId, actionBy);
		sendString(KafkaConfig.ToppicName.SaveHealthRecordCallbackBooking.val(), bookingId + "", dto, new Callback() {
			@Override
			public void onCompletion(RecordMetadata recordMetadata, Exception e) {
				if (e == null) {
					System.out.println("Push to toppic " + KafkaConfig.ToppicName.SaveHealthRecordCallbackBooking.val() + "========" + bookingId);
				} else {
					e.printStackTrace();
					System.out.println("False");
				}
			}
		});
	}

	public void sendCovidNoti(String phoneNumber, CovidNotificationDTO dto) throws TeleCareException {
		try {
			sendString(KafkaConfig.ToppicName.CovidNotificationTopic.val(), phoneNumber, dto, new Callback() {
				@Override
				public void onCompletion(RecordMetadata recordMetadata, Exception e) {
					if (e == null) {
						System.out.println("Send noti success");
					} else {
						e.printStackTrace();
						System.out.println("Send noti false");
					}
				}
			});
		} catch (Exception e) {
			log.info("error sending covid notification ", e);
			e.printStackTrace();
			throw new TeleCareException("Push kafka false");
		}
	}
}
