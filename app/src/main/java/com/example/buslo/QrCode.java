package com.example.buslo;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;

import org.w3c.dom.Text;

public class QrCode extends AppCompatActivity {
    ImageView qrCode;
    TextView paid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_code);
        qrCode = (ImageView) findViewById(R.id.qrcode);
        paid = (TextView) findViewById(R.id.paid);
        Bundle bundle = getIntent().getExtras();
        String money = bundle.getString("money");
        try{
            Bitmap bitmap = textToQrBitmap(money);
            qrCode.setImageBitmap(bitmap);
            paid.setText("Paid Rs." + money);
        }catch (Exception e){
            Log.v("AAA", e.getMessage().toString());
        }


    }

    public Bitmap textToQrBitmap(String value){
        BitMatrix bitMatrix;
        try{
            bitMatrix = new MultiFormatWriter().encode(value, BarcodeFormat.QR_CODE, 500, 500, null);
        }catch (Exception e){
            return null;
        }
        int matrixWidth = bitMatrix.getWidth();
        int matrixHEight = bitMatrix.getHeight();
        int[] pixels = new int[matrixHEight*matrixWidth];

        for(int i=0; i<matrixHEight; i++){
            int offset = i*matrixWidth;
            for(int j=0; j< matrixWidth; j++){
                pixels[offset+j] = bitMatrix.get(j, i) ?Color.BLACK : Color.WHITE;
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(matrixWidth, matrixHEight, Bitmap.Config.RGB_565);
        bitmap.setPixels(pixels, 0, 500, 0, 0, matrixWidth, matrixHEight);
        return bitmap;
    }
}
