package com.kalbenutritionals.kalcaremobieTesting.Common;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by Robert on 13/04/2018.
 */
@DatabaseTable
public class clsListPaymentMethod implements Serializable {
    @DatabaseField(id = true)
    private String kode;
    @DatabaseField
    private String nama;

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
}
