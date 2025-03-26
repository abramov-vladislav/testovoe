package org.abramov.spring.testovoe.userservice.mapper;

import io.micrometer.observation.annotation.Observed;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;


@Mapper
@Observed
@Component
public interface UserMapper {
}
