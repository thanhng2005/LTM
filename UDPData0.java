/*
package UDP;

import java.net.*;
import java.util.*;

// UDP Data type - Cau 1: tim cac gia tri con thieu trong 1..n
public class UDPData_Cau1 {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();                 // 1. Mo socket (khong can ket noi)
        socket.setSoTimeout(5000);
        InetAddress host = InetAddress.getByName("localhost");
        int port = 807;

        String msg = ";B23DCCN771;371EA16D";                          // 2a. Gui (chu y dau ";" o DAU chuoi)
        byte[] sendBuf = msg.getBytes();
        socket.send(new DatagramPacket(sendBuf, sendBuf.length, host, port));

        byte[] buf = new byte[4096];                                  // 2b. Nhan
        DatagramPacket p = new DatagramPacket(buf, buf.length);
        socket.receive(p);
        String data = new String(p.getData(), 0, p.getLength()).trim();
        System.out.println("Nhan: " + data);

        String[] parts = data.split(";");                             // 3. Xu ly
        String requestId = parts[0];
        int n = Integer.parseInt(parts[1].trim());
        boolean[] co = new boolean[n + 1];
        if (parts.length > 2 && !parts[2].isBlank())
            for (String s : parts[2].split(",")) co[Integer.parseInt(s.trim())] = true;

        List<String> thieu = new ArrayList<>();
        for (int i = 1; i <= n; i++) if (!co[i]) thieu.add(String.valueOf(i));
        String ans = requestId + ";" + String.join(",", thieu);       // nho gui kem requestId

        byte[] ansBuf = ans.getBytes();                               // 4. Gui ket qua
        socket.send(new DatagramPacket(ansBuf, ansBuf.length, host, port));
        System.out.println("Gui : " + ans);

        socket.close();                                               // 5. Dong
    }
}



------------------------------------------------------
package UDP;

import java.net.*;

public class UDPData_Cau2 {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        socket.setSoTimeout(5000);

        InetAddress host = InetAddress.getByName("localhost");
        int port = 807;

        // 1. Gửi yêu cầu
        String msg = ";B23DCCN771;C2";
        byte[] sendBuf = msg.getBytes();

        socket.send(new DatagramPacket(
                sendBuf, sendBuf.length, host, port));

        // 2. Nhận requestId;string
        byte[] buf = new byte[4096];
        DatagramPacket p = new DatagramPacket(buf, buf.length);
        socket.receive(p);

        String data = new String(p.getData(), 0, p.getLength());
        System.out.println("Nhan: " + data);

        String[] parts = data.split(";", 2);
        String requestId = parts[0];
        String[] pieces = parts[1].split(",");

        // 3. Đặt từng chuỗi vào đúng vị trí
        String[] ordered = new String[pieces.length];

        for (int i = 0; i < pieces.length; i++) {
            String[] item = pieces[i].split(":", 2);

            String text = item[0];
            int position = Integer.parseInt(item[1].trim());

            ordered[position - 1] = text;
        }

        // Nối các chuỗi theo thứ tự, không thêm dấu phân cách
        String output = "";

        for (int i = 0; i < ordered.length; i++) {
            output += ordered[i];
        }

        String ans = requestId + ";" + output;

        byte[] gui = ans.getBytes();
        socket.send(new DatagramPacket(
                gui, gui.length, host, port));

        System.out.println("Gui: " + ans);
        socket.close();
    }
}

--------------------------------------------------------
package UDP;

import java.net.*;

public class UDPData_Cau3 {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        socket.setSoTimeout(5000);

        InetAddress host = InetAddress.getByName("localhost");
        int port = 807;

        // 1. Gửi yêu cầu
        String msg = ";B23DCCN771;C3";
        byte[] sendBuf = msg.getBytes();

        socket.send(new DatagramPacket(
                sendBuf, sendBuf.length, host, port));

        // 2. Nhận requestId;num
        byte[] buf = new byte[4096];
        DatagramPacket p = new DatagramPacket(buf, buf.length);
        socket.receive(p);

        String data = new String(p.getData(), 0, p.getLength());
        System.out.println("Nhan: " + data);

        String[] parts = data.split(";", 2);
        String requestId = parts[0];
        String num = parts[1].trim();

        // 3. Tính tổng các chữ số
        int sumDigits = 0;

        for (int i = 0; i < num.length(); i++) {
            char c = num.charAt(i);

            if (c >= '0' && c <= '9') {
                sumDigits += c - '0';
            }
        }

        String ans = requestId + ";" + sumDigits;

        byte[] gui = ans.getBytes();
        socket.send(new DatagramPacket(
                gui, gui.length, host, port));

        System.out.println("Gui: " + ans);
        socket.close();
    }
}

--------------------------------------------------------
package UDP;

import java.net.*;

public class UDPData_Cau4 {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        socket.setSoTimeout(5000);

        InetAddress host = InetAddress.getByName("localhost");
        int port = 807;

        // 1. Gửi yêu cầu
        String msg = ";B23DCCN771;C4";
        byte[] sendBuf = msg.getBytes();

        socket.send(new DatagramPacket(
                sendBuf, sendBuf.length, host, port));

        // 2. Nhận requestId;n;danh sách số
        byte[] buf = new byte[4096];
        DatagramPacket p = new DatagramPacket(buf, buf.length);
        socket.receive(p);

        String data = new String(p.getData(), 0, p.getLength());
        System.out.println("Nhan: " + data);

        // Giữ phần danh sách nếu nó rỗng
        String[] parts = data.split(";", 3);
        String requestId = parts[0];
        int n = Integer.parseInt(parts[1].trim());

        // 3. Đánh dấu các số đã xuất hiện
        boolean[] co = new boolean[n + 1];

        if (!parts[2].trim().isEmpty()) {
            String[] numbers = parts[2].split(",");

            for (int i = 0; i < numbers.length; i++) {
                int x = Integer.parseInt(numbers[i].trim());

                if (x >= 1 && x <= n) {
                    co[x] = true;
                }
            }
        }

        // 4. Tìm các số còn thiếu từ 1 đến n
        String output = "";

        for (int i = 1; i <= n; i++) {
            if (!co[i]) {
                if (!output.isEmpty()) {
                    output += ",";
                }
                output += i;
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

-----------------------------------------
package UDP;

import java.net.*;

public class UDPData_Cau5 {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        socket.setSoTimeout(5000);

        InetAddress host = InetAddress.getByName("localhost");
        int port = 807;

        // 1. Gửi yêu cầu
        String msg = ";B23DCCN771;C5";
        byte[] sendBuf = msg.getBytes();

        socket.send(new DatagramPacket(
                sendBuf, sendBuf.length, host, port));

        // 2. Nhận requestId;a1,a2,...,a50
        byte[] buf = new byte[4096];
        DatagramPacket p = new DatagramPacket(buf, buf.length);
        socket.receive(p);

        String data = new String(
                p.getData(), 0, p.getLength()).trim();
        System.out.println("Nhan: " + data);

        String[] parts = data.split(";", 2);
        String requestId = parts[0];
        String[] numbers = parts[1].split(",");

        // 3. Tìm max và min
        int max = Integer.parseInt(numbers[0].trim());
        int min = max;

        for (int i = 1; i < numbers.length; i++) {
            int x = Integer.parseInt(numbers[i].trim());

            if (x > max) max = x;
            if (x < min) min = x;
        }

        String ans = requestId + ";" + max + "," + min;

        byte[] gui = ans.getBytes();
        socket.send(new DatagramPacket(
                gui, gui.length, host, port));

        System.out.println("Gui: " + ans);
        socket.close();
    }
}


------------------------------------------
package UDP;

import java.net.*;

public class UDPData_Cau6 {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        socket.setSoTimeout(5000);

        InetAddress host = InetAddress.getByName("localhost");
        int port = 807;

        String msg = ";B23DCCN771;C6";
        byte[] sendBuf = msg.getBytes();

        socket.send(new DatagramPacket(sendBuf, sendBuf.length, host, port));

        byte[] buf = new byte[4096];
        DatagramPacket p = new DatagramPacket(buf, buf.length);
        socket.receive(p);

        String data = new String(p.getData(), 0, p.getLength());
        System.out.println("Nhan: " + data);

        String[] parts = data.split(";");
        String requestId = parts[0];
        String strEncode = parts[1];
        int s = Integer.parseInt(parts[2].trim());

        s = ((s % 26) + 26) % 26;

        String strDecode = "";

        for (int i = 0; i < strEncode.length(); i++) {
            char c = strEncode.charAt(i);

            if (c >= 'A' && c <= 'Z') {
                c = (char) ('A' + (c - 'A' - s + 26) % 26);
            } else if (c >= 'a' && c <= 'z') {
                c = (char) ('a' + (c - 'a' - s + 26) % 26);
            }

            strDecode += c;
        }

        String ans = requestId + ";" + strDecode;

        byte[] gui = ans.getBytes();
        socket.send(new DatagramPacket(
                gui, gui.length, host, port));

        System.out.println("Gui: " + ans);

        socket.close();
    }
}


------------------------------------------
package UDP;

import java.net.*;

public class UDPData_Cau7 {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        socket.setSoTimeout(5000);

        InetAddress host = InetAddress.getByName("localhost");
        int port = 807;

        // 1. Gửi yêu cầu
        String msg = ";B23DCCN771;C7";
        byte[] sendBuf = msg.getBytes();

        socket.send(new DatagramPacket(
                sendBuf, sendBuf.length, host, port));

        // 2. Nhận requestId;danh sách số
        byte[] buf = new byte[4096];
        DatagramPacket p = new DatagramPacket(buf, buf.length);
        socket.receive(p);

        String data = new String(
                p.getData(), 0, p.getLength()).trim();
        System.out.println("Nhan: " + data);

        String[] parts = data.split(";", 2);
        String requestId = parts[0];
        String[] numbers = parts[1].split(",");

        int[] a = new int[numbers.length];
        for (int i = 0; i < a.length; i++) {
            a[i] = Integer.parseInt(numbers[i].trim());
        }

        // 3. Sắp xếp tăng dần
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = i + 1; j < a.length; j++) {
                if (a[j] < a[i]) {
                    int tmp = a[i];
                    a[i] = a[j];
                    a[j] = tmp;
                }
            }
        }

        // Tìm số đầu tiên khác giá trị nhỏ nhất
        int left = 1;
        while (left < a.length && a[left] == a[0]) {
            left++;
        }

        if (left == a.length) {
            socket.close();
            throw new IllegalArgumentException(
                    "Day khong co hai gia tri phan biet");
        }

        int secondMin = a[left];

        // Tìm số đầu tiên khác giá trị lớn nhất từ cuối mảng
        int right = a.length - 2;
        while (right >= 0 && a[right] == a[a.length - 1]) {
            right--;
        }

        int secondMax = a[right];

        String ans = requestId + ";" + secondMax + "," + secondMin;

        byte[] gui = ans.getBytes();
        socket.send(new DatagramPacket(
                gui, gui.length, host, port));

        System.out.println("Gui: " + ans);
        socket.close();
    }
}


------------------------------------------------------
package UDP;

import java.net.*;

public class UDPData_Cau8 {
    static boolean isPrime(int x) {
        if (x < 2) return false;

        for (int i = 2; i * i <= x; i++) {
            if (x % i == 0) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        socket.setSoTimeout(5000);

        InetAddress host = InetAddress.getByName("localhost");
        int port = 807;

        // 1. Gửi yêu cầu
        String msg = ";B23DCCN771;C8";
        byte[] sendBuf = msg.getBytes();

        socket.send(new DatagramPacket(
                sendBuf, sendBuf.length, host, port));

        // 2. Nhận requestId;n
        byte[] buf = new byte[4096];
        DatagramPacket p = new DatagramPacket(buf, buf.length);
        socket.receive(p);

        String data = new String(
                p.getData(), 0, p.getLength()).trim();
        System.out.println("Nhan: " + data);

        String[] parts = data.split(";", 2);
        String requestId = parts[0];
        int n = Integer.parseInt(parts[1].trim());

        // 3. Tìm n số nguyên tố đầu tiên
        String output = "";
        int count = 0;
        int x = 2;

        while (count < n) {
            if (isPrime(x)) {
                if (count > 0) {
                    output += ",";
                }

                output += x;
                count++;
            }

            x++;
        }

        String ans = requestId + ";" + output;

        byte[] gui = ans.getBytes();
        socket.send(new DatagramPacket(
                gui, gui.length, host, port));

        System.out.println("Gui: " + ans);
        socket.close();
    }
}


--------------------------------------
package UDP;

import java.net.*;

public class UDPData_Cau9 {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        socket.setSoTimeout(5000);

        InetAddress host = InetAddress.getByName("localhost");
        int port = 807;

        // 1. Gửi yêu cầu
        String msg = ";B23DCCN771;C9";
        byte[] sendBuf = msg.getBytes();

        socket.send(new DatagramPacket(
                sendBuf, sendBuf.length, host, port));

        // 2. Nhận requestId;n;k;danh sách số
        byte[] buf = new byte[4096];
        DatagramPacket p = new DatagramPacket(buf, buf.length);
        socket.receive(p);

        String data = new String(
                p.getData(), 0, p.getLength()).trim();
        System.out.println("Nhan: " + data);

        String[] parts = data.split(";", 4);
        String requestId = parts[0];
        int n = Integer.parseInt(parts[1].trim());
        int k = Integer.parseInt(parts[2].trim());
        String[] numbers = parts[3].split(",");

        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(numbers[i].trim());
        }

        // 3. Tìm max trong mỗi cửa sổ gồm k phần tử
        String output = "";

        for (int i = 0; i <= n - k; i++) {
            int max = a[i];

            for (int j = i + 1; j < i + k; j++) {
                if (a[j] > max) {
                    max = a[j];
                }
            }

            if (i > 0) {
                output += ",";
            }
            output += max;
        }

        String ans = requestId + ";" + output;

        byte[] gui = ans.getBytes();
        socket.send(new DatagramPacket(
                gui, gui.length, host, port));

        System.out.println("Gui: " + ans);
        socket.close();
    }
}


-------------------------------
package UDP;

import java.math.BigInteger;
import java.net.*;

public class UDPData_Cau10 {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        socket.setSoTimeout(5000);

        InetAddress host = InetAddress.getByName("localhost");
        int port = 807;

        // 1. Gửi yêu cầu, có dấu ; đầu theo ví dụ
        String msg = ";B23DCCN771;C10";
        byte[] sendBuf = msg.getBytes();

        socket.send(new DatagramPacket(
                sendBuf, sendBuf.length, host, port));

        // 2. Nhận requestId;a;b
        byte[] buf = new byte[4096];
        DatagramPacket p = new DatagramPacket(buf, buf.length);
        socket.receive(p);

        String data = new String(
                p.getData(), 0, p.getLength()).trim();
        System.out.println("Nhan: " + data);

        String[] parts = data.split(";", 3);
        String requestId = parts[0];

        BigInteger a = new BigInteger(parts[1].trim());
        BigInteger b = new BigInteger(parts[2].trim());

        // 3. Tính tổng và hiệu
        BigInteger sum = a.add(b);
        BigInteger difference = a.subtract(b);

        String ans = requestId + ";" + sum + ";" + difference;

        byte[] gui = ans.getBytes();
        socket.send(new DatagramPacket(
                gui, gui.length, host, port));

        System.out.println("Gui: " + ans);
        socket.close();
    }
}
 */