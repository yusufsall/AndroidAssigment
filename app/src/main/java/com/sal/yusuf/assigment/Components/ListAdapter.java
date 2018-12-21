package com.sal.yusuf.assigment.Components;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.sal.yusuf.assigment.Models.Order;
import com.sal.yusuf.assigment.R;
import com.sal.yusuf.assigment.Utils.Constants;

import java.util.List;

public class ListAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private List<Order> orders;
    private Activity activity;

    public ListAdapter(Activity activity, List<Order> orders){
        inflater = (LayoutInflater) activity.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        this.orders = orders;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return orders.size();
    }

    @Override
    public Object getItem(int i) {
        return orders.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View row = inflater.inflate(R.layout.row_layout,null);
        Order order = orders.get(i);

        TextView day = (TextView) row.findViewById(R.id.day);
        TextView month = (TextView) row.findViewById(R.id.month);
        TextView name = (TextView) row.findViewById(R.id.name);
        TextView ingridient = (TextView) row.findViewById(R.id.ingridient);
        TextView price = (TextView) row.findViewById(R.id.price);
        TextView state = (TextView) row.findViewById(R.id.state);
        TextView summary = (TextView) row.findViewById(R.id.summary);
        TextView summaryprice = (TextView) row.findViewById(R.id.summaryprice);
        View details = row.findViewById(R.id.detail);

        day.setText("" + order.date);
        month.setText(Constants.Aylar[order.month - 1]);
        name.setText(order.restaurantName);
        ingridient.setText(order.foodIngredients);
        price.setText(order.foodPrice + "₺");
        state.setText(order.state);
        summary.setText(order.detail.summary);
        summaryprice.setText(order.detail.summaryPrice+"₺");

        //Sipariş durumunun belirlenmesi ve ona göre view oluşturulması uzun bir işlem olduğu için ayrı bir fonksiyona alındı.
        setState(row, state, order.state);

        //Eğer ekran yenilenince detayların kaybolmasını istemiyorsak burdaki işlemle sürekli detaylar sekmesini
        //Belirlenmiş konumunda tutarız.
        if(order.isTouched)
            details.setVisibility(View.GONE);
        else
            details.setVisibility(View.VISIBLE);


        return row;
    }

    public void setState(View row, TextView state, String data){
        LinearLayout border = row.findViewById(R.id.stateborder);
        View square = row.findViewById(R.id.square);

        Drawable drawable;
        int stateColor;
        switch (data){
            case Constants.Hazirlaniyor:
                drawable = ContextCompat.getDrawable(activity, R.drawable.blackborder);
                stateColor = R.color.black;
                break;
            case Constants.Onay:
                drawable = ContextCompat.getDrawable(activity, R.drawable.orangeborder);
                stateColor = R.color.orange;
                break;
            case Constants.Yolda:
                drawable = ContextCompat.getDrawable(activity, R.drawable.greenborder);
                stateColor = R.color.green;
                break;
            default:
                drawable = ContextCompat.getDrawable(activity, R.drawable.blackborder);
                stateColor = R.color.black;
                break;
        }
        stateColor = ContextCompat.getColor(activity, stateColor);
        border.setBackground(drawable);
        state.setTextColor(stateColor);
        square.setBackgroundColor(stateColor);

    }


}
