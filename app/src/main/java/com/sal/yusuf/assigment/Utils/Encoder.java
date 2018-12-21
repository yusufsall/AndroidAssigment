package com.sal.yusuf.assigment.Utils;

import android.util.Log;
import com.google.gson.*;
import com.sal.yusuf.assigment.Models.Detail;
import com.sal.yusuf.assigment.Models.Order;

import java.util.ArrayList;
import java.util.List;

public class Encoder {

    //Gson ile gelen json verimiz modele dönüştürülür.
    public static List<Order> StringToModels(String data){
        List<Order> orders = new ArrayList<>();
        Gson gson = new Gson();

        JsonArray main = gson.fromJson(data,JsonArray.class);

        for(JsonElement element : main ){
            Order order = gson.fromJson(element, Order.class);
            Detail detail = gson.fromJson(element.getAsJsonObject().getAsJsonObject("detail"),Detail.class);

            order.detail = detail;

            orders.add(order);

        }
        return  orders;




    }
}
