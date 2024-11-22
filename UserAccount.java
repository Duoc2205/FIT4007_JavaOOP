import java.io.*;
import java.util.Scanner;

public class UserAccount {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean login(Scanner scanner) {
        File file = new File("KHOLUUTRU.txt");
        if (!file.exists() || file.length() == 0) {
            System.out.println("Chưa có tài khoản nào. Vui lòng đăng ký.");
            return false;
        }
        System.out.print("Nhập tên đăng nhập: ");
        setUsername(scanner.nextLine());
        System.out.print("Nhập mật khẩu: ");
        setPassword(scanner.nextLine());
        boolean loginSuccess = checkLogin(getUsername(), getPassword());
        if (loginSuccess) {
            System.out.println("Đăng nhập thành công!");
        } else {
            System.out.println("Tên đăng nhập hoặc mật khẩu không đúng.");
        }
        return loginSuccess;
    }

    public boolean register(Scanner scanner) {
        System.out.print("Nhập tên đăng nhập: ");
        setUsername(scanner.nextLine());

        if (usernameExists(getUsername())) {
            System.out.println("Tên đăng nhập đã tồn tại. Vui lòng chọn tên đăng nhập khác.");
            return false;
        }

        System.out.print("Nhập mật khẩu: ");
        setPassword(scanner.nextLine());
        try (FileWriter fileWriter = new FileWriter("KHOLUUTRU.txt", true)) {
            fileWriter.write("Username: " + getUsername() + "\n");
            fileWriter.write("Password: " + getPassword() + "\n");
            fileWriter.write("-------------------------------\n");
            System.out.println("Đăng ký thành công!");
            return true;
        } catch (IOException e) {
            System.out.println("Có lỗi xảy ra khi lưu thông tin tài khoản.");
            e.printStackTrace();
            return false;
        }
    }

    private boolean checkLogin(String username, String password) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("KHOLUUTRU.txt"))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.contains("Username: " + username)) {
                    String passLine = bufferedReader.readLine();
                    if (passLine != null && passLine.contains("Password: " + password)) {
                        return true;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Có lỗi xảy ra khi đọc thông tin tài khoản.");
            e.printStackTrace();
        }
        return false;
    }

    private boolean usernameExists(String username) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("KHOLUUTRU.txt"))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.contains("Username: " + username)) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("Có lỗi xảy ra khi kiểm tra tên đăng nhập.");
            e.printStackTrace();
        }
        return false;
    }
}
