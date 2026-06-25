package com.lesson.memo.security;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lesson.memo.model.Admin;
import com.lesson.memo.repository.AdminRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminDetailService implements UserDetailsService {

	@Autowired
    private AdminRepository adminRepository;
	
	@Autowired
    private PasswordEncoder passwordEncoder;

    public void register(Admin user) {

        Admin admin = new Admin();
        
        admin.setLastName(user.getLastName());
        admin.setFirstName(user.getFirstName());
        admin.setEmail(user.getEmail());

        admin.setPassword(
                passwordEncoder.encode(
                        user.getPassword()
                )
        );
        
        admin.setCreatedAt(LocalDateTime.now());
        admin.setUpdatedAt(LocalDateTime.now());

        adminRepository.save(admin);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    	Admin admin = adminRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("ユーザーが見つかりません"));

            return User.withUsername(admin.getEmail())
                .password(admin.getPassword()) // 既にエンコード済み
                .roles("ADMIN")
                .build();
    }
}