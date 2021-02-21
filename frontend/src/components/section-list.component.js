import React from "react";
import RemoveIcon from "@material-ui/icons/Remove";

export default function SectionList(props) {

    return (
        <div>
            <ul>
                {props.sections.map((section, index) => (
                    <li key={index.toString()}>
                        {section.name}
                        <ul>
                        {section.figures.map((figure, index) =>
                            <li style={{whiteSpace: "pre-wrap"}} key={index.toString()} >
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