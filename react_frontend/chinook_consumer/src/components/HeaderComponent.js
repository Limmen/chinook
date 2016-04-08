'use strict';

import React from 'react';

require('styles//Header.css');

let HeaderComponent = (props) => (
        <div classNameNameName="header-component">
        <nav className="navbar navbar-default">
        <div className="container-fluid">
        <div className="navbar-header">
        <a className="navbar-brand" href="#">WebSiteName</a>
        </div>
        <ul className="nav navbar-nav">
        <li className="active"><a href="#">Home</a></li>
        <li><a href="#">Page 1</a></li>
        <li><a href="#">Page 2</a></li> 
        <li><a href="#">Page 3</a></li> 
        </ul>
        </div>
        </nav>
        <div className="jumbotron">
        <h1>Hello, world!</h1>
        <p>...</p>
        <p><a className="btn btn-primary btn-lg" href="#" role="button">Learn more</a></p>
        </div>
        </div>
);

HeaderComponent.displayName = 'HeaderComponent';

// Uncomment properties you need
// HeaderComponent.propTypes = {};
// HeaderComponent.defaultProps = {};

export default HeaderComponent;
