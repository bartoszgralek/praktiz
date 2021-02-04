package com.gralek.praktiz.controller;

import com.gralek.praktiz.model.Figure;
import com.gralek.praktiz.model.Section;
import com.gralek.praktiz.payload.response.MessageResponse;
import com.gralek.praktiz.repository.FigureRepository;
import com.gralek.praktiz.repository.SectionRepository;
import com.gralek.praktiz.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/section")
public class SectionController {

    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private FigureRepository figureRepository;

    @Autowired
    private SectionService sectionService;

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

    @PostMapping("/{sectionName}")
    public ResponseEntity<?> createSection(@PathVariable String sectionName) {
        if(sectionRepository.existsByName(sectionName))
            return ResponseEntity.badRequest().body(new MessageResponse("Section with given name already exists"));
        else {
            sectionRepository.save(new Section(sectionName));
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
    }

    @PostMapping("/{sectionName}/figure")
    public ResponseEntity<?> createFigureInSection(@PathVariable String sectionName, @RequestBody Figure figure) {
        var section = sectionService.getSectionByName(sectionName);
        figure.setSection(section);
        figureRepository.save(figure);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{sectionName}/figure/{figureId}")
    public ResponseEntity<?> deleteFigureFromSection(@PathVariable String sectionName, @PathVariable Long figureId) {
        figureRepository.deleteById(figureId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
