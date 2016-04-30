/**
 * CustomersComponent.
 *
 * Component that fetches a list of customers from the REST-API and renders it in a datatable.
 */

'use strict';

import React from 'react/addons';
import {Table, Column, Cell} from 'fixed-data-table';
import Dimensions from 'react-dimensions'
import Formsy from 'formsy-react';
import TextInputComponent from './TextInputComponent';

require('styles//Customers.css');
require('styles//DataTable.css');

class CustomersComponent extends React.Component {
  constructor(props, context) {
    super(props, context);

    this.state = {
      customers: [],
      url: "http://localhost:7777/resources/customers",
      customer: {},
      customerUrl: "",
      employee: {},
      canSubmit: false
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


  loadEmployeeFromServer(url) {
    $.ajax({
      type: "GET",
      url: url,
      dataType: 'json',
      success: (employeeData) => {
        console.log(JSON.stringify(employeeData))
        this.setState({employee: employeeData.employee})
      },
      error: (xhr, status, err) => {
        console.error(url, status, err.toString());
      }
    });
  }

  updateCustomer(index) {
    this.setState({
      customer: this.state.customers[index].customer,
      customerUrl: this.state.customers[index]._links.self.href
    })
  }

  postCustomerToServer(data) {
    $.ajax({
      type: "POST",
      url: this.state.url,
      data: JSON.stringify(
        {
          firstName: data.firstName,
          lastName: data.lastName,
          company: data.company,
          address: data.address,
          city: data.city,
          country: data.country,
          postalCode: data.postalCode,
          state: data.state,
          phone: data.phone,
          fax: data.fax,
          email: data.email,
          supportRepId: data.supportRepId
        }),
      contentType: "application/json; charset=utf-8",
      dataType: "json",
      success: (response) => {
        this.loadCustomersFromServer();
      },
      error: (xhr, status, err) => {
        console.error(this.state.url, status, err.toString());
      }
    });
    $("#addModal").modal('hide');
  }

  putCustomerToServer(data) {
    $.ajax({
      type: "PUT",
      url: this.state.customerUrl,
      data: JSON.stringify(
        {
          firstName: data.firstName,
          lastName: data.lastName,
          company: data.company,
          address: data.address,
          city: data.city,
          country: data.country,
          postalCode: data.postalCode,
          state: data.state,
          phone: data.phone,
          fax: data.fax,
          email: data.email,
          supportRepId: data.supportRepId
        }),
      contentType: "application/json; charset=utf-8",
      dataType: "json",
      success: (response) => {
        this.loadCustomersFromServer();
      },
      error: (xhr, status, err) => {
        console.error(this.state.customerUrl, status, err.toString());
      }
    });
    $("#editModal").modal('hide');
  }

  deleteCustomerFromServer() {
    $.ajax({
      type: "DELETE",
      url: this.state.customerUrl,
      dataType: "json",
      success: (response) => {
        this.loadCustomersFromServer();
      },
      error: (xhr, status, err) => {
        console.error(this.state.customerUrl, status, err.toString());
      }
    });
  }

  componentDidMount() {
    this.loadCustomersFromServer();
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
      <div className="customers-component">
        <div id="editModal" className="modal fade" role="dialog">
          <div className="modal-dialog">
            <div className="modal-content">
              <div className="modal-header">
                <button type="button" className="close" data-dismiss="modal">&times;</button>
                <h4 className="modal-title">Edit Customer</h4>
              </div>
              <div className="modal-body row">
                <Formsy.Form onValidSubmit={this.putCustomerToServer.bind(this)} onValid={this.enableButton.bind(this)}
                             onInvalid={this.disableButton.bind(this)}>
                  <div className="form-group">
                    <label className="col-sm-4" for="customer_firstname">First Name</label>
                    <TextInputComponent name="firstName" validationError="this field is required" required
                                        id="customer_firstname"
                                        placeholder="first name"
                                        value={this.state.customer.firstName}/>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="customer_lastname">Last Name</label>
                    <TextInputComponent name="lastName" validationError="this field is required" required
                                        id="customer_lastname"
                                        placeholder="last name"
                                        value={this.state.customer.lastName}/>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="customer_company">Company</label>
                    <TextInputComponent name="company" validationError="this field is required" required
                                        id="customer_company"
                                        placeholder="company"
                                        value={this.state.customer.company}/>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="customer_address">Address</label>
                    <TextInputComponent name="address" validationError="this field is required" required
                                        id="customer_address"
                                        placeholder="address"
                                        value={this.state.customer.address}/>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="customer_city">City</label>
                    <TextInputComponent name="city" validationError="this field is required" required id="customer_city"
                                        placeholder="city"
                                        value={this.state.customer.city}/>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="customer_state">State</label>
                    <TextInputComponent name="state" validationError="this field is required" required
                                        id="customer_state"
                                        placeholder="state"
                                        value={this.state.customer.state}/>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="customer_country">Country</label>
                    <TextInputComponent name="country" validationError="this field is required" required
                                        id="customer_country"
                                        placeholder="country"
                                        value={this.state.customer.country}/>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="customer_postalcode">PostalCode</label>
                    <TextInputComponent name="postalCode" validationError="this field is required" required
                                        id="customer_postalcode"
                                        placeholder="postal code"
                                        value={this.state.customer.postalCode}/>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="customer_phone">Phone</label>
                    <TextInputComponent name="phone" validationError="this field is required" required
                                        id="customer_phone"
                                        placeholder="phone"
                                        value={this.state.customer.phone}/>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="customer_fax">Fax</label>
                    <TextInputComponent name="fax" validationError="this field is required" required id="customer_fax"
                                        placeholder="fax"
                                        value={this.state.customer.fax}/>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="customer_email">Email</label>
                    <TextInputComponent name="email" validations="isEmail" validationError="Enter a valid email"
                                        required
                                        id="customer_email"
                                        placeholder="email"
                                        value={this.state.customer.email}/>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="customer_supportRepId">Supported By Employee</label>
                    <TextInputComponent name="supportRepId" validations="isInt"
                                        validationError="Employee id needs to be a integer" required
                                        id="customer_supportRepId"
                                        placeholder="employee id"
                                        value={this.state.customer.supportRepId}/>
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
                <h4 className="modal-title">Create new Customer</h4>
              </div>
              <div className="modal-body row">
                <Formsy.Form onValidSubmit={this.postCustomerToServer.bind(this)} onValid={this.enableButton.bind(this)}
                             onInvalid={this.disableButton.bind(this)}>
                  <div className="form-group">
                    <label className="col-sm-4" for="customer_firstname">First Name</label>
                    <TextInputComponent name="firstName" validationError="this field is required" required
                                        id="customer_firstname"
                                        placeholder="first name"/>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="customer_lastname">Last Name</label>
                    <TextInputComponent name="lastName" validationError="this field is required" required
                                        id="customer_lastname"
                                        placeholder="last name"/>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="customer_company">Company</label>
                    <TextInputComponent name="company" validationError="this field is required" required
                                        id="customer_company"
                                        placeholder="company"/>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="customer_address">Address</label>
                    <TextInputComponent name="address" validationError="this field is required" required
                                        id="customer_address"
                                        placeholder="address"/>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="customer_city">City</label>
                    <TextInputComponent name="city" validationError="this field is required" required id="customer_city"
                                        placeholder="city"/>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="customer_state">State</label>
                    <TextInputComponent name="state" validationError="this field is required" required
                                        id="customer_state"
                                        placeholder="state"/>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="customer_country">Country</label>
                    <TextInputComponent name="country" validationError="this field is required" required
                                        id="customer_country"
                                        placeholder="country"/>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="customer_postalcode">PostalCode</label>
                    <TextInputComponent name="postalCode" validationError="this field is required" required
                                        id="customer_postalcode"
                                        placeholder="postal code"/>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="customer_phone">Phone</label>
                    <TextInputComponent name="phone" validationError="this field is required" required
                                        id="customer_phone"
                                        placeholder="phone"/>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="customer_fax">Fax</label>
                    <TextInputComponent name="fax" validationError="this field is required" required id="customer_fax"
                                        placeholder="fax"/>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="customer_email">Email</label>
                    <TextInputComponent name="email" validations="isEmail" validationError="Enter a valid email"
                                        required
                                        id="customer_email"
                                        placeholder="email"/>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="customer_supportRepId">Supported By Employee</label>
                    <TextInputComponent name="supportRepId" validations="isInt"
                                        validationError="Employee id needs to be a integer" required
                                        id="customer_supportRepId"
                                        placeholder="employee id"/>
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
                        onClick={this.deleteCustomerFromServer.bind(this)}
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
        <div id="employeeModal" className="modal fade" role="dialog">
          <div className="modal-dialog">
            <div className="modal-content">
              <div className="modal-header">
                <button type="button" className="close" data-dismiss="modal">&times;</button>
                <h4 className="modal-title">Supported by Employee</h4>
              </div>
              <div className="modal-body row">
                <div>
                  <label className="col-sm-4">Id</label>
                  <div className="col-sm-8 margin_bottom">
                    <p>{this.state.employee.employeeId} &nbsp;</p>
                  </div>
                  <label className="col-sm-4">First Name</label>
                  <div className="col-sm-8 margin_bottom">
                    <p>{this.state.employee.firstName} &nbsp;</p>
                  </div>
                  <label className="col-sm-4">Last name</label>
                  <div className="col-sm-8 margin_bottom">
                    <p>{this.state.employee.lastName} &nbsp;</p>
                  </div>
                  <label className="col-sm-4">Reports to empoyee id</label>
                  <div className="col-sm-8 margin_bottom">
                    <p> {this.state.employee.reportsTo} &nbsp;</p>
                  </div>
                  <label className="col-sm-4">Birthdate</label>
                  <div className="col-sm-8 margin_bottom">
                    <p>{this.state.employee.birthDate}&nbsp;</p>
                  </div>
                  <label className="col-sm-4">hireDate</label>
                  <div className="col-sm-8 margin_bottom">
                    <p>{this.state.employee.hireDate} &nbsp;</p>
                  </div>
                  <label className="col-sm-4">Address</label>
                  <div className="col-sm-8 margin_bottom">
                    <p>{this.state.employee.address} &nbsp;</p>
                  </div>
                  <label className="col-sm-4">City</label>
                  <div className="col-sm-8 margin_bottom">
                    <p>{this.state.employee.city} &nbsp;</p>
                  </div>
                  <label className="col-sm-4">State</label>
                  <div className="col-sm-8 margin_bottom">
                    <p>{this.state.employee.state} &nbsp;</p>
                  </div>
                  <label className="col-sm-4">Country</label>
                  <div className="col-sm-8 margin_bottom">
                    <p>{this.state.employee.country} &nbsp;</p>
                  </div>
                  <label className="col-sm-4">Postalcode</label>
                  <div className="col-sm-8 margin_bottom">
                    <p>{this.state.employee.postalCode} &nbsp;</p>
                  </div>
                  <label className="col-sm-4">Phone</label>
                  <div className="col-sm-8 margin_bottom">
                    <p>{this.state.employee.phone} &nbsp;</p>
                  </div>
                  <label className="col-sm-4">Fax</label>
                  <div className="col-sm-8 margin_bottom">
                    <p>{this.state.employee.fax} &nbsp;</p>
                  </div>
                  <label className="col-sm-4">Email</label>
                  <div className="col-sm-8 margin_bottom">
                    <p>{this.state.employee.email} &nbsp;</p>
                  </div>
                  <label className="col-sm-4">Title</label>
                  <div className="col-sm-8 margin_bottom">
                    <p>{this.state.employee.title} &nbsp;</p>
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
              width={450}
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
              header={<Cell>Fax</Cell>}
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
            <Column
              header={<Cell>Supported by</Cell>}
              cell={props => (
<Cell {...props}>
        <button type="button" className="btn btn-default btn-sm" data-toggle="modal" data-target="#employeeModal" onClick={this.loadEmployeeFromServer.bind(this, this.state.customers[props.rowIndex]._links.employee.href)}>
          <span className="glyphicon glyphicon-info-sign"> Id: {this.state.customers[props.rowIndex].customer.supportRepId}</span>
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
<button type="button" className="btn btn-default btn-sm" data-toggle="modal" data-target="#editModal" onClick={this.updateCustomer.bind(this, props.rowIndex)}>
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
<button type="button" className="btn btn-default btn-sm" data-toggle="modal" data-target="#deleteModal" onClick={this.updateCustomer.bind(this, props.rowIndex)}>
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

CustomersComponent.displayName = 'CustomersComponent';

CustomersComponent.propTypes = {};
CustomersComponent.defaultProps = {};

export default Dimensions()(CustomersComponent);
