public class BorrowedBook {
    private String memberId;
    private String bookId;
    private int quantity;
    private String borrowDate;
    private String returnDate;

    public BorrowedBook(String memberId, String bookId, int quantity, String borrowDate, String returnDate) {
        this.memberId = memberId;
        this.bookId = bookId;
        this.quantity = quantity;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(String borrowDate) {
        this.borrowDate = borrowDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public String toString() {
        return "Mã hội viên: " + memberId + "\n" +
                "Mã sách: " + bookId + "\n" +
                "Số lượng: " + quantity + "\n" +
                "Ngày mượn: " + borrowDate + "\n" +
                "Ngày trả: " + returnDate;
    }
}
