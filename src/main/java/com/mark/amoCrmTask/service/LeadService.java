package com.mark.amoCrmTask.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mark.amoCrmTask.dto.request.LeadRequest;
import com.mark.amoCrmTask.dto.request.apiAmoCrm.deserialize.ApiResponseLead;
import com.mark.amoCrmTask.dto.request.apiAmoCrm.deserialize.LeadRequestPayload;
import com.mark.amoCrmTask.dto.response.LeadResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class LeadService {

    @Value("${amo-crm.url}")
    String url;

    @Value("${amo-crm.token}")
    String token;

    private final RestTemplate restTemplate;

    public ResponseEntity<LeadResponse> createLead(LeadRequest leadRequest) {
        String postUrl = url + "/leads";

        LeadRequestPayload leadRequestPayload = new LeadRequestPayload();
        leadRequestPayload.setName(leadRequest.getLeadName());
        leadRequestPayload.setPipeline_id(leadRequest.getPipeline_id());
        leadRequestPayload.setPrice(leadRequest.getPrice());

        LeadRequestPayload.CustomFieldValue customFieldValue = new LeadRequestPayload.CustomFieldValue();
        customFieldValue.setFieldId(2123701L);

        LeadRequestPayload.CustomFieldValue.Value value = new LeadRequestPayload.CustomFieldValue.Value();
        value.setValue(leadRequest.isFlag());

        customFieldValue.setValues(Collections.singletonList(value));
        leadRequestPayload.setCustomFieldsValues(Collections.singletonList(customFieldValue));

        LeadRequestPayload.Embedded embedded = new LeadRequestPayload.Embedded();
        LeadRequestPayload.Embedded.Contact contact = new LeadRequestPayload.Embedded.Contact();
        contact.setId(leadRequest.getContact_id());
        embedded.setContacts(Collections.singletonList(contact));
        leadRequestPayload.setEmbedded(embedded);

        LeadRequestPayload[] reqs = new LeadRequestPayload[]{leadRequestPayload};

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        headers.set("Content-Type", "application/json");

        HttpEntity<LeadRequestPayload[]> requestEntity = new HttpEntity<>(reqs, headers);

        // Отправляем POST-запрос и получаем ответ
        ResponseEntity<String> responseEntity = restTemplate.exchange(postUrl, HttpMethod.POST, requestEntity, String.class);

        ApiResponseLead apiResponse = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            apiResponse = objectMapper.readValue(responseEntity.getBody(), ApiResponseLead.class);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Возвращаем ошибку при исключении
        }

        Long leadId = null;
        if (apiResponse != null && apiResponse.getEmbedded() != null && apiResponse.getEmbedded().getLeads() != null) {
            leadId = apiResponse.getEmbedded().getLeads()[0].getId(); // Предполагаем, что нам нужен первый элемент
        }
        LeadResponse leadResponse = LeadResponse.builder()
                .price(leadRequest.getPrice())
                .leadName(leadRequest.getLeadName())
                .lead_id(leadId)
                .build();
        return ResponseEntity.ok(leadResponse);
    }
}
