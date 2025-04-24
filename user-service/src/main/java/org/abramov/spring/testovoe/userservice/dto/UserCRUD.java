package org.abramov.spring.testovoe.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class UserCRUD {

    private UUID id;
    private String username;
    private String userFirstName;
    private String userLastName;

    public boolean isValid() {
        return this.getId() != null
                && this.getUsername() != null && !this.getUsername().isBlank()
                && this.getUserFirstName() != null && !this.getUserFirstName().isBlank()
                && this.getUserLastName() != null && !this.getUserLastName().isBlank();
    }
}
