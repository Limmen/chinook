/**
 *  Application Container.
 *
 *  Bootstraps different components into a complete application.
 */

'use strict';

import React from 'react';
import HeaderComponent from './HeaderComponent';
import FooterComponent from './FooterComponent';
import DataTableControllerComponent from './DataTableControllerComponent';

require('normalize.css/normalize.css');
require('styles/App.css');

class AppComponent extends React.Component {
  render() {
    return (
      <div className="index container-fluid">
        <div className="row">
          <HeaderComponent/>
        </div>
        <div className="row">
          <div className="col-sm-3"></div>
          <div className="col-sm-6">
            <DataTableControllerComponent location={this.props.routes[this.props.routes.length-1]} />
            {this.props.children}
          </div>
          <div className="col-sm-3"></div>
        </div>
        <div className="row">
        <FooterComponent />
          </div>
      </div>
    );
  }
}

AppComponent.defaultProps = {};

export default AppComponent;
