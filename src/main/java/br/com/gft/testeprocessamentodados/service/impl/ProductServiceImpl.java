package br.com.gft.testeprocessamentodados.service.impl;

import br.com.gft.testeprocessamentodados.controller.dto.CalculoRetorno;
import br.com.gft.testeprocessamentodados.controller.dto.ProductDto;
import br.com.gft.testeprocessamentodados.documents.Product;
import br.com.gft.testeprocessamentodados.repository.ProductRepository;
import br.com.gft.testeprocessamentodados.service.ProductService;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Override
    public List<CalculoRetorno> calcularVenda(List<ProductDto> productDtos, int quantidadeLojas) {
        //quantidadeProdutosLojas = (quantityProdutos.get() / quantidadeLojas);
        //Pegar os valores - INICIO
        List<CalculoRetorno> retorno = new ArrayList<>();
        Optional<Integer> quantidadeProdutos = productDtos.stream()
                .map(ProductDto::getQuantity)
                .reduce(Integer::sum);
        productDtos.forEach(productDto -> productDto.setVolume(productDto.getQuantity() * Double.parseDouble(productDto.getPrice().replace("$", ""))));
        Optional<Double> totalVolume = productDtos.stream().map(ProductDto::getVolume)
                .reduce(Double::sum);
        Double precoMedio = totalVolume.get() /quantidadeProdutos.get();
        //Pegar os valores - FIM

        int quantidadeProdutosLojas = quantidadeProdutos.get()/quantidadeLojas;
        int quantidadeDistribuida = 0;

        if((quantidadeProdutos.get()%quantidadeLojas) != 0){
            for (int i = 1; i <=quantidadeLojas; i++){
                if(i != quantidadeLojas){
                    quantidadeDistribuida = quantidadeDistribuida + ( quantidadeProdutosLojas+1);
                    System.out.println(i +" :"+(quantidadeProdutosLojas+1));
                    retorno.add(montarRetorno(i,quantidadeProdutosLojas+1, (quantidadeProdutosLojas+1)*precoMedio, precoMedio));
                }else {
                    System.out.println(i +" :"+(quantidadeProdutos.get()-quantidadeDistribuida));
                    retorno.add(montarRetorno(i, (quantidadeProdutos.get()-quantidadeDistribuida), (quantidadeProdutos.get()-quantidadeDistribuida)*precoMedio, precoMedio));
                }
            }
        }else {
            for (int i = 1; i <=quantidadeLojas; i++){
                retorno.add(montarRetorno(i, quantidadeProdutosLojas, totalVolume.get()/quantidadeLojas, precoMedio));
            }
        }
        return retorno;
    }

    private CalculoRetorno montarRetorno(int numeroLoja, int quantidadeProdutos, Double financeiro, Double precoMedio) {
        return new CalculoRetorno(numeroLoja, quantidadeProdutos, BigDecimal.valueOf(financeiro).setScale(4, RoundingMode.DOWN), BigDecimal.valueOf(precoMedio).setScale(4, RoundingMode.DOWN));//mapper.writeValueAsString(calculoRetorno);
    }
}
