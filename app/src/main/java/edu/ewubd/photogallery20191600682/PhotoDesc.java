package edu.ewubd.photogallery20191600682;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.squareup.picasso.Picasso;

public class PhotoDesc extends AppCompatActivity {

    ImageView image;
    TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_desc);

        image = findViewById(R.id.imageView);
        text = findViewById(R.id.textView);

        Intent intent = getIntent();

        String photoId = intent.getStringExtra("photoId");
        String desc = intent.getStringExtra("desc");
        desc = desc.replace("\n", "");

        text.setText(desc);
        Picasso.get().load("https://muthosoft.com/univ/photos/"+photoId)
                .resize(1000,1000)
                .centerCrop()
                .into(image);
    }
}
