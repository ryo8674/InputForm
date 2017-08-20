package com.example.uu119632.exercise13;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * 確認画面
 *
 * @author :ryo.yamada
 * @since :1.0 :2017/08/18
 */
public class ConfirmActivity extends AppCompatActivity {

    private static final String PREFERENCES_FILE_NAME = "PreferencesFile";
    private static final String KEY_NAME = "NAME";
    private static final String KEY_GENDER = "GENDER";
    private static final String KEY_MAIL = "MAIL";
    private static final String KEY_MAGAZINE = "MAGAZINE";
    private static final String NOT_RECEIVED_MESSAGE = "受けとらない";
    private static final String BLANK = "";

    /**
     * onCreate
     *
     * @param savedInstanceState savedInstanceState
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);

        SharedPreferences sharedPreferences = getSharedPreferences(PREFERENCES_FILE_NAME, 0);

        TextView textName = (TextView) findViewById(R.id.text_name);
        TextView textGender = (TextView) findViewById(R.id.text_gender);
        TextView textMailAddress = (TextView) findViewById(R.id.text_mail);
        TextView textMagazine = (TextView) findViewById(R.id.text_mail_magazine);

        textName.setText(sharedPreferences.getString(KEY_NAME, BLANK));
        textGender.setText(sharedPreferences.getString(KEY_GENDER, BLANK));
        textMailAddress.setText(sharedPreferences.getString(KEY_MAIL, BLANK));
        textMagazine.setText(sharedPreferences.getString(KEY_MAGAZINE, NOT_RECEIVED_MESSAGE));

        // 編集ボタン押下時の処理
        findViewById(R.id.button_edit).setOnClickListener(new View.OnClickListener() {

            /**
             * 編集画面に画面遷移
             * @param view view
             */
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ConfirmActivity.this, EditActivity.class);
                startActivity(intent);
            }
        });
    }
}
