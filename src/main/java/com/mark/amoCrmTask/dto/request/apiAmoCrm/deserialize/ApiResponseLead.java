package com.mark.amoCrmTask.dto.request.apiAmoCrm.deserialize;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiResponseLead {

    @JsonProperty("_links")
    private Links links;

    @JsonProperty("_embedded")
    private Embedded embedded;

    @Data
    public static class Links {
        private Self self;

        @Data
        public static class Self {
            private String href;
        }
    }

    @Data
    public static class Embedded {
        private Lead[] leads;
    }

    @Data
    public static class Lead {
        private Long id;
        private String request_id;

        @JsonProperty("_links")
        private LeadLinks links;

        @Data
        public static class LeadLinks {
            private Self self;

            @Data
            public static class Self {
                private String href;

            }
        }
    }
}
