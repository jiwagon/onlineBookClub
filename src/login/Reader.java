package login;

public class Reader extends UserModel{
    String readerName;
    int yearJoined;

    public Reader(String username, String password, String readerName, int yearJoined) {
        super(username, password);
        this.readerName = readerName;
        this.yearJoined = yearJoined;
    }

    public Reader(String username, String password, String email,
                  String readerName, int yearJoined) {
        super(username, password, email);
        this.readerName = readerName;
        this.yearJoined = yearJoined;
    }

    public String getReaderName() {
        return readerName;
    }

    public void setReaderName(String readerName) {
        this.readerName = readerName;
    }

    public int getYearJoined() {
        return yearJoined;
    }

    public void setYearJoined(int yearJoined) {
        this.yearJoined = yearJoined;
    }
}
