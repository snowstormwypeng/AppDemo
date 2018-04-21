package Class;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by 王彦鹏 on 2018-04-18.
 */

public class TcpServer {
    private ServerSocket serverSocket;
    private ExecutorService executorService;
    private IRecvEvent recvEvent;
    private int port;

    public TcpServer(int port) {
        this.port=port;
        executorService = Executors.newSingleThreadExecutor();
        executorService.execute(runnable);
    }
    public void addListener(IRecvEvent listener)
    {
        this.recvEvent=listener;
    }
    private Runnable runnable=new Runnable() {
        @Override
        public void run() {
            try {
                serverSocket = new ServerSocket(port);
                while (!executorService.isShutdown()) {
                    Socket clientSocket = serverSocket.accept();
                    TcpClient client = new TcpClient(clientSocket);
                    client.addListener(recvEvent);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };
}
