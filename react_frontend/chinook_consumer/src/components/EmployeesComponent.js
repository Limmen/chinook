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
      url: "http://localhost:7777/resources/employees"
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

  componentDidMount() {
    this.loadEmployeesFromServer();
  }
  render() {
    return (
      <div className="employees-component">
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
          </Table>
        </div>
      </div>
    );
  }
}

EmployeesComponent.displayName = 'EmployeesComponent';

EmployeesComponent.propTypes = {};
EmployeesComponent.defaultProps = {};

export default Dimensions()(EmployeesComponent);
