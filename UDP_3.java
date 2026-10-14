package UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

public class UDP_3 {
    public static void main(String[] args) throws Exception{
        DatagramSocket socket = new DatagramSocket();
        socket.setSoTimeout(5000);
        InetAddress host = InetAddress.getByName("36.50.135.242");
        int port = 2207;

        String msg = ";B23DCCN771;tneicleJ";
        byte[] sendBuf = msg.getBytes();
        socket.send(new DatagramPacket(sendBuf, sendBuf.length, host,port));

        byte[] buf = new byte[4096];
        DatagramPacket p = new DatagramPacket(buf, buf.length);
        socket.receive(p);
        String data = new String(p.getData(),0,p.getLength()).trim();
        System.out.println("Nhan: "+data);

        String[] parts = data.split(";");
        String requestId = parts[0];
        String[] s = parts[1].split(",");
        int max = 0;
        int min = 99999999a;
        for(int i=0; i<s.length;i++){
            int tmp = Integer.parseInt(s[i]);
            if(tmp>max) max =tmp;
            if(tmp<min) min=tmp;
        }
        String ans = requestId+";"+max+","+min;

        byte[] gui = ans.getBytes();
        socket.send(new DatagramPacket(gui,gui.length,host,port));
        System.out.println("Gui: " +ans);

    }
}
