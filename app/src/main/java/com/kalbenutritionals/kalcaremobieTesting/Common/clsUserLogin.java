package com.kalbenutritionals.kalcaremobieTesting.Common;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.Date;

@DatabaseTable
public class clsUserLogin implements Serializable {
    @DatabaseField(id = true,columnName = "txtGuiID")
    public String txtGuiId;
    @DatabaseField(columnName = "idUser")
    public String idUser;
    @DatabaseField(columnName = "grpUser")
    public String grpUser;
    @DatabaseField(columnName = "txtSumberDataID")
    public String txtSumberDataID;
    @DatabaseField(columnName = "txtCodeId")
    public String txtCodeId;
    @DatabaseField(columnName = "nmUser")
    public String nmUser;
    @DatabaseField(columnName = "txtNamaInstitusi")
    public String txtNamaInstitusi;
    @DatabaseField(columnName = "txtTeleName")
    public String txtTeleName;
    @DatabaseField(columnName = "txtTeleID")
    public String txtTeleID;
    @DatabaseField(columnName = "txtTeleID_CRM")
    public String txtTeleID_CRM;
    @DatabaseField(columnName = "txtPhoneNo")
    public String txtPhoneNo;
    @DatabaseField(columnName = "txtSubinventory")
    public String txtSubinventory;
    @DatabaseField(columnName = "txtintScheduleDevilery")
    public String txtintScheduleDevilery;
    @DatabaseField(columnName = "intSchedel")
    public Integer intSchedel;
    @DatabaseField(columnName = "txtKontakID")
    public String txtKontakID;
    @DatabaseField(dataType = DataType.DATE_LONG, canBeNull = false, useGetSet = true, columnName = "dtLogin")
    private Date dtLogin;

    public String getTxtTeleID() {
        return txtTeleID;
    }

    public void setTxtTeleID(String txtTeleID) {
        this.txtTeleID = txtTeleID;
    }

    public String getTxtTeleID_CRM() {
        return txtTeleID_CRM;
    }

    public void setTxtTeleID_CRM(String txtTeleID_CRM) {
        this.txtTeleID_CRM = txtTeleID_CRM;
    }

    public String getTxtPhoneNo() {
        return txtPhoneNo;
    }

    public void setTxtPhoneNo(String txtPhoneNo) {
        this.txtPhoneNo = txtPhoneNo;
    }

    public String getTxtSubinventory() {
        return txtSubinventory;
    }

    public void setTxtSubinventory(String txtSubinventory) {
        this.txtSubinventory = txtSubinventory;
    }

    public String getTxtintScheduleDevilery() {
        return txtintScheduleDevilery;
    }

    public void setTxtintScheduleDevilery(String txtintScheduleDevilery) {
        this.txtintScheduleDevilery = txtintScheduleDevilery;
    }

    public String getTxtGuiId() {
        return txtGuiId;
    }

    public String getTxtCodeId() {
        return txtCodeId;
    }

    public String getTxtKontakID() {
        return txtKontakID;
    }

    public void setTxtKontakID(String txtKontakID) {
        this.txtKontakID = txtKontakID;
    }

    public void setTxtCodeId(String txtCodeId) {
        this.txtCodeId = txtCodeId;
    }

    public void setTxtGuiId(String txtGuiId) {
        this.txtGuiId = txtGuiId;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getGrpUser() {
        return grpUser;
    }

    public void setGrpUser(String grpUser) {
        this.grpUser = grpUser;
    }

    public String getTxtSumberDataID() {
        return txtSumberDataID;
    }

    public void setTxtSumberDataID(String txtSumberDataID) {
        this.txtSumberDataID = txtSumberDataID;
    }

    public String getNmUser() {
        return nmUser;
    }

    public void setNmUser(String nmUser) {
        this.nmUser = nmUser;
    }

    public String getTxtNamaInstitusi() {
        return txtNamaInstitusi;
    }

    public void setTxtNamaInstitusi(String txtNamaInstitusi) {
        this.txtNamaInstitusi = txtNamaInstitusi;
    }

    public Date getDtLogin() {
        return dtLogin;
    }

    public void setDtLogin(Date dtLogin) {
        this.dtLogin = dtLogin;
    }

    public String getTxtTeleName() {
        return txtTeleName;
    }

    public void setTxtTeleName(String txtTeleName) {
        this.txtTeleName = txtTeleName;
    }

    public Integer getIntSchedel() {
        return intSchedel;
    }

    public void setIntSchedel(Integer intSchedel) {
        this.intSchedel = intSchedel;
    }
}
