/**
 * AlbumsComponent.
 *
 * Component that fetches a list of albums from the REST-API and renders it in a datatable.
 */

'use strict';

import React from 'react';
import {Table, Column, Cell} from 'fixed-data-table';
import $ from "jquery";
import Dimensions from 'react-dimensions'

require('styles//DataTable.css');
require('styles//Albums.css');

class AlbumsComponent extends React.Component {
  constructor(props, context) {
    super(props, context);

    this.state = {
      albums: [],
      url: "http://localhost:7777/resources/albums"
    }
  };

  loadAlbumsFromServer() {
    $.ajax({
      type: "GET",
      url: this.state.url,
      dataType: 'json',
      success: (albumsData) => {
        this.setState({albums: albumsData.albums})
      },
      error: (xhr, status, err) => {
        console.error(this.state.url, status, err.toString());
      }
    });
  }

  componentDidMount() {
    this.loadAlbumsFromServer();
  }

  render() {
    return (
      <div className="albums-component">
        <div className="datatablecontainer">
          <Table
            rowsCount={this.state.albums.length}
            rowHeight={50}
            headerHeight={50}
            width={this.props.containerWidth}
            height={500}>
            <Column
              header={<Cell>Id</Cell>}
              cell={props => (
           <Cell {...props}>
        {this.state.albums[props.rowIndex].album.albumId}
          </Cell>
        )}
              width={50}
              flexGrow={1}
            />
            <Column
              header={<Cell>Title</Cell>}
              cell={props => (
<Cell {...props}>
        {this.state.albums[props.rowIndex].album.title}
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

AlbumsComponent.displayName = 'AlbumsComponent';

AlbumsComponent.propTypes = {};
AlbumsComponent.defaultProps = {};

export default Dimensions()(AlbumsComponent);
