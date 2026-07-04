package com.example.controller;

import com.example.dto.UserDto;
import com.example.assemblers.UserModelAssembler;
import com.example.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/users")
@Tag(name = "User API", description = "CRUD операции с пользователями с HATEOAS и документацией Swagger Проверка")
public class UserController {

    private final UserService userService;
    private final UserModelAssembler userModelAssembler;

    public UserController(UserService userService, UserModelAssembler userModelAssembler) {
        this.userService = userService;
        this.userModelAssembler = userModelAssembler;
    }

    @Operation(summary = "Получить список всех пользователей с навигационными ссылками")
    @GetMapping
    public CollectionModel<EntityModel<UserDto>> getAll() {
        List<EntityModel<UserDto>> users = userService.getAllUsers().stream()
            .map(userModelAssembler::toModel)
            .collect(Collectors.toList());

        return CollectionModel.of(users,
            linkTo(methodOn(UserController.class).getAll()).withSelfRel());
    }

    @Operation(summary = "Получить пользователя по ID с навигационными ссылками")
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<UserDto>> getById(@PathVariable Long id) {
        UserDto userDto = userService.getUserById(id);
        return ResponseEntity.ok(userModelAssembler.toModel(userDto));
    }

    @Operation(summary = "Создать нового пользователя")
    @PostMapping
    public ResponseEntity<EntityModel<UserDto>> createUser(@RequestBody UserDto dto) {
        UserDto createdUser = userService.createUser(dto);
        return ResponseEntity
                .created(URI.create("/users/" + createdUser.getId()))
                .body(userModelAssembler.toModel(createdUser));
    }

    @Operation(summary = "Обновить пользователя по ID")
    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<UserDto>> updateUser(@PathVariable Long id, @RequestBody UserDto dto) {
        UserDto updatedUser = userService.updateUser(id, dto);
        return ResponseEntity.ok(userModelAssembler.toModel(updatedUser));
    }

    @Operation(summary = "Удалить пользователя по ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}


