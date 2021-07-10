import logo from './logo.svg';
import Index from "./Components/Index";
import Show from "./Components/Show";
import Create from "./Components/Create";
import Edit from "./Components/Edit"

import Indexprestamo from "./Components/Indexprestamo";
import Showprestamo from "./Components/Showprestamo";
import Createprestamo from "./Components/Createprestamo";
import Editprestamo from "./Components/Editprestamo"

import './App.css';
import { BrowserRouter, Redirect, Route, Switch } from 'react-router-dom';

function App() {
  return (
    <div className="App">

      <BrowserRouter>
        <Switch>
          <Route path="/insertar" component={Create} />
          <Route path="/modificar/:id" component={Edit} />
          <Route path="/:id" component={Show} />
          <Route exact path="/" component={Index} />
          <Redirect to="/" />

          <Route path="/insertarprestamo" component={Createprestamo} />
          <Route path="/modificarprestamo/:id" component={Editprestamo} />
          <Route path="/:id" component={Showprestamo} />
          <Route exact path="/" component={Indexprestamo} />
          <Redirect to="/" />

        </Switch>


      </BrowserRouter>
    </div>
  );
}

export default App;
