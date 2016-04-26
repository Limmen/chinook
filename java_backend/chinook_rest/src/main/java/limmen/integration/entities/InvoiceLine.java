package limmen.integration.entities;

import javax.validation.constraints.NotNull;

/**
 * POJO that represents a InvoiceLine entity from the chinook database
 *
 * @author Kim Hammar on 2016-03-22.
 */
public class InvoiceLine {
    @NotNull
    private int invoiceLineId;
    @NotNull
    private int invoiceId;
    @NotNull
    private int trackId;
    @NotNull
    private float unitPrice;
    @NotNull
    private int quantity;

    /**
     * Class constructor. Initializes an entity class
     *
     * @param invoiceLineId id of the invoiceline, unique
     * @param invoiceId id of the invoice on this line
     * @param trackId id of the track associated with the invoiceline
     * @param unitPrice price per unit
     * @param quantity number of units
     */
    public InvoiceLine(int invoiceLineId, int invoiceId, int trackId, float unitPrice, int quantity) {
        this.invoiceLineId = invoiceLineId;
        this.invoiceId = invoiceId;
        this.trackId = trackId;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    public InvoiceLine(){}

    public int getInvoiceLineId() {
        return invoiceLineId;
    }

    public void setInvoiceLineId(int invoiceLineId) {
        this.invoiceLineId = invoiceLineId;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public int getTrackId() {
        return trackId;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }
}
