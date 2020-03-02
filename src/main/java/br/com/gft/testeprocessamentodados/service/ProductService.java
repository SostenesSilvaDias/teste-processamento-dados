package br.com.gft.testeprocessamentodados.service;

import br.com.gft.testeprocessamentodados.documents.Product;

import java.util.List;

public interface ProductService {

    List<Product> listarTodos();

    List<Product> listarPorNome(String nome);

    void cadastrarTodos(List<Product> products);

    void deletarTodos();
}
