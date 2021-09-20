package vn.edu.tdmu.nguyentranhongnam.quanlichitieucanhan.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import vn.edu.tdmu.nguyentranhongnam.quanlichitieucanhan.R;
import vn.edu.tdmu.nguyentranhongnam.quanlichitieucanhan.database.DatabaseHandler;


public class ThuChiMainActivity extends Activity {

    Button themGD,ls,exit;
    TextView sodu, tindung, tietkiem, tienmat;
    DatabaseHandler db;
    int sodutienmat;
    int sodutindung;
    int sodutietkiem;
    int warning;

    @SuppressLint("NewApi") @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new DatabaseHandler(this);
        db.open();
        setContentView(R.layout.activity_thu_chi_main);
        themGD = (Button) findViewById(R.id.imgadd);
        ls = (Button)findViewById(R.id.imgweek);
        sodu = (TextView) findViewById(R.id.tvSoDu);
        tietkiem = (TextView) findViewById(R.id.TvTietKiem);
        tindung = (TextView) findViewById(R.id.TvTinDung);
        tienmat = (TextView) findViewById(R.id.TvTienMat);
        exit=(Button)findViewById(R.id.quit);
        exit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ThuChiMainActivity.this);
                builder.setTitle("Thoát!!!");
                builder.setMessage("Bạn có muốn thoát không?")
                        .setCancelable(false)
                        .setPositiveButton("Không", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();

                            }
                        })
                        .setNegativeButton("Có", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                System.exit(0);
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
        if (db.taikhoan("Tín Dụng").get(0) == null) {
            sodutindung = 0;

        } else {
            sodutindung = Integer.parseInt(db.taikhoan("Tín Dụng").get(0));
        }
        if (db.taikhoan("Tiền Mặt").get(0) == null) {
            sodutienmat = 0;

        } else {
            sodutienmat = Integer.parseInt(db.taikhoan("Tiền Mặt").get(0));
        }
        if (db.taikhoan("Tiết Kiệm").get(0) == null) {
            sodutietkiem = 0;

        } else {
            sodutietkiem = Integer.parseInt(db.taikhoan("Tiết Kiệm").get(0));
        }

        tietkiem.setText(sodutietkiem + "");

        tindung.setText(sodutindung + "");

        tienmat.setText(sodutienmat + "");

        sodu.setText(sodutienmat + sodutindung + sodutietkiem + "");

        themGD.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent i = new Intent(ThuChiMainActivity.this,
                        ThemgiaodichActivity.class);
                startActivity(i);
            }
        });

        ls.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent i = new Intent(ThuChiMainActivity.this,
                        ListGiaoDichMainActivity.class);
                startActivity(i);
            }
        });
    }
}