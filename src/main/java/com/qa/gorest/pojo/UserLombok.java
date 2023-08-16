package com.qa.gorest.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
//import com.user.api.UserLombok;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
//	@Builder
	@JsonInclude(JsonInclude.Include.NON_NULL)
	public class UserLombok {
		@JsonProperty("id")
		private Integer id;
		
		@JsonProperty("name")
		private String name;
		
		@JsonProperty("gender")
		private String gender;
		
		@JsonProperty("email")
		private String email;
		
		@JsonProperty("status")
		private String status;
		

		public UserLombok(String name, String gender, String email, String status) {
			this.name = name;
			this.gender = gender;
			this.email = email;
			this.status = status;
		}
		
		
		
	}


