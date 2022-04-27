package com.ianuslab.football.users;

import com.ianuslab.football.users.model.CreateUserRequest;
import com.ianuslab.football.users.model.UserDao;
import com.ianuslab.football.users.services.UserRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class AuthenticationController {
    @Autowired
    private UserRepository userRepository;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @ApiOperation(value = "Create a new user", nickname = "createUser", notes = "Create a new user and generate his football team")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "The user was created"),
            @ApiResponse(code = 409, message = "The user already exists")})
    @RequestMapping(value = "/api/v1/authentication/sign-in",
            consumes = {"application/json"},
            method = RequestMethod.POST)
    public ResponseEntity<Void> createUser(@ApiParam(value = "The create file request", required = true) @RequestBody CreateUserRequest body) {
        Optional<UserDao> userOpt = userRepository.findByEmail(body.getEmail());
        if (userOpt.isPresent()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        body.setPassword(passwordEncoder.encode(body.getPassword()));
        userRepository.save(new UserDao(body));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
