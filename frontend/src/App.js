import React from 'react';
import './App.css';
import {Switch,Route} from "react-router-dom";
import {Roller} from "./components/roller.component";
import {Home} from "./components/home.component";


function App() {
  return (
    <div>
        <Switch>
            <Route exact path="/" component={Home} />
            <Route path="/roll"   component={Roller} />
        </Switch>
    </div>
  );
}

export default App;
