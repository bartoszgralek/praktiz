import React from "react";
import SectionList from "./section-list.component";
import useAxios from "axios-hooks";


export default function Today() {

    const [{ data, loading, error }, refetch] = useAxios(
        '/api/today'
    )

    if (loading) return <p>Loading...</p>
    if (error) return <p>Error!</p>

    return (
        <div>
            <SectionList sections={data}/>
        </div>
    )
}