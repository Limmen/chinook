/**
 * PlaylistTracksComponent.
 *
 * Component that fetches a list of playlistTracks from the REST-API and renders it in a datatable.
 */
'use strict';

import React from 'react';
import {Table, Column, Cell} from 'fixed-data-table';
import Dimensions from 'react-dimensions'
import Formsy from 'formsy-react';
import TextInputComponent from './TextInputComponent';

require('styles//DataTable.css');
require('styles//PlaylistTracks.css');

class PlaylistTracksComponent extends React.Component {
  constructor(props, context) {
    super(props, context);

    this.state = {
      playlistTracks: [],
      url: "http://localhost:7777/resources/playlisttracks",
      playlistTrack: {},
      playlistTrackUrl: "",
      track: {},
      playlist: {},
      canSubmit: false
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

  loadTrackFromServer(url) {
    $.ajax({
      type: "GET",
      url: url,
      dataType: 'json',
      success: (trackData) => {
        this.setState({track: trackData.track})
      },
      error: (xhr, status, err) => {
        console.error(url, status, err.toString());
      }
    });
  }

  loadPlaylistFromServer(url) {
    $.ajax({
      type: "GET",
      url: url,
      dataType: 'json',
      success: (playlistData) => {
        this.setState({playlist: playlistData.playlist})
      },
      error: (xhr, status, err) => {
        console.error(url, status, err.toString());
      }
    });
  }

  updatePlaylistTrack(index) {
    this.setState({
      playlistTrack: this.state.playlistTracks[index].playlistTrack,
      playlistTrackUrl: this.state.playlistTracks[index]._links.self.href
    })
  }

  postPlaylistTrackToServer(data) {
    $.ajax({
      type: "POST",
      url: this.state.url,
      data: JSON.stringify(
        {
          trackId: data.trackId,
          playlistId: data.playlistId
        }),
      contentType: "application/json; charset=utf-8",
      dataType: "json",
      success: (response) => {
        this.loadPlaylistTracksFromServer();
      },
      error: (xhr, status, err) => {
        console.error(this.state.url, status, err.toString());
      }
    });
    $("#addModal").modal('hide');
  }

  putPlaylistTrackToServer(data) {
    $.ajax({
      type: "PUT",
      url: this.state.playlistTrackUrl,
      data: JSON.stringify(
        {
          trackId: data.trackId,
          playlistId: data.playlistId
        }),
      contentType: "application/json; charset=utf-8",
      dataType: "json",
      success: (response) => {
        this.loadPlaylistTracksFromServer();
      },
      error: (xhr, status, err) => {
        console.error(this.state.playlistTrackUrl, status, err.toString());
      }
    });
    $("#editModal").modal('hide');
  }

  deletePlaylistTrackFromServer() {
    $.ajax({
      type: "DELETE",
      url: this.state.playlistTrackUrl,
      dataType: "json",
      success: (response) => {
        this.loadPlaylistTracksFromServer();
      },
      error: (xhr, status, err) => {
        console.error(this.state.playlistTrackUrl, status, err.toString());
      }
    });
  }

  componentDidMount() {
    this.loadPlaylistTracksFromServer();
  }

  enableButton() {
    this.setState({
      canSubmit: true
    });
  }

  disableButton() {
    this.setState({
      canSubmit: false
    });
  }

  render() {
    return (
      <div className="playlisttracks-component">
        <div id="editModal" className="modal fade" role="dialog">
          <div className="modal-dialog">
            <div className="modal-content">
              <div className="modal-header">
                <button type="button" className="close" data-dismiss="modal">&times;</button>
                <h4 className="modal-title">Edit PlaylistTrack</h4>
              </div>
              <div className="modal-body row">
                <Formsy.Form onValidSubmit={this.putPlaylistTrackToServer.bind(this)}
                             onValid={this.enableButton.bind(this)}
                             onInvalid={this.disableButton.bind(this)}>
                  <div className="form-group">
                    <label className="col-sm-4" for="track_id">Track ID</label>
                    <TextInputComponent name="trackId" validations="isInt"
                                        validationError="Track id needs to be a integer" required id="track_id"
                                        placeholder="track id" value={this.state.playlistTrack.trackId}/>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="playlist_id">Playlist ID</label>
                    <TextInputComponent name="playlistId" validations="isInt"
                                        validationError="Playlist id needs to be a integer" required id="playlist_id"
                                        placeholder="playlist id" value={this.state.playlistTrack.playlistId}/>
                  </div>
                  <button type="submit" disabled={!this.state.canSubmit} className="btn btn-default">Submit</button>
                </Formsy.Form>
              </div>
              <div className="modal-footer">
                <button type="button" className="btn btn-default" data-dismiss="modal">Close</button>
              </div>
            </div>
          </div>
        </div>
        <div id="trackModal" className="modal fade" role="dialog">
          <div className="modal-dialog">
            <div className="modal-content">
              <div className="modal-header">
                <button type="button" className="close" data-dismiss="modal">&times;</button>
                <h4 className="modal-title">Track</h4>
              </div>
              <div className="modal-body row">
                <div>
                  <label className="col-sm-4">Id</label>
                  <div className="col-sm-8 margin_bottom">
                    <p>{this.state.track.trackId} &nbsp;</p>
                  </div>
                  <label className="col-sm-4">Name</label>
                  <div className="col-sm-8 margin_bottom">
                    <p>{this.state.track.name} &nbsp;</p>
                  </div>
                  <label className="col-sm-4">Album Id</label>
                  <div className="col-sm-8 margin_bottom">
                    <p>{this.state.track.albumId} &nbsp;</p>
                  </div>
                  <label className="col-sm-4">mediaTypeId</label>
                  <div className="col-sm-8 margin_bottom">
                    <p>{this.state.track.mediaTypeId} &nbsp;</p>
                  </div>
                  <label className="col-sm-4">Genre Id</label>
                  <div className="col-sm-8 margin_bottom">
                    <p>{this.state.track.genreId} &nbsp;</p>
                  </div>
                  <label className="col-sm-4">Composer</label>
                  <div className="col-sm-8 margin_bottom">
                    <p>{this.state.track.composer} &nbsp;</p>
                  </div>
                  <label className="col-sm-4">Milliseconds</label>
                  <div className="col-sm-8 margin_bottom">
                    <p>{this.state.track.milliseconds} &nbsp;</p>
                  </div>
                  <label className="col-sm-4">Bytes</label>
                  <div className="col-sm-8 margin_bottom">
                    <p>{this.state.track.bytes} &nbsp;</p>
                  </div>
                  <label className="col-sm-4">Unit price</label>
                  <div className="col-sm-8 margin_bottom">
                    <p>{this.state.track.unitPrice} &nbsp;</p>
                  </div>
                </div>
              </div>
              <div className="modal-footer">
                <button type="button" className="btn btn-default" data-dismiss="modal">Close</button>
              </div>
            </div>
          </div>
        </div>
        <div id="playlistModal" className="modal fade" role="dialog">
          <div className="modal-dialog">
            <div className="modal-content">
              <div className="modal-header">
                <button type="button" className="close" data-dismiss="modal">&times;</button>
                <h4 className="modal-title">Playlist</h4>
              </div>
              <div className="modal-body row">
                <div>
                  <label className="col-sm-4">Id</label>
                  <div className="col-sm-8 margin_bottom">
                    <p>{this.state.playlist.playlistId} &nbsp;</p>
                  </div>
                  <label className="col-sm-4">Name</label>
                  <div className="col-sm-8 margin_bottom">
                    <p>{this.state.playlist.name} &nbsp;</p>
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
                <button type="button" className="btn btn-default"
                        onClick={this.deletePlaylistTrackFromServer.bind(this)}
                        data-dismiss="modal">
                  Yes
                </button>
                <button type="button" className="btn btn-default" data-dismiss="modal">No</button>
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
                <h4 className="modal-title">Create new PlaylistTrack</h4>
              </div>
              <div className="modal-body row">
                <Formsy.Form onValidSubmit={this.postPlaylistTrackToServer.bind(this)}
                             onValid={this.enableButton.bind(this)}
                             onInvalid={this.disableButton.bind(this)}>
                  <div className="form-group">
                    <label className="col-sm-4" for="track_id">Track ID</label>
                    <TextInputComponent name="trackId" validations="isInt"
                                        validationError="Track id needs to be a integer" required id="track_id"
                                        placeholder="track id"/>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="playlist_id">Playlist ID</label>
                    <TextInputComponent name="playlistId" validations="isInt"
                                        validationError="Playlist id needs to be a integer" required
                                        id="playlist_id"
                                        placeholder="playlist id"/>
                  </div>
                  <button type="submit" disabled={!this.state.canSubmit} className="btn btn-default">Submit</button>
                </Formsy.Form>
              </div>
              <div className="modal-footer">
                <button type="button" className="btn btn-default" data-dismiss="modal">Close</button>
              </div>
            </div>
          </div>
        </div>
        <div className="datatablecontainer">
          <Table
            rowsCount={this.state.playlistTracks.length}
            rowHeight={50}
            headerHeight={50}
            width={this.props.containerWidth}
            height={500}>

            <Column
              header={<Cell>Track</Cell>}
              cell={props => (
<Cell {...props}>
        <button type="button" className="btn btn-default btn-sm" data-toggle="modal" data-target="#trackModal" onClick={this.loadTrackFromServer.bind(this, this.state.playlistTracks[props.rowIndex]._links.track.href)}>
          <span className="glyphicon glyphicon-info-sign"> Id: {this.state.playlistTracks[props.rowIndex].playlistTrack.trackId}</span>
        </button>
</Cell>
        )}
              width={150}
              flexGrow={1}
            />
            <Column
              header={<Cell>Playlist</Cell>}
              cell={props => (
<Cell {...props}>
        <button type="button" className="btn btn-default btn-sm" data-toggle="modal" data-target="#playlistModal" onClick={this.loadPlaylistFromServer.bind(this, this.state.playlistTracks[props.rowIndex]._links.playlist.href)}>
          <span className="glyphicon glyphicon-info-sign"> Id: {this.state.playlistTracks[props.rowIndex].playlistTrack.playlistId}</span>
        </button>
</Cell>
        )}
              width={150}
              flexGrow={1}
            />
            <Column
              header={<Cell>Edit</Cell>}
              cell={props => (
<Cell {...props}>
<button type="button" className="btn btn-default btn-sm" data-toggle="modal" data-target="#editModal" onClick={this.updatePlaylistTrack.bind(this, props.rowIndex)}>
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
<button type="button" className="btn btn-default btn-sm" data-toggle="modal" data-target="#deleteModal" onClick={this.updatePlaylistTrack.bind(this, props.rowIndex)}>
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

PlaylistTracksComponent.displayName = 'PlaylistTracksComponent';

PlaylistTracksComponent.propTypes = {};
PlaylistTracksComponent.defaultProps = {};

export default Dimensions()(PlaylistTracksComponent);
