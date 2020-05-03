package com.example.traditionalwordsproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ShareActivity extends AppCompatActivity {
    public static final  String IMAGE_RES_ID = "image_res_string";
    private EditText mQuestionEditText;
    private ImageView mImageView;
    public int mImageResId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        mImageView = findViewById(R.id.image_view_question);
        mQuestionEditText = findViewById(R.id.edit_text_share_title);
                                    // value
        mImageResId = getIntent().getIntExtra(IMAGE_RES_ID,-1);
        mImageView.setImageResource(mImageResId);
    }

    public void onShareQuestionClicked(View view){
      /*  Bitmap bitmap = BitmapFactory.decodeResource(getResources(), mImageResId);
        Uri imageUri = null;
        try {
            imageUri = Uri.parse(MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, "share", null));
        } catch (NullPointerException e) {
            e.printStackTrace();
        }*/

        Resources resources = getResources(); //
        Uri imageUri = new Uri.Builder() // uri builder
                .scheme(ContentResolver.SCHEME_ANDROID_RESOURCE) // res location
                .authority(resources.getResourcePackageName(mImageResId))
                .appendPath(resources.getResourceTypeName(mImageResId))
                .appendPath(resources.getResourceEntryName(mImageResId))
                .build();
        // Save the input of the user in the plain in a variable
        String mQuestionTitle = mQuestionEditText.getText().toString();
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND); // set an action for the clicked button
        shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
        shareIntent.putExtra(Intent.EXTRA_TEXT , mQuestionTitle);
        shareIntent.setType("image/jpeg");
        shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        startActivity(Intent.createChooser(shareIntent, "share"));
    }
}
