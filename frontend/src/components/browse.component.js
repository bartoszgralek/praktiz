import React from "react";
import useAxios from "axios-hooks";

export default function Browse() {

    const [{ data, loading, error }, refetch] = useAxios(
        '/api/section'
    )

    if (loading) return <p>Loading...</p>
    if (error) return <p>Error!</p>

    return (
        <div>
            <ul>
                {data.map((section, index) => (
                    <li key={index.toString()}>
                        {section.name}
                        <ul>
                        {section.figures.map((figure, index) =>
                            <li key={index.toString()} >
                                {figure.description}
                            </li>
                        )}
                        </ul>
                    </li>
                ))}
            </ul>
        </div>
    )
}