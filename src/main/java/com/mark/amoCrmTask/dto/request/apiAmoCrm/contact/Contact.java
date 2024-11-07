package com.mark.amoCrmTask.dto.request.apiAmoCrm.contact;

import com.mark.amoCrmTask.dto.request.apiAmoCrm.CustomField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Contact {
    private String name;
    private List<CustomField> custom_fields_values;
}
