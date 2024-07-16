package br.com.yurizp.cryptography.controllers;

import br.com.yurizp.cryptography.controllers.request.UserRequest;
import br.com.yurizp.cryptography.domain.dto.UserDto;
import br.com.yurizp.cryptography.domain.port.interfaces.UserServicePort;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    private final UserServicePort userService;

    public UserController(UserServicePort userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public UserDto findUserById(@PathVariable("id") String id) throws Throwable {
        return userService.getById(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createUser(@RequestBody @Validated UserRequest user) throws Throwable {
        return userService.create(user);
    }

}
