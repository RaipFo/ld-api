package org.piv.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventRequest {
    @NotNull(message = "Info is not be null")
    private String info;
    @NotNull(message = "Cost is not be null")
    private Long cost;
}
