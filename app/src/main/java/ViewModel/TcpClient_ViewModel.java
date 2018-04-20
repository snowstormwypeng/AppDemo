package ViewModel;

import android.view.View;

import com.example.viewbinding.ButtonBind;
import com.example.viewbinding.EditBind;
import Class.*;

/**
 * Created by wypen on 2018-04-20.
 */

public class TcpClient_ViewModel extends Base_ViewModel {
    private TcpClient client;

    public final EditBind Port=new EditBind("1346");
    public final EditBind HostIP=new EditBind("192.168.100.27");
    public final ButtonBind ConnectBtn=new ButtonBind("连接");
    public final ButtonBind DisconnectBtn=new ButtonBind("断开");
    public final ButtonBind SendBtn=new ButtonBind("发送");

    public void ConnectServer(View view)
    {
        client=new TcpClient(HostIP.TextValue.get(),Integer.valueOf(Port.TextValue.get()));
    }

    public void onSendData(View view)
    {
        byte[] data=new byte[16];
        data[0]=65;
        client.SendData(data);
    }
}
