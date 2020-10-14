package com.gralek.praktiz.controller;

import com.gralek.praktiz.payload.response.MessageResponse;
import com.gralek.praktiz.repository.SectionRepository;
import com.gralek.praktiz.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/section")
public class SectionController {

    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private SectionService sectionService;

    @GetMapping
    public ResponseEntity<?> getAllSections() {
        return ResponseEntity.ok(sectionService.getAllSections());
    }

    @GetMapping("/{name}")
    public ResponseEntity<?> getSectionByName(@PathVariable String name, @RequestParam(required = false) Integer limit) {
        name = name.toUpperCase();
        if(!sectionRepository.existsByName(name))
            return ResponseEntity.badRequest().body(new MessageResponse("Section with given name not found."));
        else {
            if(limit != null)
                return ResponseEntity.ok(sectionService.getRandomFigures(name, limit));
            else
                return ResponseEntity.ok(sectionService.getSectionByName(name));
        }
    }


}
