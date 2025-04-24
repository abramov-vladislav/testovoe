package org.abramov.spring.testovoe.userservice.client.kafka.model.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.abramov.spring.testovoe.userservice.dto.UserCRUD;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class CRUDMessageUser {

    private List<UserCRUD> objects;

}
