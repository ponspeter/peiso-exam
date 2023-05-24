package com.peiso.exam.integration.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {

    @JsonProperty("id")
    private int id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String description;

    @JsonProperty("code")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String code;

    @JsonProperty("number")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String number;

    @JsonProperty("is_modifier")
    private boolean isModifier;

    @JsonProperty("show_online")
    private boolean showOnline;

    @JsonProperty("friendly_name")
    private String friendlyName;

    @JsonProperty("always_prompt")
    private boolean alwaysPrompt;
}
