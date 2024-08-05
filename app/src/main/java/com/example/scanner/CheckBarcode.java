package com.example.scanner;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.RecyclerView;

import com.example.loginactivity.R;

import android.view.KeyEvent;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class CheckBarcode extends AppCompatActivity {
    EditText barCode;
    EditText qhsCheckBox;
    CheckBox checkBox;

     Scanner scanner;

    private BarcodeAdapter barcodeAdapter;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_check_barcode);

       barCode = findViewById(R.id.barcode_editText);
       qhsCheckBox = findViewById(R.id.qhs_editText);
       checkBox = findViewById(R.id.checkBx);



        recyclerView = findViewById(R.id.barcodes_recycler);
//        List<BarcodeDetails> barcodeList = new ArrayList<>();
//        barcodeList.add(new BarcodeDetails(123432109, 456));
//        barcodeList.add(new BarcodeDetails(987654021, 101));

        barcodeAdapter = new BarcodeAdapter(new ArrayList<>());
        recyclerView.setAdapter(barcodeAdapter);
       // barcodeAdapter.notifyDataSetChanged();

      // qhsCheckBox.setEnabled(false);

        qhsCheckBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String text = s.toString();
                if (text.equals("0")) {
                    qhsCheckBox.setError("qhs Must be greater than 0");
                    checkBox.setChecked(false);
                    checkBox.setEnabled(false);
                } else {
                    qhsCheckBox.setError(null);
                    checkBox.setEnabled(true);
                    qhsCheckBox.setEnabled(true);
                }
            }


            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    String text = qhsCheckBox.getText().toString();
                    if (text.equals("0")) {
                        qhsCheckBox.setError("qhs Must be greater than 0");
                        checkBox.setChecked(false);
                        checkBox.setEnabled(false);
                    } else {
                        qhsCheckBox.setError(null);
                        qhsCheckBox.setEnabled(true);
                    }
                }
            }
        });


       barCode.requestFocus();


    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {

            handleEnterKey();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void handleEnterKey() {

        String scannedData = barCode.getText().toString();
        processScannedData(scannedData);
    }

    private void processScannedData(String scannedData) {

        Toast.makeText(this, "Scanned Data: " + scannedData, Toast.LENGTH_SHORT).show();
    }

}