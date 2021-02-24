import React from "react";
import FigureList from "./figure-list.component";

export default function SectionList(props) {

    return (
        <ul>
            {props.sections.map((section, index) => (
                <li key={index.toString()}>
                    {section.name}
                    <FigureList figures={section.figures} />
                </li>
            ))}
        </ul>
    )
}