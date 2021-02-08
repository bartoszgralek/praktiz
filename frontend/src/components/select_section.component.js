import React, {useEffect, useState} from "react";
import {Button, InputLabel, MenuItem, Select, TextField} from "@material-ui/core";
import RemoveIcon from '@material-ui/icons/Remove';

export default function SectionSelect(props) {

    const [section, setSection] = useState('')
    const [figureNumber, setFigureNumber] = useState(1)

    useEffect(() => {
        if(section.length)
            props.set(section, figureNumber)
    }, [section, figureNumber])

    const changeSection = e =>  setSection(e.target.value)
    const changeFigure  = e =>  setFigureNumber(parseInt(e.target.value))

    return (
        <div>
            <InputLabel id="label_section">Section</InputLabel>
            <Select labelId="label" id="select" value={section} onChange={changeSection}>
                {props.names.map((item, index) => <MenuItem value={item} key={index} >{item}</MenuItem>)}
            </Select>
            <InputLabel id="label_figures">Number of figures</InputLabel>
            <TextField
                id="figures"
                label="figures"
                type="number"
                InputProps={{
                    inputProps: {
                        min: 1
                    }
                }}
                variant="filled"
                value={figureNumber}
                onChange={changeFigure}
            />
            <Button onClick={() => props.del(this, section)}>
                <RemoveIcon/>
            </Button>
        </div>
    )

}