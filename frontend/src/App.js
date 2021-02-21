import React from 'react';
import './App.css';
import {Switch, Route} from "react-router-dom";
import Home from "./components/home.component";
import Roller from "./components/roller.component";
import Browse from "./components/browse.component";
import Today from "./components/today.component";


function App() {

    return (
        <div>
            <Switch>
                <Route exact path="/" component={Home} />
                <Route path="/browse" component={Browse} />
                <Route path="/roll"   component={Roller} />
                <Route path="/today"  component={Today} />
            </Switch>
        </div>
    );
}

export default App;
