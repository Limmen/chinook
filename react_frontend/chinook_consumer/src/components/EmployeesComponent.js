/**
 * EmployeesComponent.
 *
 * Component that fetches a list of employees from the REST-API and renders it in a datatable.
 */

'use strict';

import React from 'react';
import {Table, Column, Cell} from 'fixed-data-table';
import $ from "jquery";
import Dimensions from 'react-dimensions'


require('styles//DataTable.css');
require('styles//Employees.css');

class EmployeesComponent extends React.Component {
  constructor(props, context) {
    super(props, context);

    this.state = {
      employees: [],
      url: "http://localhost:7777/resources/employees",
      employee: {},
      reportsto: {}
    }
  };

  loadEmployeesFromServer() {
    $.ajax({
      type: "GET",
      url: this.state.url,
      dataType: 'json',
      success: (employeesData) => {
        console.log(JSON.stringify(employeesData))
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
        console.log(JSON.stringify(employeeData))
        this.setState({reportsto: employeeData.employee})
      },
      error: (xhr, status, err) => {
        console.error(url, status, err.toString());
      }
    });
  }

  updateEmployee(index) {
    //this.setState({customer: index})
    this.setState({employee: this.state.employees[index].employee})
  }

  deleteEmployee(index) {

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
                <h4 className="modal-title">Edit Employee</h4>
              </div>
              <div className="modal-body row">
                <div>
                  <div className="form-group">
                    <label for="employee_id" className="col-sm-4">Id</label>
                    <div className="col-sm-8 margin_bottom">
                      <input type="text" className="form-control" id="employee_id"
                             placeholder="Employee Id"/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label for="employee_firstname" className="col-sm-4">First Name</label>
                    <div className="col-sm-8 margin_bottom">
                      <input type="text" className="form-control" id="employee_firstname"
                             placeholder="First Name"/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label for="employee_lastname" className="col-sm-4">Last name</label>
                    <div className="col-sm-8 margin_bottom">
                      <input type="text" className="form-control" id="employee_lastname"
                             placeholder="Last Name"/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label for="employee_reportsto" className="col-sm-4">Reports to employee id</label>
                    <div className="col-sm-8 margin_bottom">
                      <input type="text" className="form-control" id="employee_reportsto"
                             placeholder="Reports to"/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label for="employee_birthdate" className="col-sm-4">Birthdate</label>
                    <div className="col-sm-8 margin_bottom">
                      <input type="text" className="form-control" id="employee_birthdate"
                             placeholder="Birthdate"/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label for="employee_hiredate" className="col-sm-4">Hire date</label>
                    <div className="col-sm-8 margin_bottom">
                      <input type="text" className="form-control" id="employee_hiredate"
                             placeholder="Hire date"/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label for="employee_address" className="col-sm-4">Address</label>
                    <div className="col-sm-8 margin_bottom">
                      <input type="text" className="form-control" id="employee_address"
                             placeholder="Address"/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label for="employee_city" className="col-sm-4">City</label>
                    <div className="col-sm-8 margin_bottom">
                      <input type="text" className="form-control" id="employee_city"
                             placeholder="City"/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label for="employee_state" className="col-sm-4">State</label>
                    <div className="col-sm-8 margin_bottom">
                      <input type="text" className="form-control" id="employee_state"
                             placeholder="State"/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label for="employee_country" className="col-sm-4">Country</label>
                    <div className="col-sm-8 margin_bottom">
                      <input type="text" className="form-control" id="employee_country"
                             placeholder="Country"/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label for="employee_postalcode" className="col-sm-4">Postalcode</label>
                    <div className="col-sm-8 margin_bottom">
                      <input type="text" className="form-control" id="employee_postalcode"
                             placeholder="Postalcode"/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label for="employee_phone" className="col-sm-4">Phone</label>
                    <div className="col-sm-8 margin_bottom">
                      <input type="text" className="form-control" id="employee_phone"
                             placeholder="Phone"/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label for="employee_fax" className="col-sm-4">Fax</label>
                    <div className="col-sm-8 margin_bottom">
                      <input type="text" className="form-control" id="employee_fax"
                             placeholder="Fax"/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label for="employee_email" className="col-sm-4">Email</label>
                    <div className="col-sm-8 margin_bottom">
                      <input type="text" className="form-control" id="employee_email"
                             placeholder="Email"/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label for="employee_title" className="col-sm-4">Title</label>
                    <div className="col-sm-8 margin_bottom">
                      <input type="text" className="form-control" id="employee_title"
                             placeholder="Title"/>
                    </div>
                  </div>
                </div>
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
                <div>
                  <div className="form-group">
                    <label for="employee_id" className="col-sm-4">Id</label>
                    <div className="col-sm-8 margin_bottom">
                      <input type="text" className="form-control" id="employee_id"
                             value={this.state.employee.employeeId}/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label for="employee_firstname" className="col-sm-4">First Name</label>
                    <div className="col-sm-8 margin_bottom">
                      <input type="text" className="form-control" id="employee_firstname"
                             value={this.state.employee.firstName}/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label for="employee_lastname" className="col-sm-4">Last name</label>
                    <div className="col-sm-8 margin_bottom">
                      <input type="text" className="form-control" id="employee_lastname"
                             value={this.state.employee.lastName}/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label for="employee_reportsto" className="col-sm-4">Reports to employee id</label>
                    <div className="col-sm-8 margin_bottom">
                      <input type="text" className="form-control" id="employee_reportsto"
                             value={this.state.employee.reportsTo}/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label for="employee_birthdate" className="col-sm-4">Birthdate</label>
                    <div className="col-sm-8 margin_bottom">
                      <input type="text" className="form-control" id="employee_birthdate"
                             value={this.state.employee.birthDate}/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label for="employee_hiredate" className="col-sm-4">Hire date</label>
                    <div className="col-sm-8 margin_bottom">
                      <input type="text" className="form-control" id="employee_hiredate"
                             value={this.state.employee.hireDate}/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label for="employee_address" className="col-sm-4">Address</label>
                    <div className="col-sm-8 margin_bottom">
                      <input type="text" className="form-control" id="employee_address"
                             value={this.state.employee.address}/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label for="employee_city" className="col-sm-4">city</label>
                    <div className="col-sm-8 margin_bottom">
                      <input type="text" className="form-control" id="employee_city"
                             value={this.state.employee.city}/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label for="employee_state" className="col-sm-4">State</label>
                    <div className="col-sm-8 margin_bottom">
                      <input type="text" className="form-control" id="employee_state"
                             value={this.state.employee.state}/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label for="employee_country" className="col-sm-4">Country</label>
                    <div className="col-sm-8 margin_bottom">
                      <input type="text" className="form-control" id="employee_country"
                             value={this.state.employee.country}/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label for="employee_postalcode" className="col-sm-4">Postalcode</label>
                    <div className="col-sm-8 margin_bottom">
                      <input type="text" className="form-control" id="employee_postalcode"
                             value={this.state.employee.postalcode}/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label for="employee_phone" className="col-sm-4">Phone</label>
                    <div className="col-sm-8 margin_bottom">
                      <input type="text" className="form-control" id="employee_phone"
                             value={this.state.employee.phone}/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label for="employee_fax" className="col-sm-4">Fax</label>
                    <div className="col-sm-8 margin_bottom">
                      <input type="text" className="form-control" id="employee_fax"
                             value={this.state.employee.fax}/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label for="employee_email" className="col-sm-4">Email</label>
                    <div className="col-sm-8 margin_bottom">
                      <input type="text" className="form-control" id="employee_email"
                             value={this.state.employee.email}/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label for="employee_title" className="col-sm-4">Title</label>
                    <div className="col-sm-8 margin_bottom">
                      <input type="text" className="form-control" id="employee_title"
                             value={this.state.employee.title}/>
                    </div>
                  </div>
                </div>
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
                    <p>{this.state.reportsto.employeeId}</p>
                  </div>
                  <label className="col-sm-4">First Name</label>
                  <div className="col-sm-8 margin_bottom">
                    <p>{this.state.reportsto.firstName}</p>
                  </div>
                  <label className="col-sm-4">Last name</label>
                  <div className="col-sm-8 margin_bottom">
                    <p>{this.state.reportsto.lastName}</p>
                  </div>
                  <label className="col-sm-4">Reports to empoyee id</label>
                  <div className="col-sm-8 margin_bottom">
                    <p> {this.state.reportsto.reportsTo} </p>
                  </div>
                  <label className="col-sm-4">Birthdate</label>
                  <div className="col-sm-8 margin_bottom">
                    <p>{this.state.reportsto.birthDate}</p>
                  </div>
                  <label className="col-sm-4">hireDate</label>
                  <div className="col-sm-8 margin_bottom">
                    <p>{this.state.reportsto.hireDate}</p>
                  </div>
                  <label className="col-sm-4">Address</label>
                  <div className="col-sm-8 margin_bottom">
                    <p>{this.state.reportsto.address}</p>
                  </div>
                  <label className="col-sm-4">City</label>
                  <div className="col-sm-8 margin_bottom">
                    <p>{this.state.reportsto.city}</p>
                  </div>
                  <label className="col-sm-4">State</label>
                  <div className="col-sm-8 margin_bottom">
                    <p>{this.state.reportsto.state}</p>
                  </div>
                  <label className="col-sm-4">Country</label>
                  <div className="col-sm-8 margin_bottom">
                    <p>{this.state.reportsto.country}</p>
                  </div>
                  <label className="col-sm-4">Postalcode</label>
                  <div className="col-sm-8 margin_bottom">
                    <p>{this.state.reportsto.postalCode}</p>
                  </div>
                  <label className="col-sm-4">Phone</label>
                  <div className="col-sm-8 margin_bottom">
                    <p>{this.state.reportsto.phone}</p>
                  </div>
                  <label className="col-sm-4">Fax</label>
                  <div className="col-sm-8 margin_bottom">
                    <p>{this.state.reportsto.fax}</p>
                  </div>
                  <label className="col-sm-4">Email</label>
                  <div className="col-sm-8 margin_bottom">
                    <p>{this.state.reportsto.email}</p>
                  </div>
                  <label className="col-sm-4">Title</label>
                  <div className="col-sm-8 margin_bottom">
                    <p>{this.state.reportsto.title}</p>
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
                <button type="button" className="btn btn-default">Yes</button>
                <button type="button" className="btn btn-default">No</button>
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
<button type="button" className="btn btn-default btn-sm" data-toggle="modal" data-target="#deleteModal" onClick={this.deleteEmployee.bind(this, props.rowIndex)}>
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
