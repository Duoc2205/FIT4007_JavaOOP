import java.util.Scanner;

public class Menu {
    private LibraryManagement libraryManagement;
    private UserAccount userAccount;

    public Menu(LibraryManagement libraryManagement, UserAccount userAccount) {
        this.libraryManagement = libraryManagement;
        this.userAccount = userAccount;
    }

    public void showLibraryMenu(Scanner scanner) {
        while (true) {
            System.out.println("\n==================================");
            System.out.println("|         KHO THƯ VIỆN           |");
            System.out.println("==================================");
            System.out.println("Tài khoản hiện tại: " + userAccount.getUsername());
            System.out.println("==================================");
            System.out.println("| 1. Quản lý sách                |");
            System.out.println("| 2. Quản lý hội viên            |");
            System.out.println("| 3. Quản lý mượn trả            |");
            System.out.println("| 4. Hệ thống                    |");
            System.out.println("| 0. Đăng xuất tài khoản         |");
            System.out.println("==================================");
            System.out.print("Nhập lựa chọn của bạn: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    showBookManagementMenu(scanner);
                    break;
                case 2:
                    showMemberManagementMenu(scanner);
                    break;
                case 3:
                    showBorrowReturnMenu(scanner);
                    break;
                case 4:
                    showSystemMenu(scanner);
                    break;
                case 0:
                    System.out.println("Đăng xuất tài khoản!");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ! Vui lòng thử lại.\n");
            }
        }
    }

    private void showBookManagementMenu(Scanner scanner) {
        while (true) {
            System.out.println("\n==================================");
            System.out.println("|        QUẢN LÝ SÁCH             |");
            System.out.println("==================================");
            System.out.println("| 1. Thêm sách                   |");
            System.out.println("| 2. Xóa sách                    |");
            System.out.println("| 3. Tìm kiếm sách               |");
            System.out.println("| 4. Sắp xếp sách                |");
            System.out.println("| 5. Cập nhật số lượng sách      |");
            System.out.println("| 6. Hiển thị tất cả sách        |");
            System.out.println("| 7. Quay lại                    |");
            System.out.println("==================================");
            System.out.print("Nhập lựa chọn của bạn: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    libraryManagement.addBooks();
                    break;
                case 2:
                    System.out.print("Nhập mã sách, tên sách hoặc tên tác giả để xóa: ");
                    String removeInput = scanner.nextLine();
                    libraryManagement.removeBook(removeInput);
                    break;
                case 3:
                    System.out.print("Nhập mã sách, tên sách hoặc tên tác giả để tìm kiếm: ");
                    String searchInput = scanner.nextLine();
                    libraryManagement.searchBook(searchInput);
                    break;
                case 4:
                    libraryManagement.sortBooks();
                    break;
                case 5:
                    System.out.print("Nhập mã sách để cập nhật số lượng: ");
                    String bookIdToUpdate = scanner.nextLine();
                    System.out.print("Nhập số lượng mới: ");
                    int newQuantity = scanner.nextInt();
                    libraryManagement.updateBookQuantity(bookIdToUpdate, newQuantity);
                    break;
                case 6:
                    libraryManagement.displayAllBooks();
                    break;
                case 7:
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ! Vui lòng thử lại.\n");
            }
        }
    }

    private void showMemberManagementMenu(Scanner scanner) {
        while (true) {
            System.out.println("\n==================================");
            System.out.println("|        QUẢN LÝ HỘI VIÊN         |");
            System.out.println("==================================");
            System.out.println("| 1. Đăng ký hội viên            |");
            System.out.println("| 2. Hiển thị hội viên           |");
            System.out.println("| 3. Tìm kiếm hội viên theo tên  |");
            System.out.println("| 4. Quay lại                    |");
            System.out.println("==================================");
            System.out.print("Nhập lựa chọn của bạn: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Nhập mã hội viên: ");
                    String memberId = scanner.nextLine();
                    System.out.print("Nhập họ và tên: ");
                    String fullName = scanner.nextLine();
                    System.out.print("Nhập giới tính: ");
                    String gender = scanner.nextLine();
                    System.out.print("Nhập số điện thoại: ");
                    String phoneNumber = scanner.nextLine();
                    System.out.print("Nhập ngày sinh: ");
                    String birthDate = scanner.nextLine();
                    System.out.print("Nhập địa chỉ: ");
                    String address = scanner.nextLine();
                    libraryManagement.registerMember(memberId, fullName, gender, phoneNumber, birthDate, address);
                    break;
                case 2:
                    libraryManagement.displayMembers();
                    break;
                case 3:
                    System.out.print("Nhập tên hội viên cần tìm: ");
                    String memberName = scanner.nextLine();
                    libraryManagement.searchMemberByName(memberName);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ! Vui lòng thử lại.\n");
            }
        }
    }

    private void showBorrowReturnMenu(Scanner scanner) {
        while (true) {
            System.out.println("\n==================================");
            System.out.println("|        QUẢN LÝ MƯỢN TRẢ         |");
            System.out.println("==================================");
            System.out.println("| 1. Mượn sách                   |");
            System.out.println("| 2. Trả sách                    |");
            System.out.println("| 3. Hiển thị sách mượn          |");
            System.out.println("| 4. Gia hạn sách                |");
            System.out.println("| 5. Hiển thị sách trễ hạn trả   |");
            System.out.println("| 6. Quay lại                    |");
            System.out.println("==================================");
            System.out.print("Nhập lựa chọn của bạn: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Nhập mã hội viên: ");
                    String memberId = scanner.nextLine();
                    System.out.print("Nhập mã sách: ");
                    String bookIdToBorrow = scanner.nextLine();
                    System.out.print("Nhập số lượng: ");
                    int borrowQuantity = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Nhập ngày mượn: ");
                    String borrowDate = scanner.nextLine();
                    libraryManagement.borrowBook(memberId, bookIdToBorrow, borrowQuantity, borrowDate);
                    break;
                case 2:
                    System.out.print("Nhập mã sách để trả: ");
                    String bookIdToReturn = scanner.nextLine();
                    libraryManagement.returnBook(bookIdToReturn);
                    break;
                case 3:
                    libraryManagement.displayBorrowedBooks();
                    break;
                case 4:
                    System.out.print("Nhập mã sách để gia hạn: ");
                    String bookIdToExtend = scanner.nextLine();
                    libraryManagement.extendBorrowDate(bookIdToExtend);
                    break;
                case 5:
                    libraryManagement.displayOverdueBooks();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ! Vui lòng thử lại.\n");
            }
        }
    }

    private void showSystemMenu(Scanner scanner) {
        while (true) {
            System.out.println("\n==================================");
            System.out.println("|            HỆ THỐNG            |");
            System.out.println("==================================");
            System.out.println("| 1. Lưu vào tệp                 |");
            System.out.println("| 2. Khôi phục dữ liệu từ tệp    |");
            System.out.println("| 3. Xuất dữ liệu thống kê       |");
            System.out.println("| 4. Thống kê số lượng sách theo |");
            System.out.println("|    thể loại                    |");
            System.out.println("| 5. Thông báo sách cần bổ sung  |");
            System.out.println("| 6. Quay lại                    |");
            System.out.println("==================================");
            System.out.print("Nhập lựa chọn của bạn: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    libraryManagement.saveToFile();
                    break;
                case 2:
                    libraryManagement.loadDataFromFile();
                    break;
                case 3:
                    libraryManagement.exportStatisticsToFile();
                    break;
                case 4:
                    libraryManagement.displayBookStatisticsByCategory();
                    break;
                case 5:
                    libraryManagement.notifyBooksToReplenish();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ! Vui lòng thử lại.\n");
            }
        }
    }
}
