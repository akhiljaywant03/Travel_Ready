package com.amazonaws.youruserpools;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

import com.amazonaws.youruserpools.CognitoYourUserPoolsDemo.R;

public class QRCodeScan extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_code);
        initComponents();
    }

    private void initComponents(){
        findViewById(R.id.buttonTakePicture).setOnClickListener(this);
        findViewById(R.id.buttonScanBarcode).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonScanBarcode:
                startActivity(new Intent(this,ScannerqrcodeActivity.class));
                break;
            case R.id.buttonTakePicture:
                startActivity(new Intent(this, ManualRegister.class));
                break;
        }
    }

}
