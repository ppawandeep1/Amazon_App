package com.example.amazonapp.Adapters;
//package com.example.sq_lite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class DBProductAdapter extends SQLiteOpenHelper {

    private final static String TAG = "DBProductAdapter";
    private final Context myContext;
    private static final String DATABASE_NAME = "IMS.db"; //database name
    private static final int DATABASE_VERSION = 1;
    private String pathToSaveDBFile;

    //colums
    private static final String customerTableName = "Customer";
    private static final String first_name= "first_name";
    private static final String email_address= "email_address";
    private static final String customer_id ="customer_id";

    private static final String cartProductTableName = "Cart_Product";
    private static final String cart_product_id= "cart_product_id";
    private static final String product_id= "product_id" ;
    private static final String product_name ="product_name";
    private static final String quantity= "quantity";
    private static final String price= "price" ;
    private static final String image_URL ="image_URL" ;
    private static final String is_purchase= "is_purchase";
    private static final String is_active= "is_active" ;

    private static final String CREATE_TABLE = "CREATE TABLE" + cartProductTableName + "(" +
            cart_product_id + "INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
            customer_id + "INTEGER NOT NULL," +
            product_id + "INTEGER NOT NULL," +
            product_name + "TEXT NOT NULL," +
            quantity + "INTEGER, "  +
            price + "INTEGER, " +
            image_URL + "TEXT," +
            is_purchase + "INTEGER NOT NULL DEFAULT 0," +
            is_active + "INTEGER NOT NULL DEFAULT 1," + ")";

    public DBProductAdapter(Context context, String filePath) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.myContext = context;
        pathToSaveDBFile = new StringBuffer(filePath).append("/").append(DATABASE_NAME).toString();
    }


    public void prepareDatabase() throws IOException {
        boolean dbExist = checkDataBase();
        if(dbExist) {
            Log.d(TAG, "Database exists.");

            copyDataBase();

        } else {
            try {
                copyDataBase();
            } catch (IOException e) {
                Log.e(TAG, e.getMessage());
            }
        }
    }

    /*If file exist*/

    private boolean checkDataBase() {
        boolean checkDB = false;
        try {
            File file = new File(pathToSaveDBFile);
            checkDB = file.exists();
        } catch(SQLiteException e) {
            Log.d(TAG, e.getMessage());
        }
        return checkDB;
    }

    private void copyDataBase() throws IOException {
        OutputStream os = new FileOutputStream(pathToSaveDBFile);
        InputStream is = myContext.getAssets().open("sqlite/"+DATABASE_NAME);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = is.read(buffer)) > 0) {
            os.write(buffer, 0, length);
        }
        is.close();
        os.flush();
        os.close();
    }


    public void deleteDb() {
        File file = new File(pathToSaveDBFile);
        if(file.exists()) {
            file.delete();
            Log.d(TAG, "Database deleted.");
        }
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "onCreate");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    // create method to insert data

    public boolean insertData(int cart_product_id, int customer_id, int product_id, String product_name, String quantity, String price, String image_URL){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("cart_product_id", cart_product_id);
        contentValues.put("customer_id", customer_id);
        contentValues.put("product_id", product_id);
        contentValues.put("product_name", "product_name");
        contentValues.put("quantity", quantity);
        contentValues.put("price", price);
        contentValues.put("image_URL", image_URL);

        long result = db.insert(cartProductTableName, null, contentValues);

        return result != -1 ;//if result = -1 data doesnot insert

    }

    private int getVersionId() {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READONLY);
        String query = "SELECT version_id FROM dbVersion";
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        int v =  cursor.getInt(0);
        db.close();
        return v;
    }
}
