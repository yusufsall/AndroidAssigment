package com.sal.yusuf.assigment.Utils;

import com.sal.yusuf.assigment.Models.Order;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.List;

/*
* Jsoup bir networking kütüphanesidir ve az kodla uğraşmamızı sağlar
* İşlem sayısını ve hataların azaltılması için bu kütüphane kullanılmıştır.
*
* */

public class Network {

    public static List<Order> GetData(){
        //İnternetten çekilen veri dataya yüklenmiştir.
        String data = GetDataFromUrl(Constants.Link);

        //Data encoder sınıfımız ile modele dönüştürülmüştür.
        return Encoder.StringToModels(data);
    }

    //Jsoup ile veri çekme
    public static String GetDataFromUrl(String url){
        String json;
        try {
            json = Jsoup.connect(url).ignoreContentType(true).execute().body();

        } catch (IOException e) {
            json = null;
            e.printStackTrace();
        }
        return json;
    }

}
