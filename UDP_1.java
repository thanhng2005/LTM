package UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

public class UDP_1 {
    public static void main(String[] args) throws Exception{
        DatagramSocket socket = new DatagramSocket();
        socket.setSoTimeout(5000);
        InetAddress host = InetAddress.getByName("36.50.135.242");
        int port = 2207;

        String msg = ";B23DCCN771;g1HjZ7Z2";
        byte[] sendBuf = msg.getBytes();
        socket.send(new DatagramPacket(sendBuf, sendBuf.length, host,port));

        byte[] buf = new byte[4096];
        DatagramPacket p = new DatagramPacket(buf, buf.length);
        socket.receive(p);
        String data = new String(p.getData(),0,p.getLength()).trim();
        System.out.println("Nhan: "+data);

        String[] parts = data.split(";");
        String requestId = parts[0];
        int n = Integer.parseInt(parts[1].trim());
        boolean[] check = new boolean[n+1];
        if(parts.length>2 && !parts[2].isBlank()){
            for(String s: parts[2].split(",")){
                check[Integer.parseInt(s.trim())]=true;
            }
        }

        List<String> thieu = new ArrayList<>();
        for(int i=1; i<=n;i++){
            if(!check[i]) thieu.add(String.valueOf(i));
        }
        String ans = requestId + ";" + String.join(",",thieu);

        byte[] gui = ans.getBytes();
        socket.send(new DatagramPacket(gui,gui.length,host,port));
        System.out.println("Gui: " +ans);

    }
}
