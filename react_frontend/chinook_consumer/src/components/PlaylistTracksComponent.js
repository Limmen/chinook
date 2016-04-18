/**
 * PlaylistTracksComponent.
 *
 * Component that fetches a list of playlistTracks from the REST-API and renders it in a datatable.
 */
'use strict';

import React from 'react';
import {Table, Column, Cell} from 'fixed-data-table';
import $ from "jquery";
import Dimensions from 'react-dimensions'

require('styles//DataTable.css');
require('styles//PlaylistTracks.css');

class PlaylistTracksComponent extends React.Component {
  constructor(props, context) {
    super(props, context);

    this.state = {
      playlistTracks: [],
      url: "http://localhost:7777/resources/playlisttracks"
    }
  };

  loadPlaylistTracksFromServer() {
    $.ajax({
      type: "GET",
      url: this.state.url,
      dataType: 'json',
      success: (playlistTracksData) => {
        this.setState({playlistTracks: playlistTracksData.playlistTracks})
      },
      error: (xhr, status, err) => {
        console.error(this.state.url, status, err.toString());
      }
    });
  }

  componentDidMount() {
    this.loadPlaylistTracksFromServer();
  }
  render() {
    return (
      <div className="playlisttracks-component">
        <div className="datatablecontainer">
          <Table
            rowsCount={this.state.playlistTracks.length}
            rowHeight={50}
            headerHeight={50}
            width={this.props.containerWidth}
            height={500}>
          </Table>
        </div>
      </div>
    );
  }
}

PlaylistTracksComponent.displayName = 'PlaylistTracksComponent';

PlaylistTracksComponent.propTypes = {};
PlaylistTracksComponent.defaultProps = {};

export default Dimensions()(PlaylistTracksComponent);
