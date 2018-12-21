package com.sal.yusuf.assigment.Pages;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.sal.yusuf.assigment.Components.ListAdapter;
import com.sal.yusuf.assigment.Models.Detail;
import com.sal.yusuf.assigment.Models.Order;
import com.sal.yusuf.assigment.R;
import com.sal.yusuf.assigment.Utils.*;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView list;
    List<Order> orders = new ArrayList<>();

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                //Logouta dokununca yapılacak olanlar
                case R.id.logout:
                        ShowDialogBox();
                    return true;
            }
            return false;
        }
    };

    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which){
                //Kişi çıkış yapmak istediğinde önüne gelen diyalogta evete tıklarsa
                case DialogInterface.BUTTON_POSITIVE:
                    Database.RemoveRemember();
                    finish();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = (ListView) findViewById(R.id.list);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                //İnternetten json verimizin çekilmesi işlemi async biçimde yapılmalıdır.
                final String data = Network.GetDataFromUrl(Constants.Link);

                //Çekilen verimizin modele dönüştürülmesi ve ekranda gösterilmesi
                //Ana thread'de yapılmalıdır bunun için run on ui thread kullandık.
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        orders = Encoder.StringToModels(data);
                        ListAdapter adapter = new ListAdapter(MainActivity.this, orders);
                        list.setAdapter(adapter);
                    }
                });

            }
        });

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Order order = orders.get(i);
                Detail detail = order.detail;
                boolean isTouched = order.isTouched;
                View details = view.findViewById(R.id.detail);

                //Detaylar görünecek veya gizlenecek
                if(order.isTouched){
                    AnimationHelper.expand(details);
                }else{
                    AnimationHelper.collapse(details);
                }

                //Burda önceki değerinin tersine eşitlenir değer
                //Bu sayede listadapter görüntüyü tekrar çizerken
                //Yanlış çizim yapmayacaktır.
                order.isTouched = !isTouched;


            }
        });




    }


    private void ShowDialogBox(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("Çıkış Yapmak İstediğinize Emin misiniz ?").setPositiveButton("Evet", dialogClickListener)
                .setNegativeButton("Hayır", dialogClickListener).show();
    }

}
