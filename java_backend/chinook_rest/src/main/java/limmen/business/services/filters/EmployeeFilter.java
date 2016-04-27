package limmen.business.services.filters;

import limmen.integration.entities.Employee;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class for implementing filtering and sorting functionality for the Employee-Resource.
 *
 * @author Kim Hammar on 2016-04-25.
 */
public class EmployeeFilter {

    private String sort;
    private String employeeId;
    private String lastName;
    private String firstName;
    private String title;
    private String reportsTo;
    private Date birthDate;
    private Date hireDate;
    private String address;
    private String city;
    private String state;
    private String country;
    private String postalCode;
    private String phone;
    private String fax;
    private String email;

    /**
     * Default constructor
     */
    public EmployeeFilter() {
    }

    /**
     * Method to filter a list of employees.
     *
     * @param employees list to filter
     * @return filtered list
     */
    public List<Employee> filter(List<Employee> employees) {
        if (firstName != null)
            employees = employees.stream().filter(employee -> {if(employee.getFirstName() != null)
                return employee.getFirstName().equals(firstName); else return false;}).collect(Collectors.toList());
        if (employeeId != null)
            employees = employees.stream().filter(employee -> employee.getEmployeeId() == Integer.parseInt(employeeId)).collect(Collectors.toList());
        if (lastName != null)
            employees = employees.stream().filter(employee -> {if(employee.getLastName() != null)
                return employee.getLastName().equals(lastName); else return false; }).collect(Collectors.toList());
        if (title != null)
            employees = employees.stream().filter(employee -> {if(employee.getTitle() != null)
                return employee.getTitle().equals(title); else return false;}).collect(Collectors.toList());
        if (reportsTo != null)
            employees = employees.stream().filter(employee -> employee.getReportsTo() == Integer.parseInt(reportsTo)).collect(Collectors.toList());
        if (birthDate != null)
            employees = employees.stream().filter(employee -> {if(employee.getBirthDate() != null)
                return employee.getBirthDate().equals(birthDate); else return false;}).collect(Collectors.toList());
        if (hireDate != null)
            employees = employees.stream().filter(employee -> {if(employee.getHireDate() != null)
                return employee.getHireDate().equals(hireDate); else return false;}).collect(Collectors.toList());
        if (address != null)
            employees = employees.stream().filter(employee -> {if(employee.getAddress() != null)
                return employee.getAddress().equals(address); else return false;}).collect(Collectors.toList());
        if (city != null)
            employees = employees.stream().filter(employee -> {if(employee.getCity() != null)
                return employee.getCity().equals(city); else return false;}).collect(Collectors.toList());
        if (state != null)
            employees = employees.stream().filter(employee -> {if(employee.getState() != null)
                return employee.getState().equals(state); else return false; }).collect(Collectors.toList());
        if (country != null)
            employees = employees.stream().filter(employee -> {if(employee.getCountry() != null)
                return employee.getCountry().equals(country); else return false;}).collect(Collectors.toList());
        if (postalCode != null)
            employees = employees.stream().filter(employee -> {if(employee.getPostalCode() != null)
                return employee.getPostalCode().equals(postalCode); else return false;}).collect(Collectors.toList());
        if (phone != null)
            employees = employees.stream().filter(employee -> {if(employee.getPhone() != null)
                return employee.getPhone().equals(phone); else return false;}).collect(Collectors.toList());
        if (fax != null)
            employees = employees.stream().filter(employee -> {if(employee.getFax() != null)
                return employee.getFax().equals(fax); else return false;}).collect(Collectors.toList());
        if (email != null)
            employees = employees.stream().filter(employee -> {if(employee.getEmail() != null)
                return employee.getEmail().equals(email); else return false;}).collect(Collectors.toList());
        return employees;
    }

    /**
     * Method to sort a list of employees.
     *
     * @param employees list to sort
     * @return sorted list
     */
    public List<Employee> sort(List<Employee> employees) {
        if(sort == null)
            return employees;
        String order = sort.substring(0, 1);
        String property = sort.substring(1, sort.length());
        Comparator<Employee> comparator = null;
        if (property.equals("employeeId")) {
            comparator = (employee1, employee2) ->
            {
                if (employee1.getEmployeeId() > employee2.getEmployeeId())
                    return 1;
                else if (employee1.getEmployeeId() < employee2.getEmployeeId())
                    return -1;
                return 0;
            };
        }
        if (property.equals("firstName")) {
            comparator = (employee1, employee2) ->
            {
                if(employee1.getFirstName() == null && employee2.getFirstName() == null)
                    return 0;
                if(employee1.getFirstName() == null)
                    return -1;
                if(employee2.getFirstName() == null)
                    return 1;
                if (employee1.getFirstName().compareTo(employee2.getFirstName()) > 0)
                    return 1;
                else if (employee1.getFirstName().compareTo(employee2.getFirstName()) < 0)
                    return -1;
                return 0;
            };
        }
        if (property.equals("lastName")) {
            comparator = (employee1, employee2) ->
            {
                if(employee1.getLastName() == null && employee2.getLastName() == null)
                    return 0;
                if(employee1.getLastName() == null)
                    return -1;
                if(employee2.getLastName() == null)
                    return 1;
                if (employee1.getLastName().compareTo(employee2.getLastName()) > 0)
                    return 1;
                else if (employee1.getLastName().compareTo(employee2.getLastName()) < 0)
                    return -1;
                return 0;
            };
        }
        if (property.equals("title")) {
            comparator = (employee1, employee2) ->
            {
                if(employee1.getTitle() == null && employee2.getTitle() == null)
                    return 0;
                if(employee1.getTitle() == null)
                    return -1;
                if(employee2.getTitle() == null)
                    return 1;
                if (employee1.getTitle().compareTo(employee2.getTitle()) > 0)
                    return 1;
                else if (employee1.getTitle().compareTo(employee2.getTitle()) < 0)
                    return -1;
                return 0;
            };
        }
        if (property.equals("reportsTo")) {
            comparator = (employee1, employee2) ->
            {
                if (employee1.getReportsTo() > employee2.getReportsTo())
                    return 1;
                else if (employee1.getReportsTo() < employee2.getReportsTo())
                    return -1;
                return 0;
            };
        }
        if (property.equals("birthDate")) {
            comparator = (employee1, employee2) ->
            {
                if(employee1.getBirthDate() == null && employee2.getBirthDate() == null)
                    return 0;
                if(employee1.getBirthDate() == null)
                    return -1;
                if(employee2.getBirthDate() == null)
                    return 1;
                if (employee1.getBirthDate().compareTo(employee2.getBirthDate()) > 0)
                    return 1;
                else if (employee1.getBirthDate().compareTo(employee2.getBirthDate()) < 0)
                    return -1;
                return 0;
            };
        }
        if (property.equals("hireDate")) {
            comparator = (employee1, employee2) ->
            {
                if(employee1.getHireDate() == null && employee2.getHireDate() == null)
                    return 0;
                if(employee1.getHireDate() == null)
                    return -1;
                if(employee2.getHireDate() == null)
                    return 1;
                if (employee1.getHireDate().compareTo(employee2.getHireDate()) > 0)
                    return 1;
                else if (employee1.getHireDate().compareTo(employee2.getHireDate()) < 0)
                    return -1;
                return 0;
            };
        }
        if (property.equals("address")) {
            comparator = (employee1, employee2) ->
            {
                if(employee1.getAddress() == null && employee2.getAddress() == null)
                    return 0;
                if(employee1.getAddress() == null)
                    return -1;
                if(employee2.getAddress() == null)
                    return 1;
                if (employee1.getAddress().compareTo(employee2.getAddress()) > 0)
                    return 1;
                else if (employee1.getAddress().compareTo(employee2.getAddress()) < 0)
                    return -1;
                return 0;
            };
        }
        if (property.equals("city")) {
            comparator = (employee1, employee2) ->
            {
                if(employee1.getCity() == null && employee2.getCity() == null)
                    return 0;
                if(employee1.getCity() == null)
                    return -1;
                if(employee2.getCity() == null)
                    return 1;
                if (employee1.getCity().compareTo(employee2.getCity()) > 0)
                    return 1;
                else if (employee1.getCity().compareTo(employee2.getCity()) < 0)
                    return -1;
                return 0;
            };
        }
        if (property.equals("state")) {
            comparator = (employee1, employee2) ->
            {
                if(employee1.getState() == null && employee2.getState() == null)
                    return 0;
                if(employee1.getState() == null)
                    return -1;
                if(employee2.getState() == null)
                    return 1;
                if (employee1.getState().compareTo(employee2.getState()) > 0)
                    return 1;
                else if (employee1.getState().compareTo(employee2.getState()) < 0)
                    return -1;
                return 0;
            };
        }
        if (property.equals("country")) {
            comparator = (employee1, employee2) ->
            {
                if(employee1.getCountry() == null && employee2.getCountry() == null)
                    return 0;
                if(employee1.getCountry() == null)
                    return -1;
                if(employee2.getCountry() == null)
                    return 1;
                if (employee1.getCountry().compareTo(employee2.getCountry()) > 0)
                    return 1;
                else if (employee1.getCountry().compareTo(employee2.getCountry()) < 0)
                    return -1;
                return 0;
            };
        }
        if (property.equals("postalCode")) {
            comparator = (employee1, employee2) ->
            {
                if(employee1.getPostalCode() == null && employee2.getPostalCode() == null)
                    return 0;
                if(employee1.getPostalCode() == null)
                    return -1;
                if(employee2.getPostalCode() == null)
                    return 1;
                if (employee1.getPostalCode().compareTo(employee2.getPostalCode()) > 0)
                    return 1;
                else if (employee1.getPostalCode().compareTo(employee2.getPostalCode()) < 0)
                    return -1;
                return 0;
            };
        }
        if (property.equals("phone")) {
            comparator = (employee1, employee2) ->
            {
                if(employee1.getPhone() == null && employee2.getPhone() == null)
                    return 0;
                if(employee1.getPhone() == null)
                    return -1;
                if(employee2.getPhone() == null)
                    return 1;
                if (employee1.getPhone().compareTo(employee2.getPhone()) > 0)
                    return 1;
                else if (employee1.getPhone().compareTo(employee2.getPhone()) < 0)
                    return -1;
                return 0;
            };
        }
        if (property.equals("fax")) {
            comparator = (employee1, employee2) ->
            {
                if(employee1.getFax() == null && employee2.getFax() == null)
                    return 0;
                if(employee1.getFax() == null)
                    return -1;
                if(employee2.getFax() == null)
                    return 1;
                if (employee1.getFax().compareTo(employee2.getFax()) > 0)
                    return 1;
                else if (employee1.getFax().compareTo(employee2.getFax()) < 0)
                    return -1;
                return 0;
            };
        }
        if (property.equals("email")) {
            comparator = (employee1, employee2) ->
            {
                if(employee1.getEmail() == null && employee2.getEmail() == null)
                    return 0;
                if(employee1.getEmail() == null)
                    return -1;
                if(employee2.getEmail() == null)
                    return 1;
                if (employee1.getEmail().compareTo(employee2.getEmail()) > 0)
                    return 1;
                else if (employee1.getEmail().compareTo(employee2.getEmail()) < 0)
                    return -1;
                return 0;
            };
        }
        Collections.sort(employees, comparator);
        if (order.equals("-")) {
            Collections.reverse(employees);
        }
        return employees;
    }


    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getSort() {
        return sort;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        title = title;
    }

    public String getReportsTo() {
        return reportsTo;
    }

    public void setReportsTo(String reportsTo) {
        this.reportsTo = reportsTo;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
