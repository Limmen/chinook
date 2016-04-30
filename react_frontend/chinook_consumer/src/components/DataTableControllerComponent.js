/**
 * DataTableControllerComponent.
 *
 * Renders a list of links to datatables of different data.
 */
'use strict';

import React from 'react';
import { Link } from 'react-router'

require('styles//DataTableController.css');

class DataTableControllerComponent extends React.Component {

  render() {
    return (
      <div className="datatablecontroller-component">
        <div className="center-block align_center">
          <ul className="nav nav-pills nav-stacked">
            <li className={this.props.location.path === '/artists' ? 'active' : ''}>
              <Link to="/artists">Artists</Link>
            </li>
            <li className={this.props.location.path === '/albums' ? 'active' : ''}>
              <Link to="/albums">Albums</Link>
            </li>
            <li className={this.props.location.path === '/customers' ? 'active' : ''}>
              <Link to="/customers">Customers</Link>
            </li>
            <li className={this.props.location.path === '/employees' ? 'active' : ''}>
              <Link to="/employees">Employees</Link>
            </li>
            <li className={this.props.location.path === '/genres' ? 'active' : ''}>
              <Link to="/genres">Genres</Link>
            </li>
            <li className={this.props.location.path === '/invoices' ? 'active' : ''}>
              <Link to="/invoices">Invoices</Link>
            </li>
            <li className={this.props.location.path === '/invoicelines' ? 'active' : ''}>
              <Link to="/invoicelines">InvoiceLines</Link>
            </li>
            <li className={this.props.location.path === '/mediatypes' ? 'active' : ''}>
              <Link to="/mediatypes">MediaTypes</Link>
            </li>
            <li className={this.props.location.path === '/playlists' ? 'active' : ''}>
              <Link to="/playlists">Playlists</Link>
            </li>
            <li className={this.props.location.path === '/playlisttracks' ? 'active' : ''}>
              <Link to="/playlisttracks">PlaylistTracks</Link>
            </li>
            <li className={this.props.location.path === '/tracks' ? 'active' : ''}>
              <Link to="/tracks">Tracks</Link>
            </li>
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
