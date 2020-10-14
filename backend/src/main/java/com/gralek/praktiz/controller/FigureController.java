package com.gralek.praktiz.controller;

import com.gralek.praktiz.repository.FigureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/figure")
public class FigureController {

    @Autowired
    private FigureRepository figureRepository;

    @GetMapping
    public ResponseEntity<?> getAllFigures() {
        return ResponseEntity.ok(figureRepository.findAll());
    }
}
