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
      url: "http://localhost:7777/resources/tracks",
      track: {},
      album: {},
      mediaType: {},
      genre: {}
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
    //this.setState({album: index})
    this.setState({track: this.state.tracks[index].track})
  }

  deleteTrack(index) {

  }
  componentDidMount() {
    this.loadTracksFromServer();
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
                <div>
                  <div className="form-group">
                    <label className="col-sm-4" for="track_id">Track Id</label>
                    <div className="col-sm-8 margin_bottom">
                      <input type="text" className="form-control" id="track_id" value={this.state.track.trackId}/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="track_name">Name</label>
                    <div className="col-sm-8 margin_bottom">
                      <input type="text" className="form-control" id="track_name" value={this.state.track.name}/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="album_id">Album Id</label>
                    <div className="col-sm-8 margin_bottom">
                      <input type="text" className="form-control" id="album_id" value={this.state.track.albumId}/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="mediaType_id">MediaType Id</label>
                    <div className="col-sm-8 margin_bottom">
                      <input type="text" className="form-control" id="mediaType_id" value={this.state.track.mediaTypeId}/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="genre_id">Genre Id</label>
                    <div className="col-sm-8 margin_bottom">
                      <input type="text" className="form-control" id="genre_id" value={this.state.track.genreId}/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="track_composer">Composer</label>
                    <div className="col-sm-8 margin_bottom">
                      <input type="text" className="form-control" id="track_composer" value={this.state.track.composer}/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="track_milliseconds">Milliseconds</label>
                    <div className="col-sm-8 margin_bottom">
                      <input type="text" className="form-control" id="track_milliseconds" value={this.state.track.milliseconds}/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="track_bytes">Bytes</label>
                    <div className="col-sm-8 margin_bottom">
                      <input type="text" className="form-control" id="track_bytes" value={this.state.track.bytes}/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="track_unitPrice">Unit price</label>
                    <div className="col-sm-8 margin_bottom">
                      <input type="text" className="form-control" id="track_unitPrice" value={this.state.track.unitPrice}/>
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
                <h4 className="modal-title">Create new Track</h4>
              </div>
              <div className="modal-body row">
                <div>
                  <div className="form-group">
                    <label className="col-sm-4" for="track_id">Track Id</label>
                    <div className="col-sm-8 margin_bottom">
                      <input type="text" className="form-control" id="track_id" placeholder="id"/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="track_name">Name</label>
                    <div className="col-sm-8 margin_bottom">
                      <input type="text" className="form-control" id="track_name" placeholder="name"/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="album_id">Album Id</label>
                    <div className="col-sm-8 margin_bottom">
                      <input type="text" className="form-control" id="album_id" placeholder="album id"/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="mediaType_id">MediaType Id</label>
                    <div className="col-sm-8 margin_bottom">
                      <input type="text" className="form-control" id="mediaType_id" placeholder="mediatype id"/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="genre_id">Genre Id</label>
                    <div className="col-sm-8 margin_bottom">
                      <input type="text" className="form-control" id="genre_id" placeholder="genre id"/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="track_composer">Composer</label>
                    <div className="col-sm-8 margin_bottom">
                      <input type="text" className="form-control" id="track_composer" placeholder="composer"/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="track_milliseconds">Milliseconds</label>
                    <div className="col-sm-8 margin_bottom">
                      <input type="text" className="form-control" id="track_milliseconds" placeholder="milliseconds"/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="track_bytes">Bytes</label>
                    <div className="col-sm-8 margin_bottom">
                      <input type="text" className="form-control" id="track_bytes" placeholder="bytes"/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="track_unitPrice">Unit price</label>
                    <div className="col-sm-8 margin_bottom">
                      <input type="text" className="form-control" id="track_unitPrice" placeholder="unit price"/>
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
<button type="button" className="btn btn-default btn-sm" data-toggle="modal" data-target="#deleteModal" onClick={this.deleteTrack.bind(this, props.rowIndex)}>
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
