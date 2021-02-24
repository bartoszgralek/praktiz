import React from "react";
import SectionList from "./section-list.component";
import useAxios from "axios-hooks";
import FigureList from "./figure-list.component";


export default function Today() {

    const [{ data, loading, error }, refetch] = useAxios(
        '/api/today'
    )

    if (loading) return <p>Loading...</p>
    if (error) return <p>Error!</p>

    return (
        <div>
            <FigureList figures={data.figures} />
        </div>
    )
}