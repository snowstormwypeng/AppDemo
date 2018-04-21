package ViewModel;

import android.util.Log;
import android.view.View;
import android.widget.EditText;

import Class.*;

import com.example.helper.Convert;
import com.example.viewbinding.EditBind;
import com.example.viewbinding.TextBind;

/**
 * Created by wypen on 2018-04-18.
 */

public class Tcp_ViewModel extends Base_ViewModel implements IRecvEvent {
    public final EditBind Port=new EditBind("1346");
    public final TextBind recvData=new TextBind("");
    private String logTag=this.getClass().getSimpleName();
    private TcpServer server;

    public void StartTcpServer(View view)
    {
        server=new TcpServer(Integer.valueOf(Port.TextValue.get()));
        server.addListener(this);
        Log.d(logTag,"Tcp服务已启动");
    }

    @Override
    public void RecvEvent(TcpClient client, byte[] data) {
        recvData.Text.set(recvData.Text.get()+"\r\n"+ Convert.ByteArrayToHexString(data));
        client.SendData(data);

    }
}
