/**
 * PlaylistsComponent.
 *
 * Component that fetches a list of playlists from the REST-API and renders it in a datatable.
 */
'use strict';

import React from 'react';
import {Table, Column, Cell} from 'fixed-data-table';
import $ from "jquery";
import Dimensions from 'react-dimensions'

require('styles//DataTable.css');
require('styles//Playlists.css');

class PlaylistsComponent extends React.Component {
  constructor(props, context) {
    super(props, context);

    this.state = {
      playlists: [],
      url: "http://localhost:7777/resources/playlists"
    }
  };

  loadPlaylistsFromServer() {
    $.ajax({
      type: "GET",
      url: this.state.url,
      dataType: 'json',
      success: (playlistsData) => {
        this.setState({playlists: playlistsData.playlists})
      },
      error: (xhr, status, err) => {
        console.error(this.state.url, status, err.toString());
      }
    });
  }

  componentDidMount() {
    this.loadPlaylistsFromServer();
  }
  render() {
    return (
      <div className="playlists-component">
        <div className="datatablecontainer">
          <Table
            rowsCount={this.state.playlists.length}
            rowHeight={50}
            headerHeight={50}
            width={this.props.containerWidth}
            height={500}>
            <Column
              header={<Cell>Id</Cell>}
              cell={props => (
           <Cell {...props}>
        {this.state.playlists[props.rowIndex].playlist.playlistId}
          </Cell>
        )}
              width={50}
              flexGrow={1}
            />
            <Column
              header={<Cell>Name</Cell>}
              cell={props => (
<Cell {...props}>
        {this.state.playlists[props.rowIndex].playlist.name}
</Cell>
        )}
              width={350}
              flexGrow={1}
            />
          </Table>
        </div>
      </div>
    );
  }
}

PlaylistsComponent.displayName = 'PlaylistsComponent';

PlaylistsComponent.propTypes = {};
PlaylistsComponent.defaultProps = {};

export default Dimensions()(PlaylistsComponent);
