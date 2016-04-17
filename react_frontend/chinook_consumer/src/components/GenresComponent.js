/**
 * GenresComponent.
 *
 * Component that fetches a list of genres from the REST-API and renders it in a datatable.
 */

'use strict';

import React from 'react';
import {Table, Column, Cell} from 'fixed-data-table';
import $ from "jquery";
import Dimensions from 'react-dimensions'

require('styles//DataTable.css');
require('styles//Genres.css');

class GenresComponent extends React.Component {
  constructor(props, context) {
    super(props, context);

    this.state = {
      genres: [],
      url: "http://localhost:7777/resources/genres"
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

  componentDidMount() {
    this.loadGenresFromServer();
  }
  render() {
    return (
      <div className="genres-component">
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
          </Table>
        </div>
      </div>
    );
  }
}

GenresComponent.displayName = 'GenresComponent';

GenresComponent.propTypes = {};
GenresComponent.defaultProps = {};

export default Dimensions()(GenresComponent);
