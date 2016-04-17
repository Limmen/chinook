/**
 * ArtistsComponent.
 *
 * Component that fetches a list of artists from the REST-API and renders it in a datatable.
 */
'use strict';

import React from 'react';
import {Table, Column, Cell} from 'fixed-data-table';
import $ from "jquery";

require('styles//DataTable.css');
require('styles//Artists.css');

class ArtistsComponent extends React.Component {
  constructor(props, context) {
    super(props, context);

    this.state = {
      artists: [],
      url: "http://localhost:7777/resources/artists"
    }
  };

  loadArtistsFromServer() {
    $.ajax({
      type: "GET",
      url: this.state.url,
      dataType: 'json',
      success: (artistsData) => {
        this.setState({artists: artistsData.artists})
      },
      error: (xhr, status, err) => {
        console.error(this.state.url, status, err.toString());
      }
    });
  }

  componentDidMount() {
    this.loadArtistsFromServer();
  }

  render() {
    return (
      <div className="artists-component">
        <div className="datatablecontainer">
        <Table
          rowsCount={this.state.artists.length}
          rowHeight={50}
          headerHeight={50}
          width={1000}
          height={500}>
          <Column
            header={<Cell>Id</Cell>}
            cell={props => (
           <Cell {...props}>
        {this.state.artists[props.rowIndex].artist.artistId}
          </Cell>
        )}
            width={500}
          />
          <Column
            header={<Cell>Name</Cell>}
            cell={props => (
<Cell {...props}>
        {this.state.artists[props.rowIndex].artist.name}
</Cell>
        )}
            width={500}
          />
        </Table>
          </div>
      </div>
    );
  }
}


ArtistsComponent.displayName = 'ArtistsComponent';

ArtistsComponent.propTypes = {};
ArtistsComponent.defaultProps = {};

export default ArtistsComponent;
