
package poolgameserver;

public class ClientInfo {
    private String userName;
    private String userPassword;
    private String imageLocation;

    public ClientInfo(String userName, String userPassword) {
        this.userName = userName;
        this.userPassword = userPassword;
    }

    public ClientInfo(String userName, String userPassword, String imageLocation) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.imageLocation = imageLocation;
    }

    public String getImageLocation() {
        return imageLocation;
    }

    public void setImageLocation(String imageLocation) {
        this.imageLocation = imageLocation;
    }

    @Override
    public String toString() {
        return userName+" "+userPassword+" "+imageLocation;
    }
    
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
    
}
