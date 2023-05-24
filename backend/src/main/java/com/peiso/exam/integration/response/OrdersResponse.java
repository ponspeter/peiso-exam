package com.peiso.exam.integration.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrdersResponse {

    @JsonProperty("id")
    private int id;

    @JsonProperty("status")
    private String status;

    @JsonProperty("notes")
    private String notes;

    @JsonProperty("site_id")
    private int siteId;

    @JsonProperty("register_id")
    private int registerId;

    @JsonProperty("staff_id")
    private int staffId;

    @JsonProperty("total")
    private double total;

    @JsonProperty("total_tax")
    private double totalTax;

    @JsonProperty("paid")
    private int paid;

    @JsonProperty("tips")
    private int tips;

    @JsonProperty("deleted")
    private boolean deleted;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("updated_at")
    private String updatedAt;
}
