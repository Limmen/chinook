import 'core-js/fn/object/assign';
import React from 'react';
import ReactDOM from 'react-dom';
import { Router, Route, browserHistory, IndexRoute } from 'react-router'
import App from './components/Main';
import ArtistsComponent from './components/ArtistsComponent';

// Render the main component into the dom
ReactDOM.render(
  <Router history={browserHistory}>
    <Route path="/" component={App}>
      {/* add the routes here */}
      <IndexRoute component={ArtistsComponent}/>
      <Route path="/artists" component={ArtistsComponent}/>
    </Route>
  </Router>
  , document.getElementById('app'));
