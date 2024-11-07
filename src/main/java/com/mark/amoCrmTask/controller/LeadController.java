package com.mark.amoCrmTask.controller;

import com.mark.amoCrmTask.dto.request.ContactRequest;
import com.mark.amoCrmTask.dto.request.LeadRequest;
import com.mark.amoCrmTask.service.LeadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/lead")
public class LeadController {

    private final LeadService leadService;

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody LeadRequest req) {
        try {
            return ResponseEntity.ok(leadService.createLead(req));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }

}
