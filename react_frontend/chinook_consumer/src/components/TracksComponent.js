/**
 * TracksComponent.
 *
 * Component that fetches a list of tracks from the REST-API and renders it in a datatable.
 */
'use strict';

import React from 'react';
import {Table, Column, Cell} from 'fixed-data-table';
import Dimensions from 'react-dimensions'
import Formsy from 'formsy-react';
import TextInputComponent from './TextInputComponent';

require('styles//DataTable.css');
require('styles//Tracks.css');

class TracksComponent extends React.Component {
  constructor(props, context) {
    super(props, context);

    this.state = {
      tracks: [],
      url: "http://localhost:7777/resources/tracks",
      track: {},
      trackUrl : "",
      album: {},
      mediaType: {},
      genre: {},
      canSubmit: false
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

  loadAlbumFromServer(url) {
    $.ajax({
      type: "GET",
      url: url,
      dataType: 'json',
      success: (albumData) => {
        this.setState({album: albumData.album})
      },
      error: (xhr, status, err) => {
        console.error(url, status, err.toString());
      }
    });
  }

  loadMediaTypeFromServer(url) {
    $.ajax({
      type: "GET",
      url: url,
      dataType: 'json',
      success: (mediatypeData) => {
        this.setState({mediaType: mediatypeData.mediaTypeEntity})
      },
      error: (xhr, status, err) => {
        console.error(url, status, err.toString());
      }
    });
  }

  loadGenreFromServer(url) {
    $.ajax({
      type: "GET",
      url: url,
      dataType: 'json',
      success: (genreData) => {
        this.setState({genre: genreData.genre})
      },
      error: (xhr, status, err) => {
        console.error(url, status, err.toString());
      }
    });
  }

  updateTrack(index) {
    this.setState({track: this.state.tracks[index].track, trackUrl: this.state.tracks[index]._links.self.href})
  }

  postTrackToServer(data) {
    $.ajax({
      type: "POST",
      url: this.state.url,
      data: JSON.stringify(
        {
          name: data.name,
          albumId: data.albumId,
          mediaTypeId: data.mediaTypeId,
          genreId: data.genreId,
          composer: data.composer,
          milliseconds: data.milliseconds,
          bytes : data.bytes,
          unitPrice: data.unitPrice
        }),
      contentType: "application/json; charset=utf-8",
      dataType: "json",
      success: (response) => {
        this.loadTracksFromServer();
      },
      error: (xhr, status, err) => {
        console.error(this.state.url, status, err.toString());
      }
    });
    $("#addModal").modal('hide');
  }

  putTrackToServer(data) {
    $.ajax({
      type: "PUT",
      url: this.state.trackUrl,
      data: JSON.stringify(
        {
          name: data.name,
          albumId: data.albumId,
          mediaTypeId: data.mediaTypeId,
          genreId: data.genreId,
          composer: data.composer,
          milliseconds: data.milliseconds,
          bytes : data.bytes,
          unitPrice: data.unitPrice
        }),
      contentType: "application/json; charset=utf-8",
      dataType: "json",
      success: (response) => {
        this.loadTracksFromServer();
      },
      error: (xhr, status, err) => {
        console.error(this.state.trackUrl, status, err.toString());
      }
    });
    $("#editModal").modal('hide');
  }

  deleteTrackFromServer() {
    $.ajax({
      type: "DELETE",
      url: this.state.trackUrl,
      dataType: "json",
      success: (response) => {
        this.loadTracksFromServer();
      },
      error: (xhr, status, err) => {
        console.error(this.state.trackUrl, status, err.toString());
      }
    });
  }

  componentDidMount() {
    this.loadTracksFromServer();
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
      <div className="tracks-component">
        <div id="editModal" className="modal fade" role="dialog">
          <div className="modal-dialog">
            <div className="modal-content">
              <div className="modal-header">
                <button type="button" className="close" data-dismiss="modal">&times;</button>
                <h4 className="modal-title">Edit Track</h4>
              </div>
              <div className="modal-body row">
                <Formsy.Form onValidSubmit={this.putTrackToServer.bind(this)} onValid={this.enableButton.bind(this)}
                             onInvalid={this.disableButton.bind(this)}>
                  <div className="form-group">
                    <label className="col-sm-4" for="track_name">Name</label>
                    <TextInputComponent name="name" validationError="this field is required" required id="track_name"
                                        placeholder="name" value={this.state.track.name}/>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="track_albumid">Album Id</label>
                    <TextInputComponent name="albumId" validations="isInt" validationError="album id needs to be a integer" required id="track_albumid"
                                        placeholder="album id" value={this.state.track.albumId}/>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="track_mediatypeid">MediaType Id</label>
                    <TextInputComponent name="mediaTypeId" validations="isInt" validationError="Mediatype id needs to be a integer" required id="track_mediatypeid"
                                        placeholder="mediaTypeId" value={this.state.track.mediaTypeId}/>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="track_genreid">Genre Id</label>
                    <TextInputComponent name="genreId" validations="isInt" validationError="Genre id nees to be a integer" required id="track_genreid"
                                        placeholder="genre id" value={this.state.track.genreId}/>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="track_composer">Composer</label>
                    <TextInputComponent name="composer" validationError="this field is required" required id="track_composer"
                                        placeholder="composer" value={this.state.track.composer}/>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="track_milliseconds">Milliseconds</label>
                    <TextInputComponent name="milliseconds" validations="isNumeric" validationError="milliseconds need to be a number" required id="track_milliseconds"
                                        placeholder="milliseconds" value={this.state.track.milliseconds}/>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="track_bytes">Bytes</label>
                    <TextInputComponent name="bytes" validations="isNumeric" validationError="bytes need to be a number" required id="track_bytes"
                                        placeholder="bytes" value={this.state.track.bytes}/>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="track_unitprice">Unitprice</label>
                    <TextInputComponent name="unitPrice" validations="isNumeric" validationError="unitprice need to be a number" required id="track_unitprice"
                                        placeholder="unitPrice" value={this.state.track.unitPrice}/>
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
        <div id="albumModal" className="modal fade" role="dialog">
          <div className="modal-dialog">
            <div className="modal-content">
              <div className="modal-header">
                <button type="button" className="close" data-dismiss="modal">&times;</button>
                <h4 className="modal-title">Album</h4>
              </div>
              <div className="modal-body row">
                <div>
                  <label className="col-sm-4">Id</label>
                  <div className="col-sm-8 margin_bottom">
                    <p>{this.state.album.albumId} &nbsp;</p>
                  </div>
                  <label className="col-sm-4">Title</label>
                  <div className="col-sm-8 margin_bottom">
                    <p>{this.state.album.title} &nbsp;</p>
                  </div>
                  <label className="col-sm-4">Artist Id</label>
                  <div className="col-sm-8 margin_bottom">
                    <p>{this.state.album.artistId} &nbsp;</p>
                  </div>
                </div>
              </div>
              <div className="modal-footer">
                <button type="button" className="btn btn-default" data-dismiss="modal">Close</button>
              </div>
            </div>
          </div>
        </div>
        <div id="mediaTypeModal" className="modal fade" role="dialog">
          <div className="modal-dialog">
            <div className="modal-content">
              <div className="modal-header">
                <button type="button" className="close" data-dismiss="modal">&times;</button>
                <h4 className="modal-title">MediaType</h4>
              </div>
              <div className="modal-body row">
                <div>
                  <label className="col-sm-4">Id</label>
                  <div className="col-sm-8 margin_bottom">
                    <p>{this.state.mediaType.mediaTypeId} &nbsp;</p>
                  </div>
                  <label className="col-sm-4">Name</label>
                  <div className="col-sm-8 margin_bottom">
                    <p>{this.state.mediaType.name} &nbsp;</p>
                  </div>
                </div>
              </div>
              <div className="modal-footer">
                <button type="button" className="btn btn-default" data-dismiss="modal">Close</button>
              </div>
            </div>
          </div>
        </div>
        <div id="genreModal" className="modal fade" role="dialog">
          <div className="modal-dialog">
            <div className="modal-content">
              <div className="modal-header">
                <button type="button" className="close" data-dismiss="modal">&times;</button>
                <h4 className="modal-title">Genre</h4>
              </div>
              <div className="modal-body row">
                <div>
                  <label className="col-sm-4">Id</label>
                  <div className="col-sm-8 margin_bottom">
                    <p>{this.state.genre.genreId} &nbsp;</p>
                  </div>
                  <label className="col-sm-4">Name</label>
                  <div className="col-sm-8 margin_bottom">
                    <p>{this.state.genre.name} &nbsp;</p>
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
                        onClick={this.deleteTrackFromServer.bind(this)}
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
                <h4 className="modal-title">Create new Track</h4>
              </div>
              <div className="modal-body row">
                <Formsy.Form onValidSubmit={this.postTrackToServer.bind(this)} onValid={this.enableButton.bind(this)}
                             onInvalid={this.disableButton.bind(this)}>
                  <div className="form-group">
                    <label className="col-sm-4" for="track_name">Name</label>
                    <TextInputComponent name="name" validationError="this field is required" required id="track_name"
                                        placeholder="name" />
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="track_albumid">Album Id</label>
                    <TextInputComponent name="albumId" validations="isInt" validationError="album id needs to be a integer" required id="track_albumid"
                                        placeholder="album id" />
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="track_mediatypeid">MediaType Id</label>
                    <TextInputComponent name="mediaTypeId" validations="isInt" validationError="Mediatype id needs to be a integer" required id="track_mediatypeid"
                                        placeholder="mediaTypeId" />
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="track_genreid">Genre Id</label>
                    <TextInputComponent name="genreId" validations="isInt" validationError="Genre id nees to be a integer" required id="track_genreid"
                                        placeholder="genre id" />
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="track_composer">Composer</label>
                    <TextInputComponent name="composer" validationError="this field is required" required id="track_composer"
                                        placeholder="composer" />
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="track_milliseconds">Milliseconds</label>
                    <TextInputComponent name="milliseconds" validations="isNumeric" validationError="milliseconds need to be a number" required id="track_milliseconds"
                                        placeholder="milliseconds" />
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="track_bytes">Bytes</label>
                    <TextInputComponent name="bytes" validations="isNumeric" validationError="bytes need to be a number" required id="track_bytes"
                                        placeholder="bytes" />
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="track_unitprice">Unitprice</label>
                    <TextInputComponent name="unitPrice" validations="isNumeric" validationError="unitprice need to be a number" required id="track_unitprice"
                                        placeholder="unitPrice" />
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
            <Column
              header={<Cell>Album</Cell>}
              cell={props => (
<Cell {...props}>
        <button type="button" className="btn btn-default btn-sm" data-toggle="modal" data-target="#albumModal" onClick={this.loadAlbumFromServer.bind(this, this.state.tracks[props.rowIndex]._links.album.href)}>
          <span className="glyphicon glyphicon-info-sign"> Id: {this.state.tracks[props.rowIndex].track.albumId}</span>
        </button>
</Cell>
        )}
              width={150}
              flexGrow={1}
            />
            <Column
              header={<Cell>MediaType</Cell>}
              cell={props => (
<Cell {...props}>
        <button type="button" className="btn btn-default btn-sm" data-toggle="modal" data-target="#mediaTypeModal" onClick={this.loadMediaTypeFromServer.bind(this, this.state.tracks[props.rowIndex]._links.mediatype.href)}>
          <span className="glyphicon glyphicon-info-sign"> Id: {this.state.tracks[props.rowIndex].track.mediaTypeId}</span>
        </button>
</Cell>
        )}
              width={150}
              flexGrow={1}
            />
            <Column
              header={<Cell>Genre</Cell>}
              cell={props => (
<Cell {...props}>
        <button type="button" className="btn btn-default btn-sm" data-toggle="modal" data-target="#genreModal" onClick={this.loadGenreFromServer.bind(this, this.state.tracks[props.rowIndex]._links.genre.href)}>
          <span className="glyphicon glyphicon-info-sign"> Id: {this.state.tracks[props.rowIndex].track.genreId}</span>
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
<button type="button" className="btn btn-default btn-sm" data-toggle="modal" data-target="#editModal" onClick={this.updateTrack.bind(this, props.rowIndex)}>
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
<button type="button" className="btn btn-default btn-sm" data-toggle="modal" data-target="#deleteModal" onClick={this.updateTrack.bind(this, props.rowIndex)}>
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

TracksComponent.displayName = 'TracksComponent';

TracksComponent.propTypes = {};
TracksComponent.defaultProps = {};

export default TracksComponent;
export default Dimensions()(TracksComponent);
