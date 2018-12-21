package com.sal.yusuf.assigment.Utils;

public class Constants {
    //Uygulamaya giriş yapmak için gerekli kullanıcı adı ve şifre
    public static final String Username = "ankara";
    public static final String Password = "123hh4";

    //SharedPreferences Anahtar değerleri
    public static final String PrefName = "prefences";
    public static final String Userpref = "username";
    public static final String Passpref = "password";
    public static final String Rememberpref = "rememberme";

    //Verilerin çekileceği link
    public static final String Link = "https://steplinuxdiag318.blob.core.windows.net/mobiversite/restaurant.json";

    //Json verisinden aylar bize numara olarak gelen numara - 1 bu dizideki aya eşittir.
    public static final String[] Aylar = {"OCAK","ŞUBAT","MART","NİSAN","MAYIS","HAZİRAN","TEMMUZ","AĞUSTOS","EYLÜL","EKİM","KASIM","ARALIK"};

    //Sipariş durumunu belirlemek için tanımlanmış keyler
    public static final String Yolda = "Yolda";
    public static final String Onay = "Onay Bekliyor";
    public static final String Hazirlaniyor = "Hazırlanıyor";
}
