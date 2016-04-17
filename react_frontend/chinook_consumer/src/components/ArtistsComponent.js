'use strict';

import React from 'react';
import {Table, Column, Cell} from 'fixed-data-table';
import $ from "jquery";

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
        console.log(JSON.stringify(artistsData.artists))
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
        <Table
          className="artists-component"
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
    );
  }
}


ArtistsComponent.displayName = 'ArtistsComponent';

export default ArtistsComponent;
