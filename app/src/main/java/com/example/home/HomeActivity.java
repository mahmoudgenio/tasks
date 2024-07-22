package com.example.home;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.Room.MyRoomDb;
import com.example.categories.CategoriesActivity;
import com.example.categories.ShowCategoriesActivity;
import com.example.loginactivity.R;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;

public class HomeActivity extends AppCompatActivity {
    TextView textName;
    ImageView category1;
    ImageView category2;
    ImageView deleteData;
    ImageView exportData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        textName = findViewById(R.id.data);
        category1 = findViewById(R.id.img1);
        category2 = findViewById(R.id.img2);
        deleteData = findViewById(R.id.delete_data);
        exportData = findViewById(R.id.export_data);
        Intent intent= getIntent();
       String getName = intent.getStringExtra("name");
        textName.setText(getName);


        category1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(HomeActivity.this, CategoriesActivity.class);
                startActivity(intent);

            }

        });

        deleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteAll();
            }
        });


        category2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(HomeActivity.this, ShowCategoriesActivity.class);
                startActivity(intent);

            }

        });


        exportData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestStoragePermission();

            }

        });
    }

    private void deleteAll(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you Sure about deleteAll Data ?");
// Add the buttons.
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                MyRoomDb.getInstance(getApplicationContext()).daoCat().deleteAll();
                MyRoomDb.getInstance(getApplicationContext()).daoItem().deleteAll();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();

    }


    private static final int PERMISSION_REQUEST_CODE = 1;

    private void requestStoragePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    PERMISSION_REQUEST_CODE);
        }
        createFileWithCategoryName("Category1");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted
                createFileWithCategoryName("Category1");
                Toast.makeText(this, "Permission accepted", Toast.LENGTH_SHORT).show();
            } else {
                // Permission denied
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void createFileWithCategoryName(String name){

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet(name);
        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        cell.setCellValue("Item Description");

        Row row1 = sheet.createRow(1);
        Cell cell1 = row1.createCell(1);
        cell1.setCellValue("item quantities");


        try {

            File file = new File( Environment.getExternalStorageDirectory(),"excel.xlsx");

            // Output stream to write to the file
            FileOutputStream out = new FileOutputStream(file);
//            FileOutputStream out = new FileOutputStream(
//                    "excel.xlsx");
            workbook.write(out);
            out.close();
            Log.d("Excel file save in ", file.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}