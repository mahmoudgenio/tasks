package com.example.scanner;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginactivity.R;
import com.example.test2tables.BarcodeDetails;

import java.util.List;

public class BarcodeAdapter extends RecyclerView.Adapter<BarcodeAdapter.BarcodeViewHolder> {

    private List<BarcodeDetails> barcodes;

    public BarcodeAdapter(List<BarcodeDetails> barcodes) {
        this.barcodes = barcodes;
    }

    @NonNull
    @Override
    public BarcodeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_barcode, parent, false);
        return new BarcodeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BarcodeViewHolder holder, int position) {
        BarcodeDetails barcode = barcodes.get(position);
        holder.barcodeEditText.setText(barcode.getBarcode()); // here
        holder.quantityBarcodeEditText.setText(barcode.getQhsBarcode());
    }

    @Override
    public int getItemCount() {
        return barcodes.size();
    }

    public static class BarcodeViewHolder extends RecyclerView.ViewHolder {
        EditText barcodeEditText;
        EditText quantityBarcodeEditText;

        public BarcodeViewHolder(@NonNull View view) {
            super(view);
            barcodeEditText = view.findViewById(R.id.barcode_editText);
            quantityBarcodeEditText = view.findViewById(R.id.qhs_editText);
        }
    }
}
