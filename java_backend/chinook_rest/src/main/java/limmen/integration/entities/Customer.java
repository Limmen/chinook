package limmen.integration.entities;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * POJO that represents a Customer entity from the chinook database
 *
 * @author Kim Hammar on 2016-03-22.
 */
public class Customer {
    @NotNull
    private int customerId;
    @NotNull
    @Size(max = 40)
    private String firstName;
    @NotNull
    @Size(max = 20)
    private String lastName;
    @Size(max = 80)
    private String company;
    @Size(max = 70)
    private String address;
    @Size(max = 40)
    private String city;
    @Size(max = 40)
    private String state;
    @Size(max = 40)
    private String country;
    @Size(max = 10)
    private String postalCode;
    @Size(max = 24)
    private String phone;
    @Size(max = 24)
    private String fax;
    @NotNull
    @Size(max = 60)
    private String email;
    private int supportRepId;

    /**
     * Class constructor. Initializes an entity class.
     *
     * @param customerId id of the customer
     * @param firstName first name of the customer
     * @param lastName last name of the customer
     * @param company company of the customer
     * @param address address of the customer
     * @param city city of the customer
     * @param country country of the customer
     * @param state state of the customer
     * @param postalCode postalCode of the customer
     * @param phone phone of the customer
     * @param fax fax of the customer
     * @param email email of the customer
     * @param supportRepId id of employee that assisted
     */
    public Customer(int customerId, String firstName, String lastName, String company, String address, String city,
                    String country, String state, String postalCode, String phone, String fax, String email,
                    int supportRepId) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.company = company;
        this.address = address;
        this.city = city;
        this.country = country;
        this.state = state;
        this.postalCode = postalCode;
        this.phone = phone;
        this.fax = fax;
        this.email = email;
        this.supportRepId = supportRepId;
    }

    public Customer(){}

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCompany() {
        return company;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getPhone() {
        return phone;
    }

    public String getCountry() {
        return country;
    }

    public String getFax() {
        return fax;
    }

    public String getEmail() {
        return email;
    }

    public int getSupportRepId() {
        return supportRepId;
    }
}
