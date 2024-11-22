import java.io.*;
import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LibraryManagement {
    private List<Book> books;
    private List<Member> members;
    private List<BorrowedBook> borrowedBooks;

    public LibraryManagement() {
        books = new ArrayList<>();
        members = new ArrayList<>();
        borrowedBooks = new ArrayList<>();
    }

    public void addBooks() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập số lượng sách: ");
        int n = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < n; i++) {
            System.out.println("Sách thứ " + (i + 1) + ":");
            System.out.print("Nhập mã sách: ");
            String bookId = scanner.nextLine();
            System.out.print("Nhập tên sách: ");
            String title = scanner.nextLine();
            System.out.print("Nhập tên tác giả: ");
            String author = scanner.nextLine();
            System.out.print("Nhập số lượng: ");
            int quantity = scanner.nextInt();
            scanner.nextLine();

            books.add(new Book(bookId, title, author, quantity));
            System.out.println("Sách đã được thêm thành công!\n");
        }
    }

    public void removeBook(String input) {
        boolean removed = books.removeIf(book -> book.getBookId().equals(input) || book.getTitle().equalsIgnoreCase(input) || book.getAuthor().equalsIgnoreCase(input));
        if (removed) {
            System.out.println("Đã xóa sách thành công!\n");
        } else {
            System.out.println("Không tìm thấy sách để xóa!\n");
        }
    }

    public void searchBook(String input) {
        for (Book book : books) {
            if (book.getBookId().equals(input) || book.getTitle().equalsIgnoreCase(input) || book.getAuthor().equalsIgnoreCase(input)) {
                System.out.println(book);
                return;
            }
        }
        System.out.println("Không tìm thấy sách!\n");
    }

    public void sortBooks() {
        books.sort(Comparator.comparing(Book::getTitle));
        System.out.println("Đã sắp xếp sách theo thứ tự A-Z!\n");
    }

    public void updateBookQuantity(String bookId, int newQuantity) {
        for (Book book : books) {
            if (book.getBookId().equals(bookId)) {
                book.setQuantity(newQuantity);
                System.out.println("Đã cập nhật số lượng sách thành công!\n");
                return;
            }
        }
        System.out.println("Không tìm thấy mã sách để cập nhật!\n");
    }

    public void displayAllBooks() {
        if (books.isEmpty()) {
            System.out.println("Không có sách nào trong kho!\n");
            return;
        }
        for (Book book : books) {
            System.out.println(book);
            System.out.println("------------------------------");
        }
    }

    public void registerMember(String memberId, String fullName, String gender, String phoneNumber, String birthDate, String address) {
        members.add(new Member(memberId, fullName, gender, phoneNumber, birthDate, address));
        System.out.println("Đăng ký hội viên thành công!\n");
    }

    public void displayMembers() {
        if (members.isEmpty()) {
            System.out.println("Không có hội viên nào được đăng ký!\n");
            return;
        }
        for (Member member : members) {
            System.out.println(member);
            System.out.println("------------------------------");
        }
    }

    public void borrowBook(String memberId, String bookId, int quantity, String borrowDate) {
        boolean memberExists = members.stream().anyMatch(member -> member.getMemberId().equals(memberId));
        if (!memberExists) {
            System.out.println("Hội viên chưa được đăng ký! Vui lòng đăng ký trước.\n");
            return;
        }

        for (Book book : books) {
            if (book.getBookId().equals(bookId) && book.getQuantity() >= quantity) {
                book.setQuantity(book.getQuantity() - quantity);
                borrowedBooks.add(new BorrowedBook(memberId, bookId, quantity, borrowDate, ""));
                System.out.println("Mượn sách thành công!\n");
                return;
            }
        }
        System.out.println("Không thể mượn sách! Kiểm tra mã sách và số lượng.\n");
    }

    public void returnBook(String bookId) {
        Optional<BorrowedBook> borrowedBookOpt = borrowedBooks.stream()
                .filter(b -> b.getBookId().equals(bookId) && b.getReturnDate().isEmpty())
                .findFirst();

        if (borrowedBookOpt.isPresent()) {
            BorrowedBook borrowedBook = borrowedBookOpt.get();
            for (Book book : books) {
                if (book.getBookId().equals(bookId)) {
                    book.setQuantity(book.getQuantity() + borrowedBook.getQuantity());
                    borrowedBook.setReturnDate(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                    System.out.println("Trả sách thành công!\n");
                    return;
                }
            }
        } else {
            System.out.println("Không tìm thấy sách đã mượn!\n");
        }
    }

    public void displayBorrowedBooks() {
        if (borrowedBooks.isEmpty()) {
            System.out.println("Không có sách nào được mượn!\n");
            return;
        }
        for (BorrowedBook borrowedBook : borrowedBooks) {
            System.out.println(borrowedBook);
            System.out.println("------------------------------");
        }
    }

    public void extendBorrowDate(String bookId) {
        Optional<BorrowedBook> borrowedBookOpt = borrowedBooks.stream()
                .filter(b -> b.getBookId().equals(bookId) && b.getReturnDate().isEmpty())
                .findFirst();

        if (borrowedBookOpt.isPresent()) {
            System.out.println("Đã gia hạn thêm ngày mượn cho sách!\n");
        } else {
            System.out.println("Không thể gia hạn! Không tìm thấy sách đã mượn.\n");
        }
    }

    public void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("library_data.txt"))) {
            for (Book book : books) {
                writer.write(book.getBookId() + "," + book.getTitle() + "," + book.getAuthor() + "," + book.getQuantity());
                writer.newLine();
            }
            for (Member member : members) {
                writer.write(member.getMemberId() + "," + member.getFullName() + "," + member.getGender() + "," +
                        member.getPhoneNumber() + "," + member.getBirthDate() + "," + member.getAddress());
                writer.newLine();
            }
            for (BorrowedBook borrowedBook : borrowedBooks) {
                writer.write(borrowedBook.getMemberId() + "," + borrowedBook.getBookId() + "," + borrowedBook.getQuantity() + "," +
                        borrowedBook.getBorrowDate() + "," + borrowedBook.getReturnDate());
                writer.newLine();
            }
            System.out.println("Dữ liệu đã được lưu vào tệp thành công!\n");
        } catch (IOException e) {
            System.out.println("Có lỗi xảy ra trong quá trình lưu dữ liệu: " + e.getMessage());
        }
    }

    public void loadDataFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("library_data.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    books.add(new Book(parts[0], parts[1], parts[2], Integer.parseInt(parts[3])));
                } else if (parts.length == 6) {
                    members.add(new Member(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5]));
                } else if (parts.length == 5) {
                    borrowedBooks.add(new BorrowedBook(parts[0], parts[1], Integer.parseInt(parts[2]), parts[3], parts[4]));
                }
            }
            System.out.println("Dữ liệu đã được tải từ tệp thành công!\n");
        } catch (IOException e) {
            System.out.println("Có lỗi xảy ra trong quá trình tải dữ liệu: " + e.getMessage());
        }
    }

    public void displayOverdueBooks() {
        System.out.println("==== DANH SÁCH SÁCH TRỄ HẠN ====");
        LocalDate today = LocalDate.now();
        for (BorrowedBook record : borrowedBooks) {
            if (!record.getReturnDate().isEmpty()) {
                LocalDate returnDate = LocalDate.parse(record.getReturnDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                if (returnDate.isBefore(today)) {
                    System.out.println(record);
                }
            }
        }
    }

    public void exportStatisticsToFile() {
        try (FileWriter fileWriter = new FileWriter("statistics.txt")) {
            fileWriter.write("Thống kê số lượng sách theo thể loại:\n");
            Map<String, Integer> stats = getBookStatisticsByCategory();
            for (Map.Entry<String, Integer> entry : stats.entrySet()) {
                fileWriter.write(entry.getKey() + ": " + entry.getValue() + "\n");
            }
            System.out.println("Xuất dữ liệu thống kê thành công!");
        } catch (IOException e) {
            System.out.println("Có lỗi xảy ra khi xuất dữ liệu thống kê.");
            e.printStackTrace();
        }
    }

    public void searchMemberByName(String name) {
        System.out.println("==== TÌM KIẾM HỘI VIÊN ====");
        for (Member member : members) {
            if (member.getFullName().equalsIgnoreCase(name)) {
                System.out.println(member);
            }
        }
    }

    public void displayBookStatisticsByCategory() {
        System.out.println("==== THỐNG KÊ SÁCH THEO THỂ LOẠI ====");
        Map<String, Integer> stats = getBookStatisticsByCategory();
        for (Map.Entry<String, Integer> entry : stats.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public void notifyBooksToReplenish() {
        System.out.println("==== SÁCH CẦN BỔ SUNG ====");
        for (Book book : books) {
            if (book.getQuantity() < 5) {
                System.out.println("Sách cần bổ sung: " + book);
            }
        }
    }

    private Map<String, Integer> getBookStatisticsByCategory() {
        Map<String, Integer> stats = new HashMap<>();
        for (Book book : books) {
            stats.put(book.getAuthor(), stats.getOrDefault(book.getAuthor(), 0) + 1);
        }
        return stats;
    }

    private List<BorrowedBook> getOverdueBooks() {
        return borrowedBooks;
    }

    private List<Member> getMembers() {
        return members;
    }

    private List<Book> getBooks() {
        return books;
    }
}
