package com.kalbenutritionals.kalcaremobieTesting.ViewModel;

/**
 * Created by Robert on 22/03/2018.
 */

public class VMItemsPreview {
    String productCode;
    String productName;
    String qty;
    String qtyAvailable;
    String status;
    String partnerName;
    String partnerAddress;
    boolean bitAvailable;
    String partnerPhone;
    String txtIntFlag;

    public boolean isBitAvailable() {
        return bitAvailable;
    }

    public void setBitAvailable(boolean bitAvailable) {
        this.bitAvailable = bitAvailable;
    }

    public String getTxtIntFlag() {
        return txtIntFlag;
    }

    public void setTxtIntFlag(String txtIntFlag) {
        this.txtIntFlag = txtIntFlag;
    }

    public String getQtyAvailable() {
        return qtyAvailable;
    }

    public void setQtyAvailable(String qtyAvailable) {
        this.qtyAvailable = qtyAvailable;
    }

    public String getPartnerPhone() {
        return partnerPhone;
    }

    public void setPartnerPhone(String partnerPhone) {
        this.partnerPhone = partnerPhone;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPartnerName() {
        return partnerName;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }

    public String getPartnerAddress() {
        return partnerAddress;
    }

    public void setPartnerAddress(String partnerAddress) {
        this.partnerAddress = partnerAddress;
    }
}
