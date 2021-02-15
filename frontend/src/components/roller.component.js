import React, {useEffect, useState} from "react";
import {Button} from "@material-ui/core";
import useAxios from "axios-hooks";
import AddIcon from '@material-ui/icons/Add';
import SectionSelect from "./select_section.component";
import SectionList from "./section-list.component";
import axios from 'axios';

export default function Roller() {

    const [{ data, loading, error }, refetch] = useAxios(
        '/api/section/names'
    )

    const [selections, setSelections]   = useState([])
    const [map, setMap]                 = useState(new Map())
    const [sections, setSections]       = useState([])

    useEffect(() => {
            if (!loading) setSelections([...selections, <SectionSelect names={data} set={addToMap} del={removeFromMap} key={selections.length + 1}/>])
        }
    ,[data])

    useEffect(() => {
        map.forEach((value, key) =>  {
            axios.get(`/api/section/${key}`, {
                params: {
                    limit: value
                }
            }).then(response =>
            {
                let section = sections.filter(el => el.name === response.data.name)
                setSections([...sections, response.data])
            })
        })
    }, [map])

    function addToMap(section, number) {
        setMap(new Map(map.set(section, number)))
    }

    function removeFromMap(object, key) {
        setSelections(selections.filter(el => el !== object))
        map.delete(key)
        setMap(new Map(map))
    }

    function createNewSelection() {
        setSelections([...selections,
            <SectionSelect names={data} set={addToMap} del={removeFromMap} key={selections.length + 1}/>])
    }


    if (loading) return <p>Loading...</p>
    if (error) return <p>Error!</p>

    return (
        <div>
            {selections}
            <Button onClick={createNewSelection}>
                <AddIcon/>
            </Button>
            {/*<Button variant="contained" disabled={map.size < 1} onClick={createSectionList}>Roll!</Button>*/}
            {sections.length > 0 && <SectionList sections={sections} />}
        </div>
    )
}