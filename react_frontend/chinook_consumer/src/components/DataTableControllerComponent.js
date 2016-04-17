'use strict';

import React from 'react';
import { Link } from 'react-router'
require('styles//DataTableController.css');

class DataTableControllerComponent extends React.Component {

  render() {
    return (
      <div className="datatablecontroller-component">
        <ul className="nav nav-pills nav-stacked">
          <li><Link to="/artists" activeClassName="active">Artists</Link></li>
          <li><Link to="/albums" activeClassName="active">Albums</Link></li>
        </ul>
      </div>
    );
  }
}

DataTableControllerComponent.displayName = 'DataTableControllerComponent';

// Uncomment properties you need
// DataTableControllerComponent.propTypes = {};
// DataTableControllerComponent.defaultProps = {};

export default DataTableControllerComponent;
