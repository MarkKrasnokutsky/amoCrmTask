package com.mark.amoCrmTask.dto.request;

import lombok.Data;

@Data
public class LeadRequest {
    private Long pipeline_id;
    private Long contact_id;
    private String leadName;
    private String price;
    private boolean flag;
}
