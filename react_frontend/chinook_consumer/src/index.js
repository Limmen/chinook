/**
 * Application entry point and router.
 *
 * Initializes the application by rendering the router on element with id "app".
 */

import 'core-js/fn/object/assign';
import React from 'react';
import ReactDOM from 'react-dom';
import { Router, Route, browserHistory, IndexRoute } from 'react-router'
import App from './components/Main';
import ArtistsComponent from './components/ArtistsComponent';
import AlbumsComponent from './components/AlbumsComponent';
import CustomersComponent from './components/CustomersComponent';
import EmployeesComponent from './components/EmployeesComponent';
import GenresComponent from './components/GenresComponent';

// Render the main component into the dom
ReactDOM.render(
  <Router history={browserHistory}>
    <Route path="/" component={App}>
      {/* add the routes here */}
      {/* <IndexRoute component={ArtistsComponent}/> */}
      <Route path="/artists" component={ArtistsComponent}/>
      <Route path="/albums" component={AlbumsComponent}/>
      <Route path="/customers" component={CustomersComponent}/>
      <Route path="/employees" component={EmployeesComponent}/>
      <Route path="/genres" component={GenresComponent}/>
    </Route>
  </Router>
  , document.getElementById('app'));
