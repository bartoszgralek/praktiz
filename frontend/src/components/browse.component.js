import React from "react";
import SectionList from "./section-list.component";
import useAxios from "axios-hooks";


export default function Browse() {

    const [{ data, loading, error }, refetch] = useAxios(
        '/api/section'
    )

    if (loading) return <p>Loading...</p>
    if (error) return <p>Error!</p>

    return (
        <div>
            <SectionList sections={data}/>
        </div>
    )
}