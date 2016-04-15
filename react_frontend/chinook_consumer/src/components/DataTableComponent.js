'use strict';

import React from 'react';
import {Table, Column, Cell} from 'fixed-data-table';
import $ from "jquery";

require('styles//DataTable.css');

class DataTableComponent extends React.Component {
  constructor(props, context) {
    super(props, context);

    this.state = {
      artists: []
    }
  };

  loadArtistsFromServer() {
    $.ajax({
      type: "GET",
      url: this.props.url,
      dataType: 'json',
      success: (artistsData) => {
        console.log(JSON.stringify(artistsData.artists))
        this.setState({artists: artistsData.artists})
      },
      error: (xhr, status, err) => {
        console.error(this.props.url, status, err.toString());
      }
    });
  }

  componentDidMount() {
    this.loadArtistsFromServer();
    //setInterval(this.loadArtistsFromServer, this.props.pollInterval);
  }

  render() {
    return (
      <div className="datatable-component">
        <Table
          className="datatable-component"
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


DataTableComponent.displayName = 'DataTableComponent';

export default DataTableComponent;
