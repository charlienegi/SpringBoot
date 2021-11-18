package net.chintan.AngularwithSpring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class customUserDetails implements UserDetailsService {
    @Autowired
    private AdminService adminService;
    @Override
    public UserDetails loadUserByUsername(String Email) throws UsernameNotFoundException {
        var emp=adminService.FindByEmaildata(Email);

            if(Email.equals(emp.getEmail())){
                return new User(emp.getEmail(),emp.getPasswd(),new ArrayList<>());
            }
            else{
                throw new UsernameNotFoundException("User not found !!");
            }


        //return null;
    }
}
