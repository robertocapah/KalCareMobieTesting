package com.kalbenutritionals.kalcaremobieTesting.Common;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by Robert on 20/04/2018.
 */
@DatabaseTable
public class clsListPaymentMethodAndTipe implements Serializable {
    @DatabaseField(id = true)
    int id;
    @DatabaseField
    private String txtPaymentMethodId;
    @DatabaseField
    private String txtPaymentMethodName;
    @DatabaseField
    private String typeid;
    @DatabaseField
    private String desc_type;
    @DatabaseField
    private int intFillCardNumber;
    @DatabaseField
    private int intFillTraceNumber;

    public String Property_txtPaymentMethodId = "txtPaymentMethodId";
    public String Property_typeid = "typeid";

    public String getTypeid() {
        return typeid;
    }

    public void setTypeid(String typeid) {
        this.typeid = typeid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTxtPaymentMethodId() {
        return txtPaymentMethodId;
    }

    public void setTxtPaymentMethodId(String txtPaymentMethodId) {
        this.txtPaymentMethodId = txtPaymentMethodId;
    }

    public String getTxtPaymentMethodName() {
        return txtPaymentMethodName;
    }

    public void setTxtPaymentMethodName(String txtPaymentMethodName) {
        this.txtPaymentMethodName = txtPaymentMethodName;
    }

    public String getDesc_type() {
        return desc_type;
    }

    public void setDesc_type(String desc_type) {
        this.desc_type = desc_type;
    }

    public int getIntFillCardNumber() {
        return intFillCardNumber;
    }

    public void setIntFillCardNumber(int intFillCardNumber) {
        this.intFillCardNumber = intFillCardNumber;
    }

    public int getIntFillTraceNumber() {
        return intFillTraceNumber;
    }

    public void setIntFillTraceNumber(int intFillTraceNumber) {
        this.intFillTraceNumber = intFillTraceNumber;
    }
}
