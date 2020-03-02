package com.example.testeprocessamentodados.service.impl;

import com.example.testeprocessamentodados.documents.Product;
import com.example.testeprocessamentodados.repository.ProductRepository;
import com.example.testeprocessamentodados.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> listarTodos() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> listarPorNome(String nome) {
        return productRepository.findByName(nome);
    }

    @Override
    public void cadastrarTodos(List<Product> products) {
        productRepository.saveAll(products);
    }

    @Override
    public void deletarTodos() {
        productRepository.deleteAll();
    }
}
