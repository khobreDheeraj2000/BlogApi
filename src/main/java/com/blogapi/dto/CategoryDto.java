package com.blogapi.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {
	
	private int id ;
	@NotNull @NotEmpty @Size(min = 4 , max = 100)
	private String categoryTitle ;
	@NotNull @NotEmpty @Size(min = 4 , max = 800)
	private String categoryDiscription ;
}
