package com.lesson.memo.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import lombok.Data;

@Entity
@Data
public class Admin {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "姓を入力してください")
	@Column(nullable = false, name = "last_name")
	private String lastName;
	
	@NotBlank(message = "名を入力してください")
	@Column(nullable = false, name = "first_name")
	private String firstName;
	
	@NotBlank(message = "メールアドレスを入力してください")
	@Email(message = "メールアドレス形式で入力してください")
	@Column(nullable = false, name = "email", unique = true)
	private String email;
	
	@NotBlank(message = "パスワードを入力してください")
	@Column(nullable = false, name = "password")
	private String password;
	
	@Column(nullable = false, name = "created_at", updatable = false,
			columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

	@Column(nullable = false, name = "updated_at", updatable = true,
			columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime updatedAt;
}
