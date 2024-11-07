package com.mark.amoCrmTask.dto.request.apiAmoCrm.deserialize;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ApiResponseContact {
    @JsonProperty("_embedded")
    private Embedded embedded;

    @Data
    public static class Embedded {
        @JsonProperty("contacts")
        private Contact[] contacts;
    }

    @Data
    public static class Contact {
        @JsonProperty("id")
        private Long id;
    }
}
