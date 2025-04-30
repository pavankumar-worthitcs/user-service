package com.wcs.user_service.Entity;

import lombok.*;

import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class UserDTORequest {


        private String name;
        private String job;


}
