package com.kalbenutritionals.kalcaremobieTesting.ViewModel;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Robert on 21/02/2018.
 */

public class VMItems extends RecyclerView.ViewHolder {
    String guiid;
    String productCategory;
    String itemCode;
    String itemName;
    String itemBrand;
    String itemGroup;
    String txtGroupKN;
    String txtKN;
    String desc;
    String barcode;
    int qty;
    int qtyPaket;
    double price;
    double discount;
    double totalPrice;
    double dectax;
    double taxAmount;
    double netPrice;
    String basePoint;
    String TotalBasePoint;
    double decCalcTotalPrice;
    double decCalcTaxAmount;
    double decCalcNetPrice;
    double decCalcTotalBasePoint;
    double decCalcTotal;
    double decTotalBasePoint;
    double decBonusPoint;
    String txtGroupProduct;
    String txtDescription;
    String txtUOM;
    String txtItemPacketID;
    int intPaket;
    int intItemId;
    String decWeight;
    String bonusPoint;
    int intCampagn;
    double decHJPOriginal;
    int intTotalBonusPoint;
    int intTotalItemBasePoint;

    public VMItems(View itemView) {
        super(itemView);
    }

    public double getDecHJPOriginal() {
        return decHJPOriginal;
    }

    public void setDecHJPOriginal(double decHJPOriginal) {
        this.decHJPOriginal = decHJPOriginal;
    }

    public int getIntTotalBonusPoint() {
        return intTotalBonusPoint;
    }

    public void setIntTotalBonusPoint(int intTotalBonusPoint) {
        this.intTotalBonusPoint = intTotalBonusPoint;
    }

    public int getIntTotalItemBasePoint() {
        return intTotalItemBasePoint;
    }

    public void setIntTotalItemBasePoint(int intTotalItemBasePoint) {
        this.intTotalItemBasePoint = intTotalItemBasePoint;
    }

    public String getTxtGroupKN() {
        return txtGroupKN;
    }

    public void setTxtGroupKN(String txtGroupKN) {
        this.txtGroupKN = txtGroupKN;
    }

    public String getTxtKN() {
        return txtKN;
    }

    public void setTxtKN(String txtKN) {
        this.txtKN = txtKN;
    }

    public double getDectax() {
        return dectax;
    }

    public void setDectax(double dectax) {
        this.dectax = dectax;
    }

    public String getTxtItemPacketID() {
        return txtItemPacketID;
    }

    public void setTxtItemPacketID(String txtItemPacketID) {
        this.txtItemPacketID = txtItemPacketID;
    }

    public int getIntItemId() {
        return intItemId;
    }

    public void setIntItemId(int intItemId) {
        this.intItemId = intItemId;
    }

    public int getQtyPaket() {
        return qtyPaket;
    }

    public void setQtyPaket(int qtyPaket) {
        this.qtyPaket = qtyPaket;
    }

    public String getTotalBasePoint() {
        return TotalBasePoint;
    }

    public void setTotalBasePoint(String totalBasePoint) {
        TotalBasePoint = totalBasePoint;
    }

    public double getDecCalcTotalPrice() {
        return decCalcTotalPrice;
    }

    public void setDecCalcTotalPrice(double decCalcTotalPrice) {
        this.decCalcTotalPrice = decCalcTotalPrice;
    }

    public double getDecCalcTaxAmount() {
        return decCalcTaxAmount;
    }

    public void setDecCalcTaxAmount(double decCalcTaxAmount) {
        this.decCalcTaxAmount = decCalcTaxAmount;
    }

    public double getDecCalcNetPrice() {
        return decCalcNetPrice;
    }

    public void setDecCalcNetPrice(double decCalcNetPrice) {
        this.decCalcNetPrice = decCalcNetPrice;
    }

    public double getDecCalcTotalBasePoint() {
        return decCalcTotalBasePoint;
    }

    public void setDecCalcTotalBasePoint(double decCalcTotalBasePoint) {
        this.decCalcTotalBasePoint = decCalcTotalBasePoint;
    }

    public double getDecCalcTotal() {
        return decCalcTotal;
    }

    public void setDecCalcTotal(double decCalcTotal) {
        this.decCalcTotal = decCalcTotal;
    }

    public double getDecTotalBasePoint() {
        return decTotalBasePoint;
    }

    public void setDecTotalBasePoint(double decTotalBasePoint) {
        this.decTotalBasePoint = decTotalBasePoint;
    }

    public double getDecBonusPoint() {
        return decBonusPoint;
    }

    public void setDecBonusPoint(double decBonusPoint) {
        this.decBonusPoint = decBonusPoint;
    }

    public String getTxtGroupProduct() {
        return txtGroupProduct;
    }

    public void setTxtGroupProduct(String txtGroupProduct) {
        this.txtGroupProduct = txtGroupProduct;
    }

    public String getTxtDescription() {
        return txtDescription;
    }

    public void setTxtDescription(String txtDescription) {
        this.txtDescription = txtDescription;
    }

    public String getTxtUOM() {
        return txtUOM;
    }

    public void setTxtUOM(String txtUOM) {
        this.txtUOM = txtUOM;
    }

    public int getIntPaket() {
        return intPaket;
    }

    public void setIntPaket(int intPaket) {
        this.intPaket = intPaket;
    }

    public String getDecWeight() {
        return decWeight;
    }

    public void setDecWeight(String decWeight) {
        this.decWeight = decWeight;
    }

    public int getIntCampagn() {
        return intCampagn;
    }

    public void setIntCampagn(int intCampagn) {
        this.intCampagn = intCampagn;
    }

    public String getItemBrand() {
        return itemBrand;
    }

    public void setItemBrand(String itemBrand) {
        this.itemBrand = itemBrand;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getGuiid() {
        return guiid;
    }

    public void setGuiid(String guiid) {
        this.guiid = guiid;
    }

    public String getItemGroup() {
        return itemGroup;
    }

    public void setItemGroup(String itemGroup) {
        this.itemGroup = itemGroup;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(double taxAmount) {
        this.taxAmount = taxAmount;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getNetPrice() {
        return netPrice;
    }

    public void setNetPrice(double netPrice) {
        this.netPrice = netPrice;
    }

    public String getBasePoint() {
        return basePoint;
    }

    public void setBasePoint(String basePoint) {
        this.basePoint = basePoint;
    }

    public String getBonusPoint() {
        return bonusPoint;
    }

    public void setBonusPoint(String bonusPoint) {
        this.bonusPoint = bonusPoint;
    }
}
