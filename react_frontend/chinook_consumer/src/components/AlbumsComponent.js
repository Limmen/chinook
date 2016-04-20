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
      url: "http://localhost:7777/resources/albums",
      album: {},
      artist: {}
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

  loadArtistFromServer(url) {
    $.ajax({
      type: "GET",
      url: url,
      dataType: 'json',
      success: (artistData) => {
        console.log(JSON.stringify(artistData))
        this.setState({artist: artistData.artist})
      },
      error: (xhr, status, err) => {
        console.error(this.state.url, status, err.toString());
      }
    });
  }

  updateAlbum(index) {
    //this.setState({album: index})
    this.setState({album: this.state.albums[index].album})
  }

  deleteAlbum(index) {

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
                    <label className="col-sm-2" for="album_id">Id</label>
                    <div className="col-sm-10 margin_bottom">
                      <input type="text" className="form-control" id="album_id" value={this.state.album.albumId}/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-2" for="album_title">Title</label>
                    <div className="col-sm-10 margin_bottom">
                      <input type="text" className="form-control" id="album_title" value={this.state.album.title}/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-2" for="album_artist">Artist Id</label>
                    <div className="col-sm-10 margin_bottom">
                      <input type="text" className="form-control" id="album_artist" value={this.state.album.artistId}/>
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
        <div id="artistModal" className="modal fade" role="dialog">
          <div className="modal-dialog">
            <div className="modal-content">
              <div className="modal-header">
                <button type="button" className="close" data-dismiss="modal">&times;</button>
                <h4 className="modal-title">Artist</h4>
              </div>
              <div className="modal-body row">
                <div>
                  <label className="col-sm-2">Id</label>
                  <div className="col-sm-10 margin_bottom">
                    <p>{this.state.artist.artistId}</p>
                  </div>
                  <label className="col-sm-2">Name</label>
                  <div className="col-sm-10 margin_bottom">
                    <p>{this.state.artist.name}</p>
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
                <h4 className="modal-title">Create new Album</h4>
              </div>
              <div className="modal-body row">
                <div>
                  <div className="form-group">
                    <label className="col-sm-2" for="album_id">Id</label>
                    <div className="col-sm-10 margin_bottom">
                      <input type="text" className="form-control" id="album_id" placeholder="id"/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-2" for="album_title">Title</label>
                    <div className="col-sm-10 margin_bottom">
                      <input type="text" className="form-control" id="album_title" placeholder="title"/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-2" for="album_artist">Artist Id</label>
                    <div className="col-sm-10 margin_bottom">
                      <input type="text" className="form-control" id="album_artist" placeholder="artist id"/>
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
<button type="button" className="btn btn-default btn-sm" data-toggle="modal" data-target="#deleteModal" onClick={this.deleteAlbum.bind(this, props.rowIndex)}>
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

          AlbumsComponent.displayName = 'AlbumsComponent';

          AlbumsComponent.propTypes = {};
          AlbumsComponent.defaultProps = {};

          export default Dimensions()(AlbumsComponent);
