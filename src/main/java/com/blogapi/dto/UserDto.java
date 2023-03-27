package com.blogapi.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
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
	@NotNull @NotEmpty @Size(min=4 )
	private String name;
	@Email
	private String email;
	@NotNull @NotEmpty @Size(min=4 )
	private String password;
	@NotNull @NotEmpty @Size(min=4 )
	private String about;
}
