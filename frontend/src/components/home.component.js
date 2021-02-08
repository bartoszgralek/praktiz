import {Link} from "react-router-dom";
import React from "react";


function Home() {

    return (
        <div>
            <Link to="/browse">Browse</Link>
            <Link to="/roll">Roll</Link>
        </div>
    )
}

export default Home;