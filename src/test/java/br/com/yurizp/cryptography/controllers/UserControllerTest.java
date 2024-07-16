package br.com.yurizp.cryptography.controllers;

import br.com.yurizp.cryptography.controllers.rest.UserController;
import br.com.yurizp.cryptography.controllers.rest.error.GlobalExceptionHandler;
import br.com.yurizp.cryptography.domain.dto.UserDTO;
import br.com.yurizp.cryptography.domain.dto.imp.UserDTOStub;
import br.com.yurizp.cryptography.domain.error.imp.BadRequestError;
import br.com.yurizp.cryptography.domain.error.Error;
import br.com.yurizp.cryptography.domain.error.ErrorBuilder;
import br.com.yurizp.cryptography.domain.error.imp.NotFoundError;
import br.com.yurizp.cryptography.domain.port.UserServicePort;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    private final String BASE_URL = "/v1/users";
    private MockMvc mockMvc;
    @Mock
    private UserServicePort userServicePort;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        this.objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders.standaloneSetup(new UserController(userServicePort))
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @Test
    void shouldReturnUserById() throws Throwable {
        UserDTO userDto = UserDTOStub.create();
        when(userServicePort.findById(anyLong())).thenReturn(userDto);

        mockMvc.perform(get(BASE_URL.concat("/123")).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(OK.value()))
                .andExpect(jsonPath("$.id").value(userDto.getId()))
                .andExpect(jsonPath("$.userDocument").value(userDto.getUserDocument()))
                .andExpect(jsonPath("$.creditCardToken").value(userDto.getCreditCardToken()))
                .andExpect(jsonPath("$.value").value(userDto.getValue()));
    }

    @Test
    void shouldReturnNotFoundErrorWhenUserByIdNotExists() throws Throwable {
        Error error = ErrorBuilder.builder().message("not.found.message").code("not.found.errorCode").build(NotFoundError.class);
        when(userServicePort.findById(anyLong())).thenThrow(error);

        mockMvc.perform(get(BASE_URL.concat("/123")).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(NOT_FOUND.value()))
                .andExpect(jsonPath("$.code").value(error.getSimpleError().code()))
                .andExpect(jsonPath("$.message").value(error.getSimpleError().message()));
    }

    @Test
    void shouldReturnErrorWhenCreateUserThrowException() throws Throwable {
        Error error = ErrorBuilder.builder().message("conflict.message").code("conflict.errorCode").build(BadRequestError.class);
        when(userServicePort.saveOrUpdade(any())).thenThrow(error);

        mockMvc.perform(post(BASE_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(UserDTOStub.createWithoutId()))
                )
                .andExpect(status().is(BAD_REQUEST.value()))
                .andExpect(jsonPath("$.code").value(error.getSimpleError().code()))
                .andExpect(jsonPath("$.message").value(error.getMessage()));
    }

    @Test
    void shouldCreateUser() throws Throwable {
        UserDTO userDto = UserDTOStub.createWithoutId();
        when(userServicePort.saveOrUpdade(any())).thenReturn(userDto);

        mockMvc.perform(post(BASE_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDto))
                )
                .andExpect(status().is(CREATED.value()))
                .andExpect(jsonPath("$.id").value(userDto.getId()))
                .andExpect(jsonPath("$.userDocument").value(userDto.getUserDocument()))
                .andExpect(jsonPath("$.creditCardToken").value(userDto.getCreditCardToken()))
                .andExpect(jsonPath("$.value").value(userDto.getValue()));
    }

    @Test
    void shouldReturnErrorWhenUserHasIdInRequestBody() throws Throwable {
        mockMvc.perform(post(BASE_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(UserDTOStub.create()))
                )
                .andExpect(status().is(BAD_REQUEST.value()))
                .andExpect(jsonPath("$.datails[0].code").value("invalid.arguments"))
                .andExpect(jsonPath("$.datails[0].message").value("id: Not allow send Id."));
    }

}