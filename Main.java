package UDP;

public class Main {
}
/*
package UDP;
//Data1
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

package UDP;
//String1
import java.net.*;
import java.util.*;

public class UDPChar_Cau1(UDP2) {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        socket.setSoTimeout(5000);
        InetAddress host = InetAddress.getByName("36.50.135.242");
        int port = 2207;

        String msg = ";B23DCCN771;39JrhBVM";
        byte[] sendBuf = msg.getBytes();
        socket.send(new DatagramPacket(sendBuf, sendBuf.length, host, port));

        byte[] buf = new byte[4096];
        DatagramPacket p = new DatagramPacket(buf, buf.length);
        socket.receive(p);
        String received = new String(p.getData(), 0, p.getLength()).trim();
        System.out.println("Nhan: " + received);

        String[] parts = received.split(";", 2);
        String requestId = parts[0];
        String data = parts[1].trim();

        Map<Character, Integer> dem = new HashMap<>();
        for (char c : data.toCharArray()) dem.put(c, dem.getOrDefault(c, 0) + 1);
        int max = Collections.max(dem.values());

        char best = 0;
        for (char c : data.toCharArray())
            if (dem.get(c) == max) { best = c; break; }

        String ans = requestId + ";" + best + ":";
        for (int i = 0; i < data.length(); i++)
            if (data.charAt(i) == best) ans += (i + 1) + ",";

        byte[] gui = ans.getBytes();
        socket.send(new DatagramPacket(gui, gui.length, host, port));
        System.out.println("Gui : " + ans);

        socket.close();
    }
}


package UDP;
//Data2
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
        int min = 99999999;
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

package UDP;
//String2
import java.net.*;

public class UDPChar_Cau4(UDP4) {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        socket.setSoTimeout(5000);

        InetAddress host = InetAddress.getByName("36.50.135.242");
        int port = 2208;

        // 1. Gửi yêu cầu
        String msg = ";B23DCCN771;JDYOdhPo";
        byte[] sendBuf = msg.getBytes();

        socket.send(new DatagramPacket(
                sendBuf, sendBuf.length, host, port));

        // 2. Nhận requestId;data
        byte[] buf = new byte[4096];
        DatagramPacket p = new DatagramPacket(buf, buf.length);
        socket.receive(p);

        String data = new String(p.getData(), 0, p.getLength());
        System.out.println("Nhan: " + data);

        String[] parts = data.split(";", 2);
        String requestId = parts[0];
        String s = parts[1];

        // 3. Chuẩn hóa chuỗi
        String output = "";
        boolean dauTu = true;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (Character.isWhitespace(c)) {
                dauTu = true;
            } else {
                if (dauTu) {
                    c = Character.toUpperCase(c);
                } else {
                    c = Character.toLowerCase(c);
                }

                dauTu = false;
            }

            output += c;
        }

        String ans = requestId + ";" + output;

        byte[] gui = ans.getBytes();
        socket.send(new DatagramPacket(
                gui, gui.length, host, port));

        System.out.println("Gui: " + ans);
        socket.close();
    }
}

package UDP;
//Obj1
import java.io.*;
import java.net.*;

public class UDPObject_Cau1(UDP5) {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        socket.setSoTimeout(5000);
        InetAddress host = InetAddress.getByName("36.50.135.242");
        int port = 2209;

        String msg = ";B23DCCN771;H6BOZKtv";                        // (a) van gui CHUOI binh thuong
        byte[] sendBuf = msg.getBytes();
        socket.send(new DatagramPacket(sendBuf, sendBuf.length, host, port));

        byte[] buf = new byte[4096];                                 // (b) nhan goi tin
        DatagramPacket p = new DatagramPacket(buf, buf.length);
        socket.receive(p);

        String requestId = new String(p.getData(), 0, 8);           // 8 byte dau -> requestId
        ObjectInputStream ois = new ObjectInputStream(
                new ByteArrayInputStream(p.getData(), 8, p.getLength() - 8));  // phan con lai -> doi tuong
        Student st = (Student) ois.readObject();
        System.out.println("Nhan: " + requestId + " | " + st);

        String[] words = st.getName().trim().toLowerCase().split("\\s+");      // (c) xu ly
        String name = "";
        for (String w : words) name += Character.toUpperCase(w.charAt(0)) + w.substring(1) + " ";
        st.setName(name.trim());                                     // "Nguyen Van Tuan Nam"

        String email = words[words.length - 1];                      // ten: "nam"
        for (int i = 0; i < words.length - 1; i++) email += words[i].charAt(0);   // + "nvt"
        st.setEmail(email + "@ptit.edu.vn");

        ByteArrayOutputStream baos = new ByteArrayOutputStream();    // dong goi: requestId + doi tuong
        baos.write(requestId.getBytes());                            // 8 byte dau
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(st);                                         // cac byte con lai
        oos.flush();
        byte[] gui = baos.toByteArray();
        socket.send(new DatagramPacket(gui, gui.length, host, port));
        System.out.println("Gui : " + requestId + " | " + st);

        socket.close();                                              // (d)
    }
}
class Student implements Serializable {
    private static final long serialVersionUID = 20171107;

    private String id;
    private String code;
    private String name;
    private String email;

    public Student(String code) {
        this.code = code;
    }

    public Student(String id, String code, String name, String email) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.email = email;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }

    @Override
    public String toString() {
        return "Student{id=" + id + ", code=" + code + ", name=" + name + ", email=" + email + "}";
    }
}
 */