// UserModelAssembler.java
package com.example.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import com.example.controller.UserController;
import com.example.dto.UserDto;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class UserModelAssembler implements RepresentationModelAssembler<UserDto, EntityModel<UserDto>> {
    @Override
    public EntityModel<UserDto> toModel(UserDto userDto) {
        return EntityModel.of(userDto,
            linkTo(methodOn(UserController.class).getById(userDto.getId())).withSelfRel(),
            linkTo(methodOn(UserController.class).getAll()).withRel("users"));
    }
}
