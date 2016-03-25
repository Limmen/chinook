package limmen.integration.entities;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * POJO that represents a Invoice entity from the chinook database
 *
 * @author Kim Hammar on 2016-03-22.
 */
public class Invoice {
    @NotNull
    private int invoiceId;
    @NotNull
    private int customerId;
    @NotNull
    private Date invoiceDate;
    @Size(max = 70)
    private String billingAddress;
    @Size(max = 40)
    private String billingCity;
    @Size(max = 40)
    private String billingState;
    @Size(max = 40)
    private String billingCountry;
    @Size(max = 10)
    private String billingPostalCode;
    @NotNull
    private float total;

    /**
     * Class constructor. Initializes an immutable entity class.
     *
     * @param invoiceId if of the invoice, unique.
     * @param customerId id of the customer who is associated with the invoice
     * @param invoiceDate date when the invocie was issued
     * @param billingAddress address to send the bill
     * @param billingCity city to send the bill
     * @param billingState state to send the bill
     * @param billingCountry country to send the bill
     * @param billingPostalCode postal code to send the bill
     * @param total amount to bill
     */
    public Invoice(int invoiceId, int customerId, Date invoiceDate, String billingAddress, String billingCity,
                   String billingState, String billingCountry, String billingPostalCode, float total) {
        this.invoiceId = invoiceId;
        this.customerId = customerId;
        this.invoiceDate = invoiceDate;
        this.billingAddress = billingAddress;
        this.billingCity = billingCity;
        this.billingState = billingState;
        this.billingCountry = billingCountry;
        this.billingPostalCode = billingPostalCode;
        this.total = total;
    }

    public Invoice(){}

    public int getInvoiceId() {
        return invoiceId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public String getBillingCity() {
        return billingCity;
    }

    public String getBillingCountry() {
        return billingCountry;
    }

    public String getBillingPostalCode() {
        return billingPostalCode;
    }

    public float getTotal() {
        return total;
    }
}
