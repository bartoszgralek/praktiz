package com.gralek.praktiz.controller;

import com.gralek.praktiz.model.Section;
import com.gralek.praktiz.payload.response.MessageResponse;
import com.gralek.praktiz.repository.FigureRepository;
import com.gralek.praktiz.repository.SectionRepository;
import com.gralek.praktiz.service.FigureService;
import com.gralek.praktiz.service.SectionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/section")
public class SectionController {

    private final SectionRepository sectionRepository;

    private final FigureRepository figureRepository;

    private final SectionService sectionService;

    private final FigureService figureService;

    public SectionController(SectionRepository sectionRepository, FigureRepository figureRepository, SectionService sectionService, FigureService figureService) {
        this.sectionRepository = sectionRepository;
        this.figureRepository = figureRepository;
        this.sectionService = sectionService;
        this.figureService = figureService;
    }

    @GetMapping
    public ResponseEntity<?> getAllSections() {
        return ResponseEntity.ok(sectionService.getAllSections());
    }

    @GetMapping("/{sectionName}")
    public ResponseEntity<?> getSectionByName(@PathVariable String sectionName, @RequestParam(required = false) Integer limit) {
        if(!sectionRepository.existsByName(sectionName))
            return ResponseEntity.badRequest().body(new MessageResponse("Section with given name not found."));
        else {
            if(limit != null)
                return ResponseEntity.ok(sectionService.getRandomFigures(sectionName, limit));
            else
                return ResponseEntity.ok(sectionService.getSectionByName(sectionName));
        }
    }

    @GetMapping("/names")
    public ResponseEntity<?> getSectionNames() {
        return ResponseEntity.ok(sectionService.getSectionNames());
    }

    @PostMapping("/{sectionName}")
    public ResponseEntity<?> createSection(@PathVariable String sectionName) {
        if(sectionRepository.existsByName(sectionName))
            return ResponseEntity.badRequest().body(new MessageResponse("Section with given name already exists"));
        else {
            sectionRepository.save(new Section(sectionName));
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
    }

}
