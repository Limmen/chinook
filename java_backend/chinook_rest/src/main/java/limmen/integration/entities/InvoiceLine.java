package limmen.integration.entities;

import javax.validation.constraints.NotNull;

/**
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
