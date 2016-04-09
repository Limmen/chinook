'use strict';

import React from 'react';
import {Table, Column, Cell} from 'fixed-data-table';
import $ from "jquery";

require('styles//DataTable.css');

const rows = [{"id":1,"first_name":"William","last_name":"Elliott","email":"welliott0@wisc.edu",
               "country":"Argentina","ip_address":"247.180.226.89"},
              {"id":2,"first_name":"Carl","last_name":"Ross","email":"cross1@mlb.com",
               "country":"South Africa","ip_address":"27.146.70.36"},
              {"id":3,"first_name":"Jeremy","last_name":"Scott","email":"jscott2@cbsnews.com",
               "country":"Colombia","ip_address":"103.52.74.225"},
              
              // more data
             ];


class DataTableComponent extends React.Component {
    constructor(props, context) {
        super(props, context);

        this.state = {
            artists : {}
        }
    };

    loadArtistsFromServer() {
        $.ajax({
            type: "GET",
            url: this.props.url,
            dataType: 'json',
            success: (artistsData) => {
                console.log(JSON.stringify(artistsData))
                this.setState({artists: artistsData});
            },
            error: (xhr, status, err) => {
                console.error(this.props.url, status, err.toString());
            }
        });
    }

    componentDidMount() {
        this.loadArtistsFromServer();
        setInterval(this.loadArtistsFromServer, this.props.pollInterval);
    }
    
    render() {
        return (
                <div className="datatable-component">
                <Table
            height={rows.length * 30}
            width={1150}
            rowsCount={rows.length}
            rowHeight={30}
            headerHeight={30}
            rowGetter={function(rowIndex) {return rows[rowIndex]; }}>
                
                <Column dataKey="id" width={50} label="Id" />
                <Column dataKey="first_name" width={200} label="First Name" />
            <Column  dataKey="last_name" width={200} label="Last Name" />
            <Column  dataKey="email" width={400} label="e-mail" />
            <Column  dataKey="country" width={300} label="Country" />
                
        </Table>
            </div>
    );
}
}


DataTableComponent.displayName = 'DataTableComponent';

// Uncomment properties you need
// DataTableComponent.propTypes = {};
// DataTableComponent.defaultProps = {};

export default DataTableComponent;
