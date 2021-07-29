package com.eldorado.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.eldorado.service.AddProductDetails;

@RestController
public class ProductController {

	@GetMapping("/")
	public String HelloUser()
	{
		return "Have a nice Day";
	}
	
	@PostMapping("/addProduct")
    String AddProductDetailsService(@RequestBody AddProductDetails p)
	{
	    return "Product Details Added Successfully " + p.getPrice() + p.getTags();
	}
}
