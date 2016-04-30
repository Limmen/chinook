/**
 * InvoicesComponent.
 *
 * Component that fetches a list of invoices from the REST-API and renders it in a datatable.
 */

'use strict';

import React from 'react';
import {Table, Column, Cell} from 'fixed-data-table';
import Dimensions from 'react-dimensions'
import Formsy from 'formsy-react';
import TextInputComponent from './TextInputComponent';
import DateInputComponent from './DateInputComponent';

require('styles//DataTable.css');
require('styles//Invoices.css');

class InvoicesComponent extends React.Component {
  constructor(props, context) {
    super(props, context);

    this.state = {
      invoices: [],
      url: "http://localhost:7777/resources/invoices",
      invoice: {},
      invoiceUrl: "",
      customer: {},
      canSubmit: false
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

  loadCustomerFromServer(url) {
    $.ajax({
      type: "GET",
      url: url,
      dataType: 'json',
      success: (customerData) => {
        console.log(JSON.stringify(customerData))
        this.setState({customer: customerData.customer})
      },
      error: (xhr, status, err) => {
        console.error(url, status, err.toString());
      }
    });
  }

  updateInvoice(index) {
    //this.setState({customer: index})
    this.setState({invoice: this.state.invoices[index].invoice, invoiceUrl: this.state.invoices[index]._links.self.href})
  }

  postInvoiceToServer(data) {
    $.ajax({
      type: "POST",
      url: this.state.url,
      data: JSON.stringify(
        {
          customerId: data.customerId,
          invoiceDate: data.invoiceDate,
          billingAddress: data.billingAddress,
          billingCity : data.billingCity,
          billingCountry : data.billingCountry,
          billingPostalCode: data.billingPostalCode,
          total: data.total
        }),
      contentType: "application/json; charset=utf-8",
      dataType: "json",
      success: (response) => {
        this.loadInvoicesFromServer();
      },
      error: (xhr, status, err) => {
        console.error(this.state.url, status, err.toString());
      }
    });
    $("#addModal").modal('hide');
  }

  putInvoiceToServer(data) {
    $.ajax({
      type: "PUT",
      url: this.state.invoiceUrl,
      data: JSON.stringify(
        {
          customerId: data.customerId,
          invoiceDate: data.invoiceDate,
          billingAddress: data.billingAddress,
          billingCity : data.billingCity,
          billingCountry : data.billingCountry,
          billingPostalCode: data.billingPostalCode,
          total: data.total
        }),
      contentType: "application/json; charset=utf-8",
      dataType: "json",
      success: (response) => {
        this.loadInvoicesFromServer();
      },
      error: (xhr, status, err) => {
        console.error(this.state.invoiceUrl, status, err.toString());
      }
    });
    $("#editModal").modal('hide');
  }

  deleteInvoiceFromServer() {
    $.ajax({
      type: "DELETE",
      url: this.state.invoiceUrl,
      dataType: "json",
      success: (response) => {
        this.loadInvoicesFromServer();
      },
      error: (xhr, status, err) => {
        console.error(this.state.invoiceUrl, status, err.toString());
      }
    });
  }

  componentDidMount() {
    this.loadInvoicesFromServer();
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
      <div className="invoices-component">
        <div id="editModal" className="modal fade" role="dialog">
          <div className="modal-dialog">
            <div className="modal-content">
              <div className="modal-header">
                <button type="button" className="close" data-dismiss="modal">&times;</button>
                <h4 className="modal-title">Edit Invoice</h4>
              </div>
              <div className="modal-body row">
                <Formsy.Form onValidSubmit={this.putInvoiceToServer.bind(this)} onValid={this.enableButton.bind(this)}
                             onInvalid={this.disableButton.bind(this)}>
                  <div className="form-group">
                    <label className="col-sm-4" for="invoice_customerid">Customer Id</label>
                    <TextInputComponent name="customerId" validations="isInt"validationError="Customer id needs to be a integer" required
                                        id="invoice_customerid"
                                        placeholder="customerId" value={this.state.invoice.customerId}/>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="invoice_invoiceDate">Invoice Date</label>
                    <DateInputComponent name="invoiceDate" validationError="this field is required" required
                                        id="invoice_invoiceDate"
                                        placeholder="invoiceDate" />
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="invoice_billingAddress">Billing Address</label>
                    <TextInputComponent name="billingAddress" validationError="this field is required" required
                                        id="invoice_billingAddress"
                                        placeholder="billingAddress" value={this.state.invoice.billingAddress}/>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="invoice_billingCity">Billing City</label>
                    <TextInputComponent name="billingCity" validationError="this field is required" required
                                        id="invoice_billingCity"
                                        placeholder="billing city" value={this.state.invoice.billingCity}/>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="invoice_billingCountry">Billing Country</label>
                    <TextInputComponent name="billingCountry" validationError="this field is required" required
                                        id="invoice_billingCountry"
                                        placeholder="billing country" value={this.state.invoice.billingCountry}/>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="invoice_billingPostalCode">Billing Postal Code</label>
                    <TextInputComponent name="billingPostalCode" validationError="this field is required" required
                                        id="invoice_billingPostalCode"
                                        placeholder="billing postalcode" value={this.state.invoice.billingPostalCode}/>
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
                <h4 className="modal-title">Create new Invoice</h4>
              </div>
              <div className="modal-body row">
                <Formsy.Form onValidSubmit={this.postInvoiceToServer.bind(this)} onValid={this.enableButton.bind(this)}
                             onInvalid={this.disableButton.bind(this)}>
                  <div className="form-group">
                    <label className="col-sm-4" for="invoice_customerid">Customer Id</label>
                    <TextInputComponent name="customerId" validations="isInt"validationError="Customer id needs to be a integer" required
                                        id="invoice_customerid"
                                        placeholder="customerId"/>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="invoice_invoiceDate">Invoice Date</label>
                    <DateInputComponent name="invoiceDate" validationError="this field is required" required
                                        id="invoice_invoiceDate"
                                        placeholder="invoiceDate" />
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="invoice_billingAddress">Billing Address</label>
                    <TextInputComponent name="billingAddress" validationError="this field is required" required
                                        id="invoice_billingAddress"
                                        placeholder="billingAddress"/>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="invoice_billingCity">Billing City</label>
                    <TextInputComponent name="billingCity" validationError="this field is required" required
                                        id="invoice_billingCity"
                                        placeholder="billing city" />
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="invoice_billingCountry">Billing Country</label>
                    <TextInputComponent name="billingCountry" validationError="this field is required" required
                                        id="invoice_billingCountry"
                                        placeholder="billing country" />
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="invoice_billingPostalCode">Billing Postal Code</label>
                    <TextInputComponent name="billingPostalCode" validationError="this field is required" required
                                        id="invoice_billingPostalCode"
                                        placeholder="billing postalcode" />
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
                        onClick={this.deleteInvoiceFromServer.bind(this)}
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
        <div id="customerModal" className="modal fade" role="dialog">
          <div className="modal-dialog">
            <div className="modal-content">
              <div className="modal-header">
                <button type="button" className="close" data-dismiss="modal">&times;</button>
                <h4 className="modal-title">Customer</h4>
              </div>
              <div className="modal-body row">
                <div>
                  <label className="col-sm-4">Id</label>
                  <div className="col-sm-8 margin_bottom">
                    <p>{this.state.customer.customerId} &nbsp;</p>
                  </div>
                  <label className="col-sm-4">First Name</label>
                  <div className="col-sm-8 margin_bottom">
                    <p>{this.state.customer.firstName} &nbsp;</p>
                  </div>
                  <label className="col-sm-4">Last name</label>
                  <div className="col-sm-8 margin_bottom">
                    <p>{this.state.customer.lastName} &nbsp;</p>
                  </div>
                  <label className="col-sm-4">Company</label>
                  <div className="col-sm-8 margin_bottom">
                    <p> {this.state.customer.company} &nbsp; </p>
                  </div>
                  <label className="col-sm-4">Address</label>
                  <div className="col-sm-8 margin_bottom">
                    <p>{this.state.customer.address} &nbsp;</p>
                  </div>
                  <label className="col-sm-4">City</label>
                  <div className="col-sm-8 margin_bottom">
                    <p>{this.state.customer.city} &nbsp;</p>
                  </div>
                  <label className="col-sm-4">State</label>
                  <div className="col-sm-8 margin_bottom">
                    <p>{this.state.customer.state} &nbsp;</p>
                  </div>
                  <label className="col-sm-4">Country</label>
                  <div className="col-sm-8 margin_bottom">
                    <p>{this.state.customer.country} &nbsp;</p>
                  </div>
                  <label className="col-sm-4">Postal code</label>
                  <div className="col-sm-8 margin_bottom">
                    <p>{this.state.customer.postalCode} &nbsp;</p>
                  </div>
                  <label className="col-sm-4">Phone</label>
                  <div className="col-sm-8 margin_bottom">
                    <p>{this.state.customer.phone} &nbsp;</p>
                  </div>
                  <label className="col-sm-4">Fax</label>
                  <div className="col-sm-8 margin_bottom">
                    <p>{this.state.customer.fax} &nbsp;</p>
                  </div>
                  <label className="col-sm-4">Email</label>
                  <div className="col-sm-8 margin_bottom">
                    <p>{this.state.customer.email} &nbsp;</p>
                  </div>
                  <label className="col-sm-4">Supported by employee id</label>
                  <div className="col-sm-8 margin_bottom">
                    <p>{this.state.customer.supportRepId} &nbsp;</p>
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
              width={250}
              flexGrow={1}
            />
            <Column
              header={<Cell>Billing Address</Cell>}
              cell={props => (
<Cell {...props}>
        {this.state.invoices[props.rowIndex].invoice.billingAddress}
</Cell>
        )}
              width={250}
              flexGrow={1}
            />
            <Column
              header={<Cell>Billing City</Cell>}
              cell={props => (
<Cell {...props}>
        {this.state.invoices[props.rowIndex].invoice.billingCity}
</Cell>
        )}
              width={250}
              flexGrow={1}
            />
            <Column
              header={<Cell>Billing Country</Cell>}
              cell={props => (
<Cell {...props}>
        {this.state.invoices[props.rowIndex].invoice.billingCountry}
</Cell>
        )}
              width={250}
              flexGrow={1}
            />
            <Column
              header={<Cell>Billing Postalcode</Cell>}
              cell={props => (
<Cell {...props}>
        {this.state.invoices[props.rowIndex].invoice.billingPostalCode}
</Cell>
        )}
              width={250}
              flexGrow={1}
            />
            <Column
              header={<Cell>Total</Cell>}
              cell={props => (
<Cell {...props}>
        {this.state.invoices[props.rowIndex].invoice.total}
</Cell>
        )}
              width={250}
              flexGrow={1}
            />
            <Column
              header={<Cell>Customer</Cell>}
              cell={props => (
<Cell {...props}>
        <button type="button" className="btn btn-default btn-sm" data-toggle="modal" data-target="#customerModal" onClick={this.loadCustomerFromServer.bind(this, this.state.invoices[props.rowIndex]._links.customer.href)}>
          <span className="glyphicon glyphicon-info-sign"> Id: {this.state.invoices[props.rowIndex].invoice.customerId}</span>
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
<button type="button" className="btn btn-default btn-sm" data-toggle="modal" data-target="#editModal" onClick={this.updateInvoice.bind(this, props.rowIndex)}>
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
<button type="button" className="btn btn-default btn-sm" data-toggle="modal" data-target="#deleteModal" onClick={this.updateInvoice.bind(this, props.rowIndex)}>
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

InvoicesComponent.displayName = 'InvoicesComponent';

InvoicesComponent.propTypes = {};
InvoicesComponent.defaultProps = {};

export default Dimensions()(InvoicesComponent);
