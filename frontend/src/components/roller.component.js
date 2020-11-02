import React, {useEffect, useState} from "react";
import {SectionChooser} from "./section-chooser.component";
import {Button} from "react-bootstrap";
import html2canvas from "html2canvas";
import {List} from "./list.component";

export const Roller = props => {

    const [lists, setLists] = useState([]);

    function createList(section, limit) {

        const list = <List section={section} limit={limit} ref={section}/>
        setLists([...lists, list])
    }

    function capture() {
        html2canvas(document.querySelector("#capture")).then(canvas => {
            window.document.write('<img src="'+canvas.toDataURL("image/png")+'"/>');
        });
    }

    function downloadInnerHtml(filename, elId, mimeType) {
        var elHtml = document.getElementById(elId).innerHTML;
        var link = document.createElement('a');
        mimeType = mimeType || 'text/plain';

        link.setAttribute('download', filename);
        link.setAttribute('href', 'data:' + mimeType  +  ';charset=iso-8859-1,' + encodeURIComponent(elHtml));
        link.click();
    }

    return (
        <div>
            <SectionChooser createList={(section, limit) => createList(section, limit)}/>
            <div id="capture">
                {lists}
            </div>
            <Button onClick={() => downloadInnerHtml("figures.html", 'capture', 'text/html')}>Capture!</Button>
            {/*<Button onClick={() => capture()}>Capture!</Button>*/}
        </div>
    )
}