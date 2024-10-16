package icet.edu.book.service.impl;

import icet.edu.book.dto.Book;
import icet.edu.book.entity.BookEntity;
import icet.edu.book.repository.BookRepository;
import icet.edu.book.service.BookService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
	
	@Autowired
    private BookRepository repository;
	
    ModelMapper mapper;
    @PostConstruct
    public void setup(){
        this.mapper=new ModelMapper();
    }
    @Override
    public void addBook(Book book) {
        BookEntity entity = mapper.map(book, BookEntity.class);
        repository.save(entity);
    }

    @Override
    public Iterable<BookEntity> getBooks() {
        return repository.findAll();
    }

    @Override
    public boolean deleteBook(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }else { return false;}
    }

    @Override
    public Book getBookId(Long id) {
        Optional<BookEntity> byId = repository.findById(id);
        return mapper.map(byId, Book.class);
    }
}
