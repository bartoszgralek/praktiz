import React, {useState} from "react";
import {Button, Form} from "react-bootstrap";
import sections from "../constants/sections";
import {List} from "./list.component";

export const SectionChooser = props => {

    const [section, setSection] = useState("kizomba");
    const [limit, setLimit] = useState(0);

    function submitForm(event) {
        event.preventDefault();

        props.createList(section, limit);
    }

    function onChangeLimit(e) {
        if (!isNaN(Number(e.target.value))) setLimit(parseInt(e.target.value));
    }

    return (
        <Form
        onSubmit={event => submitForm(event)}

        >
            PRAKTIZ
            <hr />
            Select any section :
            <Form.Control
                as="select"
                custom
                onChange={e => setSection(e.target.value)}
            >
                {sections.map(section =>
                    <option key={sections.indexOf(section)} value={section}>{section.toUpperCase()}</option>
                )}
            </Form.Control>
            Select limit:
            <hr />
            <Button onClick={() => {if(limit>0) setLimit(limit-1)}}>-</Button>
            <Form.Control
                custom
                type="number"
                value={limit}
                onChange={e => onChangeLimit(e)}
            />
            <Button onClick={() => setLimit(limit+1)}>+</Button>
            <Button variant="danger" type="submit">
                Submit
            </Button>
        </Form>
    )
}