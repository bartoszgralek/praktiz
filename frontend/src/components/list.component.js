import React, {useEffect, useState} from "react";
import axios from 'axios';
import {ListItem} from "./list-item.component";

export const List = props => {
    // State
    const [list, setList] = useState([]);
    const [loading, setLoading] = useState(false)

    useEffect(() => {
        setLoading(true);
        axios.get('https://praktiz-backend.herokuapp.com/api/section/' + props.section + "?limit=" + props.limit)
            .then(res => {
                console.log(res)
                setList(res.data.figures)
                setLoading(false);
            })
    }, []);

    function addToList(...elements) {
        setList([...list, elements]);
    }

    return (

        <div>
            <h2>{props.section}</h2>
            <ul>
            {list.map((el, id) => (
                <ListItem key={id} element={el}/>
            ))}
            </ul>
        </div>
    );
}