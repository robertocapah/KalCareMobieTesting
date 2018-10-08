package com.kalbenutritionals.kalcaremobieTesting.Common;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by Robert on 26/03/2018.
 */
@DatabaseTable
public class clsCustomerData implements Serializable {
    @DatabaseField(id = true)
    private int id;
    @DatabaseField
    String txtKontakID;
    @DatabaseField
    String txtNama;
    @DatabaseField
    String txtAlamat;
    @DatabaseField
    String txtPhoneNumber;
    @DatabaseField
    String txtNamaKabKota;
    @DatabaseField
    String txtNamaPropinsi;
    @DatabaseField
    String JenisAlamat;
    @DatabaseField
    String txtListMedia;
    @DatabaseField
    String txtNamaKecamatan;
    @DatabaseField
    String txtNamaKelurahan;
    @DatabaseField
    String txtNHDSiteID;
    @DatabaseField
    String txtPropinsiID;
    @DatabaseField
    String txtKabKotaID;
    @DatabaseField
    String txtKecamatan;
    @DatabaseField
    String txtKodePos;

    public String getTxtPhoneNumber() {
        return txtPhoneNumber;
    }

    public void setTxtPhoneNumber(String txtPhoneNumber) {
        this.txtPhoneNumber = txtPhoneNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTxtKontakID() {
        return txtKontakID;
    }

    public void setTxtKontakID(String txtKontakID) {
        this.txtKontakID = txtKontakID;
    }

    public String getTxtNama() {
        return txtNama;
    }

    public void setTxtNama(String txtNama) {
        this.txtNama = txtNama;
    }

    public String getTxtAlamat() {
        return txtAlamat;
    }

    public void setTxtAlamat(String txtAlamat) {
        this.txtAlamat = txtAlamat;
    }

    public String getTxtNamaKabKota() {
        return txtNamaKabKota;
    }

    public void setTxtNamaKabKota(String txtNamaKabKota) {
        this.txtNamaKabKota = txtNamaKabKota;
    }

    public String getTxtNamaPropinsi() {
        return txtNamaPropinsi;
    }

    public void setTxtNamaPropinsi(String txtNamaPropinsi) {
        this.txtNamaPropinsi = txtNamaPropinsi;
    }

    public String getJenisAlamat() {
        return JenisAlamat;
    }

    public void setJenisAlamat(String jenisAlamat) {
        JenisAlamat = jenisAlamat;
    }

    public String getTxtListMedia() {
        return txtListMedia;
    }

    public void setTxtListMedia(String txtListMedia) {
        this.txtListMedia = txtListMedia;
    }

    public String getTxtNamaKecamatan() {
        return txtNamaKecamatan;
    }

    public void setTxtNamaKecamatan(String txtNamaKecamatan) {
        this.txtNamaKecamatan = txtNamaKecamatan;
    }

    public String getTxtNamaKelurahan() {
        return txtNamaKelurahan;
    }

    public void setTxtNamaKelurahan(String txtNamaKelurahan) {
        this.txtNamaKelurahan = txtNamaKelurahan;
    }

    public String getTxtNHDSiteID() {
        return txtNHDSiteID;
    }

    public void setTxtNHDSiteID(String txtNHDSiteID) {
        this.txtNHDSiteID = txtNHDSiteID;
    }

    public String getTxtPropinsiID() {
        return txtPropinsiID;
    }

    public void setTxtPropinsiID(String txtPropinsiID) {
        this.txtPropinsiID = txtPropinsiID;
    }

    public String getTxtKabKotaID() {
        return txtKabKotaID;
    }

    public void setTxtKabKotaID(String txtKabKotaID) {
        this.txtKabKotaID = txtKabKotaID;
    }

    public String getTxtKecamatan() {
        return txtKecamatan;
    }

    public void setTxtKecamatan(String txtKecamatan) {
        this.txtKecamatan = txtKecamatan;
    }

    public String getTxtKodePos() {
        return txtKodePos;
    }

    public void setTxtKodePos(String txtKodePos) {
        this.txtKodePos = txtKodePos;
    }
}
