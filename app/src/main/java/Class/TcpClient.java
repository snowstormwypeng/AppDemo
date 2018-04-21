package Class;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by wypen on 2018-04-18.
 */

public class TcpClient {
    private Socket socket;
    private IRecvEvent recvEvent;
    private ExecutorService executorService;
    private InputStream inputStream;
    private OutputStream outputStream;
    private Object syncObject=new Object();
    private String host;
    private int port;

    public TcpClient(Socket socket) {
        this.socket = socket;
        executorService = Executors.newSingleThreadExecutor();
        executorService.execute(runnable);
    }
    public TcpClient(String host,int port) {
        this.port = port;
        this.host = host;
        executorService = Executors.newSingleThreadExecutor();
        executorService.execute(runnable);
    }

    public void Close()
    {
        try {
            executorService.shutdown();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private Runnable runnable=new Runnable() {
        @Override
        public void run() {
            try {
                if (socket==null) {
                    socket = new Socket(host, port);
                }
                inputStream=socket.getInputStream();
                outputStream=socket.getOutputStream();
                while (!executorService.isShutdown()) {
                    synchronized (syncObject) {
                        try {
                            int len = inputStream.available();
                            if (len > 0) {
                                byte[] buf = new byte[len];
                                len = inputStream.read(buf);
                                if (recvEvent != null) {
                                    recvEvent.RecvEvent(TcpClient.this, buf);
                                }
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    Thread.sleep(10);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    };
    public void addListener(IRecvEvent listener)
    {
        this.recvEvent=listener;
    }
    public void SendData(byte[] data)
    {
        synchronized (syncObject)
        {
            try {
                outputStream.write(data);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
