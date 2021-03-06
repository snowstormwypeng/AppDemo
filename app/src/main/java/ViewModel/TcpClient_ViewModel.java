package ViewModel;

import android.view.View;

import com.example.helper.Convert;
import com.example.viewbinding.ButtonBind;
import com.example.viewbinding.EditBind;
import com.example.viewbinding.TextBind;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import Class.*;

/**
 * Created by wypen on 2018-04-20.
 */

public class TcpClient_ViewModel extends Base_ViewModel {
    private TcpClient client;
    public final EditBind Port=new EditBind("1346");
    public final EditBind HostIP=new EditBind("127.0.0.1");
    public final ButtonBind ConnectBtn=new ButtonBind("连接");
    public final ButtonBind DisconnectBtn=new ButtonBind("断开");
    public final ButtonBind SendBtn=new ButtonBind("发送");
    public final TextBind recvData=new TextBind("");
    public final TextBind ConnState=new TextBind("未连接");

    private IRecvEvent recvEvent=new IRecvEvent() {
        @Override
        public void RecvEvent(TcpClient client, byte[] data) {
            recvData.Text.set(recvData.Text.get()+"\r\n"+ Convert.ByteArrayToHexString(data));
        }
    };

    public void ConnectServer(View view)
    {
        client = new TcpClient(HostIP.TextValue.get(), Integer.valueOf(Port.TextValue.get()));
        client.addListener(recvEvent);
    }

    public void onSendData(View view)
    {
        byte[] data=new byte[16];
        data[0]=01;
        data[15]=16;
        client.SendData(data);
    }
}
