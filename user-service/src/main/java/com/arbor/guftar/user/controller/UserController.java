package com.arbor.guftar.user.controller;

import com.arbor.guftar.common.dto.PaginatedResponse;
import com.arbor.guftar.common.dto.SuccessResponse;
import com.arbor.guftar.user.dto.CreateUserRequest;
import com.arbor.guftar.user.dto.CreateUserResponse;
import com.arbor.guftar.user.dto.UpdateUserDto;
import com.arbor.guftar.user.dto.UserResponse;
import com.arbor.guftar.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<PaginatedResponse<UserResponse>> findUsers(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(userService.findAllUsers(pageable));
    }

    @PostMapping
    public ResponseEntity<CreateUserResponse> createUser(@Valid @RequestBody CreateUserRequest userRequest) {
        return new ResponseEntity<>(userService.create(userRequest), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long id, @RequestBody UpdateUserDto updateUserDto) {
        return ResponseEntity.ok(userService.updateUser(id, updateUserDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessResponse> deleteUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.deleteUser(id));
    }
}
