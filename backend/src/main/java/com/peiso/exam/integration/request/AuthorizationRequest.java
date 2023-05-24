package com.peiso.exam.integration.request;

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
public class AuthorizationRequest {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("code")
    private String code;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("redirect_uri")
    private String redirectUri;

    @JsonProperty("client_id")
    private String clientId;

    @JsonProperty("client_secret")
    private String clientSecret;

    @JsonProperty("grant_type")
    private String grantType;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("refresh_token")
    private String refreshToken;
}
