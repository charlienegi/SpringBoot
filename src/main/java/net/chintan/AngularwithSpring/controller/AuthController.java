package net.chintan.AngularwithSpring.controller;

import net.chintan.AngularwithSpring.Model.OTPBody;
import net.chintan.AngularwithSpring.Model.ResponseBody;
import net.chintan.AngularwithSpring.Model.User;
import net.chintan.AngularwithSpring.Repository.AdminAuth;
import net.chintan.AngularwithSpring.helper.JwtHelper;
import net.chintan.AngularwithSpring.services.AdminService;
import net.chintan.AngularwithSpring.services.QRGenerator;
import net.chintan.AngularwithSpring.services.customUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.BufferedImageHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private AdminAuth adminAuth;
    @Autowired
    private customUserDetails customUserDetails;
    @Autowired
    private JwtHelper jwtHelper;
    @Autowired
    private AuthenticationManager authenticationManager;
    @RequestMapping("/api/register")
    public ResponseEntity<User> Register(@RequestBody User user){
                adminService.EmployeeAuth(user);
                return ResponseEntity.ok().body(user);
    }
  @RequestMapping(value = "/token",method = RequestMethod.POST)
  public ResponseEntity<?> Login(@RequestBody User user){
    var empdata=adminService.FindByEmaildata(user.getEmail());

      if(empdata.getEmail().equals(user.getEmail()))
      {
          try{
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(empdata.getEmail(),empdata.getPasswd()));
          }
          catch (UsernameNotFoundException ex) {
              ex.printStackTrace();
              throw new BadCredentialsException("Bad credentials");
          }
        UserDetails userDetails= customUserDetails.loadUserByUsername(empdata.getEmail());
          String token=jwtHelper.generateToken(userDetails);
          return ResponseEntity.ok().body(new ResponseBody(token));
      }

    return ResponseEntity.badRequest().body("Data has null");
  }
    @GetMapping("/api/Forget/{email}")
  public ResponseEntity<?> ForgetPassword(@PathVariable String email){
        var Userdata=adminService.FindByEmaildata(email);
            if(Userdata.getEmail().equals(email)){
                Random rnd=new Random(1000);
                Integer Token=rnd.nextInt(99999);
                Userdata.setOTP(Token);
                adminService.EmployeeAuth(Userdata);
                return ResponseEntity.ok().body(Userdata);
            }
                else{
                    return ResponseEntity.badRequest().body("User has not found in database");
            }
            //return ResponseEntity.ok().body(Userdata);
  }

  @PostMapping("/api/verifyotp/{id}")
    public ResponseEntity<?> VerifyOtp(@RequestBody OTPBody otpBody,@PathVariable Integer id){
        User data=adminService.GetByID(id);
        if(data.getOTP().equals(otpBody.getOTP())){
            return ResponseEntity.ok().body(data);
        }
      return ResponseEntity.badRequest().body("data may be null");
  }
  //Update the password
  @PostMapping("/api/Passwordupdate")
    public ResponseEntity<?> PasswordChanged(@RequestBody User user){
        try {
            User data=adminService.GetByID(user.getId());
                if (data.getId() == user.getId()) {
                    data.setPasswd(user.getPasswd());
                    adminService.EmployeeAuth(data);
                }

        }
        catch (Exception ex){
            throw ex;
        }
        return ResponseEntity.ok().body("Password has been updated");
  }

    @PostMapping(value = "/api/QRcode",produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<BufferedImage> zxingQRCode(@RequestBody String barcode) throws Exception{

        return suceesResponse(QRGenerator.generatecode(barcode));
    }

    private ResponseEntity<BufferedImage>suceesResponse(BufferedImage image){
        return new ResponseEntity<>(image, HttpStatus.OK);
    }
    @Bean
    public HttpMessageConverter<BufferedImage> createImageHttpMessageConverter() {
        return new BufferedImageHttpMessageConverter();
    }
}
