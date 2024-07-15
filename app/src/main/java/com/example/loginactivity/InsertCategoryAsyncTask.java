package com.example.loginactivity;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.loginactivity.Room.Category;
import com.example.loginactivity.Room.MyRoomDb;

public class InsertCategoryAsyncTask extends AsyncTask<Void, Void, Boolean> {
    private Context context;
    private Category category;

    public InsertCategoryAsyncTask(Context context, Category category) {
        this.context = context;
        this.category = category;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        try {
            // Insert category into database
            MyRoomDb.getInstance(context).dao().insert(category);
            return true; // Insertion successful
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Insertion failed
        }
    }

    @Override
    protected void onPostExecute(Boolean success) {
        super.onPostExecute(success);

        if (success) {
            Log.d("Database", "Category added successfully"); // Log message for successful insertion
            Toast.makeText(context, "Category added successfully", Toast.LENGTH_SHORT).show();
        } else {
            Log.e("Database", "Failed to add category"); // Log message for insertion failure
            Toast.makeText(context, "Failed to add category", Toast.LENGTH_SHORT).show();
        }
    }
}
