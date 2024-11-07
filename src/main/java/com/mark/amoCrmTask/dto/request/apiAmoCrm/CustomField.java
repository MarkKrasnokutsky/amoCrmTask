package com.mark.amoCrmTask.dto.request.apiAmoCrm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomField {
    private int field_id;
    private List<Value> values;
}
