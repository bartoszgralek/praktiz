package com.gralek.praktiz.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.gralek.praktiz.exceptions.FigureNotFoundException;
import com.gralek.praktiz.model.Figure;
import com.gralek.praktiz.model.Section;
import com.gralek.praktiz.repository.FigureRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FigureService {

    final
    SectionService sectionService;

    final
    FigureRepository figureRepository;

    private ObjectMapper objectMapper = new ObjectMapper();

    public FigureService(SectionService sectionService, FigureRepository figureRepository) {
        this.sectionService = sectionService;
        this.figureRepository = figureRepository;
    }

    public Optional<Figure> findFigure(Long id) {
        return figureRepository.findById(id);
    }

    public Figure saveFigure(String sectionName, Figure figure) {
        var section = sectionService.getSectionByName(sectionName);
        figure.setSection(section);
        return figureRepository.save(figure);
    }

    public Figure patchFigure(String sectionName, Long figureId, JsonPatch patch) throws FigureNotFoundException, JsonPatchException, JsonProcessingException {
        Figure figure = findFigure(figureId).orElseThrow(() -> new FigureNotFoundException(figureId));
        Figure figurePatched = applyPatchToFigure(patch, figure);
        Section section = sectionService.getSectionByName(sectionName);
        figurePatched.setSection(section);
        return figureRepository.save(figurePatched);
    }

    private Figure applyPatchToFigure(JsonPatch patch, Figure targetFigure) throws JsonPatchException, JsonProcessingException {
        JsonNode patched = patch.apply(objectMapper.convertValue(targetFigure, JsonNode.class));
        return objectMapper.treeToValue(patched, Figure.class);
    }
}
