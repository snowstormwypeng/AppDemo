package ViewModel;

import android.util.Log;
import android.view.View;
import Class.*;
import com.example.viewbinding.TextBind;

/**
 * Created by wypen on 2018-04-18.
 */

public class Tcp_ViewModel extends Base_ViewModel implements IRecvEvent {
    public final TextBind Port=new TextBind("0");
    private String logTag=this.getClass().getSimpleName();
    private TcpServer server;

    public void StartTcpServer(View view)
    {
        server=new TcpServer(Integer.valueOf(Port.Text.get()));
        server.addListener(this);
        Log.d(logTag,"Tcp服务已启动");
    }

    @Override
    public void RecvEvent(TcpClient client, byte[] data) {
        Log.d(logTag,"收到数据："+data.toString());

    }
}
