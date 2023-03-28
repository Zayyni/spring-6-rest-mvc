package com.zayyni8.spring6restmvc.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;
/*CODED BY ZAYYNI
 * */

@Data
@Builder
public class CustomerDTO {
    private UUID id;
    private String name;

    private Integer version;

    private LocalDateTime createdDate;
    private LocalDateTime updateDate;
}
