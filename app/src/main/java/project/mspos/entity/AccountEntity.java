package project.mspos.entity;

public class AccountEntity {
    private int id;
    private String webUrl;
    private String userName;
    private String passWord;


    public AccountEntity() {}

    public AccountEntity(String userName, String passWord,String webUrl) {
        this.webUrl = webUrl;
        this.userName = userName;
        this.passWord = passWord;
    }
    public AccountEntity(int id, String userName, String passWord, String webUrl) {
        this.id = id;
        this.webUrl = webUrl;
        this.userName = userName;
        this.passWord = passWord;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

}
