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
      url: "http://localhost:7777/resources/playlists",
      playlist: {}
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
  updatePlaylist(index) {
    //this.setState({artist: index})
    this.setState({playlist: this.state.playlists[index].playlist})
  }

  deletePlaylist(index) {

  }
  componentDidMount() {
    this.loadPlaylistsFromServer();
  }
  render() {
    return (
      <div className="playlists-component">
        <div id="editModal" className="modal fade" role="dialog">
          <div className="modal-dialog">
            <div className="modal-content">
              <div className="modal-header">
                <button type="button" className="close" data-dismiss="modal">&times;</button>
                <h4 className="modal-title">Edit Playlist</h4>
              </div>
              <div className="modal-body row">
                <div>
                  <div className="form-group">
                    <label className="col-sm-4" for="artist_id">Id</label>
                    <div className="col-sm-8 margin_bottom">
                      <input type="text" className="form-control" id="artist_id" value={this.state.playlist.playlistId}/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="artist_name">Name</label>
                    <div className="col-sm-8 margin_bottom">
                      <input type="text" className="form-control" id="artist_name" value={this.state.playlist.name}/>
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
                <h4 className="modal-title">Create new Playlist</h4>
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
            <Column
              header={<Cell>Edit</Cell>}
              cell={props => (
<Cell {...props}>
<button type="button" className="btn btn-default btn-sm" data-toggle="modal" data-target="#editModal" onClick={this.updatePlaylist.bind(this, props.rowIndex)}>
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
<button type="button" className="btn btn-default btn-sm" data-toggle="modal" data-target="#deleteModal" onClick={this.deletePlaylist.bind(this, props.rowIndex)}>
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

PlaylistsComponent.displayName = 'PlaylistsComponent';

PlaylistsComponent.propTypes = {};
PlaylistsComponent.defaultProps = {};

export default Dimensions()(PlaylistsComponent);
