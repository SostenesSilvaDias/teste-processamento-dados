package com.example.testeprocessamentodados.service;

import com.example.testeprocessamentodados.documents.Product;

import java.util.List;

public interface ProductService {

    List<Product> listarTodos();

    List<Product> listarPorNome(String nome);

    void cadastrarTodos(List<Product> products);

    void deletarTodos();
}
