import React from "react";

export const ListItem = props => {

    return (
        <li>
            {props.element.description}
            <ul>
            {props.element.children.map((child,id) => (
                <ListItem key={id} element={child}/>
            ))}
            </ul>
        </li>
    )

}