package edu.ewubd.photogallery20191600682;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends Activity {

    private GridView gridView;
    CustomPhotoAdapter customImageAdapter;
    ArrayList<PhotoArrayList> arrayList;
    boolean doubleBackToExitPressedOnce = false;

    private String URL = "https://muthosoft.com/univ/photos/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = findViewById(R.id.gridview);
        arrayList = new ArrayList<>();

        httpRequest();
    }
    @SuppressLint("StaticFieldLeak")
    private void httpRequest(){
        new AsyncTask<Void, Void, String>(){
            @Override
            protected String doInBackground(Void... param){
                try{
                    String data = JSONParser.getInstance().makeHttpRequest(URL, "POST");
                    return data;
                }catch (Exception ex){
                    ex.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(String data) {
                super.onPostExecute(data);
                String[] splitByComma = data.split(",");
                ArrayList<PhotoArrayList> arrayList = new ArrayList<>();
                for (String a : splitByComma){
                    String[] splitByColon = a.split(":");
                    PhotoArrayList imagesArrayList = new PhotoArrayList(splitByColon[0], splitByColon[1]);
                    arrayList.add(imagesArrayList);
                }

                loadData(arrayList);
            }
        }.execute();
    }
    void loadData(ArrayList arrayList){
        customImageAdapter = new CustomPhotoAdapter(this,arrayList);
        gridView.setAdapter(customImageAdapter);
        customImageAdapter.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}