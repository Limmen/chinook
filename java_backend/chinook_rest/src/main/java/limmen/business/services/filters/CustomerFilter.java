package limmen.business.services.filters;

import limmen.integration.entities.Customer;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class for implementing filtering and sorting functionality for the Customer-Resource.
 *
 * @author Kim Hammar on 2016-04-25.
 */
public class CustomerFilter {

    private String sort;
    private String customerId;
    private String firstName;
    private String lastName;
    private String company;
    private String address;
    private String city;
    private String state;
    private String country;
    private String postalCode;
    private String phone;
    private String fax;
    private String email;
    private String supportRepId;

    /**
     * Default constructor
     */
    public CustomerFilter() {
    }

    /**
     * Method to filter a list of customers.
     *
     * @param customers list to filter
     * @return filtered list
     */
    public List<Customer> filter(List<Customer> customers) {
        if (firstName != null)
            customers = customers.stream().filter(customer -> customer.getFirstName().equals(firstName)).collect(Collectors.toList());
        if (customerId != null)
            customers = customers.stream().filter(customer -> customer.getCustomerId() == Integer.parseInt(customerId)).collect(Collectors.toList());
        if (lastName != null)
            customers = customers.stream().filter(customer -> customer.getLastName().equals(lastName)).collect(Collectors.toList());
        if (company != null)
            customers = customers.stream().filter(customer -> customer.getCompany().equals(company)).collect(Collectors.toList());
        if (address != null)
            customers = customers.stream().filter(customer -> customer.getAddress().equals(address)).collect(Collectors.toList());
        if (city != null)
            customers = customers.stream().filter(customer -> customer.getCity().equals(city)).collect(Collectors.toList());
        if (state != null)
            customers = customers.stream().filter(customer -> customer.getState().equals(state)).collect(Collectors.toList());
        if (country != null)
            customers = customers.stream().filter(customer -> customer.getCountry().equals(country)).collect(Collectors.toList());
        if (postalCode != null)
            customers = customers.stream().filter(customer -> customer.getPostalCode().equals(postalCode)).collect(Collectors.toList());
        if (phone != null)
            customers = customers.stream().filter(customer -> customer.getPhone().equals(phone)).collect(Collectors.toList());
        if (fax != null)
            customers = customers.stream().filter(customer -> customer.getFax().equals(fax)).collect(Collectors.toList());
        if (email != null)
            customers = customers.stream().filter(customer -> customer.getEmail().equals(email)).collect(Collectors.toList());
        if (supportRepId != null)
            customers = customers.stream().filter(customer -> customer.getSupportRepId() == Integer.parseInt(supportRepId)).collect(Collectors.toList());
        return customers;
    }

    /**
     * Method to sort a list of customers.
     *
     * @param customers list to sort
     * @return sorted list
     */
    public List<Customer> sort(List<Customer> customers) {
        if(sort == null)
            return customers;
        String order = sort.substring(0, 1);
        String property = sort.substring(1, sort.length());
        Comparator<Customer> comparator = null;
        if (property.equals("customerId")) {
            comparator = (customer1, customer2) ->
            {
                if (customer1.getCustomerId() > customer2.getCustomerId())
                    return 1;
                else if (customer1.getCustomerId() < customer2.getCustomerId())
                    return -1;
                return 0;
            };
        }
        if (property.equals("firstName")) {
            comparator = (customer1, customer2) ->
            {
                if (customer1.getFirstName().compareTo(customer2.getFirstName()) > 0)
                    return 1;
                else if (customer1.getFirstName().compareTo(customer2.getFirstName()) < 0)
                    return -1;
                return 0;
            };
        }
        if (property.equals("lastName")) {
            comparator = (customer1, customer2) ->
            {
                if (customer1.getLastName().compareTo(customer2.getLastName()) > 0)
                    return 1;
                else if (customer1.getLastName().compareTo(customer2.getLastName()) < 0)
                    return -1;
                return 0;
            };
        }
        if (property.equals("company")) {
            comparator = (customer1, customer2) ->
            {
                if (customer1.getCompany().compareTo(customer2.getCompany()) > 0)
                    return 1;
                else if (customer1.getCompany().compareTo(customer2.getCompany()) < 0)
                    return -1;
                return 0;
            };
        }
        if (property.equals("address")) {
            comparator = (customer1, customer2) ->
            {
                if (customer1.getAddress().compareTo(customer2.getAddress()) > 0)
                    return 1;
                else if (customer1.getAddress().compareTo(customer2.getAddress()) < 0)
                    return -1;
                return 0;
            };
        }
        if (property.equals("city")) {
            comparator = (customer1, customer2) ->
            {
                if (customer1.getCity().compareTo(customer2.getCity()) > 0)
                    return 1;
                else if (customer1.getCity().compareTo(customer2.getCity()) < 0)
                    return -1;
                return 0;
            };
        }
        if (property.equals("state")) {
            comparator = (customer1, customer2) ->
            {
                if (customer1.getState().compareTo(customer2.getState()) > 0)
                    return 1;
                else if (customer1.getState().compareTo(customer2.getState()) < 0)
                    return -1;
                return 0;
            };
        }
        if (property.equals("country")) {
            comparator = (customer1, customer2) ->
            {
                if (customer1.getCountry().compareTo(customer2.getCountry()) > 0)
                    return 1;
                else if (customer1.getCountry().compareTo(customer2.getCountry()) < 0)
                    return -1;
                return 0;
            };
        }
        if (property.equals("postalCode")) {
            comparator = (customer1, customer2) ->
            {
                if (customer1.getPostalCode().compareTo(customer2.getPostalCode()) > 0)
                    return 1;
                else if (customer1.getPostalCode().compareTo(customer2.getPostalCode()) < 0)
                    return -1;
                return 0;
            };
        }
        if (property.equals("phone")) {
            comparator = (customer1, customer2) ->
            {
                if (customer1.getPhone().compareTo(customer2.getPhone()) > 0)
                    return 1;
                else if (customer1.getPhone().compareTo(customer2.getPhone()) < 0)
                    return -1;
                return 0;
            };
        }
        if (property.equals("fax")) {
            comparator = (customer1, customer2) ->
            {
                if (customer1.getFax().compareTo(customer2.getFax()) > 0)
                    return 1;
                else if (customer1.getFax().compareTo(customer2.getFax()) < 0)
                    return -1;
                return 0;
            };
        }
        if (property.equals("email")) {
            comparator = (customer1, customer2) ->
            {
                if (customer1.getFirstName().compareTo(customer2.getEmail()) > 0)
                    return 1;
                else if (customer1.getFirstName().compareTo(customer2.getEmail()) < 0)
                    return -1;
                return 0;
            };
        }
        if (property.equals("supportRepId")) {
            comparator = (customer1, customer2) ->
            {
                if (customer1.getSupportRepId() > customer2.getSupportRepId())
                    return 1;
                else if (customer1.getSupportRepId() < customer2.getSupportRepId())
                    return -1;
                return 0;
            };
        }
        Collections.sort(customers, comparator);
        if (order.equals("-")) {
            Collections.reverse(customers);
        }
        return customers;
    }


    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getSort() {
        return sort;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
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

    public String getSupportRepId() {
        return supportRepId;
    }

    public void setSupportRepId(String supportRepId) {
        this.supportRepId = supportRepId;
    }
}
