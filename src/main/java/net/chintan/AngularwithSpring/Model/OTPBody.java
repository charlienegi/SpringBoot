package net.chintan.AngularwithSpring.Model;

public class OTPBody {
    private int OTP;
    private User users;
    public OTPBody(int OTP,User users) {
        this.OTP = OTP;
        this.users=users;
    }

    public int getOTP() {
        return OTP;
    }
    public User getUsers() {
        return users;
    }

    public void setOTP(int OTP) {
        this.OTP = OTP;
    }
    public void setUsers(User users) {
        this.users = users;
    }
}
