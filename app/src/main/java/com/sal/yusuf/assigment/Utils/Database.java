package com.sal.yusuf.assigment.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
/*
*
* Bu işlem yapılırken shared prefences tercih edilmiştir çünkü database veya dosya kullanılarak
* kullanıcıdan sdcarda erişim izni istemek istenmemiştir.
*
* Ayrıca sharedpreferences diğer yöntemlere kıyasla daha kolay ve hata çıkarma ihtimali
* daha düşüktür.
*
* */
public class Database {
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;

    //Shared Prefences'a erişmek için
    private static void Prepare(Activity activity){
        sharedPreferences = activity.getSharedPreferences(Constants.PrefName, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    //Remember değerleri kaydedilir.
    public static void RememberMe(Activity activity,String username, String password){
        //Eğer giriş yapılmamışsa yapılır.
        if(editor == null)
            Prepare(activity);

        //Veriler güncellenir.
        editor.putString(Constants.Userpref, username);
        editor.putString(Constants.Passpref, password);
        editor.putBoolean(Constants.Rememberpref, true);

        //Ve kayıt işlemi yapılır.
        editor.apply();
    }

    //Bu fonksiyon ile daha önce remember yapılıp yapılmadığı incelenir.
    public static boolean isRemembered(Activity activity){
        if(sharedPreferences == null)
            Prepare(activity);
        return sharedPreferences.getBoolean(Constants.Rememberpref, false);
    }

    //Bu fonksiyon ile çıkış yapıldıktan sonra remember değerleri silinir.
    public static void RemoveRemember(){
        editor.putBoolean(Constants.Rememberpref, false);
        editor.apply();
    }




}
