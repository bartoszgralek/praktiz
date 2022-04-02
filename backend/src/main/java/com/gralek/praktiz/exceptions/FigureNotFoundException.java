package com.gralek.praktiz.exceptions;

public class FigureNotFoundException extends Exception {

    private final String errorFormat = "Figure with id = %d not found";

    private Long figureId;

    public FigureNotFoundException(Long figureId) {
        this.figureId = figureId;
    }

    @Override
    public String getMessage() {
        return String.format(errorFormat, figureId);
    }
}
