/**
 * CustomersComponent.
 *
 * Component that fetches a list of customers from the REST-API and renders it in a datatable.
 */

'use strict';

import React from 'react/addons';
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
      url: "http://localhost:7777/resources/customers",
      customer: {},
      customerUrl: "",
      employee: {}
    }
  };

  handleFirstNameChange(e) {
    var newState = React.addons.update(this.state, {
      customer: {
        firstName: {$set: e.target.value}
      }
    });
    this.setState(newState);
  }

  handleLastNameChange(e) {
    var newState = React.addons.update(this.state, {
      customer: {
        lastName: {$set: e.target.value}
      }
    });
    this.setState(newState);
  }

  handleCompanyChange(e) {
    var newState = React.addons.update(this.state, {
      customer: {
        company: {$set: e.target.value}
      }
    });
    this.setState(newState);
  }

  handleAddressChange(e) {
    var newState = React.addons.update(this.state, {
      customer: {
        address: {$set: e.target.value}
      }
    });
    this.setState(newState);
  }

  handleCityChange(e) {
    var newState = React.addons.update(this.state, {
      customer: {
        city: {$set: e.target.value}
      }
    });
    this.setState(newState);
  }

  handleCountryChange(e) {
    var newState = React.addons.update(this.state, {
      customer: {
        country: {$set: e.target.value}
      }
    });
    this.setState(newState);
  }

  handlePostalCodeChange(e) {
    var newState = React.addons.update(this.state, {
      customer: {
        postalCode: {$set: e.target.value}
      }
    });
    this.setState(newState);
  }

  handleStateChange(e) {
    var newState = React.addons.update(this.state, {
      customer: {
        state: {$set: e.target.value}
      }
    });
    this.setState(newState);
  }

  handlePhoneChange(e) {
    var newState = React.addons.update(this.state, {
      customer: {
        phone: {$set: e.target.value}
      }
    });
    this.setState(newState);
  }

  handleFaxChange(e) {
    var newState = React.addons.update(this.state, {
      customer: {
        fax: {$set: e.target.value}
      }
    });
    this.setState(newState);
  }

  handleEmailChange(e) {
    var newState = React.addons.update(this.state, {
      customer: {
        email: {$set: e.target.value}
      }
    });
    this.setState(newState);
  }

  handleSupportRepIdChange(e) {
    var newState = React.addons.update(this.state, {
      customer: {
        supportRepId: {$set: e.target.value}
      }
    });
    this.setState(newState);
  }

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

  addCustomer() {
    this.setState({customer: {}})
  }

  postCustomerToServer() {
    $.ajax({
      type: "POST",
      url: this.state.url,
      data: JSON.stringify(
        {
          firstName: this.state.customer.firstName,
          lastName: this.state.customer.lastName,
          company: this.state.customer.company,
          address: this.state.customer.address,
          city: this.state.customer.city,
          country: this.state.customer.country,
          postalCode: this.state.customer.postalCode,
          state: this.state.customer.state,
          phone: this.state.customer.phone,
          fax: this.state.customer.fax,
          email: this.state.customer.email,
          supportRepId: this.state.customer.supportRepId
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
  }

  putCustomerToServer() {
    $.ajax({
      type: "PUT",
      url: this.state.customerUrl,
      data: JSON.stringify(
        {
          firstName: this.state.customer.firstName,
          lastName: this.state.customer.lastName,
          company: this.state.customer.company,
          address: this.state.customer.address,
          city: this.state.customer.city,
          country: this.state.customer.country,
          postalCode: this.state.customer.postalCode,
          state: this.state.customer.state,
          phone: this.state.customer.phone,
          fax: this.state.customer.fax,
          email: this.state.customer.email,
          supportRepId: this.state.customer.supportRepId
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
                <div>
                  <div className="form-group">
                    <label className="col-sm-4" for="customer_firstname">First Name</label>
                    <div className="col-sm-8 margin_bottom">
                      <input type="text" className="form-control" id="customer_firstname"
                             value={this.state.customer.firstName}
                             onChange={this.handleFirstNameChange.bind(this)}/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="customer_lastname">Last name</label>
                    <div className="col-sm-8 margin_bottom">
                      <input type="text" className="form-control" id="customer_lastname"
                             value={this.state.customer.lastName}
                             onChange={this.handleLastNameChange.bind(this)}/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="customer_company">Company</label>
                    <div className="col-sm-8 margin_bottom">
                      <input type="text" className="form-control" id="customer_company"
                             value={this.state.customer.company}
                             onChange={this.handleCompanyChange.bind(this)}/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="customer_address">Address</label>
                    <div className="col-sm-8 margin_bottom">
                      <input type="text" className="form-control" id="customer_address"
                             value={this.state.customer.address}
                             onChange={this.handleAddressChange.bind(this)}/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="customer_city">City</label>
                    <div className="col-sm-8 margin_bottom">
                      <input type="text" className="form-control" id="customer_city"
                             value={this.state.customer.city}
                             onChange={this.handleCityChange.bind(this)}/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="customer_state">State</label>
                    <div className="col-sm-8 margin_bottom">
                      <input type="text" className="form-control" id="customer_state"
                             value={this.state.customer.state}
                             onChange={this.handleStateChange.bind(this)}/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="customer_country">Country</label>
                    <div className="col-sm-8 margin_bottom">
                      <input type="text" className="form-control" id="customer_country"
                             value={this.state.customer.country}
                             onChange={this.handleCountryChange.bind(this)}/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="customer_postalcode">PostalCode</label>
                    <div className="col-sm-8 margin_bottom">
                      <input type="text" className="form-control" id="customer_postalcode"
                             value={this.state.customer.postalCode}
                             onChange={this.handlePostalCodeChange.bind(this)}/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="customer_phone">Phone</label>
                    <div className="col-sm-8 margin_bottom">
                      <input type="text" className="form-control" id="customer_phone"
                             value={this.state.customer.phone}
                             onChange={this.handlePhoneChange.bind(this)}/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="customer_fax">Fax</label>
                    <div className="col-sm-8 margin_bottom">
                      <input type="text" className="form-control" id="customer_fax"
                             value={this.state.customer.fax}
                             onChange={this.handleFaxChange.bind(this)}/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="customer_email">Email</label>
                    <div className="col-sm-8 margin_bottom">
                      <input type="text" className="form-control" id="customer_email"
                             value={this.state.customer.email}
                             onChange={this.handleEmailChange.bind(this)}/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="customer_supportRepId">Supported by employee</label>
                    <div className="col-sm-8 margin_bottom">
                      <input type="text" className="form-control" id="customer_supportedRepId"
                             value={this.state.customer.supportRepId}
                             onChange={this.handleSupportRepIdChange.bind(this)}/>
                    </div>
                  </div>
                </div>
              </div>
              <div className="modal-footer">
                <button type="button" className="btn btn-default" data-dismiss="modal"
                        onClick={this.putCustomerToServer.bind(this)}>Submit
                </button>
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
                <div>
                  <div className="form-group">
                    <label className="col-sm-4" for="customer_firstname">First Name</label>
                    <div className="col-sm-8 margin_bottom">
                      <input type="text" className="form-control" id="customer_firstname" placeholder="first name"
                             onChange={this.handleFirstNameChange.bind(this)}/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="customer_lastname">Last name</label>
                    <div className="col-sm-8 margin_bottom">
                      <input type="text" className="form-control" id="customer_lastname" placeholder="last name"
                             onChange={this.handleLastNameChange.bind(this)}/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="customer_company">Company</label>
                    <div className="col-sm-8 margin_bottom">
                      <input type="text" className="form-control" id="customer_company" placeholder="company"
                             onChange={this.handleCompanyChange.bind(this)}/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="customer_address">Address</label>
                    <div className="col-sm-8 margin_bottom">
                      <input type="text" className="form-control" id="customer_address" placeholder="address"
                             onChange={this.handleAddressChange.bind(this)}/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="customer_city">City</label>
                    <div className="col-sm-8 margin_bottom">
                      <input type="text" className="form-control" id="customer_city" placeholder="city"
                             onChange={this.handleCityChange.bind(this)}/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="customer_state">State</label>
                    <div className="col-sm-8 margin_bottom">
                      <input type="text" className="form-control" id="customer_state" placeholder="state"
                             onChange={this.handleStateChange.bind(this)}/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="customer_country">Country</label>
                    <div className="col-sm-8 margin_bottom">
                      <input type="text" className="form-control" id="customer_country" placeholder="country"
                             onChange={this.handleCountryChange.bind(this)}/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="customer_postalcode">PostalCode</label>
                    <div className="col-sm-8 margin_bottom">
                      <input type="text" className="form-control" id="customer_postalcode"
                             placeholder="postalCode"
                             onChange={this.handlePostalCodeChange.bind(this)}/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="customer_phone">Phone</label>
                    <div className="col-sm-8 margin_bottom">
                      <input type="text" className="form-control" id="customer_phone" placeholder="phone"
                             onChange={this.handlePhoneChange.bind(this)}/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="customer_fax">Fax</label>
                    <div className="col-sm-8 margin_bottom">
                      <input type="text" className="form-control" id="customer_fax" placeholder="fax"
                             onChange={this.handleFaxChange.bind(this)}/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="customer_email">Email</label>
                    <div className="col-sm-8 margin_bottom">
                      <input type="text" className="form-control" id="customer_email" placeholder="email"
                             onChange={this.handleEmailChange.bind(this)}/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="customer_supportRepId">Supported by employee</label>
                    <div className="col-sm-8 margin_bottom">
                      <input type="text" className="form-control" id="customer_supportedRepId"
                             placeholder="employee id"
                             onChange={this.handleSupportRepIdChange.bind(this)}/>
                    </div>
                  </div>
                </div>
              </div>
              <div className="modal-footer">
                <button type="button" className="btn btn-default" data-dismiss="modal"
                        onClick={this.postCustomerToServer.bind(this)}>Submit
                </button>
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
        <button type="button" className="btn btn-default" data-toggle="modal" data-target="#addModal"
                onClick={this.addCustomer.bind(this)}>
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
