import {Link} from "react-router-dom";
import React from "react";
import {Button} from "@material-ui/core";


function Home() {

    return (
        <div>
            <Link to="/browse">
                <Button>Browse</Button>
            </Link>
            <Link to="/roll">
                <Button>Roll</Button>
            </Link>
            <Link to="/today">
                <Button>Today</Button>
            </Link>
        </div>
    )
}

export default Home;