package net.jiangchen.Model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "users")
public class User implements Serializable{
    private int uid;

    private String username;

    private int permission; //root权限为0
    private String passwd;
    private String email;

    public User(int id, String username, int permission, String email,String passwd) {
        this.uid = id;
        this.username = username;
        this.permission = permission;
        this.email = email;
        this.passwd = passwd;
    }
    public User(){

    }

    @GenericGenerator(name = "generator", strategy = "uuid.hex")
    @Id
    @Column(name = "uid", unique = true, nullable = false)
    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    @Column(name = "username", nullable = false)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "permission", nullable = false)
    public int getPermission() {
        return permission;
    }


    public void setPermission(int permission) {
        this.permission = permission;
    }

    @Column(name = "email", nullable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "passwd", nullable = false)
    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
}
