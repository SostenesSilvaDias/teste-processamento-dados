package br.com.gft.testeprocessamentodados.config;

import br.com.gft.testeprocessamentodados.controller.dto.DataDto;
import br.com.gft.testeprocessamentodados.controller.dto.ProductDto;
import br.com.gft.testeprocessamentodados.documents.Product;
import br.com.gft.testeprocessamentodados.service.ProductService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

@Component
public class dbProductSeeder implements CommandLineRunner {
    @Value("${path.json.arquivos}")
    private String pathName;
    private List<ProductDto> products;
    private DataDto data;
    private ObjectMapper mapper;
    private TypeReference<DataDto> typeReference;
    private ProductService productService;

    dbProductSeeder(ProductService productService) {
        this.productService = productService;
        this.products = new ArrayList<>();
        this.data = new DataDto(products);
        this.mapper = new ObjectMapper();
        this.typeReference =  new TypeReference<DataDto>(){};
    }

    @Override
    public void run(String... args) {
        File diretorio = new File(pathName);
        File[] listFiles = diretorio.listFiles();
        InputStream inputStream;

        try {
            assert listFiles != null;
            for (File file : listFiles) {

                inputStream = new FileInputStream(file);
                DataDto readValue = mapper.readValue(inputStream, typeReference);
                data.getProducts().addAll(readValue.getProducts());
            }
            List<ProductDto> productsInBase = ProductDto.convert(productService.listarTodos());
            products.removeAll(productsInBase);
            productService.cadastrarTodos(Product.convert(products));
        } catch (IOException e){
            System.out.println("Unable to save users: " + e.getMessage());
        }

    }
}
