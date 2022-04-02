package com.gralek.praktiz.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.gralek.praktiz.exceptions.FigureNotFoundException;
import com.gralek.praktiz.model.Figure;
import com.gralek.praktiz.repository.FigureRepository;
import com.gralek.praktiz.service.FigureService;
import com.gralek.praktiz.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/section/{sectionName}/figure")
public class FigureController {

    @Autowired
    FigureService figureService;

    @Autowired
    FigureRepository figureRepository;

    @Autowired
    SectionService sectionService;

    @PostMapping
    public ResponseEntity<?> createFigureInSection(@PathVariable String sectionName, @RequestBody Figure figure) {
        Figure savedFigure = figureService.saveFigure(sectionName, figure);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedFigure.getId());
    }

    @PatchMapping(path = "/{figureId}", consumes = "application/json-patch+json")
    public ResponseEntity<?> patchFigure(@PathVariable String sectionName, @PathVariable Long figureId, @RequestBody JsonPatch patch) {
        try {
            Figure figurePatched = figureService.patchFigure(sectionName, figureId, patch);
            return ResponseEntity.ok(figurePatched);
        } catch (JsonPatchException | JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (FigureNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{figureId}")
    public ResponseEntity<?> deleteFigureFromSection(@PathVariable String sectionName, @PathVariable Long figureId) {
        figureRepository.deleteById(figureId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
