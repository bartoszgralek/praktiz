import React from "react";

export default function SectionList(props) {

    return (
        <div>
            <ul>
                {props.sections.map((section, index) => (
                    <li key={index.toString()}>
                        {section.name}
                        <ul>
                        {section.figures.map((figure, index) =>
                            <li key={index.toString()} >
                                {figure.description}
                            </li>
                        )}
                        </ul>
                    </li>
                ))}
            </ul>
        </div>
    )
}