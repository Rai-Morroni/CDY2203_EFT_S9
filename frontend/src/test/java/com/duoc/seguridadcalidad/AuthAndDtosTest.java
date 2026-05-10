package com.duoc.seguridadcalidad;

import com.duoc.seguridadcalidad.dto.AuthRequest;
import com.duoc.seguridadcalidad.dto.AuthResponse;
import com.duoc.seguridadcalidad.dto.InvoiceCreateRequest;
import com.duoc.seguridadcalidad.model.InvoiceLineItem;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AuthAndDtosTest {

    @Test
    void testAuthRequest() {
        AuthRequest req = new AuthRequest();
        req.setUsername("admin");
        req.setPassword("1234");
        assertEquals("admin", req.getUsername());
        assertEquals("1234", req.getPassword());
    }

    @Test
    void testAuthResponse() {
        AuthResponse res = new AuthResponse("token-secreto");
        assertEquals("token-secreto", res.getToken());
    }

    @Test
    void testInvoiceCreateRequest() {
        InvoiceCreateRequest req = new InvoiceCreateRequest();
        req.setIssueDate(LocalDate.of(2026, 5, 10));
        req.setVatRate(new BigDecimal("0.19"));
        req.setNotes("Nota test");
        req.setItems(new ArrayList<InvoiceLineItem>());

        assertEquals(LocalDate.of(2026, 5, 10), req.getIssueDate());
        assertEquals(new BigDecimal("0.19"), req.getVatRate());
        assertEquals("Nota test", req.getNotes());
        assertEquals(0, req.getItems().size());
    }
}