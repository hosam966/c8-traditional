package com.example.traditionalwordsproject;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;


import java.util.Random;


public class MainActivity extends AppCompatActivity {
    public static final String ANSWER_SHARE_TITLE_KEY = "answer_share_title_key";
    public static final String APP_PREF ="app_pref";
    public static final String APP_LANG = "app_lang";
    private String[] answersShareTitle;
    private String[] answerDetails;
    private ImageView mQuestionImageView;
    private Random mRandom;
    private int[] imagesQuestionViewsArrays  = {
            R.drawable.icon_1,
            R.drawable.icon_2,
            R.drawable.icon_3,
            R.drawable.icon_4,
            R.drawable.icon_5,
            R.drawable.icon_6,
            R.drawable.icon_7,
            R.drawable.icon_8,
            R.drawable.icon_9,
            R.drawable.icon_10,
            R.drawable.icon_11,
            R.drawable.icon_12,
            R.drawable.icon_13
    };

    private int mCurrentIndex = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        saveLanguage(MainActivity.APP_LANG);
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences = getSharedPreferences(MainActivity.APP_LANG,MODE_PRIVATE);
        String appLang = sharedPreferences.getString(MainActivity.APP_LANG,"1");
        if (!appLang.equals("1"))
            com.example.traditionalwordsproject.LocaleHelper.setLocale(this,MainActivity.APP_LANG);
        setContentView(R.layout.activity_main);
        mQuestionImageView = findViewById(R.id.image_view_question);
        answersShareTitle = getResources().getStringArray(R.array.answers);
        answerDetails = getResources().getStringArray(R.array.answer_description);
        mRandom = new Random();
    }

     public void showLanguageDialog(View view ){
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle(R.string.change_language)
                .setItems(R.array.languages, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        String language = "ar";
                        switch (which) {
                            case 0:
                                language = "ar";
                                break;
                            case 1:
                                language = "en";
                                break;

                        }
                        saveLanguage(language);
                        LocaleHelper.setLocale(MainActivity.this, language);
                        recreate();
                    }
                }).create();
            alertDialog.show();
    }

    private void saveLanguage (String lang){
        SharedPreferences sharedPreferences = getSharedPreferences(MainActivity.APP_PREF,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(MainActivity.APP_LANG,lang);
        editor.apply();
    }

    public void changeQuestionClicked(View view){
            mCurrentIndex = mRandom.nextInt(13);
            showImage();
    }

    public void onShareQuestionClicked(View view ){
        Intent shareIntent = new Intent(MainActivity.this,ShareActivity.class);
        shareIntent.putExtra(ShareActivity.IMAGE_RES_ID,imagesQuestionViewsArrays[mCurrentIndex]);
        startActivity(shareIntent);
    }

    public void showAnswer(View view){
        Intent mAnswerIntent = new Intent(MainActivity.this,AnswerActivity.class);
        mAnswerIntent.putExtra(MainActivity.ANSWER_SHARE_TITLE_KEY,answersShareTitle[mCurrentIndex]);
        mAnswerIntent.putExtra(AnswerActivity.ANSWER_DESCRIPTION_KEY,answerDetails[mCurrentIndex]);
        startActivity(mAnswerIntent);
    }

    public void showImage() {
        Drawable imagesQuestionViewsDrawable = ContextCompat.getDrawable(this, imagesQuestionViewsArrays[mCurrentIndex]);
        mQuestionImageView.setImageDrawable(imagesQuestionViewsDrawable);
    }
}
