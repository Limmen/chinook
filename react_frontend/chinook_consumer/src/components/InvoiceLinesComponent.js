/**
 * InvoiceLinesComponent.
 *
 * Component that fetches a list of invoicelines from the REST-API and renders it in a datatable.
 */

'use strict';

import React from 'react';
import {Table, Column, Cell} from 'fixed-data-table';
import Dimensions from 'react-dimensions'
import Formsy from 'formsy-react';
import TextInputComponent from './TextInputComponent';

require('styles//DataTable.css');
require('styles//InvoiceLines.css');

class InvoiceLinesComponent extends React.Component {
  constructor(props, context) {
    super(props, context);

    this.state = {
      invoiceLines: [],
      url: "http://localhost:7777/resources/invoicelines",
      invoiceLine: {},
      invoiceLineUrl: {},
      invoice: {},
      track: {},
      canSubmit: false
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

  loadInvoiceFromServer(url) {
    $.ajax({
      type: "GET",
      url: url,
      dataType: 'json',
      success: (invoiceData) => {
        this.setState({invoice: invoiceData.invoice})
      },
      error: (xhr, status, err) => {
        console.error(url, status, err.toString());
      }
    });
  }

  loadTrackFromServer(url) {
    $.ajax({
      type: "GET",
      url: url,
      dataType: 'json',
      success: (trackData) => {
        this.setState({track: trackData.track})
      },
      error: (xhr, status, err) => {
        console.error(url, status, err.toString());
      }
    });
  }

  updateInvoiceLine(index) {
    this.setState({
      invoiceLine: this.state.invoiceLines[index].invoiceLine,
      invoiceLineUrl: this.state.invoiceLines[index]._links.self.href
    })
  }

  postInvoiceLineToServer(data) {
    console.log("post invoiceLine to serv");
    $.ajax({
      type: "POST",
      url: this.state.url,
      data: JSON.stringify(
        {
          invoiceId: data.invoiceId,
          trackId: data.trackId,
          unitPrice: data.unitPrice,
          quantity: data.quantity
        }),
      contentType: "application/json; charset=utf-8",
      dataType: "json",
      success: (response) => {
        this.loadInvoiceLinesFromServer();
      },
      error: (xhr, status, err) => {
        console.error(this.state.url, status, err.toString());
      }
    });
    $("#addModal").modal('hide');
  }

  putInvoiceLineToServer(data) {
    $.ajax({
      type: "PUT",
      url: this.state.invoiceLineUrl,
      data: JSON.stringify(
        {
          invoiceId: data.invoiceId,
          trackId: data.trackId,
          unitPrice: data.unitPrice,
          quantity: data.quantity
        }),
      contentType: "application/json; charset=utf-8",
      dataType: "json",
      success: (response) => {
        this.loadInvoiceLinesFromServer();
      },
      error: (xhr, status, err) => {
        console.error(this.state.invoiceLineUrl, status, err.toString());
      }
    });
    $("#editModal").modal('hide');
  }

  deleteInvoiceLineFromServer() {
    $.ajax({
      type: "DELETE",
      url: this.state.invoiceLineUrl,
      dataType: "json",
      success: (response) => {
        this.loadInvoiceLinesFromServer();
      },
      error: (xhr, status, err) => {
        console.error(this.state.invoiceLineUrl, status, err.toString());
      }
    });
  }

  componentDidMount() {
    this.loadInvoiceLinesFromServer();
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
      <div className="invoicelines-component">
        <div id="editModal" className="modal fade" role="dialog">
          <div className="modal-dialog">
            <div className="modal-content">
              <div className="modal-header">
                <button type="button" className="close" data-dismiss="modal">&times;</button>
                <h4 className="modal-title">Edit InvoiceLine</h4>
              </div>
              <div className="modal-body row">
                <Formsy.Form onValidSubmit={this.putInvoiceLineToServer.bind(this)}
                             onValid={this.enableButton.bind(this)} onInvalid={this.disableButton.bind(this)}>
                  <div className="form-group">
                    <label className="col-sm-4" for="invoice_id">Invoice Id</label>
                    <TextInputComponent name="invoiceId" validations="isInt"
                                        validationError="Invoice id needs to be a integer" required id="invoice_id"
                                        placeholder="invoiceId" value={this.state.invoiceLine.invoiceId}/>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="track_id">Track Id</label>
                    <TextInputComponent name="trackId" validations="isInt"
                                        validationError="Track id needs to be a integer" required id="track_id"
                                        placeholder="trackId" value={this.state.invoiceLine.trackId}/>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="unitPrice">Unit Price</label>
                    <TextInputComponent name="unitPrice" validations="isNumeric"
                                        validationError="Unit price needs to be a number" required id="unitPrice"
                                        placeholder="unitPrice" value={this.state.invoiceLine.unitPrice}/>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="invoice_quantity">Quantity</label>
                    <TextInputComponent name="quantity" validations="isInt"
                                        validationError="Quantity needs to be a integer" required id="invoice_quantity"
                                        placeholder="quantity" value={this.state.invoiceLine.quantity}/>
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
                <h4 className="modal-title">Create new InvoiceLine</h4>
              </div>
              <div className="modal-body row">
                <Formsy.Form onValidSubmit={this.postInvoiceLineToServer.bind(this)}
                             onValid={this.enableButton.bind(this)} onInvalid={this.disableButton.bind(this)}>
                  <div className="form-group">
                    <label className="col-sm-4" for="invoice_id">Invoice Id</label>
                    <TextInputComponent name="invoiceId" validations="isInt"
                                        validationError="Invoice id needs to be a integer" required id="invoice_id"
                                        placeholder="invoiceId"/>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="track_id">Track Id</label>
                    <TextInputComponent name="trackId" validations="isInt"
                                        validationError="Track id needs to be a integer" required id="track_id"
                                        placeholder="trackId"/>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="unitPrice">Unit Price</label>
                    <TextInputComponent name="unitPrice" validations="isNumeric"
                                        validationError="Unit price needs to be a number" required id="unitPrice"
                                        placeholder="unitPrice"/>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="invoice_quantity">Quantity</label>
                    <TextInputComponent name="quantity" validations="isInt"
                                        validationError="Quantity needs to be a integer" required id="invoice_quantity"
                                        placeholder="quantity"/>
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
                        onClick={this.deleteInvoiceLineFromServer.bind(this)}
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
        <div id="invoiceModal" className="modal fade" role="dialog">
          <div className="modal-dialog">
            <div className="modal-content">
              <div className="modal-header">
                <button type="button" className="close" data-dismiss="modal">&times;</button>
                <h4 className="modal-title">Invoice</h4>
              </div>
              <div className="modal-body row">
                <div>
                  <label className="col-sm-4">Id</label>
                  <div className="col-sm-8 margin_bottom">
                    <p>{this.state.invoice.invoiceId} &nbsp;</p>
                  </div>
                  <label className="col-sm-4">Customer id</label>
                  <div className="col-sm-8 margin_bottom">
                    <p>{this.state.invoice.customerId} &nbsp;</p>
                  </div>
                  <label className="col-sm-4">Invoice date</label>
                  <div className="col-sm-8 margin_bottom">
                    <p>{this.state.invoice.invoiceDate} &nbsp;</p>
                  </div>
                  <label className="col-sm-4">Billing Address</label>
                  <div className="col-sm-8 margin_bottom">
                    <p> {this.state.invoice.billingAddress} &nbsp; </p>
                  </div>
                  <label className="col-sm-4">Billing City</label>
                  <div className="col-sm-8 margin_bottom">
                    <p>{this.state.invoice.billingCity} &nbsp;</p>
                  </div>
                  <label className="col-sm-4">Billing Country</label>
                  <div className="col-sm-8 margin_bottom">
                    <p>{this.state.invoice.billingCountry} &nbsp;</p>
                  </div>
                  <label className="col-sm-4">Billing postal code</label>
                  <div className="col-sm-8 margin_bottom">
                    <p>{this.state.invoice.billingPostalCode} &nbsp;</p>
                  </div>
                  <label className="col-sm-4">Total</label>
                  <div className="col-sm-8 margin_bottom">
                    <p>{this.state.invoice.total} &nbsp;</p>
                  </div>
                </div>
              </div>
              <div className="modal-footer">
                <button type="button" className="btn btn-default" data-dismiss="modal">Close</button>
              </div>
            </div>
          </div>
        </div>
        <div id="trackModal" className="modal fade" role="dialog">
          <div className="modal-dialog">
            <div className="modal-content">
              <div className="modal-header">
                <button type="button" className="close" data-dismiss="modal">&times;</button>
                <h4 className="modal-title">Track</h4>
              </div>
              <div className="modal-body row">
                <div>
                  <label className="col-sm-4">Id</label>
                  <div className="col-sm-8 margin_bottom">
                    <p>{this.state.track.trackId} &nbsp;</p>
                  </div>
                  <label className="col-sm-4">Name</label>
                  <div className="col-sm-8 margin_bottom">
                    <p>{this.state.track.name} &nbsp;</p>
                  </div>
                  <label className="col-sm-4">Album id</label>
                  <div className="col-sm-8 margin_bottom">
                    <p>{this.state.track.albumdId} &nbsp;</p>
                  </div>
                  <label className="col-sm-4">Mediatype id</label>
                  <div className="col-sm-8 margin_bottom">
                    <p> {this.state.track.mediaTypeId} &nbsp; </p>
                  </div>
                  <label className="col-sm-4">Genre id</label>
                  <div className="col-sm-8 margin_bottom">
                    <p>{this.state.track.genreId} &nbsp;</p>
                  </div>
                  <label className="col-sm-4">composer</label>
                  <div className="col-sm-8 margin_bottom">
                    <p>{this.state.track.composer} &nbsp;</p>
                  </div>
                  <label className="col-sm-4">Milliseconds</label>
                  <div className="col-sm-8 margin_bottom">
                    <p>{this.state.track.milliseconds} &nbsp;</p>
                  </div>
                  <label className="col-sm-4">Bytes</label>
                  <div className="col-sm-8 margin_bottom">
                    <p>{this.state.track.bytes} &nbsp;</p>
                  </div>
                  <label className="col-sm-4">Unit price</label>
                  <div className="col-sm-8 margin_bottom">
                    <p>{this.state.track.unitPrice} &nbsp;</p>
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
              width={250}
              flexGrow={1}
            />
            <Column
              header={<Cell>Quantity</Cell>}
              cell={props => (
<Cell {...props}>
        {this.state.invoiceLines[props.rowIndex].invoiceLine.quantity}
</Cell>
        )}
              width={250}
              flexGrow={1}
            />
            <Column
              header={<Cell>Invoice</Cell>}
              cell={props => (
<Cell {...props}>
        <button type="button" className="btn btn-default btn-sm" data-toggle="modal" data-target="#invoiceModal" onClick={this.loadInvoiceFromServer.bind(this, this.state.invoiceLines[props.rowIndex]._links.invoice.href)}>
          <span className="glyphicon glyphicon-info-sign"> Id: {this.state.invoiceLines[props.rowIndex].invoiceLine.invoiceId}</span>
        </button>
</Cell>
        )}
              width={250}
              flexGrow={1}
            />
            <Column
              header={<Cell>Track</Cell>}
              cell={props => (
<Cell {...props}>
        <button type="button" className="btn btn-default btn-sm" data-toggle="modal" data-target="#trackModal" onClick={this.loadTrackFromServer.bind(this, this.state.invoiceLines[props.rowIndex]._links.track.href)}>
          <span className="glyphicon glyphicon-info-sign"> Id: {this.state.invoiceLines[props.rowIndex].invoiceLine.trackId}</span>
        </button>
</Cell>
        )}
              width={250}
              flexGrow={1}
            />
            <Column
              header={<Cell>Edit</Cell>}
              cell={props => (
<Cell {...props}>
<button type="button" className="btn btn-default btn-sm" data-toggle="modal" data-target="#editModal" onClick={this.updateInvoiceLine.bind(this, props.rowIndex)}>
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
<button type="button" className="btn btn-default btn-sm" data-toggle="modal" data-target="#deleteModal" onClick={this.updateInvoiceLine.bind(this, props.rowIndex)}>
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

InvoiceLinesComponent.displayName = 'InvoiceLinesComponent';

InvoiceLinesComponent.propTypes = {};
InvoiceLinesComponent.defaultProps = {};

export default Dimensions()(InvoiceLinesComponent);
