package transage.com.scalebutton_showdialog;


import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.R.attr.height;
import static android.R.attr.width;

/**
 * 按钮点击缩放动画，修改系统dialog尺寸
 */
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.button1)
    LinearLayout button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        button1.setOnTouchListener(new ScaleTouchListener(button1));


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
        }

        File file = new File(Environment.getExternalStorageDirectory()+"/WhatsApp/Media/WhatsApp Video/Sent/asd.3gp");
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file), "video/*");
        //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = intent.getData();
        Log.d("MainActivity", "uri:" + uri);
        startActivity(intent); // Crashes on this line


    }







    @OnClick(R.id.button1)
    public void showAlertDialog() {
        //1、LayoutInflater加载出Dialog中的view,展示到dialog中
        //LayoutInflater inflater = LayoutInflater.from(this);
        //View view = inflater.inflate(R.layout.content, null);
        //1、当然，也可以直接new 出一个ImageView,展示到dialog中
        ImageView view = new ImageView(this);
        view.setImageResource(R.mipmap.dd);
        view.setScaleType(ImageView.ScaleType.FIT_XY);
        final Dialog dialog = new AlertDialog.Builder(this).create();
        dialog.show();
        dialog.setContentView(view);
        //2、测量出View的宽高 单位dp
        int intwidth = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int intheight = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(width, height);//这里的width 和 height 引的包是：import static android.R.attr.height; import static android.R.attr.width;
        intwidth = dip2px((float) view.getMeasuredWidth());  //view.getMeasuredWidth()获取到的是dp 需要转换为 px
        intheight = dip2px((float) view.getMeasuredHeight());
        //3、测量出屏幕的宽高 单位px
        DisplayMetrics dm = getResources().getDisplayMetrics();
        int screenWidth = dm.widthPixels;
        int screenHeight = dm.heightPixels;
        //4、判断View的宽高与屏幕的宽高的关系，然后对View的宽高进行等比例的缩放
        if (intwidth > intheight && intwidth > screenWidth) { //当view的实际宽度非常宽的时候，通过该逻辑控制宽度缩放
            Log.d("MainActivity", "intwidth > screenWidth");
            intheight = intheight * screenWidth / intwidth;
            intwidth = screenWidth;
        }
        if (intheight > intwidth && intheight > screenHeight / 2) { //当view的实际高度非常高的时候，通过该逻辑控制高度缩放
            Log.d("MainActivity", "intheight > screenHeight / 2");
            intwidth = (screenHeight / 2) * intwidth / intheight;
            intheight = screenHeight / 2;
        }
        //5、将缩放后的View的宽高 通过attributes设置给dialog.getWindow
        WindowManager.LayoutParams attributes = dialog.getWindow().getAttributes();
        attributes.width = intwidth;  //attributes.width用的是px单位
        attributes.height = intheight;
        dialog.getWindow().setAttributes(attributes);
    }

    public int dip2px(float dipValue) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }


}
