package com.gralek.praktiz.controller;

import com.gralek.praktiz.service.DocsService;
import org.mortbay.jetty.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.sql.SQLException;

@RestController
@RequestMapping("/api/docs")
public class DocsController {

    @Autowired
    private DocsService docsService;

    @GetMapping("/update")
    public ResponseEntity<?> updateDatabaseWithDoc() {
        try {
            docsService.updateDatabase();
            return ResponseEntity.ok().build();
        } catch (GeneralSecurityException | IOException | SQLException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.ORDINAL_500_Internal_Server_Error).build();
        }
    }

}
