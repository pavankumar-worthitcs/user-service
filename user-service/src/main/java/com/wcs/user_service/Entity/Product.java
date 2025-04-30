package com.wcs.user_service.Entity;
import lombok.*;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
public class Product {
    private Long productId;
    private Long userId;
    private String productName;
    private String paymentType;

}

