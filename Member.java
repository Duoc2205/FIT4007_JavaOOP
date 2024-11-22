public class Member {
    private String memberId;
    private String fullName;
    private String gender;
    private String phoneNumber;
    private String birthDate;
    private String address;

    public Member(String memberId, String fullName, String gender, String phoneNumber, String birthDate, String address) {
        this.memberId = memberId;
        this.fullName = fullName;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
        this.address = address;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Mã hội viên: " + memberId + "\n" +
                "Họ và tên: " + fullName + "\n" +
                "Giới tính: " + gender + "\n" +
                "Số điện thoại: " + phoneNumber + "\n" +
                "Ngày sinh: " + birthDate + "\n" +
                "Địa chỉ: " + address;
    }
}
