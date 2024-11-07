package com.mark.amoCrmTask.service;

import com.mark.amoCrmTask.dto.request.ContactRequest;
import com.mark.amoCrmTask.dto.request.apiAmoCrm.CustomField;
import com.mark.amoCrmTask.dto.request.apiAmoCrm.contact.Contact;
import com.mark.amoCrmTask.dto.request.apiAmoCrm.deserialize.ApiResponseContact;
import com.mark.amoCrmTask.dto.response.ContactResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class ContactService {

    private final RestTemplate restTemplate;

    @Value("${amo-crm.url}")
    String url;

    @Value("${amo-crm.token}")
    String token;

    public ResponseEntity<ContactResponse> addContact(ContactRequest contactRequest) {
        String postUrl = url + "/contacts";

        Contact contact = new Contact(contactRequest.getName(), Arrays.asList(
                new CustomField(2123171, Arrays.asList(new com.mark.amoCrmTask.dto.request.apiAmoCrm.Value(contactRequest.getPhone()))),
                new CustomField(2123243, Arrays.asList(new com.mark.amoCrmTask.dto.request.apiAmoCrm.Value(contactRequest.getEmail())))
        ));
        Contact[] contacts = new Contact[]{contact};
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        headers.set("Content-Type", "application/json");

        HttpEntity<Contact[]> requestEntity = new HttpEntity<>(contacts, headers);
        ResponseEntity<ApiResponseContact> response = restTemplate.exchange(postUrl, HttpMethod.POST, requestEntity, ApiResponseContact.class);

        Long contactId = response.getBody().getEmbedded().getContacts()[0].getId();

        ContactResponse contactResponse = ContactResponse.builder()
                .id(contactId)
                .name(contactRequest.getName())
                .email(contactRequest.getEmail())
                .phone(contactRequest.getPhone())
                .build();
        return ResponseEntity.ok(contactResponse);
    }

}
