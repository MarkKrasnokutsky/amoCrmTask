package com.mark.amoCrmTask.controller;

import com.mark.amoCrmTask.dto.request.ContactRequest;
import com.mark.amoCrmTask.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/contact")
public class ContactController {

    private final ContactService contactService;

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody ContactRequest req) {
        try {
            return ResponseEntity.ok(contactService.addContact(req));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }

}
