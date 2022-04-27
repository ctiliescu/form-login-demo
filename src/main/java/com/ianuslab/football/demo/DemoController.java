package com.ianuslab.football.demo;

import com.ianuslab.football.users.model.UserData;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    @ApiOperation(value = "Get secret value", nickname = "getSecret", notes = "Get secret value")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "The secret value")})
    @RequestMapping(value = "/api/v1/secret", method = RequestMethod.GET)
    public String createUser() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        UserData userData = (UserData) authentication.getPrincipal();
        return "Secret secure value " + userData.getUsername();
    }
}
