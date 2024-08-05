package com.example.test2tables;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity

public class BarcodeDetails {
    @ColumnInfo(name = "Barcode")
   int Barcode;
    @ColumnInfo(name = "qhsBarcode")
    int qhsBarcode;

    public BarcodeDetails(int barcode, int qhs) {
        Barcode = barcode;
        this.qhsBarcode = qhs;
    }

    public int getBarcode() {
        return Barcode;
    }

    public int getQhsBarcode() {
        return qhsBarcode;
    }

    public void setBarcode(int barcode) {
        Barcode = barcode;
    }

    public void setQhsBarcode(int qhsBarcode) {
        this.qhsBarcode = qhsBarcode;
    }
}
