package br.com.talthur.librarysystem;

import org.springframework.data.repository.CrudRepository;

import br.com.talthur.librarysystem.models.Book;

public interface BookRepository extends CrudRepository<Book, Long>{

}
