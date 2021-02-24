package com.gralek.praktiz.service;

import com.gralek.praktiz.model.Figure;
import com.gralek.praktiz.model.Section;
import com.gralek.praktiz.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class SectionService {

    @Autowired
    private SectionRepository sectionRepository;

    public Section getSectionByName(String name) {
        return sectionRepository.findByName(name).orElseThrow(() -> new RuntimeException(String.format("Section %s not found", name)));
    }

    public List<Section> getAllSections() {
        return sectionRepository.findAll();
    }

    public List<String> getSectionNames() {
        return getAllSections().stream().map(Section::getName).collect(Collectors.toList());
    }

    public List<Figure> getRandomFigures(String sectionName, int numberOfFigures) {
        Section section = getSectionByName(sectionName);

        if(numberOfFigures >= section.getFigures().size()) return section.getFigures();

        return selectKItems(section.getFigures(), numberOfFigures);
    }

    static <T> List<T> selectKItems(List<T> input, int k)
    {
        Random r = new Random();

        // reservoir[] is the output array. Initialize it with
        // first k elements from stream[]
        List<T> reservoir = new ArrayList<>(input.subList(0, k));

        // Iterate from the (k+1)th element to nth element
        for (int i = k; i < input.size(); i++)
        {
            // Pick a random index from 0 to i.
            int j = r.nextInt(i + 1);

            // If the randomly  picked index is smaller than k,
            // then replace the element present at the index
            // with new element from stream
            if(j < k)
                reservoir.set(j, input.get(i));
        }

        return reservoir;
    }

}
