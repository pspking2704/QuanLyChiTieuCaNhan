package vn.edu.tdmu.nguyentranhongnam.quanlichitieucanhan.activity;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import vn.edu.tdmu.nguyentranhongnam.quanlichitieucanhan.R;
import vn.edu.tdmu.nguyentranhongnam.quanlichitieucanhan.database.CustomAdapter;
import vn.edu.tdmu.nguyentranhongnam.quanlichitieucanhan.database.DatabaseHandler;


@SuppressLint("NewApi")
public class ListGiaoDichMainActivity extends AppCompatActivity {
    static final int DATE_DIALOG_ID = 0;
    private ListView mListView;
    private CustomAdapter mCustomAdapter;
    Toast mToast;
    private ArrayList<LichSuGiaoDich> _Contacts = new ArrayList<LichSuGiaoDich>();

    DatabaseHandler db;
    LichSuGiaoDich contacts;

    @SuppressLint("NewApi") @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_giao_dich_main);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        db = new DatabaseHandler(this);
        db.open();


        for (int i = 0; i < db.lichsugiaodich().size(); i++) {
            contacts = new LichSuGiaoDich();
            contacts.setTime(db.lichsugiaodich().get(i).getTime());
            contacts.setPhanloai(db.lichsugiaodich().get(i).getPhanloai());
            contacts.setSotien(db.lichsugiaodich().get(i).getSotien());
            contacts.setTaikhoan(db.lichsugiaodich().get(i).getTaikhoan());
            _Contacts.add(contacts);

        }
        mListView = (ListView) findViewById(R.id.listView1);

        mCustomAdapter = new CustomAdapter(ListGiaoDichMainActivity.this,
                _Contacts);
        mListView.setAdapter(mCustomAdapter);
        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           int position, long id) {
                delcaidat(position);

                return false;
            }
        });

    }



    public void delcaidat(final int position) {
        new AlertDialog.Builder(ListGiaoDichMainActivity.this)
                .setTitle("Chú ý")
                .setMessage("Bạn có chắc xóa không")
                .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @TargetApi(11)
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        LayoutInflater inflater = getLayoutInflater();
                        View mToastView = inflater.inflate(R.layout.xoa_custom,
                                null);
                        mToast = new Toast(ListGiaoDichMainActivity.this);
                        mToast.setView(mToastView);
                        mToast.show();
                        db.Deletels(
                                db.lichsugiaodich().get(position).getTime(), db
                                        .lichsugiaodich().get(position)
                                        .getPhanloai(), db.lichsugiaodich()
                                        .get(position).getSotien(), db
                                        .lichsugiaodich().get(position)
                                        .getTaikhoan());
                        Intent intent = new Intent(getApplicationContext(),
                                ListGiaoDichMainActivity.class);
                        finish();
                        startActivity(intent);
                    }
                })
                .setNegativeButton("Không",
                        new DialogInterface.OnClickListener() {
                            @TargetApi(11)
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        }).show();

    }



}
