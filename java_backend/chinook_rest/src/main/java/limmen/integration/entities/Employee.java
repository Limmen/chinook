package limmen.integration.entities;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * POJO that represents a Employee entity from the chinook database
 *
 * @author Kim Hammar on 2016-03-22.
 */
public class Employee {
    @NotNull
    private int employeeId;
    @NotNull
    @Size(max = 20)
    private String lastName;
    @NotNull
    @Size(max = 20)
    private String firstName;
    @Size(max = 20)
    private String title;
    private int reportsTo;
    private Date birthDate;
    private Date hireDate;
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
    @Size(max = 60)
    private String email;


    /**
     * Class constructor. Initializes an immutable entity class
     *
     * @param employeeId id of the employee
     * @param lastName lastname of the employee
     * @param firstName firstname of the employee
     * @param title title of the employee
     * @param reportsTo other employee or self that this employee reports to
     * @param birthDate birtdate of the employee
     * @param hireDate date of hirement
     * @param address address of the employee
     * @param city city where the employee lives
     * @param state state where the employee lives
     * @param country country where the employee lives
     * @param postalCode postalcode where the employee lives
     * @param phone phonenumber of the employee
     * @param fax fax of the employee
     * @param email email of the employee
     */
    public Employee(int employeeId, String lastName, String firstName, String title, int reportsTo, Date birthDate,
                    Date hireDate, String address, String city, String state, String country, String postalCode,
                    String phone, String fax, String email) {
        this.employeeId = employeeId;
        this.lastName = lastName;
        this.firstName = firstName;
        this.title = title;
        this.reportsTo = reportsTo;
        this.birthDate = birthDate;
        this.hireDate = hireDate;
        this.address = address;
        this.city = city;
        this.state = state;
        this.country = country;
        this.postalCode = postalCode;
        this.phone = phone;
        this.fax = fax;
        this.email = email;
    }

    public Employee(){}

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getLastName() {
        return lastName;
    }

    public int getReportsTo() {
        return reportsTo;
    }

    public String getTitle() {
        return title;
    }

    public String getFirstName() {
        return firstName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public Date getHireDate() {
        return hireDate;
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

    public String getCountry() {
        return country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getFax() {
        return fax;
    }
}
