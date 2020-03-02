package br.com.gft.testeprocessamentodados.documents;

import br.com.gft.testeprocessamentodados.controller.dto.ProductDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
@Setter
@Document(collection = "product")
public class Product {
    @Id
    private String id;
    @Indexed(direction = IndexDirection.DESCENDING)
    private String name;
    private int quantity;
    private String price;
    private String type;
    private String industry;
    private String origin;

    public Product(){}

    Product(ProductDto productDto){
        this.industry = productDto.getIndustry();
        this.name = productDto.getName();
        this.origin = productDto.getOrigin();
        this.price = productDto.getPrice();
        this.quantity = productDto.getQuantity();
        this.type = productDto.getType();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return quantity == product.quantity &&
                Objects.equals(id, product.id) &&
                Objects.equals(name, product.name) &&
                Objects.equals(price, product.price) &&
                Objects.equals(type, product.type) &&
                Objects.equals(industry, product.industry) &&
                Objects.equals(origin, product.origin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, quantity, price, type, industry, origin);
    }

    public static List<Product> convert(List<ProductDto> productDtos){
        return  productDtos.stream().map(Product::new).collect(Collectors.toList());
    }
}
