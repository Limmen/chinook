/**
 * ArtistsComponent.
 *
 * Component that fetches a list of artists from the REST-API and renders it in a datatable.
 */
'use strict';

import React from 'react';
import {Table, Column, Cell} from 'fixed-data-table';
import $ from "jquery";
import Dimensions from 'react-dimensions'


require('styles//DataTable.css');
require('styles//Artists.css');

class ArtistsComponent extends React.Component {
  constructor(props, context) {
    super(props, context);

    this.state = {
      artists: [],
      url: "http://localhost:7777/resources/artists",
      artist: {}
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
  updateArtist(index) {
    //this.setState({artist: index})
    this.setState({artist: this.state.artists[index].artist})
  }

  deleteArtist(index) {

  }
  componentDidMount() {
    this.loadArtistsFromServer();
  }

  render() {
    return (
      <div className="artists-component">
        <div id="editModal" className="modal fade" role="dialog">
          <div className="modal-dialog">
            <div className="modal-content">
              <div className="modal-header">
                <button type="button" className="close" data-dismiss="modal">&times;</button>
                <h4 className="modal-title">Edit Artist</h4>
              </div>
              <div className="modal-body row">
                <div>
                  <div className="form-group">
                    <label className="col-sm-4" for="artist_id">Id</label>
                    <div className="col-sm-8 margin_bottom">
                      <input type="text" className="form-control" id="artist_id" value={this.state.artist.artistId}/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="artist_name">Name</label>
                    <div className="col-sm-8 margin_bottom">
                      <input type="text" className="form-control" id="artist_name" value={this.state.artist.name}/>
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
                <h4 className="modal-title">Create new Artist</h4>
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
          rowsCount={this.state.artists.length}
          rowHeight={50}
          headerHeight={50}
          width={this.props.containerWidth}
          height={500}>
          <Column
            header={<Cell>Id</Cell>}
            cell={props => (
           <Cell {...props}>
        {this.state.artists[props.rowIndex].artist.artistId}
          </Cell>
        )}
            width={50}
            flexGrow={1}
          />
          <Column
            header={<Cell>Name</Cell>}
            cell={props => (
<Cell {...props}>
        {this.state.artists[props.rowIndex].artist.name}
</Cell>
        )}
            width={350}
            flexGrow={1}
          />
          <Column
            header={<Cell>Edit</Cell>}
            cell={props => (
<Cell {...props}>
<button type="button" className="btn btn-default btn-sm" data-toggle="modal" data-target="#editModal" onClick={this.updateArtist.bind(this, props.rowIndex)}>
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
<button type="button" className="btn btn-default btn-sm" data-toggle="modal" data-target="#deleteModal" onClick={this.deleteArtist.bind(this, props.rowIndex)}>
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


ArtistsComponent.displayName = 'ArtistsComponent';

ArtistsComponent.propTypes = {};
ArtistsComponent.defaultProps = {};

export default Dimensions()(ArtistsComponent);
