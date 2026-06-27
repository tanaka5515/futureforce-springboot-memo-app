package com.lesson.memo.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
		.authorizeHttpRequests(authorize -> authorize//認証設定
				//静的リソースは認証不要
						.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
						//記載のページは認証不要
						.requestMatchers("/admin/signin", "/admin/signup").permitAll()
						//その他は認証必要
						.anyRequest().authenticated()
						)
		.formLogin(login -> login//ログイン処理
				//ログイン画面
				.loginPage("/admin/signin")
				//ログイン成功後
				.defaultSuccessUrl("/memo", true)
				.failureUrl("/admin/signin?error")
				.permitAll()
				)
		.logout(logout -> logout//ログアウト処理
				.logoutSuccessUrl("/admin/signin")
				.permitAll()
				);
		
		return httpSecurity.build();
	}
}
