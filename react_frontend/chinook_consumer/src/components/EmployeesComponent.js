/**
 * EmployeesComponent.
 *
 * Component that fetches a list of employees from the REST-API and renders it in a datatable.
 */

'use strict';

import React from 'react/addons';
import {Table, Column, Cell} from 'fixed-data-table';
import $ from "jquery";
import Dimensions from 'react-dimensions'
import DatePicker from 'react-datepicker'
import moment from 'moment'

require('styles//DataTable.css');
require('styles//Employees.css');
require('react-datepicker/dist/react-datepicker.css');

class EmployeesComponent extends React.Component {
  constructor(props, context) {
    super(props, context);

    this.state = {
      employees: [],
      url: "http://localhost:7777/resources/employees",
      employee: {},
      employeeUrl: "",
      reportsto: {}
    }
  };

  handleFirstNameChange(e) {
    var newState = React.addons.update(this.state, {
      employee: {
        firstName: {$set: e.target.value}
      }
    });
    this.setState(newState);
  }

  handleLastNameChange(e) {
    var newState = React.addons.update(this.state, {
      employee: {
        lastName: {$set: e.target.value}
      }
    });
    this.setState(newState);
  }

  handleReportsToChange(e) {
    var newState = React.addons.update(this.state, {
      employee: {
        reportsTo: {$set: e.target.value}
      }
    });
    this.setState(newState);
  }

  handleTitleChange(e) {
    var newState = React.addons.update(this.state, {
      employee: {
        title: {$set: e.target.value}
      }
    });
    this.setState(newState);
  }

  handleBirthDateChange(date) {
    var newState = React.addons.update(this.state, {
      employee: {
        birthDate: {$set: date}
      }
    });
    this.setState(newState);
  }

  handleHireDateChange(date) {
    var newState = React.addons.update(this.state, {
      employee: {
        hireDate: {$set: date}
      }
    });
    this.setState(newState);
  }

  handleAddressChange(e) {
    var newState = React.addons.update(this.state, {
      employee: {
        address: {$set: e.target.value}
      }
    });
    this.setState(newState);
  }

  handleCityChange(e) {
    var newState = React.addons.update(this.state, {
      employee: {
        city: {$set: e.target.value}
      }
    });
    this.setState(newState);
  }

  handleCountryChange(e) {
    var newState = React.addons.update(this.state, {
      employee: {
        country: {$set: e.target.value}
      }
    });
    this.setState(newState);
  }

  handlePostalCodeChange(e) {
    var newState = React.addons.update(this.state, {
      employee: {
        postalCode: {$set: e.target.value}
      }
    });
    this.setState(newState);
  }

  handleStateChange(e) {
    var newState = React.addons.update(this.state, {
      employee: {
        state: {$set: e.target.value}
      }
    });
    this.setState(newState);
  }

  handlePhoneChange(e) {
    var newState = React.addons.update(this.state, {
      employee: {
        phone: {$set: e.target.value}
      }
    });
    this.setState(newState);
  }

  handleFaxChange(e) {
    var newState = React.addons.update(this.state, {
      employee: {
        fax: {$set: e.target.value}
      }
    });
    this.setState(newState);
  }

  handleEmailChange(e) {
    var newState = React.addons.update(this.state, {
      employee: {
        email: {$set: e.target.value}
      }
    });
    this.setState(newState);
  }

  loadEmployeesFromServer() {
    $.ajax({
      type: "GET",
      url: this.state.url,
      dataType: 'json',
      success: (employeesData) => {
        this.setState({employees: employeesData.employees})
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
        this.setState({reportsto: employeeData.employee})
      },
      error: (xhr, status, err) => {
        console.error(url, status, err.toString());
      }
    });
  }

  updateEmployee(index) {
    this.setState({
      employee: this.state.employees[index].employee,
      employeeUrl: this.state.employees[index]._links.self.href
    })
  }

  addEmployee() {
    this.setState({employee: {}})
  }

  postEmployeeToServer() {
    $.ajax({
      type: "POST",
      url: this.state.url,
      data: JSON.stringify(
        {
          firstName: this.state.employee.firstName,
          lastName: this.state.employee.lastName,
          reportsTo: this.state.employee.reportsTo,
          title: this.state.employee.title,
          birthDate: this.state.employee.birthDate,
          hireDate: this.state.employee.hireDate,
          address: this.state.employee.address,
          city: this.state.employee.city,
          country: this.state.employee.country,
          postalCode: this.state.employee.postalCode,
          state: this.state.employee.state,
          phone: this.state.employee.phone,
          fax: this.state.employee.fax,
          email: this.state.employee.email,
        }),
      contentType: "application/json; charset=utf-8",
      dataType: "json",
      success: (response) => {
        this.loadEmployeesFromServer();
      },
      error: (xhr, status, err) => {
        console.error(this.state.url, status, err.toString());
      }
    });
  }

  putEmployeeToServer() {
    $.ajax({
      type: "PUT",
      url: this.state.employeeUrl,
      data: JSON.stringify(
        {
          firstName: this.state.employee.firstName,
          lastName: this.state.employee.lastName,
          reportsTo: this.state.employee.reportsTo,
          title: this.state.employee.title,
          birthDate: this.state.employee.birthDate,
          hireDate: this.state.employee.hireDate,
          address: this.state.employee.address,
          city: this.state.employee.city,
          country: this.state.employee.country,
          postalCode: this.state.employee.postalCode,
          state: this.state.employee.state,
          phone: this.state.employee.phone,
          fax: this.state.employee.fax,
          email: this.state.employee.email,
        }),
      contentType: "application/json; charset=utf-8",
      dataType: "json",
      success: (response) => {
        this.loadEmployeesFromServer();
      },
      error: (xhr, status, err) => {
        console.error(this.state.employeeUrl, status, err.toString());
      }
    });
  }

  deleteEmployeeFromServer() {
    $.ajax({
      type: "DELETE",
      url: this.state.employeeUrl,
      dataType: "json",
      success: (response) => {
        this.loadEmployeesFromServer();
      },
      error: (xhr, status, err) => {
        console.error(this.state.employeeUrl, status, err.toString());
      }
    });
  }

  componentDidMount() {
    this.loadEmployeesFromServer();
  }

  render() {
    return (
      <div className="employees-component">
        <div id="addModal" className="modal fade" role="dialog">
          <div className="modal-dialog">
            <div className="modal-content">
              <div className="modal-header">
                <button type="button" className="close" data-dismiss="modal">&times;</button>
                <h4 className="modal-title">Create new Employee</h4>
              </div>
              <div className="modal-body row">
                <div>
                  <div className="form-group">
                    <label for="employee_firstname" className="col-sm-4">First Name</label>
                    <div className="col-sm-8 margin_bottom">
                      <input type="text" className="form-control" id="employee_firstname"
                             placeholder="First Name"
                             onChange={this.handleFirstNameChange.bind(this)}/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label for="employee_lastname" className="col-sm-4">Last name</label>
                    <div className="col-sm-8 margin_bottom">
                      <input type="text" className="form-control" id="employee_lastname"
                             placeholder="Last Name"
                             onChange={this.handleLastNameChange.bind(this)}/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label for="employee_reportsto" className="col-sm-4">Reports to employee id</label>
                    <div className="col-sm-8 margin_bottom">
                      <input type="text" className="form-control" id="employee_reportsto"
                             placeholder="Reports to"
                             onChange={this.handleReportsToChange.bind(this)}/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label for="employee_birthdate" className="col-sm-4">Birthdate</label>
                    <div className="col-sm-8 margin_bottom">
                      <DatePicker id="employee_hiredate" className="form-control"
                                  dateFormat="YYYY-MM-DD"
                                  selected={this.state.employee.birthDate}
                                  onChange={this.handleBirthDateChange.bind(this)}/>;
                    </div>
                  </div>
                  <div className="form-group">
                    <label for="employee_hiredate" className="col-sm-4">Hire date</label>
                    <div className="col-sm-8 margin_bottom">
                      <DatePicker id="employee_hiredate" className="form-control"
                                  dateFormat="YYYY-MM-DD"
                                  selected={this.state.employee.hireDate}
                                  onChange={this.handleHireDateChange.bind(this)}/>;
                    </div>
                  </div>
                  <div className="form-group">
                    <label for="employee_address" className="col-sm-4">Address</label>
                    <div className="col-sm-8 margin_bottom">
                      <input type="text" className="form-control" id="employee_address"
                             placeholder="Address"
                             onChange={this.handleAddressChange.bind(this)}/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label for="employee_city" className="col-sm-4">City</label>
                    <div className="col-sm-8 margin_bottom">
                      <input type="text" className="form-control" id="employee_city"
                             placeholder="City"
                             onChange={this.handleCityChange.bind(this)}/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label for="employee_state" className="col-sm-4">State</label>
                    <div className="col-sm-8 margin_bottom">
                      <input type="text" className="form-control" id="employee_state"
                             placeholder="State"
                             onChange={this.handleStateChange.bind(this)}/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label for="employee_country" className="col-sm-4">Country</label>
                    <div className="col-sm-8 margin_bottom">
                      <input type="text" className="form-control" id="employee_country"
                             placeholder="Country"
                             onChange={this.handleCountryChange.bind(this)}/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label for="employee_postalcode" className="col-sm-4">Postalcode</label>
                    <div className="col-sm-8 margin_bottom">
                      <input type="text" className="form-control" id="employee_postalcode"
                             placeholder="Postalcode"
                             onChange={this.handlePostalCodeChange.bind(this)}/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label for="employee_phone" className="col-sm-4">Phone</label>
                    <div className="col-sm-8 margin_bottom">
                      <input type="text" className="form-control" id="employee_phone"
                             placeholder="Phone"
                             onChange={this.handlePhoneChange.bind(this)}/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label for="employee_fax" className="col-sm-4">Fax</label>
                    <div className="col-sm-8 margin_bottom">
                      <input type="text" className="form-control" id="employee_fax"
                             placeholder="Fax"
                             onChange={this.handleFaxChange.bind(this)}/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label for="employee_email" className="col-sm-4">Email</label>
                    <div className="col-sm-8 margin_bottom">
                      <input type="text" className="form-control" id="employee_email"
                             placeholder="Email"
                             onChange={this.handleEmailChange.bind(this)}/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label for="employee_title" className="col-sm-4">Title</label>
                    <div className="col-sm-8 margin_bottom">
                      <input type="text" className="form-control" id="employee_title"
                             placeholder="Title"
                             onChange={this.handleTitleChange.bind(this)}/>
                    </div>
                  </div>
                </div>
              </div>
              <div className="modal-footer">
                <button type="button" className="btn btn-default" data-dismiss="modal"
                        onClick={this.postEmployeeToServer.bind(this)}>Submit
                </button>
                <button type="button" className="btn btn-default" data-dismiss="modal">Close</button>
              </div>
            </div>
          </div>
        </div>
        <div id="editModal" className="modal fade" role="dialog">
          <div className="modal-dialog">
            <div className="modal-content">
              <div className="modal-header">
                <button type="button" className="close" data-dismiss="modal">&times;</button>
                <h4 className="modal-title">Edit Employee</h4>
              </div>
              <div className="modal-body row">
                <div>
                  <div className="form-group">
                    <label className="col-sm-4" for="employee_firstname">First Name</label>
                    <div className="col-sm-8 margin_bottom">
                      <input type="text" className="form-control" id="employee_firstname"
                             value={this.state.employee.firstName}
                             onChange={this.handleFirstNameChange.bind(this)}/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="employee_lastname">Last name</label>
                    <div className="col-sm-8 margin_bottom">
                      <input type="text" className="form-control" id="employee_lastname"
                             value={this.state.employee.lastName}
                             onChange={this.handleLastNameChange.bind(this)}/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label for="employee_reportsto" className="col-sm-4">Reports to employee id</label>
                    <div className="col-sm-8 margin_bottom">
                      <input type="text" className="form-control" id="employee_reportsto"
                             value={this.state.employee.reportsTo}
                             onChange={this.handleReportsToChange.bind(this)}/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label for="employee_birthdate" className="col-sm-4">Birthdate</label>
                    <div className="col-sm-8 margin_bottom">
                      <input type="text" className="form-control" id="employee_birthdate"
                             value={this.state.employee.birthDate}
                             onChange={this.handleBirthDateChange.bind(this)}/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label for="employee_hiredate" className="col-sm-4">Hire date</label>
                    <div className="col-sm-8 margin_bottom">
                      <input type="text" className="form-control" id="employee_hiredate"
                             value={this.state.employee.hireDate}
                             onChange={this.handleHireDateChange.bind(this)}/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="employee_address">Address</label>
                    <div className="col-sm-8 margin_bottom">
                      <input type="text" className="form-control" id="employee_address"
                             value={this.state.employee.address}
                             onChange={this.handleAddressChange.bind(this)}/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="employee_city">City</label>
                    <div className="col-sm-8 margin_bottom">
                      <input type="text" className="form-control" id="employee_city"
                             value={this.state.employee.city}
                             onChange={this.handleCityChange.bind(this)}/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="employee_state">State</label>
                    <div className="col-sm-8 margin_bottom">
                      <input type="text" className="form-control" id="employee_state"
                             value={this.state.employee.state}
                             onChange={this.handleStateChange.bind(this)}/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="employee_country">Country</label>
                    <div className="col-sm-8 margin_bottom">
                      <input type="text" className="form-control" id="employee_country"
                             value={this.state.employee.country}
                             onChange={this.handleCountryChange.bind(this)}/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="employee_postalcode">PostalCode</label>
                    <div className="col-sm-8 margin_bottom">
                      <input type="text" className="form-control" id="employee_postalcode"
                             value={this.state.employee.postalCode}
                             onChange={this.handlePostalCodeChange.bind(this)}/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="employee_phone">Phone</label>
                    <div className="col-sm-8 margin_bottom">
                      <input type="text" className="form-control" id="employee_phone"
                             value={this.state.employee.phone}
                             onChange={this.handlePhoneChange.bind(this)}/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="employee_fax">Fax</label>
                    <div className="col-sm-8 margin_bottom">
                      <input type="text" className="form-control" id="employee_fax"
                             value={this.state.employee.fax}
                             onChange={this.handleFaxChange.bind(this)}/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="employee_email">Email</label>
                    <div className="col-sm-8 margin_bottom">
                      <input type="text" className="form-control" id="employee_email"
                             value={this.state.employee.email}
                             onChange={this.handleEmailChange.bind(this)}/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label for="employee_title" className="col-sm-4">Title</label>
                    <div className="col-sm-8 margin_bottom">
                      <input type="text" className="form-control" id="employee_title"
                             value={this.state.employee.title}
                             onChange={this.handleTitleChange.bind(this)}/>
                    </div>
                  </div>
                </div>
              </div>
              <div className="modal-footer">
                <button type="button" className="btn btn-default" data-dismiss="modal"
                        onClick={this.putEmployeeToServer.bind(this)}>Submit
                </button>
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
                <h4 className="modal-title">Reports to Employee</h4>
              </div>
              <div className="modal-body row">
                <div>
                  <label className="col-sm-4">Id</label>
                  <div className="col-sm-8 margin_bottom">
                    <p>{this.state.reportsto.employeeId} &nbsp;</p>
                  </div>
                  <label className="col-sm-4">First Name</label>
                  <div className="col-sm-8 margin_bottom">
                    <p>{this.state.reportsto.firstName} &nbsp;</p>
                  </div>
                  <label className="col-sm-4">Last name</label>
                  <div className="col-sm-8 margin_bottom">
                    <p>{this.state.reportsto.lastName} &nbsp;</p>
                  </div>
                  <label className="col-sm-4">Reports to empoyee id</label>
                  <div className="col-sm-8 margin_bottom">
                    <p> {this.state.reportsto.reportsTo} &nbsp;</p>
                  </div>
                  <label className="col-sm-4">Birthdate</label>
                  <div className="col-sm-8 margin_bottom">
                    <p>{this.state.reportsto.birthDate}&nbsp;</p>
                  </div>
                  <label className="col-sm-4">hireDate</label>
                  <div className="col-sm-8 margin_bottom">
                    <p>{this.state.reportsto.hireDate}&nbsp;</p>
                  </div>
                  <label className="col-sm-4">Address</label>
                  <div className="col-sm-8 margin_bottom">
                    <p>{this.state.reportsto.address}&nbsp;</p>
                  </div>
                  <label className="col-sm-4">City</label>
                  <div className="col-sm-8 margin_bottom">
                    <p>{this.state.reportsto.city}&nbsp;</p>
                  </div>
                  <label className="col-sm-4">State</label>
                  <div className="col-sm-8 margin_bottom">
                    <p>{this.state.reportsto.state}&nbsp;</p>
                  </div>
                  <label className="col-sm-4">Country</label>
                  <div className="col-sm-8 margin_bottom">
                    <p>{this.state.reportsto.country}&nbsp;</p>
                  </div>
                  <label className="col-sm-4">Postalcode</label>
                  <div className="col-sm-8 margin_bottom">
                    <p>{this.state.reportsto.postalCode} &nbsp;</p>
                  </div>
                  <label className="col-sm-4">Phone</label>
                  <div className="col-sm-8 margin_bottom">
                    <p>{this.state.reportsto.phone} &nbsp;</p>
                  </div>
                  <label className="col-sm-4">Fax</label>
                  <div className="col-sm-8 margin_bottom">
                    <p>{this.state.reportsto.fax} &nbsp;</p>
                  </div>
                  <label className="col-sm-4">Email</label>
                  <div className="col-sm-8 margin_bottom">
                    <p>{this.state.reportsto.email} &nbsp;</p>
                  </div>
                  <label className="col-sm-4">Title</label>
                  <div className="col-sm-8 margin_bottom">
                    <p>{this.state.reportsto.title} &nbsp;</p>
                  </div>
                </div>
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
                        onClick={this.deleteEmployeeFromServer.bind(this)}
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
            rowsCount={this.state.employees.length}
            rowHeight={50}
            headerHeight={50}
            width={this.props.containerWidth}
            height={500}>
            <Column
              header={<Cell>Id</Cell>}
              cell={props => (
           <Cell {...props}>
        {this.state.employees[props.rowIndex].employee.employeeId}
          </Cell>
        )}
              width={50}
              flexGrow={1}
            />
            <Column
              header={<Cell>FirstName</Cell>}
              cell={props => (
<Cell {...props}>
        {this.state.employees[props.rowIndex].employee.firstName}
</Cell>
        )}
              width={250}
              flexGrow={1}
            />
            <Column
              header={<Cell>LastName</Cell>}
              cell={props => (
<Cell {...props}>
        {this.state.employees[props.rowIndex].employee.firstName}
</Cell>
        )}
              width={250}
              flexGrow={1}
            />
            <Column
              header={<Cell>Birthdate</Cell>}
              cell={props => (
<Cell {...props}>
        {this.state.employees[props.rowIndex].employee.birthDate}
</Cell>
        )}
              width={250}
              flexGrow={1}
            />
            <Column
              header={<Cell>Hiredate</Cell>}
              cell={props => (
<Cell {...props}>
        {this.state.employees[props.rowIndex].employee.hireDate}
</Cell>
        )}
              width={250}
              flexGrow={1}
            />
            <Column
              header={<Cell>Address</Cell>}
              cell={props => (
<Cell {...props}>
        {this.state.employees[props.rowIndex].employee.address}
</Cell>
        )}
              width={250}
              flexGrow={1}
            />
            <Column
              header={<Cell>City</Cell>}
              cell={props => (
<Cell {...props}>
        {this.state.employees[props.rowIndex].employee.city}
</Cell>
        )}
              width={250}
              flexGrow={1}
            />
            <Column
              header={<Cell>State</Cell>}
              cell={props => (
<Cell {...props}>
        {this.state.employees[props.rowIndex].employee.state}
</Cell>
        )}
              width={250}
              flexGrow={1}
            />
            <Column
              header={<Cell>Country</Cell>}
              cell={props => (
<Cell {...props}>
        {this.state.employees[props.rowIndex].employee.country}
</Cell>
        )}
              width={250}
              flexGrow={1}
            />
            <Column
              header={<Cell>PostalCode</Cell>}
              cell={props => (
<Cell {...props}>
        {this.state.employees[props.rowIndex].employee.postalCode}
</Cell>
        )}
              width={250}
              flexGrow={1}
            />
            <Column
              header={<Cell>Phone</Cell>}
              cell={props => (
<Cell {...props}>
        {this.state.employees[props.rowIndex].employee.phone}
</Cell>
        )}
              width={250}
              flexGrow={1}
            />
            <Column
              header={<Cell>fax</Cell>}
              cell={props => (
<Cell {...props}>
        {this.state.employees[props.rowIndex].employee.fax}
</Cell>
        )}
              width={250}
              flexGrow={1}
            />
            <Column
              header={<Cell>Email</Cell>}
              cell={props => (
<Cell {...props}>
        {this.state.employees[props.rowIndex].employee.email}
</Cell>
        )}
              width={250}
              flexGrow={1}
            />
            <Column
              header={<Cell>Title</Cell>}
              cell={props => (
<Cell {...props}>
        {this.state.employees[props.rowIndex].employee.title}
</Cell>
        )}
              width={250}
              flexGrow={1}
            />
            <Column
              header={<Cell>Reports to</Cell>}
              cell={props => (
<Cell {...props}>
        <button type="button" className="btn btn-default btn-sm" data-toggle="modal" data-target="#employeeModal" onClick={this.loadEmployeeFromServer.bind(this, this.state.employees[props.rowIndex]._links.reportsTo.href)}>
          <span className="glyphicon glyphicon-info-sign"> Id: {this.state.employees[props.rowIndex].employee.reportsTo}</span>
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
<button type="button" className="btn btn-default btn-sm" data-toggle="modal" data-target="#editModal" onClick={this.updateEmployee.bind(this, props.rowIndex)}>
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
<button type="button" className="btn btn-default btn-sm" data-toggle="modal" data-target="#deleteModal" onClick={this.updateEmployee.bind(this, props.rowIndex)}>
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
                onClick={this.addEmployee.bind(this)}>
          <span className="glyphicon glyphicon-plus"></span> Add
        </button>
      </div>
    );
  }
}

EmployeesComponent.displayName = 'EmployeesComponent';

EmployeesComponent.propTypes = {};
EmployeesComponent.defaultProps = {};

export default Dimensions()(EmployeesComponent);
