package com.kalbenutritionals.kalcaremobieTesting.Common;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Robert on 28/02/2018.
 */
@DatabaseTable
public class clsDraft implements Serializable {
    @DatabaseField(id = true)
    private String guiId;
    @DatabaseField
    private String txtNoSO;
    @DatabaseField
    private String txtSOStatus;
    @DatabaseField
    private Integer intStatus;
    @DatabaseField
    private String txtSODate;
    @DatabaseField
    private Date dtSODate;
    @DatabaseField
    private String txtSoSource;
    @DatabaseField
    private Date dtDeliverySche;
    @DatabaseField
    private String txtAgentName;
    @DatabaseField
    private String txtPhoneNumber;
    @DatabaseField
    private String txtKontakID;
    @DatabaseField
    private String txtOrderLocation;
    @DatabaseField
    private boolean boolPassBy;
    @DatabaseField
    private boolean boolAttachCustomer;
    @DatabaseField
    private boolean boolWalkinCustomer;
    @DatabaseField
    private String txtContactID;
    @DatabaseField
    private String txtMemberID;
    @DatabaseField
    private String txtCustomerName;
    @DatabaseField
    private String txtProvince;
    @DatabaseField
    private String txtProvinceID;
    @DatabaseField
    private String txtKabKot;
    @DatabaseField
    private String txtKabKotID;
    @DatabaseField
    private String txtKecamatan;
    @DatabaseField
    private String txtKecamatanID;
    @DatabaseField
    private String txtKelurahan;
    @DatabaseField
    private String txtKelurahanID;
    @DatabaseField
    private String txtRegionID;
    @DatabaseField
    private String txtPostCode;
    @DatabaseField
    private String txtAddress;
    @DatabaseField
    private String txtRemarks;

    public String getTxtKontakID() {
        return txtKontakID;
    }

    public boolean isBoolWalkinCustomer() {
        return boolWalkinCustomer;
    }

    public void setBoolWalkinCustomer(boolean boolWalkinCustomer) {
        this.boolWalkinCustomer = boolWalkinCustomer;
    }

    public void setTxtKontakID(String txtKontakID) {
        this.txtKontakID = txtKontakID;
    }

    public static String txtPropertyIntStatus = "intStatus";

    public String getTxtPhoneNumber() {
        return txtPhoneNumber;
    }

    public void setTxtPhoneNumber(String txtPhoneNumber) {
        this.txtPhoneNumber = txtPhoneNumber;
    }

    public String getTxtProvinceID() {
        return txtProvinceID;
    }

    public void setTxtProvinceID(String txtProvinceID) {
        this.txtProvinceID = txtProvinceID;
    }

    public String getTxtKabKotID() {
        return txtKabKotID;
    }

    public void setTxtKabKotID(String txtKabKotID) {
        this.txtKabKotID = txtKabKotID;
    }

    public String getTxtKecamatanID() {
        return txtKecamatanID;
    }

    public void setTxtKecamatanID(String txtKecamatanID) {
        this.txtKecamatanID = txtKecamatanID;
    }

    public String getTxtKelurahan() {
        return txtKelurahan;
    }

    public void setTxtKelurahan(String txtKelurahan) {
        this.txtKelurahan = txtKelurahan;
    }

    public String getTxtKelurahanID() {
        return txtKelurahanID;
    }

    public void setTxtKelurahanID(String txtKelurahanID) {
        this.txtKelurahanID = txtKelurahanID;
    }

    public String getTxtRegionID() {
        return txtRegionID;
    }

    public void setTxtRegionID(String txtRegionID) {
        this.txtRegionID = txtRegionID;
    }

    public Integer getIntStatus() {
        return intStatus;
    }

    public void setIntStatus(Integer intStatus) {
        this.intStatus = intStatus;
    }

    public String getGuiId() {
        return guiId;
    }

    public void setGuiId(String guiId) {
        this.guiId = guiId;
    }

    public String getTxtNoSO() {
        return txtNoSO;
    }

    public void setTxtNoSO(String txtNoSO) {
        this.txtNoSO = txtNoSO;
    }

    public String getTxtSOStatus() {
        return txtSOStatus;
    }

    public void setTxtSOStatus(String txtSOStatus) {
        this.txtSOStatus = txtSOStatus;
    }

    public String getTxtSODate() {
        return txtSODate;
    }

    public void setTxtSODate(String txtSODate) {
        this.txtSODate = txtSODate;
    }

    public Date getDtSODate() {
        return dtSODate;
    }

    public void setDtSODate(Date dtSODate) {
        this.dtSODate = dtSODate;
    }

    public String getTxtSoSource() {
        return txtSoSource;
    }

    public void setTxtSoSource(String txtSoSource) {
        this.txtSoSource = txtSoSource;
    }

    public Date getDtDeliverySche() {
        return dtDeliverySche;
    }

    public void setDtDeliverySche(Date dtDeliverySche) {
        this.dtDeliverySche = dtDeliverySche;
    }

    public String getTxtAgentName() {
        return txtAgentName;
    }

    public void setTxtAgentName(String txtAgentName) {
        this.txtAgentName = txtAgentName;
    }

    public String getTxtOrderLocation() {
        return txtOrderLocation;
    }

    public void setTxtOrderLocation(String txtOrderLocation) {
        this.txtOrderLocation = txtOrderLocation;
    }

    public boolean isBoolPassBy() {
        return boolPassBy;
    }

    public void setBoolPassBy(boolean boolPassBy) {
        this.boolPassBy = boolPassBy;
    }

    public boolean isBoolAttachCustomer() {
        return boolAttachCustomer;
    }

    public void setBoolAttachCustomer(boolean boolAttachCustomer) {
        this.boolAttachCustomer = boolAttachCustomer;
    }

    public String getTxtContactID() {
        return txtContactID;
    }

    public void setTxtContactID(String txtContactID) {
        this.txtContactID = txtContactID;
    }

    public String getTxtMemberID() {
        return txtMemberID;
    }

    public void setTxtMemberID(String txtMemberID) {
        this.txtMemberID = txtMemberID;
    }

    public String getTxtCustomerName() {
        return txtCustomerName;
    }

    public void setTxtCustomerName(String txtCustomerName) {
        this.txtCustomerName = txtCustomerName;
    }

    public String getTxtProvince() {
        return txtProvince;
    }

    public void setTxtProvince(String txtProvince) {
        this.txtProvince = txtProvince;
    }

    public String getTxtKabKot() {
        return txtKabKot;
    }

    public void setTxtKabKot(String txtKabKot) {
        this.txtKabKot = txtKabKot;
    }

    public String getTxtKecamatan() {
        return txtKecamatan;
    }

    public void setTxtKecamatan(String txtKecamatan) {
        this.txtKecamatan = txtKecamatan;
    }

    public String getTxtPostCode() {
        return txtPostCode;
    }

    public void setTxtPostCode(String txtPostCode) {
        this.txtPostCode = txtPostCode;
    }

    public String getTxtAddress() {
        return txtAddress;
    }

    public void setTxtAddress(String txtAddress) {
        this.txtAddress = txtAddress;
    }

    public String getTxtRemarks() {
        return txtRemarks;
    }

    public void setTxtRemarks(String txtRemarks) {
        this.txtRemarks = txtRemarks;
    }
}
