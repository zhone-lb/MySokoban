package controller.user;

import javax.swing.*;

import model.data.MyReader;
import model.data.calcmd5;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class User implements Serializable {
//    static int idcnt=0;
//    public static int currentuser=0;

    public String name;
    public String password;
    public String md5;
    public boolean error=false;
    //    int id;
    ArrayList<JFrame> framelistsave;
    public static ArrayList<User> userlist=new ArrayList<>();
    public static User currentuser=null;
    public User(String name, String password) {
        this.name = name;
        this.password = password;
        this.md5=null;
        userlist.add(this);
    }
    public User(String name, String password, String md5) {

        this.name = name;
        this.password = password;
        this.md5 = md5;
        framelistsave= MyReader.read("src/model/data/User/" + name + "save.txt");
        try {
            if(framelistsave ==null||!calcmd5.calc(new FileInputStream("src/model/data/User/" + name + "save.txt")).equals(md5)){
                error=true;
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        userlist.add(this);
    }
    public static User getuser(String name){
        for(User x :userlist){
            if(x.name.equals(name)){
                return x;
            }
        }
        return null;
    }
    public static void getuserlist() {
        File file=new File("src/model/data/User/userlist.txt");
        try{
            Scanner input=new Scanner(file);
            int n=input.nextInt();
            input.nextLine();
            System.out.println(n);
            for(int i=0;i<n;i++){
                new User(input.nextLine(),input.nextLine(),input.nextLine());
            }

            input.close();
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
    }
    public static void saveuserlist(){
        try(FileWriter wr=new FileWriter("src/model/data/User/userlist.txt")){
            wr.write(userlist.size()+"\n");
            for(int i=0;i<userlist.size();i++){
                wr.write(userlist.get(i).name+"\n");
                wr.write(userlist.get(i).password+"\n");
                wr.write(userlist.get(i).md5+"\n");
            }
            wr.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public static int checkuser(String name, String password){
        System.out.println(name);
        System.out.println(password);
        User x=null;
        boolean flag=false;
        for(User now:userlist){
            if(now.name.equals(name)){
                System.out.println(now.name);
                System.out.println(now.password);
                x=now;
                flag=true;
                break;
            }
        }
        if(!flag){
            return 0;
        }
        if(x.password.equals(password)){
            return 1;
        }
        return -1;
    }

}
