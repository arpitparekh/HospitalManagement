package arpit.hospitalmanagement.all_in_one_firebase;

import java.io.Serializable;

public class User{

    String email;
    String uid;
    String imageUrl;
    String name;

    User(){

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User(String email, String uid, String imageUrl, String name) {
        this.email = email;
        this.uid = uid;
        this.imageUrl = imageUrl;
        this.name = name;
    }
}
