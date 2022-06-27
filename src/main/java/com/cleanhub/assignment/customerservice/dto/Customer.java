package com.cleanhub.assignment.customerservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Data
@Builder
@Component
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Customer {

    String uuid;
    @JsonProperty("landingPageRoute")
    String name;

    @JsonProperty("logo")
    private void unpackNested(Map<String, Object> logo) {
        this.uuid = (String) logo.get("uuid");
    }
}
