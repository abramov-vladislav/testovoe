package org.abramov.spring.testovoe.userservice.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserDto {

    private String username;
    private String userLastName;
    private String userFirstName;
}