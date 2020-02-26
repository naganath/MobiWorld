package com.naganath.cs478.project2;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import static com.naganath.cs478.project2.MainActivity.IMAGE_KEY;
import static com.naganath.cs478.project2.MainActivity.WEB_KEY;


public class ImageDisplayActivity extends AppCompatActivity {


    private String webLink;

    //todo open browser activity.
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(webLink));
            intent.addCategory(Intent.CATEGORY_BROWSABLE);
//            Intent intent = new Intent(getApplication(), WebsiteDisplayActivity.class);
            Log.e(ImageDisplayActivity.class.toString(),getResources().getString(R.string.image_id));
            startActivity(intent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_display);
        Intent intent = getIntent();
        Integer id = intent.getExtras().getInt(IMAGE_KEY);
        ImageView imageView = findViewById(R.id.image_display);
        imageView.setImageResource(id);
        webLink = intent.getExtras().getString(WEB_KEY);
        imageView.setOnClickListener(onClickListener);

    }
}
