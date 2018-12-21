package com.sal.yusuf.assigment.Models;

import java.util.ArrayList;
import java.util.List;
/*
* Burdaki model jsondan gelen veriler birebir uyacak şekilde tasarlanmıştır.
*
* Json modeline ek olarak isTouched değerini taşır bu değer listviewde
* Bu siparişin detaylar sekmesinin açık olup olmadığını belirtir.
*
* */
public class Order {
    public int date;
    public int month;
    public String restaurantName;
    public String foodIngredients;
    public float foodPrice;
    public String state;
    public boolean isTouched = true;

    public Detail detail;
}
