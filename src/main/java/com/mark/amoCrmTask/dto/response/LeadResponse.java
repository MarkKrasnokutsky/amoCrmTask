package com.mark.amoCrmTask.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LeadResponse {
    private Long lead_id;
    private String leadName;
    private Long price;
}

