package org.abramov.spring.testovoe.userservice.dto.request;

import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserDto {

    private String username;
    private String userLastName;
    private String userFirstName;
}