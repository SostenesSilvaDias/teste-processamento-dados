package br.com.gft.testeprocessamentodados.controller;

import br.com.gft.testeprocessamentodados.controller.dto.CalculoRetorno;
import br.com.gft.testeprocessamentodados.controller.dto.ProductDto;
import br.com.gft.testeprocessamentodados.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping(path = "/{nome}/lojas/{quantidadeLojas}")
    public ResponseEntity<List<CalculoRetorno>> venderProdutos(@PathVariable String nome, @PathVariable int quantidadeLojas) throws IOException {
        List<ProductDto> collect = ProductDto.convert(productService.listarPorNome(nome));
        return ResponseEntity.ok(productService.calcularVenda(collect,quantidadeLojas));
    }

    @DeleteMapping(path = "/deletarTodos")
    public ResponseEntity<String> deletarTodos(){
        productService.deletarTodos();
        return ResponseEntity.noContent().build();
    }

}
