package net.chintan.AngularwithSpring.services;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import net.chintan.AngularwithSpring.Model.User;
import net.chintan.AngularwithSpring.Repository.AdminAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.awt.image.BufferedImage;
import java.util.List;

@Service
public class AdminService{

    @Autowired
    private AdminAuth adminAuth;


    public User EmployeeAuth(User user){
        return adminAuth.save(user);
    }
    public List<User> GetAlldata(){
        return adminAuth.findAll();
    }
    public User FindByEmaildata(String Email){
        return adminAuth.findByemail(Email);
    }
    public User GetByID(int id){
        return adminAuth.findByid(id);
    }



}
