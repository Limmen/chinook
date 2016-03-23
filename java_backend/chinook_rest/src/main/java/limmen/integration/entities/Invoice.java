package limmen.integration.entities;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
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
