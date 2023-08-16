package com.qa.gorest.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FakeStoreAPIProducts {
	String title;
    double price;
    String description;
    String image;
    String category;    
}
