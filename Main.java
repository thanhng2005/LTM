package UDP;

public class Main {
}


















/*
| **2.1.1** | Tìm các số còn thiếu trong `1..n`, dãy đầu vào không trùng. |
| **2.1.2** | Sắp xếp các đoạn `chuỗi:vị_trí` rồi nối thành chuỗi hoàn chỉnh. |
| **2.1.3** | Tính tổng chữ số của một số nguyên lớn. |
| **2.1.4** | Tìm các số còn thiếu trong `1..n`, dãy đầu vào có thể trùng. |
| **2.1.5** | Tìm giá trị lớn nhất và nhỏ nhất của 50 số nguyên. |
| **2.1.6** | Giải mã Caesar với độ dịch `s`. |
| **2.1.7** | Tìm số lớn thứ hai và nhỏ thứ hai của 50 số nguyên. |
| **2.1.8** | Liệt kê `n` số nguyên tố đầu tiên. |
| **2.1.9** | Tìm max trong mỗi cửa sổ trượt kích thước `k`. |
| **2.1.10** | Tính tổng và hiệu `a - b` của hai số nguyên lớn. |
| **2.2.1** | Tìm ký tự xuất hiện nhiều nhất và liệt kê các vị trí xuất hiện. |
| **2.2.2** | Cộng hai số nhị phân, gửi tổng dưới dạng thập phân. |
| **2.2.3** | Xóa khỏi `str1` các ký tự có trong `str2`, giữ nguyên thứ tự. |
| **2.2.4** | Viết hoa chữ đầu mỗi từ, các chữ còn lại viết thường. |
| **2.2.5** | Loại số, ký tự đặc biệt và ký tự trùng; giữ thứ tự chữ cái. |
| **2.2.6** | Đếm từng ký tự rồi ghép dạng `3W1E2a…` theo thứ tự xuất hiện đầu tiên. |
| **2.2.7** | Đếm ký tự, xếp tần suất giảm dần; bằng nhau thì xếp theo bảng chữ cái. |
| **2.2.8** | Lọc các từ chỉ gồm nguyên âm `a, e, i, o, u`. |
| **2.2.9** | Sắp xếp các từ theo thứ tự từ điển giảm dần. |
| **2.3.1** | **Student:** chuẩn hóa tên, tạo email dạng `namnvt@ptit.edu.vn`. |
| **2.3.2** | **Employee:** chuẩn hóa tên, tăng lương theo tổng chữ số năm sinh, đổi ngày tuyển dụng sang `dd/mm/yyyy`. |
| **2.3.3** | **Customer:** tên dạng `DUONG, Nguyen Van Hai`, ngày sinh `dd/mm/yyyy`, tài khoản dạng `nvhduong`. |
| **2.3.4** | **Product:** đổi chỗ từ đầu/cuối của tên và đảo ngược chữ số số lượng. |
| **2.3.5** | **Book:** chuẩn hóa tiêu đề, tác giả `"Họ, Tên"`, ISBN có dấu gạch nối và ngày xuất bản `mm/yyyy`. |
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
