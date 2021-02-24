import React, {useState} from 'react';
import {InputLabel, MenuItem, Select} from "@material-ui/core";
import useAxios from "axios-hooks";
import axios from "axios";
import {useHistory} from "react-router-dom";

export default function AddForm(props) {

    const [{ data, loading, error }, refetch] = useAxios(
        '/api/section/names'
    )
    const [figure, setFigure] = useState("");
    const [section, setSection] = useState("");

    const handleSubmit = (evt) => {
        evt.preventDefault();
        axios.post(`/api/section/${section}/figure`,{ description: figure })
            .then(response => props.onAdd(section, { id: response.data, description: figure }))
    }

    if (loading) return <p>Loading...</p>
    if (error) return <p>Error!</p>

    return (
        <form onSubmit={handleSubmit}>
            <InputLabel id="label_section">Section</InputLabel>
            <Select labelId="label" id="select" value={section} onChange={e => setSection(e.target.value)}>
                {data.map((item, index) => <MenuItem value={item} key={index} >{item}</MenuItem>)}
            </Select>
            <label>
                Input figure
                <textarea
                    value={figure}
                    onChange={e => setFigure(e.target.value)}
                />
            </label>
            <input type="submit" value="Submit" />
        </form>
    );
}