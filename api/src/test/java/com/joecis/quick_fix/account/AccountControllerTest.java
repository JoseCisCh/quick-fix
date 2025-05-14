package com.joecis.quick_fix.account;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.Mockito.when;

import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.joecis.quick_fix.DTO.AccountUserDto;
import com.joecis.quick_fix.DTO.LoginRequest;
import com.joecis.quick_fix.DTO.RegisterRequest;
import com.joecis.quick_fix.jwt.TokenService;
import com.joecis.quick_fix.role.Role;
import com.joecis.quick_fix.user.User;
import com.joecis.quick_fix.user.UserService;

@WebMvcTest(controllers = AccountController.class, excludeAutoConfiguration = { SecurityAutoConfiguration.class })
public class AccountControllerTest {

    @Autowired
    MockMvc mockMvc;

    /**
     * IMPORTANT
     * All AccountController dependencies that are injected into this class instance
     * must be declared as MockitoBean, even if in the test to be defined
     * they are not used.
     * Otherwise, the test wont be able to be ran.
     */
    @MockitoBean
    UserService userService;
    @MockitoBean
    AuthenticationManager authenticationManager;
    @MockitoBean
    TokenService tokenService;

    @Test
    @DisplayName("User registration with valid details")
    void testRegisterUser_whenValidPayloadIsProvided_shouldReturnAccountUserDto() throws Exception {
        // Arrange
        String email = "test_valid@gmail.com";
        String username = "test_valid@gmail.com";
        String firstName = "FirstName";
        String lastName = "LastName";
        String password = "c0mplexPassword#2023";

        RegisterRequest userDetails = new RegisterRequest(email, username, firstName, lastName, password);
        AccountUserDto accountUserDto = new AccountUserDto(username, firstName, lastName);

        when(userService.getByUsername(any(String.class))).thenReturn(null);
        when(userService.registerUser(any(RegisterRequest.class)))
                .thenReturn(accountUserDto);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(userDetails));

        // Act
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(result);
        String responseBody = result.getResponse().getContentAsString();
        AccountUserDto responseUserDto = new ObjectMapper()
                .readValue(responseBody, AccountUserDto.class);

        // Assert
        assertEquals(username,
                responseUserDto.getUsername(),
                "The returned username does not contain the expected value.");
        assertEquals(firstName,
                responseUserDto.getFirstName(),
                "The returned first name does not contain the expected value.");
        assertEquals(lastName,
                responseUserDto.getLastName(),
                "The returned last name does not contain the expected value.");

    }

    @Test
    @DisplayName("User registration with empty email")
    void testRegisterUser_whenEmptyEmailIsProvided_shouldReturnBadRequest() throws Exception {
        // Arrange
        String email = "";
        String username = "test@gmail.com";
        String firstName = "FirstName";
        String lastName = "LastName";
        String password = "c0mplexPassword#2023";

        RegisterRequest userDetails = new RegisterRequest(email, username, firstName, lastName, password);
        AccountUserDto accountUserDto = new AccountUserDto(username, firstName, lastName);

        when(userService.getByUsername(any(String.class))).thenReturn(null);
        when(userService.registerUser(any(RegisterRequest.class)))
                .thenReturn(accountUserDto);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(userDetails));

        // Act
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST.value(),
                result.getResponse().getStatus(),
                "Should return bad request code (400)");

    }

    @Test
    @DisplayName("User registration invalid password")
    void testRegisterUser_whenInvalidPasswordIsProvided_shouldReturnBadRequestStatus() throws Exception {
        // Arrange
        String email = "test_valid@gmail.com";
        String username = "test_valid@gmail.com";
        String firstName = "FirstName";
        String lastName = "LastName";
        String password = "weakpassword";

        RegisterRequest userDetails = new RegisterRequest(email, username, firstName, lastName, password);
        AccountUserDto accountUserDto = new AccountUserDto(username, firstName, lastName);

        when(userService.getByUsername(any(String.class))).thenReturn(null);
        when(userService.registerUser(any(RegisterRequest.class)))
                .thenReturn(accountUserDto);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(userDetails));

        // Act
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST.value(),
                result.getResponse().getStatus(),
                "Should return bad request code (400)");

    }

    @Test
    @DisplayName("User registration when empty first name is provided")
    void testRegisterUser_whenEmptyFirstNameIsProvided_shouldReturnBadRequestStatus() throws Exception {
        // Arrange
        String email = "test_valid@gmail.com";
        String username = "test_valid@gmail.com";
        String firstName = "";
        String lastName = "LastName";
        String password = "weakpassword";

        RegisterRequest userDetails = new RegisterRequest(email, username, firstName, lastName, password);
        AccountUserDto accountUserDto = new AccountUserDto(username, firstName, lastName);

        when(userService.getByUsername(any(String.class))).thenReturn(null);
        when(userService.registerUser(any(RegisterRequest.class)))
                .thenReturn(accountUserDto);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(userDetails));

        // Act
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST.value(),
                result.getResponse().getStatus(),
                "Should return bad request code (400)");

    }

    @Test
    @DisplayName("User registration when empty last name is provided")
    void testRegisterUser_whenEmptyLastNameIsProvided_shouldReturnBadRequestStatus() throws Exception {
        // Arrange
        String email = "test_valid@gmail.com";
        String username = "test_valid@gmail.com";
        String firstName = "FirstName";
        String lastName = "";
        String password = "weakpassword";

        RegisterRequest userDetails = new RegisterRequest(email, username, firstName, lastName, password);
        AccountUserDto accountUserDto = new AccountUserDto(username, firstName, lastName);

        when(userService.getByUsername(any(String.class))).thenReturn(null);
        when(userService.registerUser(any(RegisterRequest.class)))
                .thenReturn(accountUserDto);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(userDetails));

        // Act
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST.value(),
                result.getResponse().getStatus(),
                "Should return bad request code (400)");

    }

    @Test
    @DisplayName("User registration when empty username is provided")
    void testRegisterUser_whenEmptyUsernameIsProvided_shouldReturnBadRequestStatus() throws Exception {
        // Arrange
        String email = "test_valid@gmail.com";
        String username = "";
        String firstName = "FirstName";
        String lastName = "LastName";
        String password = "weakpassword";

        RegisterRequest userDetails = new RegisterRequest(email, username, firstName, lastName, password);
        AccountUserDto accountUserDto = new AccountUserDto(username, firstName, lastName);

        when(userService.getByUsername(any(String.class))).thenReturn(null);
        when(userService.registerUser(any(RegisterRequest.class)))
                .thenReturn(accountUserDto);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(userDetails));

        // Act
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST.value(),
                result.getResponse().getStatus(),
                "Should return bad request code (400)");

    }

    @Test
    @DisplayName("User login with valid username and password")
    void testLoginUser_whenValidCredentialsProvided_shouldReturnAccountUserDto() throws Exception {
        // Arrange
        String username = "test_valid@gmail.com";
        String password = "c0mplexPassword#2023";
        String firstName = "FirstName";
        String lastName = "LastName";

        LoginRequest userLoginDetails = new LoginRequest(username, password);
        Authentication mockAuth = new UsernamePasswordAuthenticationToken(
                new User(username, firstName, lastName),
                null,
                Set.of(new Role("USER")));

        when(authenticationManager.authenticate(any(Authentication.class)))
                .thenReturn(mockAuth);
        when(tokenService.generateToken(any(), any(Set.class)))
                .thenReturn("mockedToken");

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(userLoginDetails));

        // Act
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println("Printing response result");
        System.out.println(result.getResponse().getContentAsString());
        Map<String, Object> responseBody = new ObjectMapper()
                .readValue(result.getResponse().getContentAsString(), Map.class);
        // AccountUserDto responseUserDto = new ObjectMapper()
        // .readValue(responseBody, AccountUserDto.class);

        // Assert
        assertEquals(username,
                responseBody.get("username"),
                "The returned username does not contain the expected value.");
        assertEquals(firstName,
                responseBody.get("firstName"),
                "The returned first name does not contain the expected value.");
        assertEquals(lastName,
                responseBody.get("lastName"),
                "The returned last name does not contain the expected value.");

    }


    @Test
    @DisplayName("User login with empty username")
    void testLoginUser_whenEmptyUsernameIsProvided_shouldReturnBadRequestStatus()
        throws Exception {
        // Arrange
        String username = "";
        String password = "c0mplexPassword#2023";

        LoginRequest userLoginDetails = new LoginRequest(username, password);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(userLoginDetails));

        // Act
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        
        // Assert
        assertEquals(HttpStatus.BAD_REQUEST.value(),
                result.getResponse().getStatus());
    }


    @Test
    @DisplayName("User login with username shorter than 5 characters")
    void testLoginUser_whenUsernameShorterThanFiveIsProvided_shouldReturnBadRequestStatus()
        throws Exception {
        // Arrange
        String username = "user";
        String password = "c0mplexPassword#2023";

        LoginRequest userLoginDetails = new LoginRequest(username, password);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(userLoginDetails));

        // Act
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        
        // Assert
        assertEquals(HttpStatus.BAD_REQUEST.value(),
                result.getResponse().getStatus());
    }


    @Test
    @DisplayName("User login with invalid password provided")
    void testLoginUser_whenInvalidPasswordProvided_shouldReturnBadRequestStatus()
        throws Exception {
        // Arrange
        String username = "test_valid@gmail.com";
        String password = "weakpassowr";

        LoginRequest userLoginDetails = new LoginRequest(username, password);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(userLoginDetails));

        // Act
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        
        // Assert
        assertEquals(HttpStatus.BAD_REQUEST.value(),
                result.getResponse().getStatus());
    }
}
