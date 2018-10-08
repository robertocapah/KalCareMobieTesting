package com.kalbenutritionals.kalcaremobieTesting.Common;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by Robert on 13/04/2018.
 */
@DatabaseTable
public class clsListMediaJasa implements Serializable {
    @DatabaseField(generatedId = true)
    int id;
    @DatabaseField
    private String typeid;
    @DatabaseField
    private String descType;
    @DatabaseField
    private String txtmediajasapaymentid;
    @DatabaseField
    private String txtmediajasapayment;

    public String Property_StrTypeId = "typeid";
    public String Property_txtmediajasapaymentid = "txtmediajasapaymentid";

    public String getTypeid() {
        return typeid;
    }

    public void setTypeid(String typeid) {
        this.typeid = typeid;
    }

    public String getDescType() {
        return descType;
    }

    public void setDescType(String descType) {
        this.descType = descType;
    }

    public String getTxtmediajasapaymentid() {
        return txtmediajasapaymentid;
    }

    public void setTxtmediajasapaymentid(String txtmediajasapaymentid) {
        this.txtmediajasapaymentid = txtmediajasapaymentid;
    }

    public String getTxtmediajasapayment() {
        return txtmediajasapayment;
    }

    public void setTxtmediajasapayment(String txtmediajasapayment) {
        this.txtmediajasapayment = txtmediajasapayment;
    }
}
