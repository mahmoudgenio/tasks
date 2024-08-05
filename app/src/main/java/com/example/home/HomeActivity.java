package com.example.home;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.Api.CategoriesResponse;
import com.example.Api.RetrofitService;
import com.example.Room.MyRoomDb;
import com.example.categories.CategoriesActivity;
import com.example.categories.ShowCategoriesActivity;
import com.example.loginactivity.R;
import com.example.scanner.CheckBarcode;
import com.example.test2tables.BarcodeDetails;
import com.example.test2tables.CategoryNameSpinner;
import com.example.test2tables.ItemDetails;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeActivity extends AppCompatActivity {
    TextView textName;
    ImageView category1;
    ImageView category2;
    ImageView deleteData;
    ImageView exportData;
    ImageView barcodeQhs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        textName = findViewById(R.id.data);
        category1 = findViewById(R.id.img1);
        category2 = findViewById(R.id.img2);
        deleteData = findViewById(R.id.delete_data);
        exportData = findViewById(R.id.export_data);
        barcodeQhs = findViewById(R.id.barcode_btn);


        barcodeQhs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, CheckBarcode.class);
                startActivity(intent);
            }
        });

        Intent intent= getIntent();
       String getName = intent.getStringExtra("name");
        textName.setText(getName);


        category1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //getAllCategories();
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

                    createFileWithCategoryName();

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
                MyRoomDb.getInstance(getApplicationContext()).daoCat().resetPrimaryKey();
                MyRoomDb.getInstance(getApplicationContext()).daoItem().resetItemPrimaryKey();
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


    public void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (!Environment.isExternalStorageManager()) {
                try {
                    Uri uri = Uri.parse("package:com.example.loginactivity");
                    Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION, uri);
                    startActivity(intent);
                    Toast.makeText(HomeActivity.this, ("Permission Accepted"), Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(HomeActivity.this,("Permission Denied"), Toast.LENGTH_SHORT).show();
                }
            }

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PackageManager.PERMISSION_GRANTED) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                createFileWithCategoryName();
            }
            else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void createFileWithCategoryName() {
        requestPermission();
        List<CategoryNameSpinner> list = MyRoomDb.getInstance(this).daoCat().getAllCategories();

        for (int i = 0; i < list.size(); i++) {
            CategoryNameSpinner category = list.get(i);
            String name = category.getName();
            int id = category.getId();
            Log.d("CategoryName", name);

            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet(name);

            // Create header row
            Row headerRow = sheet.createRow(0);
            Cell headerCell1 = headerRow.createCell(0);
            headerCell1.setCellValue("Item Description");
            Cell headerCell2 = headerRow.createCell(1);
            headerCell2.setCellValue("Item Quantities");

            // Create Items for each category Id
            List<ItemDetails> items = MyRoomDb.getInstance(this).daoItem().getItemsForCategory(id);
            for (int j = 1; j < items.size(); j++) {
                ItemDetails item = items.get(j);
                String itemDesc = item.desc;
                int itemQhs = item.qhs;

                Log.d("ItemDesc", itemDesc);
                Row row = sheet.createRow(j + 1);
                Cell cell1 = row.createCell(0);
                cell1.setCellValue(itemDesc);

                Log.d("ItemQhs", String.valueOf(itemQhs));
                Cell cell2 = row.createCell(1);
                cell2.setCellValue(itemQhs);
            }
            File file = new File(Environment.getExternalStorageDirectory(), name + ".xlsx");

            try (OutputStream out = new FileOutputStream(file)) {
                workbook.write(out);
                Log.d("Excel file save in", file.getAbsolutePath());
                Toast.makeText(this, "File saved to " + file.getAbsolutePath(), Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, "Error saving file", Toast.LENGTH_SHORT).show();
            } finally {
                try {
                    workbook.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }





}


