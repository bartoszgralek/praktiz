import React, {useEffect, useState} from "react";
import SectionList from "./section-list.component";
import useAxios from "axios-hooks";
import AddForm from "./addform.component";


export default function Browse() {

    const [{ data, loading, error }, refetch] = useAxios(
        '/api/section'
    )

    const [sections, setSections] = useState([])

    useEffect(() => setSections(data)
    , [data])

    function addFigure(sectionName, figure) {
        var newSections = sections
        newSections.find(section => section.name === sectionName).figures.push(figure)
        setSections([...newSections])
    }

    if (loading) return <p>Loading...</p>
    if (error) return <p>Error!</p>

    return (
        <div>
            <SectionList sections={data}/>
            <AddForm onAdd={addFigure}/>
        </div>
    )
}