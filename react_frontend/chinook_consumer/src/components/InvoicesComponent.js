/**
 * InvoicesComponent.
 *
 * Component that fetches a list of invoices from the REST-API and renders it in a datatable.
 */

'use strict';

import React from 'react';
import {Table, Column, Cell} from 'fixed-data-table';
import $ from "jquery";
import Dimensions from 'react-dimensions'

require('styles//DataTable.css');
require('styles//Invoices.css');

class InvoicesComponent extends React.Component {
  constructor(props, context) {
    super(props, context);

    this.state = {
      invoices: [],
      url: "http://localhost:7777/resources/invoices"
    }
  };

  loadInvoicesFromServer() {
    $.ajax({
      type: "GET",
      url: this.state.url,
      dataType: 'json',
      success: (invoicesData) => {
        this.setState({invoices: invoicesData.invoices})
      },
      error: (xhr, status, err) => {
        console.error(this.state.url, status, err.toString());
      }
    });
  }

  componentDidMount() {
    this.loadInvoicesFromServer();
  }
  render() {
    return (
      <div className="invoices-component">
        <div className="datatablecontainer">
          <Table
            rowsCount={this.state.invoices.length}
            rowHeight={50}
            headerHeight={50}
            width={this.props.containerWidth}
            height={500}>
            <Column
              header={<Cell>Id</Cell>}
              cell={props => (
           <Cell {...props}>
        {this.state.invoices[props.rowIndex].invoice.invoiceId}
          </Cell>
        )}
              width={50}
              flexGrow={1}
            />
            <Column
              header={<Cell>Invoice date</Cell>}
              cell={props => (
<Cell {...props}>
        {this.state.invoices[props.rowIndex].invoice.invoiceDate}
</Cell>
        )}
              width={350}
              flexGrow={1}
            />
            <Column
              header={<Cell>Billing Address</Cell>}
              cell={props => (
<Cell {...props}>
        {this.state.invoices[props.rowIndex].invoice.billingAddress}
</Cell>
        )}
              width={350}
              flexGrow={1}
            />
            <Column
              header={<Cell>Billing City</Cell>}
              cell={props => (
<Cell {...props}>
        {this.state.invoices[props.rowIndex].invoice.billingCity}
</Cell>
        )}
              width={350}
              flexGrow={1}
            />
            <Column
              header={<Cell>Billing Country</Cell>}
              cell={props => (
<Cell {...props}>
        {this.state.invoices[props.rowIndex].invoice.billingCountry}
</Cell>
        )}
              width={350}
              flexGrow={1}
            />
            <Column
              header={<Cell>Billing Postalcode</Cell>}
              cell={props => (
<Cell {...props}>
        {this.state.invoices[props.rowIndex].invoice.billingPostalCode}
</Cell>
        )}
              width={350}
              flexGrow={1}
            />
            <Column
              header={<Cell>Total</Cell>}
              cell={props => (
<Cell {...props}>
        {this.state.invoices[props.rowIndex].invoice.total}
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

InvoicesComponent.displayName = 'InvoicesComponent';

InvoicesComponent.propTypes = {};
InvoicesComponent.defaultProps = {};

export default Dimensions()(InvoicesComponent);
