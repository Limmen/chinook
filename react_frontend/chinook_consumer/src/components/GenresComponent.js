/**
 * GenresComponent.
 *
 * Component that fetches a list of genres from the REST-API and renders it in a datatable.
 */

'use strict';

import React from 'react';
import {Table, Column, Cell} from 'fixed-data-table';
import Dimensions from 'react-dimensions'
import Formsy from 'formsy-react';
import TextInputComponent from './TextInputComponent';

require('styles//DataTable.css');
require('styles//Genres.css');

class GenresComponent extends React.Component {
  constructor(props, context) {
    super(props, context);

    this.state = {
      genres: [],
      url: "http://localhost:7777/resources/genres",
      genre: {},
      genreUrl: "",
      canSubmit: false
    }
  };

  loadGenresFromServer() {
    $.ajax({
      type: "GET",
      url: this.state.url,
      dataType: 'json',
      success: (genresData) => {
        this.setState({genres: genresData.genres})
      },
      error: (xhr, status, err) => {
        console.error(this.state.url, status, err.toString());
      }
    });
  }

  updateGenre(index) {
    //this.setState({customer: index})
    this.setState({genre: this.state.genres[index].genre, genreUrl: this.state.genres[index]._links.self.href})
  }

  postGenreToServer(data) {
    $.ajax({
      type: "POST",
      url: this.state.url,
      data: JSON.stringify({name: data.name}),
      contentType: "application/json; charset=utf-8",
      dataType: "json",
      success: (response) => {
        this.loadGenresFromServer();
      },
      error: (xhr, status, err) => {
        console.error(this.state.url, status, err.toString());
      }
    });
    $("#addModal").modal('hide');
  }

  putGenreToServer(data) {
    $.ajax({
      type: "PUT",
      url: this.state.genreUrl,
      data: JSON.stringify({name: data.name}),
      contentType: "application/json; charset=utf-8",
      dataType: "json",
      success: (response) => {
        this.loadGenresFromServer();
      },
      error: (xhr, status, err) => {
        console.error(this.state.genreUrl, status, err.toString());
      }
    });
    $("#editModal").modal('hide');
  }

  deleteGenreFromServer() {
    $.ajax({
      type: "DELETE",
      url: this.state.genreUrl,
      dataType: "json",
      success: (response) => {
        this.loadGenresFromServer();
      },
      error: (xhr, status, err) => {
        console.error(this.state.genreUrl, status, err.toString());
      }
    });
  }


  componentDidMount() {
    this.loadGenresFromServer();
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
      <div className="genres-component">
        <div id="editModal" className="modal fade" role="dialog">
          <div className="modal-dialog">
            <div className="modal-content">
              <div className="modal-header">
                <button type="button" className="close" data-dismiss="modal">&times;</button>
                <h4 className="modal-title">Edit Genre</h4>
              </div>
              <div className="modal-body row">
                <Formsy.Form onValidSubmit={this.putGenreToServer.bind(this)} onValid={this.enableButton.bind(this)}
                             onInvalid={this.disableButton.bind(this)}>
                  <div className="form-group">
                    <label className="col-sm-4" for="genre_name">Name</label>
                    <TextInputComponent name="name" validationError="this field is required" required id="genre_name"
                                        placeholder="name"
                                        value={this.state.genre.name}/>
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
        <div id="addModal" className="modal fade" role="dialog">
          <div className="modal-dialog">
            <div className="modal-content">
              <div className="modal-header">
                <button type="button" className="close" data-dismiss="modal">&times;</button>
                <h4 className="modal-title">Create new Genre</h4>
              </div>
              <div className="modal-body row">
                <Formsy.Form onValidSubmit={this.postGenreToServer.bind(this)} onValid={this.enableButton.bind(this)}
                             onInvalid={this.disableButton.bind(this)}>
                  <div className="form-group">
                    <label className="col-sm-4" for="genre_name">Name</label>
                    <TextInputComponent name="name" validationError="this field is required" required id="genre_name"
                                        placeholder="name"/>
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
        <div id="deleteModal" className="modal fade" role="dialog">
          <div className="modal-dialog">
            <div className="modal-content">
              <div className="modal-header">
                <button type="button" className="close" data-dismiss="modal">&times;</button>
                <h4 className="modal-title">Are you sure?</h4>
              </div>
              <div className="modal-body row">
                <button type="button" className="btn btn-default"
                        onClick={this.deleteGenreFromServer.bind(this)}
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
        <div className="datatablecontainer">
          <Table
            rowsCount={this.state.genres.length}
            rowHeight={50}
            headerHeight={50}
            width={this.props.containerWidth}
            height={500}>
            <Column
              header={<Cell>Id</Cell>}
              cell={props => (
           <Cell {...props}>
        {this.state.genres[props.rowIndex].genre.genreId}
          </Cell>
        )}
              width={50}
              flexGrow={1}
            />
            <Column
              header={<Cell>Name</Cell>}
              cell={props => (
<Cell {...props}>
        {this.state.genres[props.rowIndex].genre.name}
</Cell>
        )}
              width={350}
              flexGrow={1}
            />
            <Column
              header={<Cell>Edit</Cell>}
              cell={props => (
<Cell {...props}>
<button type="button" className="btn btn-default btn-sm" data-toggle="modal" data-target="#editModal" onClick={this.updateGenre.bind(this, props.rowIndex)}>
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
<button type="button" className="btn btn-default btn-sm" data-toggle="modal" data-target="#deleteModal" onClick={this.updateGenre.bind(this, props.rowIndex)}>
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

GenresComponent.displayName = 'GenresComponent';

GenresComponent.propTypes = {};
GenresComponent.defaultProps = {};

export default Dimensions()(GenresComponent);
