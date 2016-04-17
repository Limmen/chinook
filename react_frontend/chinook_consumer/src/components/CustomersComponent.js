/**
 * CustomersComponent.
 *
 * Component that fetches a list of customers from the REST-API and renders it in a datatable.
 */

'use strict';

import React from 'react';
import {Table, Column, Cell} from 'fixed-data-table';
import $ from "jquery";
import Dimensions from 'react-dimensions'

require('styles//Customers.css');
require('styles//DataTable.css');

class CustomersComponent extends React.Component {
  constructor(props, context) {
    super(props, context);

    this.state = {
      customers: [],
      url: "http://localhost:7777/resources/customers"
    }
  };

  loadCustomersFromServer() {
    $.ajax({
      type: "GET",
      url: this.state.url,
      dataType: 'json',
      success: (customersData) => {
        this.setState({customers: customersData.customers})
      },
      error: (xhr, status, err) => {
        console.error(this.state.url, status, err.toString());
      }
    });
  }

  componentDidMount() {
    this.loadCustomersFromServer();
  }

  render() {
    return (
      <div className="customers-component">
        <div className="datatablecontainer">
          <Table
            rowsCount={this.state.customers.length}
            rowHeight={50}
            headerHeight={50}
            width={this.props.containerWidth}
            height={500}>
            <Column
              header={<Cell>Id</Cell>}
              cell={props => (
           <Cell {...props}>
        {this.state.customers[props.rowIndex].customer.customerId}
          </Cell>
        )}
              width={50}
              flexGrow={1}
            />
            <Column
              header={<Cell>FirstName</Cell>}
              cell={props => (
<Cell {...props}>
        {this.state.customers[props.rowIndex].customer.firstName}
</Cell>
        )}
              width={250}
              flexGrow={1}
            />
            <Column
              header={<Cell>LastName</Cell>}
              cell={props => (
<Cell {...props}>
        {this.state.customers[props.rowIndex].customer.lastName}
</Cell>
        )}
              width={250}
              flexGrow={1}
            />
            <Column
              header={<Cell>Company</Cell>}
              cell={props => (
<Cell {...props}>
        {this.state.customers[props.rowIndex].customer.company}
</Cell>
        )}
              width={250}
              flexGrow={1}
            />
            <Column
              header={<Cell>Address</Cell>}
              cell={props => (
<Cell {...props}>
        {this.state.customers[props.rowIndex].customer.address}
</Cell>
        )}
              width={250}
              flexGrow={1}
            />
            <Column
              header={<Cell>City</Cell>}
              cell={props => (
<Cell {...props}>
        {this.state.customers[props.rowIndex].customer.city}
</Cell>
        )}
              width={250}
              flexGrow={1}
            />
            <Column
              header={<Cell>State</Cell>}
              cell={props => (
<Cell {...props}>
        {this.state.customers[props.rowIndex].customer.state}
</Cell>
        )}
              width={250}
              flexGrow={1}
            />
            <Column
              header={<Cell>Country</Cell>}
              cell={props => (
<Cell {...props}>
        {this.state.customers[props.rowIndex].customer.country}
</Cell>
        )}
              width={250}
              flexGrow={1}
            />
            <Column
              header={<Cell>PostalCode</Cell>}
              cell={props => (
<Cell {...props}>
        {this.state.customers[props.rowIndex].customer.postalCode}
</Cell>
        )}
              width={250}
              flexGrow={1}
            />
            <Column
              header={<Cell>Phone</Cell>}
              cell={props => (
<Cell {...props}>
        {this.state.customers[props.rowIndex].customer.phone}
</Cell>
        )}
              width={250}
              flexGrow={1}
            />
            <Column
              header={<Cell>fax</Cell>}
              cell={props => (
<Cell {...props}>
        {this.state.customers[props.rowIndex].customer.fax}
</Cell>
        )}
              width={250}
              flexGrow={1}
            />
            <Column
              header={<Cell>Email</Cell>}
              cell={props => (
<Cell {...props}>
        {this.state.customers[props.rowIndex].customer.email}
</Cell>
        )}
              width={250}
              flexGrow={1}
            />
          </Table>
        </div>
      </div>
    );
  }
}

CustomersComponent.displayName = 'CustomersComponent';

CustomersComponent.propTypes = {};
CustomersComponent.defaultProps = {};

export default Dimensions()(CustomersComponent);
