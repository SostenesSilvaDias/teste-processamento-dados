package br.com.gft.testeprocessamentodados.repository;

import br.com.gft.testeprocessamentodados.documents.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

    List<Product> findByName(String nome);
}
