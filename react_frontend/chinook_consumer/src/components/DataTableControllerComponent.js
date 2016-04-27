/**
 * DataTableControllerComponent.
 *
 * Renders a list of links to datatables of different data.
 */
'use strict';

import React from 'react';
import { Link } from 'react-router'
import activeComponent from 'react-router-active-component'

require('styles//DataTableController.css');

class DataTableControllerComponent extends React.Component {

  render() {
    let NavLink = activeComponent('li')
    return (
      <div className="datatablecontroller-component">
        <div className="center-block align_center">
        <ul className="nav nav-pills nav-stacked">
          <NavLink to="/artists">Artists</NavLink>
          <NavLink to="/albums">Albums</NavLink>
          <NavLink to="/customers">Customers</NavLink>
          <NavLink to="/Employees">Employees</NavLink>
          <NavLink to="/genres">Genres</NavLink>
          <NavLink to="/invoices">Invoices</NavLink>
          <NavLink to="/invoicelines">InvoiceLines</NavLink>
          <NavLink to="/mediatypes">MediaTypes</NavLink>
          <NavLink to="/playlists">Playlists</NavLink>
          <NavLink to="/playlisttracks">PlaylistTracks</NavLink>
          <NavLink to="/Tracks">Tracks</NavLink>
        </ul>
      </div>
        </div>
    );
  }
}

DataTableControllerComponent.displayName = 'DataTableControllerComponent';

DataTableControllerComponent.propTypes = {};
DataTableControllerComponent.defaultProps = {};

export default DataTableControllerComponent;
