package com.academy.certificate.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class GithubResponse {
    @JsonProperty("access_token")
    private String accessToken;

    private String scope;

    @JsonProperty("token_type")
    private String tokenType;
}
