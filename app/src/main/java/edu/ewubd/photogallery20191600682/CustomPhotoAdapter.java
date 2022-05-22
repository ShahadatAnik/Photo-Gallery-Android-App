package edu.ewubd.photogallery20191600682;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class CustomPhotoAdapter extends BaseAdapter {

    Context context;
    ArrayList<PhotoArrayList> arrayList;
    public CustomPhotoAdapter(Context context, ArrayList<PhotoArrayList> arrayList){
        this.context = context;
        this.arrayList = arrayList;

    }
    @Override
    public int getCount() {
        return this.arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView = inflater.inflate(R.layout.custom_grid_photo, parent, false);

        ImageView photo_name = gridView.findViewById(R.id.photo_name);

        PhotoArrayList photoArr = arrayList.get(position);

        Picasso.get().load("https://muthosoft.com/univ/photos/"+photoArr.gePhotoId())
                .resize(400,400)
                .centerCrop()
                .into(photo_name);

        gridView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, PhotoDesc.class);
                intent.putExtra("photoId", photoArr.gePhotoId());
                intent.putExtra("desc", photoArr.getDesc());
                context.startActivity(intent);
            }
        });

        return gridView;
    }
}


