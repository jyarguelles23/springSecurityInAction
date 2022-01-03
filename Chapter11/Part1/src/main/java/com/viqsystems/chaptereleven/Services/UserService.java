package com.viqsystems.chaptereleven.Services;

import com.viqsystems.chaptereleven.AuxClasses.GenerateCodeUtil;
import com.viqsystems.chaptereleven.Entities.Otp;
import com.viqsystems.chaptereleven.Entities.Users;
import com.viqsystems.chaptereleven.Repositories.OtpRepository;
import com.viqsystems.chaptereleven.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    UserRepository userRepository;
    OtpRepository otpRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    UserService(UserRepository userRepository,OtpRepository otpRepository){
        this.userRepository=userRepository;
        this.otpRepository=otpRepository;
    }

    public void addUser(Users user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public void auth(Users user){
        Optional<Users> u= userRepository.findUsersByUsername(user.getUsername());
        if((u.isPresent()) && (passwordEncoder.matches(user.getPassword(),u.get().getPassword()))){
             renewOtp(u.get());
        }
        else{
            throw new BadCredentialsException("Bad credentials.");
        }

    }

    private void renewOtp(Users u){
        String code = GenerateCodeUtil.generateCode();
        Optional<Otp> userOtp =
                otpRepository.findOtpByUsername(u.getUsername());
        if (userOtp.isPresent()) {
            Otp otp = userOtp.get();
            otp.setCode(code);
        } else {
            Otp otp = new Otp();
            otp.setUsername(u.getUsername());
            otp.setCode(code);
            otpRepository.save(otp);
        }
    }

    public boolean check(Otp otpToValidate) {
        Optional<Otp> userOtp =
                otpRepository.findOtpByUsername(
                        otpToValidate.getUsername());
        if (userOtp.isPresent()) {
            Otp otp = userOtp.get();
            if (otpToValidate.getCode().equals(otp.getCode())) {
                return true;
            }
        }
        return false;
    }

}
