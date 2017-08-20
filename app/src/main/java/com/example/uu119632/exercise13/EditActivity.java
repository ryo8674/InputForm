package com.example.uu119632.exercise13;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

/**
 * 編集画面
 *
 * @author :ryo.yamada
 * @since :1.0 :2017/08/18
 */
public class EditActivity extends AppCompatActivity {

    private static final String PREFERENCES_FILE_NAME = "PreferencesFile";
    private static final String KEY_NAME = "NAME";
    private static final String KEY_GENDER = "GENDER";
    private static final String KEY_MAIL = "MAIL";
    private static final String KEY_MAGAZINE = "MAGAZINE";
    private static final String NOT_RECEIVED_MESSAGE = "受けとらない";
    private static final String RECEIVED_MESSAGE = "受けとる";

    private String gender;
    private String received;

    /**
     * onCreate
     *
     * @param savedInstanceState savedInstanceState
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        SharedPreferences sharedPreferences = getSharedPreferences(PREFERENCES_FILE_NAME, 0);
        final SharedPreferences.Editor editor = sharedPreferences.edit();

        final EditText editName = (EditText) findViewById(R.id.edit_name);
        RadioGroup radioGender = (RadioGroup) findViewById(R.id.radio_gender);
        final EditText editMailAddress = (EditText) findViewById(R.id.edit_mail);
        CheckBox checkMagazine = (CheckBox) findViewById(R.id.checkbox);

        // 性別選択
        radioGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            /**
             * チェックされたradioButtonのTextを変数に格納
             *
             * @param radioGroup radioGroup
             * @param id radioButtonのId
             */
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int id) {
                RadioButton rb = (RadioButton) findViewById(id);
                gender = rb.getText().toString();
            }
        });

        // メルマガの受け取り可否選択
        checkMagazine.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            /**
             * checkFlgで変数に格納する文字列を選択
             *
             * @param compoundButton compoundButton
             * @param checkFlg checkFlg
             */
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checkFlg) {
                if (checkFlg) {
                    received = RECEIVED_MESSAGE;
                } else {
                    received = NOT_RECEIVED_MESSAGE;
                }
            }
        });

        // 確定ボタン押下時の処理
        findViewById(R.id.button_determine).setOnClickListener(new View.OnClickListener() {
            /**
             * Preferenceに保存し、確認画面に画面遷移
             * @param view view
             */
            @Override
            public void onClick(View view) {

                editor.putString(KEY_NAME, editName.getText().toString());
                editor.putString(KEY_GENDER, gender);
                editor.putString(KEY_MAIL, editMailAddress.getText().toString());
                editor.putString(KEY_MAGAZINE, received);
                editor.apply();

                Intent intent = new Intent(EditActivity.this, ConfirmActivity.class);
                startActivity(intent);
            }
        });
    }
}
