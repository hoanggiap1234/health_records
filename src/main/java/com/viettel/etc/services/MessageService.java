package com.viettel.etc.services;


import com.viettel.etc.dto.MessageDTO;
import com.viettel.etc.repositories.tables.entities.CatsMessagesEntity;
import com.viettel.etc.services.tables.CatsMessagesServiceJPA;
import com.viettel.etc.utils.Constants;
import com.viettel.etc.utils.FnCommon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MessageService {

    @Autowired
    CatsMessagesServiceJPA messageServiceJPA;

    public Map<String, MessageDTO> messagesMap = new HashMap<>();

    @PostConstruct
    public void setMessages() {
        if (messagesMap.isEmpty()) {
            initializeMessages();
        }
    }

    public void initializeMessages() {
        List<CatsMessagesEntity> messages = messageServiceJPA.getAllMessages();
        for (CatsMessagesEntity message : messages) {
            messagesMap.put(message.getMessageCode(), (MessageDTO) FnCommon.convertObjectToObject(message, MessageDTO.class));
        }
    }

    public MessageDTO getMessage(String messageCode, String language) {
        if (!(language.equals(Constants.ENGLISH_CODE) || language.equals(Constants.VIETNAM_CODE))) {
            return null;
        }
        MessageDTO messageDTO = new MessageDTO();
        for (Map.Entry<String, MessageDTO> entry : messagesMap.entrySet()) {
            if (entry.getKey().equals(messageCode)) {
                messageDTO = (MessageDTO) FnCommon.convertObjectToObject(entry.getValue(), MessageDTO.class);
                break;
            }
        }
        if (messageDTO != null) {
            if (language.equals(Constants.VIETNAM_CODE)) {
                messageDTO.setMessageEn(null);
                messageDTO.setDescription(messageDTO.getMessageVi());
            }
            else if (language.equals(Constants.ENGLISH_CODE)) {
                messageDTO.setMessageVi(null);
                messageDTO.setDescription(messageDTO.getMessageEn());
            }
            return messageDTO;
        }
        return null;
    }

}
