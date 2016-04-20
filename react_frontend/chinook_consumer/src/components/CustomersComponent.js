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
      url: "http://localhost:7777/resources/customers",
      customer: {},
      employee: {}
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
        console.error(this.state.url, status, err.toString());
      }
    });
  }

  updateCustomer(index) {
    //this.setState({customer: index})
    this.setState({customer: this.state.customers[index].customer})
  }

  deleteCustomer(index) {

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
                    <label className="col-sm-2" for="customer_id">Id</label>
                    <div className="col-sm-10 margin_bottom">
                      <input type="text" className="form-control" id="customer_id"
                             value={this.state.customer.customerId}/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-2" for="customer_firstname">First Name</label>
                    <div className="col-sm-10 margin_bottom">
                      <input type="text" className="form-control" id="customer_firstname"
                             value={this.state.customer.firstName}/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-2" for="customer_lastname">Last name</label>
                    <div className="col-sm-10 margin_bottom">
                      <input type="text" className="form-control" id="customer_lastname"
                             value={this.state.customer.lastName}/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-2" for="customer_company">Company</label>
                    <div className="col-sm-10 margin_bottom">
                      <input type="text" className="form-control" id="customer_company"
                             value={this.state.customer.company}/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-2" for="customer_address">Address</label>
                    <div className="col-sm-10 margin_bottom">
                      <input type="text" className="form-control" id="customer_address"
                             value={this.state.customer.address}/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-2" for="customer_city">City</label>
                    <div className="col-sm-10 margin_bottom">
                      <input type="text" className="form-control" id="customer_city" value={this.state.customer.city}/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-2" for="customer_state">State</label>
                    <div className="col-sm-10 margin_bottom">
                      <input type="text" className="form-control" id="customer_state"
                             value={this.state.customer.state}/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-2" for="customer_country">Country</label>
                    <div className="col-sm-10 margin_bottom">
                      <input type="text" className="form-control" id="customer_country"
                             value={this.state.customer.country}/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-2" for="customer_phone">Phone</label>
                    <div className="col-sm-10 margin_bottom">
                      <input type="text" className="form-control" id="customer_phone"
                             value={this.state.customer.phone}/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-2" for="customer_fax">Fax</label>
                    <div className="col-sm-10 margin_bottom">
                      <input type="text" className="form-control" id="customer_fax" value={this.state.customer.fax}/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-2" for="customer_email">Email</label>
                    <div className="col-sm-10 margin_bottom">
                      <input type="text" className="form-control" id="customer_email"
                             value={this.state.customer.email}/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-2" for="customer_supportRepId">Supported by employee</label>
                    <div className="col-sm-10 margin_bottom">
                      <input type="text" className="form-control" id="customer_supportedRepId"
                             value={this.state.customer.supportRepId}/>
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
                    <label className="col-sm-2" for="customer_id">Id</label>
                    <div className="col-sm-10 margin_bottom">
                      <input type="text" className="form-control" id="customer_id" placeholder="id"/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-2" for="customer_firstname">First Name</label>
                    <div className="col-sm-10 margin_bottom">
                      <input type="text" className="form-control" id="customer_firstname" placeholder="first name"/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-2" for="customer_lastname">Last name</label>
                    <div className="col-sm-10 margin_bottom">
                      <input type="text" className="form-control" id="customer_lastname" placeholder="last name"/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-2" for="customer_company">Company</label>
                    <div className="col-sm-10 margin_bottom">
                      <input type="text" className="form-control" id="customer_company" placeholder="company"/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-2" for="customer_address">Address</label>
                    <div className="col-sm-10 margin_bottom">
                      <input type="text" className="form-control" id="customer_address" placeholder="address"/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-2" for="customer_city">City</label>
                    <div className="col-sm-10 margin_bottom">
                      <input type="text" className="form-control" id="customer_city" placeholder="city"/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-2" for="customer_state">State</label>
                    <div className="col-sm-10 margin_bottom">
                      <input type="text" className="form-control" id="customer_state" placeholder="state"/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-2" for="customer_country">Country</label>
                    <div className="col-sm-10 margin_bottom">
                      <input type="text" className="form-control" id="customer_country" placeholder="country"/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-2" for="customer_phone">Phone</label>
                    <div className="col-sm-10 margin_bottom">
                      <input type="text" className="form-control" id="customer_phone" placeholder="phone"/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-2" for="customer_fax">Fax</label>
                    <div className="col-sm-10 margin_bottom">
                      <input type="text" className="form-control" id="customer_fax" placeholder="fax"/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-2" for="customer_email">Email</label>
                    <div className="col-sm-10 margin_bottom">
                      <input type="text" className="form-control" id="customer_email" placeholder="email"/>
                    </div>
                  </div>
                  <div className="form-group">
                    <label className="col-sm-2" for="customer_supportRepId">Supported by employee</label>
                    <div className="col-sm-10 margin_bottom">
                      <input type="text" className="form-control" id="customer_supportedRepId"
                             placeholder="employee id"/>
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
                  <div className="col-sm-6 margin_bottom">
                    <p>{this.state.employee.employeeId}</p>
                  </div>
                  <label className="col-sm-4">First Name</label>
                  <div className="col-sm-6 margin_bottom">
                    <p>{this.state.employee.firstName}</p>
                  </div>
                  <label className="col-sm-4">Last name</label>
                  <div className="col-sm-6 margin_bottom">
                    <p>{this.state.employee.lastName}</p>
                  </div>
                  <label className="col-sm-4">Reports to empoyee id</label>
                  <div className="col-sm-6 margin_bottom">
                    <p> {this.state.employee.reportsTo} </p>
                  </div>
                  <label className="col-sm-4">Birthdate</label>
                  <div className="col-sm-6 margin_bottom">
                    <p>{this.state.employee.birthDate}</p>
                  </div>
                  <label className="col-sm-4">hireDate</label>
                  <div className="col-sm-6 margin_bottom">
                    <p>{this.state.employee.hireDate}</p>
                  </div>
                  <label className="col-sm-4">Address</label>
                  <div className="col-sm-6 margin_bottom">
                    <p>{this.state.employee.address}</p>
                  </div>
                  <label className="col-sm-4">City</label>
                  <div className="col-sm-6 margin_bottom">
                    <p>{this.state.employee.city}</p>
                  </div>
                  <label className="col-sm-4">State</label>
                  <div className="col-sm-6 margin_bottom">
                    <p>{this.state.employee.state}</p>
                  </div>
                  <label className="col-sm-4">Country</label>
                  <div className="col-sm-6 margin_bottom">
                    <p>{this.state.employee.country}</p>
                  </div>
                  <label className="col-sm-4">Postalcode</label>
                  <div className="col-sm-6 margin_bottom">
                    <p>{this.state.employee.postalCode}</p>
                  </div>
                  <label className="col-sm-4">Phone</label>
                  <div className="col-sm-6 margin_bottom">
                    <p>{this.state.employee.phone}</p>
                  </div>
                  <label className="col-sm-4">Fax</label>
                  <div className="col-sm-6 margin_bottom">
                    <p>{this.state.employee.fax}</p>
                  </div>
                  <label className="col-sm-4">Email</label>
                  <div className="col-sm-6 margin_bottom">
                    <p>{this.state.employee.email}</p>
                  </div>
                  <label className="col-sm-4">Title</label>
                  <div className="col-sm-6 margin_bottom">
                    <p>{this.state.employee.title}</p>
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
<button type="button" className="btn btn-default btn-sm" data-toggle="modal" data-target="#deleteModal" onClick={this.deleteCustomer.bind(this, props.rowIndex)}>
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
