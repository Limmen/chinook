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
      <div className="index">
        <HeaderComponent/>
        <div className="row">
          <div className="col-sm-4"></div>
          <div className="col-sm-6">
            <DataTableControllerComponent />
            {this.props.children}
          </div>
          <div className="col-sm-4"></div>
        </div>
        <FooterComponent className="col-sm-12"/>
      </div>
    );
  }
}

 AppComponent.defaultProps = {};

 export default AppComponent;
