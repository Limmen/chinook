/**
 * InvoiceLinesComponent.
 *
 * Component that fetches a list of invoicelines from the REST-API and renders it in a datatable.
 */

'use strict';

import React from 'react';
import {Table, Column, Cell} from 'fixed-data-table';
import $ from "jquery";
import Dimensions from 'react-dimensions'

require('styles//DataTable.css');
require('styles//InvoiceLines.css');

class InvoiceLinesComponent extends React.Component {
  constructor(props, context) {
    super(props, context);

    this.state = {
      invoiceLines: [],
      url: "http://localhost:7777/resources/invoicelines"
    }
  };

  loadInvoiceLinesFromServer() {
    $.ajax({
      type: "GET",
      url: this.state.url,
      dataType: 'json',
      success: (invoiceLinesData) => {
        this.setState({invoiceLines: invoiceLinesData.invoiceLines})
      },
      error: (xhr, status, err) => {
        console.error(this.state.url, status, err.toString());
      }
    });
  }

  componentDidMount() {
    this.loadInvoiceLinesFromServer();
  }
  render() {
    return (
      <div className="invoicelines-component">
        <div className="datatablecontainer">
          <Table
            rowsCount={this.state.invoiceLines.length}
            rowHeight={50}
            headerHeight={50}
            width={this.props.containerWidth}
            height={500}>
            <Column
              header={<Cell>Id</Cell>}
              cell={props => (
           <Cell {...props}>
        {this.state.invoiceLines[props.rowIndex].invoiceLine.invoiceLineId}
          </Cell>
        )}
              width={50}
              flexGrow={1}
            />
            <Column
              header={<Cell>Unit price</Cell>}
              cell={props => (
<Cell {...props}>
        {this.state.invoiceLines[props.rowIndex].invoiceLine.unitPrice}
</Cell>
        )}
              width={350}
              flexGrow={1}
            />
            <Column
              header={<Cell>Quantity</Cell>}
              cell={props => (
<Cell {...props}>
        {this.state.invoiceLines[props.rowIndex].invoiceLine.quantity}
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

InvoiceLinesComponent.displayName = 'InvoiceLinesComponent';

InvoiceLinesComponent.propTypes = {};
InvoiceLinesComponent.defaultProps = {};

export default Dimensions()(InvoiceLinesComponent);
