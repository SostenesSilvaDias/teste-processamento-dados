package com.example.testeprocessamentodados.controller;

import com.example.testeprocessamentodados.controller.dto.ProductDto;
import com.example.testeprocessamentodados.documents.Product;
import com.example.testeprocessamentodados.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/products")
public class ProductController {

    private ProductService productService;

    ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(path = "/listarTodos")
    public ResponseEntity<List<ProductDto>> listarTodos(){
        return ResponseEntity.ok(ProductDto.convert(productService.listarTodos()));
    }

    @GetMapping(path = "/{nome}")
    public ResponseEntity<List<ProductDto>> listarPorNome(@PathVariable String nome){
        return ResponseEntity.ok(ProductDto.convert(productService.listarPorNome(nome)));
    }

    @DeleteMapping(path = "/deletarTodos")
    public ResponseEntity<String> deletarTodos(){
        productService.deletarTodos();
        return ResponseEntity.noContent().build();
    }
}
