package com.example.testeprocessamentodados.controller.dto;

import com.example.testeprocessamentodados.documents.Product;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
@Setter
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDto {

	@JsonProperty("product")
	String name;
	@JsonProperty("quantity")
	Integer quantity;
	@JsonProperty("price")
	String price;
	@JsonProperty("type")
	String type;
	@JsonProperty("industry")
	String industry;
	@JsonProperty("origin")
	String origin;
	
	ProductDto() {};

	ProductDto(Product product){
		this.industry = product.getIndustry();
		this.name = product.getName();
		this.origin = product.getOrigin();
		this.price = product.getPrice();
		this.quantity = product.getQuantity();
		this.type = product.getType();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ProductDto that = (ProductDto) o;
		return Objects.equals(name, that.name) &&
				Objects.equals(quantity, that.quantity) &&
				Objects.equals(price, that.price) &&
				Objects.equals(type, that.type) &&
				Objects.equals(industry, that.industry) &&
				Objects.equals(origin, that.origin);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, quantity, price, type, industry, origin);
	}

	public static List<ProductDto> convert(List<Product> products){
		return products.stream().map(ProductDto::new).collect(Collectors.toList());
	}

}
