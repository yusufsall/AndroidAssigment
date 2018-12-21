package com.sal.yusuf.assigment.Utils;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.LinearLayout;
/*
* Bu kod parçası internetten alınmıştır ancak temel mantığı
* Details sekmesi önce görünmez yapılır sonra yukarı kaydırılarak
* Liste hücreleri arasında boşluk oluşması engellenir.
*
* */
public class AnimationHelper {
    public static void expand(final View view) {
        view.measure(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        final int targetHeight = view.getMeasuredHeight();

        //SDK 19'dan önce görünmez yapılan bir view tekrar görünür yapılırsa yüksekliği 0 olur.
        view.getLayoutParams().height = 1;
        view.setVisibility(View.VISIBLE);
        Animation a = new Animation()
        {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                view.getLayoutParams().height = interpolatedTime == 1
                        ? LinearLayout.LayoutParams.WRAP_CONTENT
                        : (int)(targetHeight * interpolatedTime);
                view.requestLayout();
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };
        //Saniyede 1 dp gidecek şekilde hesaplanmıştır.
        a.setDuration((int)(targetHeight / view.getContext().getResources().getDisplayMetrics().density));
        view.startAnimation(a);
    }

    public static void collapse(final View view) {
        final int initialHeight = view.getMeasuredHeight();

        Animation a = new Animation()
        {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if(interpolatedTime == 1){
                    view.setVisibility(View.GONE);
                }else{
                    view.getLayoutParams().height = initialHeight - (int)(initialHeight * interpolatedTime);
                    view.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        a.setDuration((int)(initialHeight / view.getContext().getResources().getDisplayMetrics().density));
        view.startAnimation(a);
    }
}
