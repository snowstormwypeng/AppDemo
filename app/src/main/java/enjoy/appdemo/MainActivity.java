package enjoy.appdemo;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import Class.*;

import java.util.ArrayList;
import java.util.List;

import ViewModel.MainActivity_ViewModel;
import ViewModel.TcpClient_ViewModel;
import ViewModel.Tcp_ViewModel;
import enjoy.appdemo.databinding.ActivityMainBinding;
import enjoy.appdemo.databinding.TcpclientViewBinding;
import enjoy.appdemo.databinding.TcpserverViewBinding;


public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    private MainActivity_ViewModel viewModel=new MainActivity_ViewModel();
    private Tcp_ViewModel tcpViewModel=new Tcp_ViewModel();
    private TcpClient_ViewModel tcpClient_viewModel=new TcpClient_ViewModel();

    ViewPager viewPager;
    FrameLayout layout_frame;
    List<View> list_view = new ArrayList<View>();
    List<ImageView> list_pointView = new ArrayList<ImageView>();

    //ViewPager 适配器
    ViewPagerAdapter adapter;

    // 两点之间间距
    int pointSpacing;
    String logTag=this.getClass().getSimpleName();
    Thread thread;

    private Runnable runnable=new Runnable() {
        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted())
            {
                try
                {
                    for (int i=0;i<100;i++) {
                        Log.d(logTag, String.valueOf(i));
                        Thread.sleep(5000);
                    }
                }
                catch (InterruptedException e)
                {
                    Log.d(logTag, "退出");
                    break;
                }
            }
        }
    };
    public void StartThread(View view)
    {
        thread=new Thread(runnable);
        thread.start();
    }
    public void StopThread(View view)
    {
        thread.interrupt();;
    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    viewPager.setCurrentItem(0);
                    return true;
                case R.id.navigation_dashboard:
                    viewPager.setCurrentItem(1);
                    return true;
                case R.id.navigation_notifications:
                    viewPager.setCurrentItem(2);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding dataBinding= DataBindingUtil.setContentView(this,R.layout.activity_main);
        dataBinding.setViewModel(viewModel);

        initView();
        initGuideView();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        viewModel.Tcp_Server_Port.Text.set("0");
    }
    /**
     * 初始化引导页相关
     */
    private void initGuideView() {
        //添加展示的View
        LayoutInflater inflater = LayoutInflater.from(this);
        TcpserverViewBinding tcpViewBinding=TcpserverViewBinding.inflate(inflater);
        tcpViewBinding.setViewModel(tcpViewModel);

        TcpclientViewBinding tcpclientViewBinding=TcpclientViewBinding.inflate(inflater);
        tcpclientViewBinding.setViewModel(tcpClient_viewModel);

        list_view.add(tcpViewBinding.getRoot());
        list_view.add(tcpclientViewBinding.getRoot());
        list_view.add(inflater.inflate(R.layout.bluetooth_view, null));
        adapter = new ViewPagerAdapter(list_view);
        viewPager.setAdapter(adapter);
    }

    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        layout_frame = (FrameLayout) findViewById(R.id.layout_frame);

        viewPager.setOnPageChangeListener(this);
    }

    @Override
    public void onPageScrollStateChanged(int arg0) {
    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {

    }

    @Override
    public void onPageSelected(int arg0) {

    }
}
