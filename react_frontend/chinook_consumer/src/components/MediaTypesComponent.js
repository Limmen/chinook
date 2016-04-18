/**
 * MediaTypesComponent.
 *
 * Component that fetches a list of mediaTypes from the REST-API and renders it in a datatable.
 */
'use strict';

import React from 'react';
import {Table, Column, Cell} from 'fixed-data-table';
import $ from "jquery";
import Dimensions from 'react-dimensions'

require('styles//DataTable.css');
require('styles//MediaTypes.css');

class MediaTypesComponent extends React.Component {
  constructor(props, context) {
    super(props, context);

    this.state = {
      mediaTypes: [],
      url: "http://localhost:7777/resources/mediatypes"
    }
  };

  loadMediaTypesFromServer() {
    $.ajax({
      type: "GET",
      url: this.state.url,
      dataType: 'json',
      success: (mediaTypesData) => {
        this.setState({mediaTypes: mediaTypesData.mediaTypes})
      },
      error: (xhr, status, err) => {
        console.error(this.state.url, status, err.toString());
      }
    });
  }

  componentDidMount() {
    this.loadMediaTypesFromServer();
  }
  render() {
    return (
      <div className="mediatype-component">
        <div className="datatablecontainer">
          <Table
            rowsCount={this.state.mediaTypes.length}
            rowHeight={50}
            headerHeight={50}
            width={this.props.containerWidth}
            height={500}>
            <Column
              header={<Cell>Id</Cell>}
              cell={props => (
           <Cell {...props}>
        {this.state.mediaTypes[props.rowIndex].mediaTypeEntity.mediaTypeId}
          </Cell>
        )}
              width={50}
              flexGrow={1}
            />
            <Column
              header={<Cell>Name</Cell>}
              cell={props => (
<Cell {...props}>
        {this.state.mediaTypes[props.rowIndex].mediaTypeEntity.name}
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

MediaTypesComponent.displayName = 'MediaTypeComponent';

MediaTypesComponent.propTypes = {};
MediaTypesComponent.defaultProps = {};

export default Dimensions()(MediaTypesComponent);
