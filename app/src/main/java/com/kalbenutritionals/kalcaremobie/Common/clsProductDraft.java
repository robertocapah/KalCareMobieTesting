package com.kalbenutritionals.kalcaremobie.Common;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by Robert on 28/02/2018.
 */
@DatabaseTable
public class clsProductDraft implements Serializable {
    @DatabaseField(id = true)
    private String txtProductDraftGUI;
    @DatabaseField
    private String txtDraftGUI;
    @DatabaseField
    private String txtItemCode;
    @DatabaseField
    private String txtProductCategory;
    @DatabaseField
    private int intQty;
    @DatabaseField
    private double dblItemDiscount;
    @DatabaseField
    private double dblItemTax;
    @DatabaseField
    private String txtItemName;
    @DatabaseField
    private String txtItemBrand;
    @DatabaseField
    private double dblPrice;
    @DatabaseField
    private double dblTotalPrice;
    @DatabaseField
    private String txtBasedPoint;
    @DatabaseField
    private String txtBonusPoint;
    @DatabaseField
    private double dblNetPrice;

    public String getTxtItemBrand() {
        return txtItemBrand;
    }

    public void setTxtItemBrand(String txtItemBrand) {
        this.txtItemBrand = txtItemBrand;
    }

    public String getTxtProductDraftGUI() {
        return txtProductDraftGUI;
    }

    public String getTxtProductCategory() {
        return txtProductCategory;
    }

    public void setTxtProductCategory(String txtProductCategory) {
        this.txtProductCategory = txtProductCategory;
    }

    public String getTxtBonusPoint() {
        return txtBonusPoint;
    }

    public void setTxtBonusPoint(String txtBonusPoint) {
        this.txtBonusPoint = txtBonusPoint;
    }

    public String getTxtBasedPoint() {
        return txtBasedPoint;
    }

    public void setTxtBasedPoint(String txtBasedPoint) {
        this.txtBasedPoint = txtBasedPoint;
    }

    public void setTxtProductDraftGUI(String txtProductDraftGUI) {
        this.txtProductDraftGUI = txtProductDraftGUI;
    }

    public double getDblPrice() {
        return dblPrice;
    }

    public void setDblPrice(double dblPrice) {
        this.dblPrice = dblPrice;
    }

    public String getTxtDraftGUI() {
        return txtDraftGUI;
    }

    public void setTxtDraftGUI(String txtDraftGUI) {
        this.txtDraftGUI = txtDraftGUI;
    }

    public String getTxtItemCode() {
        return txtItemCode;
    }

    public void setTxtItemCode(String txtItemCode) {
        this.txtItemCode = txtItemCode;
    }

    public int getIntQty() {
        return intQty;
    }

    public void setIntQty(int intQty) {
        this.intQty = intQty;
    }

    public double getDblItemDiscount() {
        return dblItemDiscount;
    }

    public void setDblItemDiscount(double dblItemDiscount) {
        this.dblItemDiscount = dblItemDiscount;
    }

    public double getDblItemTax() {
        return dblItemTax;
    }

    public void setDblItemTax(double dblItemTax) {
        this.dblItemTax = dblItemTax;
    }

    public String getTxtItemName() {
        return txtItemName;
    }

    public void setTxtItemName(String txtItemName) {
        this.txtItemName = txtItemName;
    }

    public double getDblTotalPrice() {
        return dblTotalPrice;
    }

    public void setDblTotalPrice(double dblTotalPrice) {
        this.dblTotalPrice = dblTotalPrice;
    }

    public double getDblNetPrice() {
        return dblNetPrice;
    }

    public void setDblNetPrice(double dblNetPrice) {
        this.dblNetPrice = dblNetPrice;
    }
}
