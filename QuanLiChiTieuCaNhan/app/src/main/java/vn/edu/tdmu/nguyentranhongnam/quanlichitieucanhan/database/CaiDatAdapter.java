package vn.edu.tdmu.nguyentranhongnam.quanlichitieucanhan.database;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import vn.edu.tdmu.nguyentranhongnam.quanlichitieucanhan.R;
import vn.edu.tdmu.nguyentranhongnam.quanlichitieucanhan.activity.CaiDat;



public class CaiDatAdapter extends ArrayAdapter<CaiDat> {
    private Activity activity;
    private int idLayout;
    private ArrayList<CaiDat> llist;

    public CaiDatAdapter(Activity activity, int idLayout, ArrayList<CaiDat> llist) {
        super(activity, idLayout,llist);
        this.activity=activity;
        this.idLayout=idLayout;
        this.llist=llist;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(activity);
        convertView=inflater.inflate(idLayout,null);
        TextView txtkhoanthukhoanchi = (TextView)convertView.findViewById(R.id.txtkhoanthukhoanchi2);
        TextView txtphanloai=(TextView)convertView.findViewById(R.id.txtphannhom2);

        txtkhoanthukhoanchi.setText(llist.get(position).getKhoanthukhoanchi());
        txtphanloai.setText(llist.get(position).getPhanloai());
        return convertView;
    }
}
