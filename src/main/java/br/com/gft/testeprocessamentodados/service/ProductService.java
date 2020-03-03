package br.com.gft.testeprocessamentodados.service;

import br.com.gft.testeprocessamentodados.controller.dto.CalculoRetorno;
import br.com.gft.testeprocessamentodados.controller.dto.ProductDto;
import br.com.gft.testeprocessamentodados.documents.Product;

import java.io.IOException;
import java.util.List;

public interface ProductService {

    List<Product> listarTodos();

    List<Product> listarPorNome(String nome);

    void cadastrarTodos(List<Product> products);

    void deletarTodos();

    List<CalculoRetorno> calcularVenda(List<ProductDto> productDtos, int quantidadeLojas) throws IOException;
}
