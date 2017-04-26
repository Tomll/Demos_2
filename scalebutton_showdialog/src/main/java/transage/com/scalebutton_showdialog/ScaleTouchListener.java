package transage.com.scalebutton_showdialog;

import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.OvershootInterpolator;

import java.lang.ref.WeakReference;

/**
 * like and nope的动画处理
 * Created by No_Chuxi on 2017/1/23.
 */

public class ScaleTouchListener implements View.OnTouchListener {

    private WeakReference<View> viewWeakReference;
    public ScaleTouchListener(View targetView) {
        viewWeakReference = new WeakReference<>(targetView);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // 手指按下执行缩小动画
               setState(true);
                break;

            case MotionEvent.ACTION_UP:
                // 手指放开，执行放大动画
                setState(false);
                break;
        }
        return false;
    }

   public void setState(boolean mIsDown){
       View view = getView();
       if(view == null){
           return;
       }
      if(!mIsDown){
          view.animate()
                  .scaleX(1.0f)
                  .scaleY(1.0f)
                  .setDuration(200)
                  .setInterpolator(new OvershootInterpolator())
                  .start();
      }else{
          view.animate()
                  .scaleX(0.9f)
                  .scaleY(0.9f)
                  .setDuration(200)
                  .setInterpolator(new OvershootInterpolator())
                  .start();
      }
   }
    @Nullable
    public View getView() {
        if (viewWeakReference == null || viewWeakReference.get() == null) {
            return null;
        }
        return viewWeakReference.get();
    }
}
