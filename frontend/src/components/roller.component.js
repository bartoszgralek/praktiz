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

    async function fetchSections() {
        const resultList = [];
        const sectionArray = Array.from(map, ([section, limit]) => ({section, limit}));

        await Promise.all(sectionArray.map(item =>
            axios
                .get(`/api/section/${item.section}`, { params: {limit: item.limit}})
                .then(response => {
                    resultList.push(response.data)
                })
        ))


        setSections(resultList)
    }

    if (loading) return <p>Loading...</p>
    if (error) return <p>Error!</p>

    return (
        <div>
            {selections}
            <Button onClick={createNewSelection}>
                <AddIcon/>
            </Button>
            <Button variant="contained" disabled={map.size < 1} onClick={fetchSections}>Roll!</Button>
            {sections && <SectionList sections={sections}/>}
        </div>
    )
}