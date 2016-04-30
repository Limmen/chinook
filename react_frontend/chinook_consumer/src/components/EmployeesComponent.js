/**
 * EmployeesComponent.
 *
 * Component that fetches a list of employees from the REST-API and renders it in a datatable.
 */

'use strict';

import React from 'react/addons';
import {Table, Column, Cell} from 'fixed-data-table';
import Dimensions from 'react-dimensions'
import Formsy from 'formsy-react';
import TextInputComponent from './TextInputComponent';
import DateInputComponent from './DateInputComponent';

require('styles//DataTable.css');
require('styles//Employees.css');


class EmployeesComponent extends React.Component {
  constructor(props, context) {
    super(props, context);

    this.state = {
      employees: [],
      url: "http://localhost:7777/resources/employees",
      employee: {},
      employeeUrl: "",
      reportsTo: {},
      canSubmit: false
    }
  };

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
        this.setState({reportsTo: employeeData.employee})
      },
      error: (xhr, status, err) => {
        console.error(url, status, err.toString());
      }
    });
  }

  postEmployeeToServer(data) {
    console.log("post employee to serv");
    $.ajax({
      type: "POST",
      url: this.state.url,
      data: JSON.stringify(
        {
          firstName: data.firstName,
          lastName: data.lastName,
          reportsTo: data.reportsTo,
          hireDate: data.hireDate,
          birthDate: data.birthDate,
          address: data.address,
          city: data.city,
          state: data.state,
          country: data.country,
          postalCode: data.postalCode,
          phone: data.phone,
          fax: data.fax,
          email: data.email,
          title: data.title
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
    $("#addModal").modal('hide');
  }

  putEmployeeToServer(data) {
    $.ajax({
      type: "PUT",
      url: this.state.employeeUrl,
      data: JSON.stringify(
        {
          firstName: data.firstName,
          lastName: data.lastName,
          reportsTo: data.reportsTo,
          hireDate: data.hireDate,
          birthDate: data.birthDate,
          address: data.address,
          city: data.city,
          state: data.state,
          country: data.country,
          postalCode: data.postalCode,
          phone: data.phone,
          fax: data.fax,
          email: data.email,
          title: data.title
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
    $("#editModal").modal('hide');
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

  updateEmployee(index) {
    this.setState({
      employee: this.state.employees[index].employee,
      employeeUrl: this.state.employees[index]._links.self.href
    })
  }

  componentDidMount() {
    this.loadEmployeesFromServer();
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
      <div className="employees-component">
        <div id="addModal" className="modal fade" role="dialog">
          <div className="modal-dialog">
            <div className="modal-content">
              <div className="modal-header">
                <button type="button" className="close" data-dismiss="modal">&times;</button>
                <h4 className="modal-title">Create new Employee</h4>
              </div>
              <div className="modal-body row">
                <Formsy.Form onValidSubmit={this.postEmployeeToServer.bind(this)} onValid={this.enableButton.bind(this)} onInvalid={this.disableButton.bind(this)}>
                  <div className="form-group">
                    <label className="col-sm-4" for="employee_firstname">First Name</label>
                    <TextInputComponent name="firstName" validationError="this field is required" required id="employee_firstname"
                                        placeholder="first name" />
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="employee_lastname">Last Name</label>
                    <TextInputComponent name="lastName" validationError="this field is required" required id="employee_lastname"
                                        placeholder="last name" />
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="employee_reportsto">Reports to Employee</label>
                    <TextInputComponent name="reportsTo" validations="isInt"validationError="Employee id needs to be a integer" required id="employee_reportsto"
                                        placeholder="employee id" />
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="employee_birthdate">BirthDate</label>
                    <DateInputComponent name="birthDate" validationError="this field is required" required id="employee_birthdate"
                                        placeholder="birth date" />
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="employee_hiredate">HireDate</label>
                    <DateInputComponent name="hireDate" validationError="this field is required" required id="employee_hiredate"
                                        placeholder="birth date" />
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="employee_address">Address</label>
                    <TextInputComponent name="address" validationError="this field is required" required id="employee_address"
                                        placeholder="address" />
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="employee_city">City</label>
                    <TextInputComponent name="city" validationError="this field is required" required id="employee_city"
                                        placeholder="city" />
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="employee_state">State</label>
                    <TextInputComponent name="state" validationError="this field is required" required id="employee_state"
                                        placeholder="state" />
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="employee_country">Country</label>
                    <TextInputComponent name="country" validationError="this field is required" required id="employee_country"
                                        placeholder="country" />
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="employee_postalcode">PostalCode</label>
                    <TextInputComponent name="postalCode" validationError="this field is required" required id="employee_postalcode"
                                        placeholder="postalcode" />
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="employee_phone">Phone</label>
                    <TextInputComponent name="phone" validationError="this field is required" required id="employee_phone"
                                        placeholder="phone" />
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="employee_fax">Fax</label>
                    <TextInputComponent name="fax" validationError="this field is required" required id="employee_fax"
                                        placeholder="fax" />
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="employee_email">Email</label>
                    <TextInputComponent name="email" validations="isEmail" validationError="Enter a valid email" required id="employee_email"
                                        placeholder="email" />
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="employee_title">Title</label>
                    <TextInputComponent name="title" validationError="this field is required" required id="employee_title"
                                        placeholder="title" />
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
        <div id="editModal" className="modal fade" role="dialog">
          <div className="modal-dialog">
            <div className="modal-content">
              <div className="modal-header">
                <button type="button" className="close" data-dismiss="modal">&times;</button>
                <h4 className="modal-title">Edit Employee</h4>
              </div>
              <div className="modal-body row">
                <Formsy.Form onValidSubmit={this.putEmployeeToServer.bind(this)} onValid={this.enableButton.bind(this)} onInvalid={this.disableButton.bind(this)}>
                  <div className="form-group">
                    <label className="col-sm-4" for="employee_firstname">First Name</label>
                    <TextInputComponent name="firstName" validationError="this field is required" required id="employee_firstname"
                                        placeholder="first name" value={this.state.employee.firstName}/>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="employee_lastname">Last Name</label>
                    <TextInputComponent name="lastName" validationError="this field is required" required id="employee_lastname"
                                        placeholder="last name" value={this.state.employee.lastName}/>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="employee_reportsto">Reports to Employee</label>
                    <TextInputComponent name="reportsTo" validations="isInt"validationError="Employee id needs to be a integer" required id="employee_reportsto"
                                        placeholder="employee id" value={this.state.employee.reportsTo}/>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="employee_birthdate">BirthDate</label>
                    <DateInputComponent name="birthDate" validationError="this field is required" required id="employee_birthdate"
                                        placeholder="birth date"/>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="employee_hiredate">HireDate</label>
                    <DateInputComponent name="hireDate" validationError="this field is required" required id="employee_hiredate"
                                        placeholder="hire date"/>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="employee_address">Address</label>
                    <TextInputComponent name="address" validationError="this field is required" required id="employee_address"
                                        placeholder="address" value={this.state.employee.reportsTo}/>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="employee_city">City</label>
                    <TextInputComponent name="city" validationError="this field is required" required id="employee_city"
                                        placeholder="city" value={this.state.employee.city}/>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="employee_state">State</label>
                    <TextInputComponent name="state" validationError="this field is required" required id="employee_state"
                                        placeholder="state" value={this.state.employee.state}/>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="employee_country">Country</label>
                    <TextInputComponent name="country" validationError="this field is required" required id="employee_country"
                                        placeholder="country" value={this.state.employee.country}/>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="employee_postalcode">PostalCode</label>
                    <TextInputComponent name="postalCode" validationError="this field is required" required id="employee_postalcode"
                                        placeholder="postalcode" value={this.state.employee.postalCode}/>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="employee_phone">Phone</label>
                    <TextInputComponent name="phone" validationError="this field is required" required id="employee_phone"
                                        placeholder="phone" value={this.state.employee.phone}/>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="employee_fax">Fax</label>
                    <TextInputComponent name="fax" validationError="this field is required" required id="employee_fax"
                                        placeholder="fax" value={this.state.employee.fax}/>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="employee_email">Email</label>
                    <TextInputComponent name="email" validations="isEmail" validationError="Enter a valid email" required id="employee_email"
                                        placeholder="email" value={this.state.employee.email}/>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-4" for="employee_title">Title</label>
                    <TextInputComponent name="title" validationError="this field is required" required id="employee_title"
                                        placeholder="title" value={this.state.employee.title}/>
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
                    <p>{this.state.reportsTo.employeeId} &nbsp;</p>
                  </div>
                  <label className="col-sm-4">First Name</label>
                  <div className="col-sm-8 margin_bottom">
                    <p>{this.state.reportsTo.firstName} &nbsp;</p>
                  </div>
                  <label className="col-sm-4">Last name</label>
                  <div className="col-sm-8 margin_bottom">
                    <p>{this.state.reportsTo.lastName} &nbsp;</p>
                  </div>
                  <label className="col-sm-4">Reports to empoyee id</label>
                  <div className="col-sm-8 margin_bottom">
                    <p> {this.state.reportsTo.reportsTo} &nbsp;</p>
                  </div>
                  <label className="col-sm-4">Birthdate</label>
                  <div className="col-sm-8 margin_bottom">
                    <p>{this.state.reportsTo.birthDate}&nbsp;</p>
                  </div>
                  <label className="col-sm-4">hireDate</label>
                  <div className="col-sm-8 margin_bottom">
                    <p>{this.state.reportsTo.hireDate}&nbsp;</p>
                  </div>
                  <label className="col-sm-4">Address</label>
                  <div className="col-sm-8 margin_bottom">
                    <p>{this.state.reportsTo.address}&nbsp;</p>
                  </div>
                  <label className="col-sm-4">City</label>
                  <div className="col-sm-8 margin_bottom">
                    <p>{this.state.reportsTo.city}&nbsp;</p>
                  </div>
                  <label className="col-sm-4">State</label>
                  <div className="col-sm-8 margin_bottom">
                    <p>{this.state.reportsTo.state}&nbsp;</p>
                  </div>
                  <label className="col-sm-4">Country</label>
                  <div className="col-sm-8 margin_bottom">
                    <p>{this.state.reportsTo.country}&nbsp;</p>
                  </div>
                  <label className="col-sm-4">Postalcode</label>
                  <div className="col-sm-8 margin_bottom">
                    <p>{this.state.reportsTo.postalCode} &nbsp;</p>
                  </div>
                  <label className="col-sm-4">Phone</label>
                  <div className="col-sm-8 margin_bottom">
                    <p>{this.state.reportsTo.phone} &nbsp;</p>
                  </div>
                  <label className="col-sm-4">Fax</label>
                  <div className="col-sm-8 margin_bottom">
                    <p>{this.state.reportsTo.fax} &nbsp;</p>
                  </div>
                  <label className="col-sm-4">Email</label>
                  <div className="col-sm-8 margin_bottom">
                    <p>{this.state.reportsTo.email} &nbsp;</p>
                  </div>
                  <label className="col-sm-4">Title</label>
                  <div className="col-sm-8 margin_bottom">
                    <p>{this.state.reportsTo.title} &nbsp;</p>
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
              header={<Cell>Fax</Cell>}
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
        <button type="button" className="btn btn-default" data-toggle="modal" data-target="#addModal">
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
