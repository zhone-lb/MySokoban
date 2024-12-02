package controller.user;

public class user {
    static private int idcnt=0;
    static int
    private String name,password;
    private int id;
    public user(String name, String password) {
        this.name = name;
        this.password = password;
        ++idcnt;
        this.id = idcnt;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

}
