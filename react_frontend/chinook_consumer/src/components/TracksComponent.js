/**
 * TracksComponent.
 *
 * Component that fetches a list of tracks from the REST-API and renders it in a datatable.
 */
'use strict';

import React from 'react';
import {Table, Column, Cell} from 'fixed-data-table';
import $ from "jquery";
import Dimensions from 'react-dimensions'

require('styles//DataTable.css');
require('styles//Tracks.css');

class TracksComponent extends React.Component {
  constructor(props, context) {
    super(props, context);

    this.state = {
      tracks: [],
      url: "http://localhost:7777/resources/tracks"
    }
  };

  loadTracksFromServer() {
    $.ajax({
      type: "GET",
      url: this.state.url,
      dataType: 'json',
      success: (tracksData) => {
        this.setState({tracks: tracksData.tracks})
      },
      error: (xhr, status, err) => {
        console.error(this.state.url, status, err.toString());
      }
    });
  }

  componentDidMount() {
    this.loadTracksFromServer();
  }
  render() {
    return (
      <div className="tracks-component">
        <div className="datatablecontainer">
          <Table
            rowsCount={this.state.tracks.length}
            rowHeight={50}
            headerHeight={50}
            width={this.props.containerWidth}
            height={500}>
            <Column
              header={<Cell>Id</Cell>}
              cell={props => (
           <Cell {...props}>
        {this.state.tracks[props.rowIndex].track.trackId}
          </Cell>
        )}
              width={50}
              flexGrow={1}
            />
            <Column
              header={<Cell>Name</Cell>}
              cell={props => (
<Cell {...props}>
        {this.state.tracks[props.rowIndex].track.name}
</Cell>
        )}
              width={250}
              flexGrow={1}
            />
            <Column
              header={<Cell>Composer</Cell>}
              cell={props => (
<Cell {...props}>
        {this.state.tracks[props.rowIndex].track.composer}
</Cell>
        )}
              width={250}
              flexGrow={1}
            />
            <Column
              header={<Cell>Milliseconds</Cell>}
              cell={props => (
<Cell {...props}>
        {this.state.tracks[props.rowIndex].track.milliseconds}
</Cell>
        )}
              width={250}
              flexGrow={1}
            />
            <Column
              header={<Cell>Bytes</Cell>}
              cell={props => (
<Cell {...props}>
        {this.state.tracks[props.rowIndex].track.bytes}
</Cell>
        )}
              width={250}
              flexGrow={1}
            />
            <Column
              header={<Cell>Unitprice</Cell>}
              cell={props => (
<Cell {...props}>
        {this.state.tracks[props.rowIndex].track.unitPrice}
</Cell>
        )}
              width={250}
              flexGrow={1}
            />
          </Table>
        </div>
      </div>
    );
  }
}

TracksComponent.displayName = 'TracksComponent';

TracksComponent.propTypes = {};
TracksComponent.defaultProps = {};

export default TracksComponent;
export default Dimensions()(TracksComponent);
