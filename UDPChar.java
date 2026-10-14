/*
package UDP;

import java.net.*;
import java.util.*;

public class UDPChar_Cau1 {
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


---------------------------------------
package UDP;

import java.net.*;

public class UDPChar_Cau2 {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        socket.setSoTimeout(5000);

        InetAddress host = InetAddress.getByName("localhost");
        int port = 808;

        // 1. Gửi yêu cầu
        String msg = ";B23DCCN771;C2";
        byte[] sendBuf = msg.getBytes();

        socket.send(new DatagramPacket(
                sendBuf, sendBuf.length, host, port));

        // 2. Nhận requestId;b1,b2
        byte[] buf = new byte[4096];
        DatagramPacket p = new DatagramPacket(buf, buf.length);
        socket.receive(p);

        String data = new String(
                p.getData(), 0, p.getLength()).trim();
        System.out.println("Nhan: " + data);

        String[] parts = data.split(";");
        String requestId = parts[0];
        String[] s = parts[1].split(",");

        // 3. Chuyển từ hệ 2 sang số nguyên và tính tổng
        int b1 = Integer.parseInt(s[0].trim(), 2);
        int b2 = Integer.parseInt(s[1].trim(), 2);
        int sum = b1 + b2;

        String ans = requestId + ";" + sum;

        byte[] gui = ans.getBytes();
        socket.send(new DatagramPacket(
                gui, gui.length, host, port));

        System.out.println("Gui: " + ans);
        socket.close();
    }
}



------------------------------
package UDP;

import java.net.*;

public class UDPChar_Cau3 {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        socket.setSoTimeout(5000);

        InetAddress host = InetAddress.getByName("localhost");
        int port = 808;

        // 1. Gửi yêu cầu
        String msg = ";B23DCCN771;C3";
        byte[] sendBuf = msg.getBytes();

        socket.send(new DatagramPacket(sendBuf, sendBuf.length, host, port));

        // 2. Nhận requestId;str1;str2
        byte[] buf = new byte[4096];
        DatagramPacket p = new DatagramPacket(buf, buf.length);
        socket.receive(p);

        String data = new String(p.getData(), 0, p.getLength());
        System.out.println("Nhan: " + data);

        // -1 giúp giữ phần tử rỗng nếu str2 rỗng
        String[] parts = data.split(";", -1);
        String requestId = parts[0];
        String str1 = parts[1];
        String str2 = parts[2];

        // 3. Giữ các ký tự không xuất hiện trong str2
        String strOutput = "";

        for (int i = 0; i < str1.length(); i++) {
            char c = str1.charAt(i);

            if (str2.indexOf(c) == -1) {
                strOutput += c;
            }
        }

        String ans = requestId + ";" + strOutput;

        byte[] gui = ans.getBytes();
        socket.send(new DatagramPacket(
                gui, gui.length, host, port));

        System.out.println("Gui: " + ans);

        // 4. Đóng socket
        socket.close();
    }
}


----------------------------------
package UDP;

import java.net.*;

public class UDPChar_Cau4 {
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


-------------------------------------
package UDP;

import java.net.*;

public class UDPChar_Cau5 {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        socket.setSoTimeout(5000);

        InetAddress host = InetAddress.getByName("localhost");
        int port = 808;

        // 1. Gửi yêu cầu
        String msg = ";B23DCCN771;C5";
        byte[] sendBuf = msg.getBytes();

        socket.send(new DatagramPacket(
                sendBuf, sendBuf.length, host, port));

        // 2. Nhận requestId;strInput
        byte[] buf = new byte[4096];
        DatagramPacket p = new DatagramPacket(buf, buf.length);
        socket.receive(p);

        String data = new String(p.getData(), 0, p.getLength());
        System.out.println("Nhan: " + data);

        String[] parts = data.split(";", 2);
        String requestId = parts[0];
        String strInput = parts[1];

        // 3. Giữ chữ cái chưa xuất hiện trong output
        String output = "";

        for (int i = 0; i < strInput.length(); i++) {
            char c = strInput.charAt(i);

            if (Character.isLetter(c) && output.indexOf(c) == -1) {
                output += c;
            }
        }

        String ans = requestId + ";" + output;

        byte[] gui = ans.getBytes();
        socket.send(new DatagramPacket(
                gui, gui.length, host, port));

        System.out.println("Gui: " + ans);
        socket.close();
    }
}


----------------------------------------
package UDP;

import java.net.*;

public class UDPChar_Cau6 {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        socket.setSoTimeout(5000);

        InetAddress host = InetAddress.getByName("localhost");
        int port = 808;

        // 1. Gửi yêu cầu
        String msg = ";B23DCCN771;C6";
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

        // 3. Đếm và gom ký tự
        String tmp = "";
        String processedData = "";

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            // Bỏ qua ký tự đã được đếm
            if (tmp.indexOf(c) != -1) {
                continue;
            }

            int count = 0;
            for (int j = i; j < s.length(); j++) {
                if (s.charAt(j) == c) {
                    count++;
                }
            }

            tmp += c;
            processedData += String.valueOf(count) + c;
        }

        String ans = requestId + ";" + processedData;

        byte[] gui = ans.getBytes();
        socket.send(new DatagramPacket(
                gui, gui.length, host, port));

        System.out.println("Gui: " + ans);
        socket.close();
    }
}


------------------------------------
package UDP;

import java.net.*;

public class UDPChar_Cau7 {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        socket.setSoTimeout(5000);

        InetAddress host = InetAddress.getByName("localhost");
        int port = 808;

        // 1. Gửi yêu cầu, có dấu ; đầu theo ví dụ của đề
        String msg = ";B23DCCN771;C7";
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

        // 3. Đếm từng ký tự
        char[] kyTu = new char[s.length()];
        int[] count = new int[s.length()];
        String tmp = "";
        int n = 0; // Số ký tự khác nhau

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (tmp.indexOf(c) != -1) {
                continue;
            }

            tmp += c;
            kyTu[n] = c;

            for (int j = i; j < s.length(); j++) {
                if (s.charAt(j) == c) {
                    count[n]++;
                }
            }

            n++;
        }

        // 4. Sắp xếp, đổi chỗ cả ký tự và số lần xuất hiện
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (count[j] > count[i]
                        || (count[j] == count[i]
                        && kyTu[j] < kyTu[i])) {

                    int tempCount = count[i];
                    count[i] = count[j];
                    count[j] = tempCount;

                    char tempChar = kyTu[i];
                    kyTu[i] = kyTu[j];
                    kyTu[j] = tempChar;
                }
            }
        }

        // 5. Ghép kết quả, không có dấu phẩy thừa cuối
        String output = "";

        for (int i = 0; i < n; i++) {
            if (i > 0) {
                output += ",";
            }

            output += kyTu[i] + ":" + count[i];
        }

        String ans = requestId + ";" + output;

        byte[] gui = ans.getBytes();
        socket.send(new DatagramPacket(
                gui, gui.length, host, port));

        System.out.println("Gui: " + ans);
        socket.close();
    }
}


-------------------------
package UDP;

import java.net.*;

public class UDPChar_Cau8 {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        socket.setSoTimeout(5000);

        InetAddress host = InetAddress.getByName("localhost");
        int port = 808;

        // 1. Gửi yêu cầu, có dấu ; đầu theo ví dụ
        String msg = ";B23DCCN771;C8";
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
        String[] words = parts[1].trim().split("\\s+");

        // 3. Tìm các từ chỉ gồm nguyên âm
        String nguyenAm = "aeiou";
        String output = "";

        for (String word : words) {
            if (word.isEmpty()) {
                continue;
            }

            boolean hopLe = true;

            for (int i = 0; i < word.length(); i++) {
                char c = Character.toLowerCase(word.charAt(i));

                if (nguyenAm.indexOf(c) == -1) {
                    hopLe = false;
                    break;
                }
            }

            if (hopLe) {
                if (!output.isEmpty()) {
                    output += ",";
                }
                output += word;
            }
        }

        String ans = requestId + ";" + output;

        byte[] gui = ans.getBytes();
        socket.send(new DatagramPacket(
                gui, gui.length, host, port));

        System.out.println("Gui: " + ans);
        socket.close();
    }
}


------------------------------
package UDP;

import java.net.*;

public class UDPChar_Cau9 {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        socket.setSoTimeout(5000);

        InetAddress host = InetAddress.getByName("localhost");
        int port = 808;

        // 1. Gửi yêu cầu, có dấu ; đầu theo ví dụ
        String msg = ";B23DCCN771;C9";
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
        String[] words = parts[1].trim().split("\\s+");

        // 3. Sắp xếp các từ giảm dần
        for (int i = 0; i < words.length - 1; i++) {
            for (int j = i + 1; j < words.length; j++) {
                if (words[j].compareTo(words[i]) > 0) {
                    String tmp = words[i];
                    words[i] = words[j];
                    words[j] = tmp;
                }
            }
        }

        // 4. Ghép các từ bằng dấu phẩy
        String output = "";

        for (int i = 0; i < words.length; i++) {
            if (i > 0) {
                output += ",";
            }
            output += words[i];
        }

        String ans = requestId + ";" + output;

        byte[] gui = ans.getBytes();
        socket.send(new DatagramPacket(
                gui, gui.length, host, port));

        System.out.println("Gui: " + ans);
        socket.close();
    }
}

---------------------------
package UDP;
//obj2
import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

public class UDPObject_Cau3(UDP6) {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        socket.setSoTimeout(5000);

        // Thay IP va ma cau hoi khi ket noi server that.
        InetAddress host = InetAddress.getByName("36.50.135.242");
        int port = 2209;

        // 1. Gui yeu cau dang chuoi.
        String msg = ";B23DCCN771;b0wGfCAf";
        byte[] sendBuf = msg.getBytes(StandardCharsets.UTF_8);
        socket.send(new DatagramPacket(sendBuf, sendBuf.length, host, port));

        // 2. Nhan 8 byte requestId + doi tuong Customer.
        byte[] buf = new byte[4096];
        DatagramPacket p = new DatagramPacket(buf, buf.length);
        socket.receive(p);

        String requestId = new String(
                p.getData(), p.getOffset(), 8, StandardCharsets.US_ASCII);

        ObjectInputStream ois = new ObjectInputStream(
                new ByteArrayInputStream(
                        p.getData(), p.getOffset() + 8, p.getLength() - 8));
        Customer customer = (Customer) ois.readObject();
        System.out.println("Nhan: " + requestId + " | " + customer);

        // Lay cac tu tu ten GOC, truoc khi gan ten moi.
        String[] words = customer.getName()
                .trim()
                .toLowerCase(Locale.ROOT)
                .split("\\s+");
        int last = words.length - 1;

        // 3a. userName: chu dau ho va ten dem + nguyen tu cuoi.
        String userName = "";
        for (int i = 0; i < last; i++) {
            userName += words[i].charAt(0);
        }
        userName += words[last];

        // 3b. Ten: TU CUOI VIET HOA, cac tu truoc viet hoa chu dau.
        String name = words[last].toUpperCase(Locale.ROOT);
        if (last > 0) {
            name += ", ";
            for (int i = 0; i < last; i++) {
                if (i > 0) {
                    name += " ";
                }
                name += Character.toUpperCase(words[i].charAt(0))
                        + words[i].substring(1);
            }
        }

        // 3c. Chuyen mm-dd-yyyy thanh dd/mm/yyyy.
        String[] date = customer.getDayOfBirth().split("-");
        String dayOfBirth = date[1] + "/" + date[0] + "/" + date[2];

        customer.setName(name);
        customer.setDayOfBirth(dayOfBirth);
        customer.setUserName(userName);

        // 4. Gui lai: giu nguyen 8 byte requestId + doi tuong da sua.
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        baos.write(p.getData(), p.getOffset(), 8);

        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(customer);
        oos.flush();

        byte[] gui = baos.toByteArray();
        socket.send(new DatagramPacket(gui, gui.length, host, port));
        System.out.println("Gui: " + requestId + " | " + customer);

        socket.close();
    }
}
class Customer implements Serializable {
    private static final long serialVersionUID = 20151107L;

    private String id;
    private String code;
    private String name;
    private String dayOfBirth;
    private String userName;

    public Customer(String id, String code, String name,
                    String dayOfBirth, String userName) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.dayOfBirth = dayOfBirth;
        this.userName = userName;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDayOfBirth() { return dayOfBirth; }
    public void setDayOfBirth(String dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
    }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    @Override
    public String toString() {
        return "Customer{id=" + id
                + ", code=" + code
                + ", name=" + name
                + ", dayOfBirth=" + dayOfBirth
                + ", userName=" + userName + "}";
    }
}

 */