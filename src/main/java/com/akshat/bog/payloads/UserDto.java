package com.akshat.bog.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

	private int id;

	@NotNull
	@NotBlank
	@Size(min = 4, message = "Username must be of 4 characters")
	private String name;

	@NotNull
	@NotBlank
	@Email(message = "Enter valid email address")
	private String email;

	@NotNull
	@NotBlank
	@Size(min = 4, message = "Min password length should be 8 and max be 15 characters long")
	private String password;

	@NotNull
	@NotBlank
	private String about;

}
