package com.viettel.etc.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.Gson;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.viettel.etc.controllers.MedicalHealthcareAllergyController;
import com.viettel.etc.dto.PatientRequestDTO;
import com.viettel.etc.dto.RequestDTO;
import com.viettel.etc.dto.ResponseDTO;
import com.viettel.etc.utils.Constants;
import com.viettel.etc.utils.ErrorApp;
import com.viettel.etc.utils.FnCommon;
import com.viettel.etc.utils.TeleCareException;
import org.apache.log4j.Logger;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Objects;

@Service
public class KeycloakService {

    private static final Logger LOGGER = Logger.getLogger(MedicalHealthcareAllergyController.class);

    public String createUserInKeyCloak(PatientRequestDTO patientDTO) throws TeleCareException {
        String url = System.getenv("Keycloak") + FnCommon.getPropertiesValue("ws.keycloak.register.user");
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(Objects.requireNonNull(FnCommon.getAdminToken()));

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode body = objectMapper.createObjectNode();
        body.put("email", patientDTO.getEmail());
        body.put("username", patientDTO.getPhoneNumber());
        body.put("firstName", patientDTO.getFullname());
        body.put("lastName", "");
        body.put("enabled", true);

        ArrayNode credentials = objectMapper.createArrayNode();
        ObjectNode password = objectMapper.createObjectNode();
        password.put("type", "password");
        password.put("value", patientDTO.getPassword());
        credentials.add(password);

        body.put("credentials", credentials);
        String userId = "";
        ResponseEntity<ObjectNode> exchange;
        try {
            exchange = restTemplate.exchange(url,
                    HttpMethod.POST, new HttpEntity<>(body, headers),
                    ObjectNode.class);
            for (String location : Objects.requireNonNull(exchange.getHeaders().get("Location"))) {
                String[] split = location.split("/");
                userId = split[split.length - 1];
            }
        } catch (Exception e) {
            LOGGER.info(e);
            FnCommon.throwsErrorApp(ErrorApp.ERROR_DATA_PHONE_NUMBER_EXIST);
//            LOG.error("Has ERROR", e);
        }
        return userId;
    }

    /**
     * khoa tai khoan user
     *
     * @param params params client
     * @param authentication thong tin user
     * @return thanh cong
     */
    public Object lockUser(String userId, RequestDTO params, Authentication authentication) throws TeleCareException, IOException {
        String lockUserUrl = System.getenv("Keycloak") + FnCommon.getPropertiesValue("ws.keycloak.lock.user");
//        String token = FnCommon.getStringToken(authentication);
        // TODO @tungvv2 bao giờ phân quyền thì tính sau
        String token = FnCommon.getAdminToken();
        // lock user => enabled = false
        params.setEnabled(false);
        String url = lockUserUrl.replace("{userId}", userId);
        RequestBody body = RequestBody.create(Constants.JSON, FnCommon.toStringJson(params));
        Response response = FnCommon.doPutRequest(url, token, body);
        ResponseDTO responseDTO = new Gson().fromJson(response.body().string(), ResponseDTO.class);
        if (responseDTO == null) {
            responseDTO = new ResponseDTO();
        }
        responseDTO.setCode(response.code());
        responseDTO.setMessage("Lock user thành công");
        return responseDTO;
    }

    /**
     * reset password user
     *
     * @param params params client
     * @param authentication thong tin user
     * @return thanh cong
     */
    public Object resetPassword(String userId, RequestDTO params, Authentication authentication) throws TeleCareException, IOException {
        String resetPasswordUrl = System.getenv("Keycloak") + FnCommon.getPropertiesValue("ws.keycloak.resetpassword.user");
//        String token = FnCommon.getStringToken(authentication);
        // TODO @tungvv2 bao giờ phân quyền thì tính sau
        String token = FnCommon.getAdminToken();
        params.setTemporary(false);
        params.setType("password");
        params.setValue(params.getValue());
        String url = resetPasswordUrl.replace("{userId}", userId);
        RequestBody body = RequestBody.create(Constants.JSON, FnCommon.toStringJson(params));
        Response response = FnCommon.doPutRequest(url, token, body);
        ResponseDTO responseDTO = new Gson().fromJson(response.body().string(), ResponseDTO.class);
        if (responseDTO == null) {
            responseDTO = new ResponseDTO();
        }
        responseDTO.setCode(response.code());
        responseDTO.setMessage("Reset password thành công");
        return responseDTO;
    }
}
