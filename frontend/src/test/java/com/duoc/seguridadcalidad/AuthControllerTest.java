package com.duoc.seguridadcalidad;

import com.duoc.seguridadcalidad.controller.AuthController;
import com.duoc.seguridadcalidad.dto.AuthRequest;
import com.duoc.seguridadcalidad.dto.AuthResponse;
import com.duoc.seguridadcalidad.service.BackendService;
import com.duoc.seguridadcalidad.service.JwtCookieService;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AuthControllerTest {

    @Mock
    private BackendService backendService;

    @Mock
    private JwtCookieService jwtCookieService;

    @InjectMocks
    private AuthController authController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLoginSuccess() {
        AuthRequest req = new AuthRequest();
        AuthResponse res = new AuthResponse("token-valido");
        when(backendService.login(any())).thenReturn(res);
        when(jwtCookieService.createAuthCookie(anyString())).thenReturn(ResponseCookie.from("jwt", "token").build());

        ResponseEntity<?> response = authController.createAuthenticationToken(req);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void testLoginHttpError() {
        AuthRequest req = new AuthRequest();
        when(backendService.login(any())).thenThrow(new HttpClientErrorException(HttpStatus.UNAUTHORIZED));
        
        ResponseEntity<?> response = authController.createAuthenticationToken(req);
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }

    @Test
    void testLoginConnectionError() {
        AuthRequest req = new AuthRequest();
        when(backendService.login(any())).thenThrow(new ResourceAccessException("Backend down"));
        
        ResponseEntity<?> response = authController.createAuthenticationToken(req);
        assertEquals(HttpStatus.SERVICE_UNAVAILABLE, response.getStatusCode());
    }

    @Test
    void testLogout() {
        when(jwtCookieService.clearAuthCookie()).thenReturn(ResponseCookie.from("jwt", "").build());
        ResponseEntity<Void> response = authController.logout();
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void testSessionWithValidToken() {
        HttpServletRequest req = mock(HttpServletRequest.class);
        when(jwtCookieService.extractToken(req)).thenReturn("token");
        
        ResponseEntity<Void> response = authController.session(req);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void testSessionWithNullToken() {
        HttpServletRequest req = mock(HttpServletRequest.class);
        when(jwtCookieService.extractToken(req)).thenReturn(null);
        
        ResponseEntity<Void> response = authController.session(req);
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }
}