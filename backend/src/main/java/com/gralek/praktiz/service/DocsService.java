package com.gralek.praktiz.service;

import com.google.api.services.docs.v1.Docs;
import com.google.api.services.docs.v1.model.Document;
import com.google.api.services.docs.v1.model.StructuralElement;
import com.gralek.praktiz.auth.DocsAuth;
import com.gralek.praktiz.model.Figure;
import com.gralek.praktiz.model.Section;
import com.gralek.praktiz.repository.FigureRepository;
import com.gralek.praktiz.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

@Service
public class DocsService {

    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private FigureRepository figureRepository;

    @Autowired
    private DatabaseService databaseService;

    final private static String DOCUMENT_ID = "1LMAfODijH2l707DqXEiI2iVUlo80otZxZ-PDJvRjBqU";

    public void updateDatabase() throws GeneralSecurityException, IOException, SQLException {
        Docs service = DocsAuth.getServiceInstance();
        Document document = service.documents().get(DOCUMENT_ID).execute();

        databaseService.clearDatabase();

        List<StructuralElement> elementList = document.getBody().getContent();
        Section currentSection = null;
        HashMap<Integer, Figure> parentMap = new HashMap<>();

        for(StructuralElement element : elementList) {
            if(isSection(element)) {
                currentSection = sectionRepository.save(new Section(toSectionName(element)));
                parentMap = new HashMap<>();
            } else if(isFigure(element)) {
                Figure figure = figureRepository.save(new Figure(toFigureDescription(element), currentSection));

                Integer nestingLevel = getNestingLevel(element);
                if(nestingLevel != null) {
                    Figure parent = parentMap.get(nestingLevel-1);
                    figure.setParent(parent);
                    figureRepository.save(figure);

                    parentMap.put(nestingLevel, figure);
                } else {
                    parentMap.put(0, figure);
                }

            }
        }
    }

    static boolean isSection(StructuralElement element) {
        return  element.getParagraph() != null &&
                element.getParagraph().getBullet() == null &&
                !element.getParagraph().getElements().get(0).getTextRun().getContent().isBlank();
    }

    static String toSectionName(StructuralElement element) {
        return element.getParagraph().getElements().get(0).getTextRun().getContent().trim().replace(":", "").toUpperCase();
    }

    static boolean isFigure(StructuralElement element) {
        return  element.getParagraph() != null &&
                element.getParagraph().getBullet() != null;
    }

    static String toFigureDescription(StructuralElement element) {
        return element.getParagraph().getElements().get(0).getTextRun().getContent().trim();
    }

    static Integer getNestingLevel(StructuralElement element) {
        return element.getParagraph().getBullet().getNestingLevel();
    }

}
