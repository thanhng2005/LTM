/*
package UDP;

import java.io.*;
import java.net.*;

public class UDPObject_Cau1 {
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

------------------------------------------
package UDP;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

public class UDPObject_Cau2 {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        socket.setSoTimeout(5000);

        // Thay IP va ma cau hoi khi ket noi server that.
        InetAddress host = InetAddress.getByName("localhost");
        int port = 809;

        // 1. Gui yeu cau dang chuoi.
        String msg = ";B23DCCN771;C2";
        byte[] sendBuf = msg.getBytes(StandardCharsets.UTF_8);
        socket.send(new DatagramPacket(sendBuf, sendBuf.length, host, port));

        // 2. Nhan 8 byte requestId + doi tuong Employee.
        byte[] buf = new byte[4096];
        DatagramPacket p = new DatagramPacket(buf, buf.length);
        socket.receive(p);

        String requestId = new String(
                p.getData(), p.getOffset(), 8, StandardCharsets.US_ASCII);

        ObjectInputStream ois = new ObjectInputStream(
                new ByteArrayInputStream(
                        p.getData(), p.getOffset() + 8, p.getLength() - 8));
        Employee employee = (Employee) ois.readObject();
        System.out.println("Nhan: " + requestId + " | " + employee);

        // 3a. Chuan hoa ten: viet hoa chu dau tung tu.
        String[] words = employee.getName()
                .trim()
                .toLowerCase(Locale.ROOT)
                .split("\\s+");

        String name = "";
        for (String word : words) {
            name += Character.toUpperCase(word.charAt(0))
                    + word.substring(1) + " ";
        }
        employee.setName(name.trim());

        // 3b. Lay nam tu hireDate goc (yyyy-mm-dd).
        String[] date = employee.getHireDate().split("-");
        int namHire = Integer.parseInt(date[0]);
        int x = 0;

        while (namHire > 0) {
            x += namHire % 10;
            namHire /= 10;
        }

        // Tang luong x%, voi x la tong chu so nam duoc tuyen dung.
        double salary = employee.getSalary();
        employee.setSalary(salary + salary * x / 100);

        // 3c. Chuyen yyyy-mm-dd thanh dd/mm/yyyy.
        employee.setHireDate(date[2] + "/" + date[1] + "/" + date[0]);

        // 4. Gui lai: giu nguyen 8 byte requestId, sau do la doi tuong.
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        baos.write(p.getData(), p.getOffset(), 8);

        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(employee);
        oos.flush();

        byte[] gui = baos.toByteArray();
        socket.send(new DatagramPacket(gui, gui.length, host, port));
        System.out.println("Gui: " + requestId + " | " + employee);

        socket.close();
    }
}

class Employee implements Serializable {
    private static final long serialVersionUID = 20261107L;

    private String id;
    private String name;
    private double salary;
    private String hireDate;

    public Employee(String id, String name, double salary, String hireDate) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.hireDate = hireDate;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }

    public String getHireDate() { return hireDate; }
    public void setHireDate(String hireDate) { this.hireDate = hireDate; }

    @Override
    public String toString() {
        return "Employee{id=" + id
                + ", name=" + name
                + ", salary=" + salary
                + ", hireDate=" + hireDate + "}";
    }
}


---------------------------------
package UDP;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

public class UDPObject_Cau3 {
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


-------------------------------
package UDP;

import java.io.*;
import java.net.*;

public class UDPObject_Cau4 {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        socket.setSoTimeout(5000);

        InetAddress host = InetAddress.getByName("localhost");
        int port = 809;

        // 1. Gửi yêu cầu dạng chuỗi
        String msg = ";B23DCCN771;C4";
        byte[] sendBuf = msg.getBytes();

        socket.send(new DatagramPacket(
                sendBuf, sendBuf.length, host, port));

        // 2. Nhận 8 byte requestId + đối tượng Product
        byte[] buf = new byte[4096];
        DatagramPacket p = new DatagramPacket(buf, buf.length);
        socket.receive(p);

        String requestId = new String(
                p.getData(), p.getOffset(), 8);

        ObjectInputStream ois = new ObjectInputStream(
                new ByteArrayInputStream(
                        p.getData(), p.getOffset() + 8,
                        p.getLength() - 8));

        Product product = (Product) ois.readObject();
        System.out.println("Nhan: " + requestId + " | " + product);

        // 3a. Đổi chỗ từ đầu tiên và từ cuối cùng
        String[] words = product.getName().trim().split("\\s+");

        String tmp = words[0];
        words[0] = words[words.length - 1];
        words[words.length - 1] = tmp;

        product.setName(String.join(" ", words));

        // 3b. Đảo ngược số lượng
        int n = product.getQuantity();
        int dao = 0;

        while (n > 0) {
            dao = dao * 10 + n % 10;
            n /= 10;
        }

        product.setQuantity(dao);

        // 4. Đóng gói: 8 byte requestId + đối tượng đã sửa
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        baos.write(p.getData(), p.getOffset(), 8);

        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(product);
        oos.flush();

        byte[] gui = baos.toByteArray();
        socket.send(new DatagramPacket(
                gui, gui.length, host, port));

        System.out.println("Gui: " + requestId + " | " + product);
        socket.close();
    }
}

class Product implements Serializable {
    private static final long serialVersionUID = 20161107L;

    private String id;
    private String code;
    private String name;
    private int quantity;

    public Product(String id, String code, String name, int quantity) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.quantity = quantity;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Product{id=" + id
                + ", code=" + code
                + ", name=" + name
                + ", quantity=" + quantity + "}";
    }
}

------------------------------------------------------
package UDP;

import java.io.*;
import java.net.*;
import java.util.Locale;

public class UDPObject_Cau5 {
    // Viết hoa chữ đầu mỗi từ, các chữ còn lại viết thường
    static String chuanHoa(String s) {
        String[] words = s.trim()
                .toLowerCase(Locale.ROOT)
                .split("\\s+");

        String ans = "";

        for (String word : words) {
            if (word.isEmpty()) continue;

            if (!ans.isEmpty()) ans += " ";

            ans += Character.toUpperCase(word.charAt(0))
                    + word.substring(1);
        }

        return ans;
    }

    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        socket.setSoTimeout(5000);

        InetAddress host = InetAddress.getByName("localhost");
        int port = 809;

        // 1. Gửi yêu cầu dạng chuỗi
        String msg = ";B23DCCN771;C5";
        byte[] sendBuf = msg.getBytes();

        socket.send(new DatagramPacket(
                sendBuf, sendBuf.length, host, port));

        // 2. Nhận 8 byte requestId + đối tượng Book
        byte[] buf = new byte[4096];
        DatagramPacket p = new DatagramPacket(buf, buf.length);
        socket.receive(p);

        String requestId = new String(
                p.getData(), p.getOffset(), 8);

        ObjectInputStream ois = new ObjectInputStream(
                new ByteArrayInputStream(
                        p.getData(), p.getOffset() + 8,
                        p.getLength() - 8));

        Book book = (Book) ois.readObject();
        System.out.println("Nhan: " + requestId + " | " + book);

        // 3a. Chuẩn hóa title
        book.setTitle(chuanHoa(book.getTitle()));

        // 3b. Author: từ cuối là họ, đưa lên trước
        String[] words = chuanHoa(book.getAuthor()).split("\\s+");
        int last = words.length - 1;

        String author = words[last];

        if (last > 0) {
            author += ", ";

            for (int i = 0; i < last; i++) {
                if (i > 0) author += " ";
                author += words[i];
            }
        }

        book.setAuthor(author);

        // 3c. Bỏ dấu phân cách cũ, giữ lại các chữ số ISBN
        String digits = book.getIsbn().replaceAll("[^0-9]", "");

        if (digits.length() != 13) {
            socket.close();
            throw new IllegalArgumentException(
                    "ISBN can co 13 chu so theo mau de");
        }

        String isbn = digits.substring(0, 3) + "-"
                + digits.substring(3, 4) + "-"
                + digits.substring(4, 6) + "-"
                + digits.substring(6, 12) + "-"
                + digits.substring(12, 13);

        book.setIsbn(isbn);

        // 3d. Chuyển yyyy-mm-dd thành mm/yyyy
        String[] date = book.getPublishDate().split("-");
        book.setPublishDate(date[1] + "/" + date[0]);

        // 4. Đóng gói: 8 byte requestId + đối tượng đã sửa
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        baos.write(p.getData(), p.getOffset(), 8);

        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(book);
        oos.flush();

        byte[] gui = baos.toByteArray();
        socket.send(new DatagramPacket(
                gui, gui.length, host, port));

        System.out.println("Gui: " + requestId + " | " + book);
        socket.close();
    }
}

class Book implements Serializable {
    private static final long serialVersionUID = 20251107L;

    private String id;
    private String title;
    private String author;
    private String isbn;
    private String publishDate;

    public Book(String id, String title, String author,
                String isbn, String publishDate) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publishDate = publishDate;
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public String getPublishDate() { return publishDate; }
    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    @Override
    public String toString() {
        return "Book{id=" + id
                + ", title=" + title
                + ", author=" + author
                + ", isbn=" + isbn
                + ", publishDate=" + publishDate + "}";
    }
}
Nếu họ là từ đầu, giữ nguyên thứ tự các từ, Thay phần xử lý author bằng:.:
String[] words = chuanHoa(book.getAuthor()).split("\\s+");
String author = words[0];

if (words.length > 1) {
    author += ", ";

    for (int i = 1; i < words.length; i++) {
        if (i > 1) author += " ";
        author += words[i];
    }
}

book.setAuthor(author);
*/
