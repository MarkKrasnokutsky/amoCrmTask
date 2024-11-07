package com.mark.amoCrmTask.dto.request.apiAmoCrm.deserialize;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class LeadRequestPayload {
    private String name;
    private Long pipeline_id;
    private Long price;

    @JsonProperty("custom_fields_values")
    private List<CustomFieldValue> customFieldsValues;

    @JsonProperty("_embedded")
    private Embedded embedded;

    @Data
    public static class Embedded {
        private List<Contact> contacts;

        @Data
        public static class Contact {
            private Long id;
        }
    }

    @Data
    public static class CustomFieldValue {
        @JsonProperty("field_id")
        private Long fieldId;

        private List<Value> values;

        @Data
        public static class Value {
            private Boolean value;
        }
    }
}
