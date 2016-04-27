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
      url: "http://localhost:7777/resources/mediatypes",
      mediaType: {}
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
  updateMediaType(index) {
    //this.setState({artist: index})
    this.setState({mediaType: this.state.mediaTypes[index].mediaTypeEntity})
  }

  deleteMediaType(index) {

  }
  componentDidMount() {
    this.loadMediaTypesFromServer();
  }
  render() {
    return (
      <div className="mediatype-component">
        <div id="editModal" className="modal fade" role="dialog">
          <div className="modal-dialog">
            <div className="modal-content">
              <div className="modal-header">
                <button type="button" className="close" data-dismiss="modal">&times;</button>
                <h4 className="modal-title">Edit MediaType</h4>
              </div>
              <div className="modal-body row">
                <div>
                  <div className="form-group">
                    <label className="col-sm-4" for="artist_id">Id</label>
                    <div className="col-sm-8 margin_bottom">
                      <input type="text" className="form-control" id="artist_id" value={this.state.mediaType.mediaTypeId}/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="artist_name">Name</label>
                    <div className="col-sm-8 margin_bottom">
                      <input type="text" className="form-control" id="artist_name" value={this.state.mediaType.name}/>
                    </div>
                  </div>
                </div>
              </div>
              <div className="modal-footer">
                <button type="button" className="btn btn-default" data-dismiss="modal">Close</button>
              </div>
            </div>
          </div>
        </div>
        <div id="deleteModal" className="modal fade" role="dialog">
          <div className="modal-dialog">
            <div className="modal-content">
              <div className="modal-header">
                <button type="button" className="close" data-dismiss="modal">&times;</button>
                <h4 className="modal-title">Are you sure?</h4>
              </div>
              <div className="modal-body row">
                <button type="button" className="btn btn-default">Yes</button><button type="button" className="btn btn-default">No</button>
              </div>
              <div className="modal-footer">
                <button type="button" className="btn btn-default" data-dismiss="modal">Close</button>
              </div>
            </div>
          </div>
        </div>
        <div id="addModal" className="modal fade" role="dialog">
          <div className="modal-dialog">
            <div className="modal-content">
              <div className="modal-header">
                <button type="button" className="close" data-dismiss="modal">&times;</button>
                <h4 className="modal-title">Create new MediaType</h4>
              </div>
              <div className="modal-body row">
                <div>
                  <div className="form-group">
                    <label className="col-sm-4" for="artist_id">Id</label>
                    <div className="col-sm-8 margin_bottom">
                      <input type="text" className="form-control" id="artist_id" placeholder="id"/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="artist_title">Name</label>
                    <div className="col-sm-8 margin_bottom">
                      <input type="text" className="form-control" id="artist_title" placeholder="title"/>
                    </div>
                  </div>
                </div>
              </div>
              <div className="modal-footer">
                <button type="button" className="btn btn-default" data-dismiss="modal">Close</button>
              </div>
            </div>
          </div>
        </div>
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
            <Column
              header={<Cell>Edit</Cell>}
              cell={props => (
<Cell {...props}>
<button type="button" className="btn btn-default btn-sm" data-toggle="modal" data-target="#editModal" onClick={this.updateMediaType.bind(this, props.rowIndex)}>
          <span className="glyphicon glyphicon-edit"></span> Edit
        </button>
</Cell>
        )}
              width={150}
              flexGrow={1}
            />
            <Column
              header={<Cell>Delete</Cell>}
              cell={props => (
<Cell {...props}>
<button type="button" className="btn btn-default btn-sm" data-toggle="modal" data-target="#deleteModal" onClick={this.deleteMediaType.bind(this, props.rowIndex)}>
          <span className="glyphicon glyphicon-trash"></span> Delete
        </button>
</Cell>
        )}
              width={150}
              flexGrow={1}
            />
          </Table>
        </div>
        <button type="button" className="btn btn-default" data-toggle="modal" data-target="#addModal">
          <span className="glyphicon glyphicon-plus"></span> Add
        </button>
      </div>
    );
  }
}

MediaTypesComponent.displayName = 'MediaTypeComponent';

MediaTypesComponent.propTypes = {};
MediaTypesComponent.defaultProps = {};

export default Dimensions()(MediaTypesComponent);
