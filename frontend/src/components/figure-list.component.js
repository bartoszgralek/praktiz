import React from "react";

export default function FigureList(props) {

    return (
        <ul>
            {props.figures.map((figure, index) =>
                <li style={{whiteSpace: "pre-wrap"}} key={index.toString()} >
                    {figure.description}
                </li>
            )}
        </ul>
    )
}