package com.system.Learning_system_springboot.controller;
import com.system.Learning_system_springboot.model.dto.ApiResponse;
import com.system.Learning_system_springboot.model.dto.ChangePasswordRequest;
import com.system.Learning_system_springboot.model.exception.InvalidFieldsException;
import com.system.Learning_system_springboot.security.CustomUserDetailsService;
import com.system.Learning_system_springboot.security.JwtUtil;
import com.system.Learning_system_springboot.model.dto.LoginRequest;
import com.system.Learning_system_springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;
    private final UserService userService;
    public AuthController(AuthenticationManager authenticationManager, CustomUserDetailsService userDetailsService, JwtUtil jwtUtil, PasswordEncoder passwordEncoder, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<String>> login(@RequestBody LoginRequest loginRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUserCode(), loginRequest.getPassword())
            );
            UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUserCode());
            String token = jwtUtil.generateToken(userDetails.getUsername());
            return ResponseEntity.ok(ApiResponse.success(200, "Login successful", "token : " + token));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(401).body(ApiResponse.error(401, "Wrong password"));
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(404).body(ApiResponse.error(404, "User not found"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(ApiResponse.error(500, "Internal server error"));
        }
    }
    @PostMapping("/change_password")
    public ResponseEntity<ApiResponse<String>> changePassword(@RequestBody ChangePasswordRequest changePasswordRequest) {
        String userCode = SecurityContextHolder.getContext().getAuthentication().getName();
        try {
            userService.changePassword(userCode, changePasswordRequest.getCurrentPassword(), changePasswordRequest.getNewPassword());
            return ResponseEntity.ok(ApiResponse.success(200, "Password changed successfully", null));
        } catch (InvalidFieldsException e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(400, e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error(500, userCode)); //userCode will show anonymousUser
        }
    }
}