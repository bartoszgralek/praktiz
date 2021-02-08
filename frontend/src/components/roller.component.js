import React, {useEffect, useState} from "react";
import {Button, InputLabel, MenuItem, Select, TextField} from "@material-ui/core";
import useAxios from "axios-hooks";
import AddIcon from '@material-ui/icons/Add';
import SectionSelect from "./select_section.component";

export default function Roller() {

    const [{ data, loading, error }, refetch] = useAxios(
        '/api/section/names'
    )

    const [selections, setSelections] = useState([])

    useEffect(() => {
            if (!loading) setSelections([...selections, <SectionSelect names={data} set={addToMap} del={removeFromMap} key={selections.length + 1}/>])
        }
    ,[data])

    const [map, setMap] = useState(new Map())

    function addToMap(section, number) {
        map.set(section, number)
        setMap(map)
        console.log(map)
    }

    function removeFromMap(object, key) {
        setSelections(selections.filter(el => el !== object))
        map.delete(key)
        setMap(map)
    }

    if (loading) return <p>Loading...</p>
    if (error) return <p>Error!</p>

    return (
        <div>
            {selections}
            <Button onClick={() => setSelections([...selections, <SectionSelect names={data} set={addToMap} del={removeFromMap} key={selections.length + 1}/>])}>
                <AddIcon/>
            </Button>
        </div>
    )
}