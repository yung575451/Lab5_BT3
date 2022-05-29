package com.hungphamcom.lab5_bt3;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView selectImage,imageview;
    private EditText yourName,yourEmail,yourPhoneNumber;
    private RadioButton male,female;
    private Button save;
    private TextView cancel;
    private SharedPreferences sharedPreferences;
    private ActivityResultLauncher<String> mTakePhoto;
    private Uri imageURI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        selectImage=findViewById(R.id.select_image_btn);
        imageview=findViewById(R.id.imageView);
        yourName=findViewById(R.id.your_name);
        yourEmail=findViewById(R.id.your_email);
        yourPhoneNumber=findViewById(R.id.your_phone_number);
        male=findViewById(R.id.male_Btn);
        female=findViewById(R.id.female_Btn);
        save=findViewById(R.id.save);
        cancel=findViewById(R.id.cancel);

        sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);

        mTakePhoto = registerForActivityResult(
                new ActivityResultContracts.GetContent(),
                new ActivityResultCallback<Uri>() {
                    @Override
                    public void onActivityResult(Uri result) {
                        imageview.setImageURI(result);
                        imageURI = result;
                    }
                });

        selectImage.setOnClickListener(this);
        save.setOnClickListener(this);
        cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.select_image_btn:
                mTakePhoto.launch("image/*");
                selectImage.setAlpha(0.5F);
                break;
            case R.id.save:
                saveInformation();
                break;
        }
    }

    private void saveInformation() {
        String name=yourName.getText().toString().trim();
        String email=yourEmail.getText().toString().trim();
        String phone=yourPhoneNumber.getText().toString().trim();
        if(!TextUtils.isEmpty(name)
        &&!TextUtils.isEmpty(email)
        &&!TextUtils.isEmpty(phone)
        &&!TextUtils.isEmpty((CharSequence) imageURI)){
            SharedPreferences sharedPreferences = PreferenceManager
                    .getDefaultSharedPreferences(this);
            SharedPreferences.Editor editor=sharedPreferences.edit();

        }else{
            Toast.makeText(MainActivity.this,"Please fill all the information"
            , Toast.LENGTH_LONG).show();
        }
    }
}