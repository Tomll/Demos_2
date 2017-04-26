package transage.com.windowmanager_demo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.widget.CompoundButton;
import android.widget.Switch;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 应用设置界面
 */
public class SettingActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {
    private Intent intent;
    private boolean switchOn;
    public static SharedPreferences sp;
    public static SharedPreferences.Editor editor;
    @BindView(R.id.switch0)
    Switch xpointSwitch;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);//ButterKnife初始化，必须紧跟在setContentView后面

        initDate();
        initView();
        xpointSwitch.setOnCheckedChangeListener(this);

    }

    /**
     * 初始化数据
     */
    private void initDate() {
        intent = new Intent(this, PointService.class);//指向PointService的intent
        sp = getSharedPreferences("xpoint", MODE_PRIVATE);
        editor = sp.edit();
        switchOn = sp.getBoolean("switch", false);
    }

    /**
     * 初始化view
     */
    private void initView() {
        if (switchOn) {
            xpointSwitch.setChecked(true);
        } else {
            xpointSwitch.setChecked(false);
        }
    }

    //开关监听
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            startService(intent);//开启PointService
            editor.putBoolean("switch", true);
        } else {
            stopService(intent);//关闭PointService
            editor.putBoolean("switch", false);
        }
        editor.commit();
    }
}
