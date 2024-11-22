import java.util.Scanner;

public class Main {
    private UserAccount userAccount;
    private Menu menu;

    public Main() {
        LibraryManagement libraryManagement = new LibraryManagement();
        userAccount = new UserAccount();
        menu = new Menu(libraryManagement, userAccount);
    }

    public static void main(String[] args) {
        Main system = new Main();
        system.showMenu();
    }

    public void showMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("==================================");
            System.out.println("|          TÀI KHOẢN             |");
            System.out.println("==================================");
            System.out.println("| 1. Đăng nhập                   |");
            System.out.println("| 2. Đăng ký                     |");
            System.out.println("| 0. Thoát chương trình          |");
            System.out.println("==================================");
            System.out.print("Nhập lựa chọn của bạn: ");
            int choice1 = scanner.nextInt();
            scanner.nextLine();
            boolean success = false;
            switch (choice1) {
                case 1:
                    success = userAccount.login(scanner);
                    break;
                case 2:
                    success = userAccount.register(scanner);
                    break;
                case 0:
                    System.out.println("Cảm ơn bạn đã sử dụng chương trình. Hẹn gặp lại!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
            }
            if (success) {
                menu.showLibraryMenu(scanner);
            }
        }
    }
}
