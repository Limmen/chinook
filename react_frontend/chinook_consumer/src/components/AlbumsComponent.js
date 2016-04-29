/**
 * AlbumsComponent.
 *
 * Component that fetches a list of albums from the REST-API and renders it in a datatable.
 */

'use strict';

import React from 'react/addons';
import {Table, Column, Cell} from 'fixed-data-table';
import Dimensions from 'react-dimensions'

require('styles//DataTable.css');
require('styles//Albums.css');

class AlbumsComponent extends React.Component {
  constructor(props, context) {
    super(props, context);

    this.state = {
      albums: [],
      url: "http://localhost:7777/resources/albums",
      album: {},
      albumUrl: "",
      artist: {}
    }
  };

  handleTitleChange(e) {
    var newState = React.addons.update(this.state, {
      album: {
        title: {$set: e.target.value}
      }
    });
    this.setState(newState);
  }

  handleArtistIdChange(e) {
    var newState = React.addons.update(this.state, {
      album: {
        artistId: {$set: e.target.value}
      }
    });
    this.setState(newState);
  }

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

  loadArtistFromServer(url) {
    $.ajax({
      type: "GET",
      url: url,
      dataType: 'json',
      success: (artistData) => {
        this.setState({artist: artistData.artist})
      },
      error: (xhr, status, err) => {
        console.error(url, status, err.toString());
      }
    });
  }

  updateAlbum(index) {
    this.setState({album: this.state.albums[index].album, albumUrl: this.state.albums[index]._links.self.href})
  }
  addAlbum(){
    this.setState({album: {}})
  }
  postAlbumToServer() {
    $.ajax({
      type: "POST",
      url: this.state.url,
      data: JSON.stringify({title: this.state.album.title, artistId: this.state.album.artistId}),
      contentType: "application/json; charset=utf-8",
      dataType: "json",
      success: (response) => {
        this.loadAlbumsFromServer();
      },
      error: (xhr, status, err) => {
        console.error(this.state.url, status, err.toString());
      }
    });
  }

  putAlbumToServer() {
    $.ajax({
      type: "PUT",
      url: this.state.albumUrl,
      data: JSON.stringify({title: this.state.album.title, artistId: this.state.album.artistId}),
      contentType: "application/json; charset=utf-8",
      dataType: "json",
      success: (response) => {
        this.loadAlbumsFromServer();
      },
      error: (xhr, status, err) => {
        console.error(this.state.albumUrl, status, err.toString());
      }
    });
  }

  deleteAlbumFromServer() {
    $.ajax({
      type: "DELETE",
      url: this.state.albumUrl,
      dataType: "json",
      success: (response) => {
        this.loadAlbumsFromServer();
      },
      error: (xhr, status, err) => {
        console.error(this.state.albumUrl, status, err.toString());
      }
    });
  }
  componentDidMount() {
    this.loadAlbumsFromServer();
  }

  render() {
    return (
      <div className="albums-component">
        <div id="editModal" className="modal fade" role="dialog">
          <div className="modal-dialog">
            <div className="modal-content">
              <div className="modal-header">
                <button type="button" className="close" data-dismiss="modal">&times;</button>
                <h4 className="modal-title">Edit Album</h4>
              </div>
              <div className="modal-body row">
                <div>
                  <div className="form-group">
                    <label className="col-sm-4" for="album_title">Title</label>
                    <div className="col-sm-8 margin_bottom">
                      <input type="text" className="form-control" id="album_title"
                             value={this.state.album.title}
                             onChange={this.handleTitleChange.bind(this)}/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="album_artist">Artist Id</label>
                    <div className="col-sm-8 margin_bottom">
                      <input type="text" className="form-control" id="album_artist"
                             value={this.state.album.artistId}
                             onChange={this.handleArtistIdChange.bind(this)}/>
                    </div>
                  </div>
                </div>
              </div>
              <div className="modal-footer">
                <button type="button" className="btn btn-default" data-dismiss="modal"
                        onClick={this.putAlbumToServer.bind(this)}>Submit
                </button>
                <button type="button" className="btn btn-default" data-dismiss="modal">Close</button>
              </div>
            </div>
          </div>
        </div>
        <div id="artistModal" className="modal fade" role="dialog">
          <div className="modal-dialog">
            <div className="modal-content">
              <div className="modal-header">
                <button type="button" className="close" data-dismiss="modal">&times;</button>
                <h4 className="modal-title">Artist</h4>
              </div>
              <div className="modal-body row">
                <div>
                  <label className="col-sm-4">Id</label>
                  <div className="col-sm-8 margin_bottom">
                    <p>{this.state.artist.artistId} &nbsp;</p>
                  </div>
                  <label className="col-sm-4">Name</label>
                  <div className="col-sm-8 margin_bottom">
                    <p>{this.state.artist.name} &nbsp;</p>
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
                        onClick={this.deleteAlbumFromServer.bind(this)}
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
                <h4 className="modal-title">Create new Album</h4>
              </div>
              <div className="modal-body row">
                <div>
                  <div className="form-group">
                    <label className="col-sm-4" for="album_title">Title</label>
                    <div className="col-sm-8 margin_bottom">
                      <input type="text" className="form-control" id="album_title" placeholder="title"
                             value={this.state.album.title}
                             onChange={this.handleTitleChange.bind(this)}/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="album_artist">Artist Id</label>
                    <div className="col-sm-8 margin_bottom">
                      <input type="text" className="form-control" id="album_artist" placeholder="artist id"
                             value={this.state.album.artistId}
                             onChange={this.handleArtistIdChange.bind(this)}/>
                    </div>
                  </div>
                </div>
              </div>
              <div className="modal-footer">
                <button type="button" className="btn btn-default" data-dismiss="modal"
                        onClick={this.postAlbumToServer.bind(this)}>Submit
                </button>
                <button type="button" className="btn btn-default" data-dismiss="modal">Close</button>
              </div>
            </div>
          </div>
        </div>
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
            <Column
              header={<Cell>Artist</Cell>}
              cell={props => (
<Cell {...props}>
        <button type="button" className="btn btn-default btn-sm" data-toggle="modal" data-target="#artistModal" onClick={this.loadArtistFromServer.bind(this, this.state.albums[props.rowIndex]._links.artist.href)}>
          <span className="glyphicon glyphicon-info-sign"> Id: {this.state.albums[props.rowIndex].album.artistId}</span>
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
<button type="button" className="btn btn-default btn-sm" data-toggle="modal" data-target="#editModal" onClick={this.updateAlbum.bind(this, props.rowIndex)}>
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
<button type="button" className="btn btn-default btn-sm" data-toggle="modal" data-target="#deleteModal" onClick={this.updateAlbum.bind(this, props.rowIndex)}>
          <span className="glyphicon glyphicon-trash"></span> Delete
        </button>
</Cell>
        )}
              width={150}
              flexGrow={1}
            />
          </Table>
        </div>
        <button type="button" className="btn btn-default" data-toggle="modal" data-target="#addModal"
                onClick={this.addAlbum.bind(this)}>
          <span className="glyphicon glyphicon-plus"></span> Add
        </button>
      </div>
    );
  }
}

AlbumsComponent.displayName = 'AlbumsComponent';

AlbumsComponent.propTypes = {};
AlbumsComponent.defaultProps = {};

export default Dimensions()(AlbumsComponent);
