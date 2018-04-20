package Class;

import java.net.Socket;

/**
 * Created by 王彦鹏 on 2018-04-18.
 */

public interface IRecvEvent {
    void RecvEvent(TcpClient client, byte[] data);
}
