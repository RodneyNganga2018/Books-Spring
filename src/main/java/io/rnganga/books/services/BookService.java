package io.rnganga.books.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import io.rnganga.books.models.Book;
import io.rnganga.books.repositories.BookRepository;

@Service
public class BookService {
	private final BookRepository bookRepository;
	
	public BookService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}
	
	public List<Book> findAll() {
		return bookRepository.findAll();
	}
	
	public Book createBook (Book b) {
		return bookRepository.save(b);
	}
	
	public Book findOne (Long id) {
		Optional<Book> optionalBook = bookRepository.findById(id);
		if(optionalBook.isPresent()) {
			return optionalBook.get();
		} else {
			return null;
		}
	}
	
	public void deleteBook (Long id) {
		bookRepository.deleteById(id);
	}

	public Book updateBook(Long id, String title, String desc, String lang, Integer numOfPages) {
		if (bookRepository.findById(id).isPresent()) {
			Book book = bookRepository.findById(id).get();
			book.setTitle(title);
			book.setDescription(desc);
			book.setLanguage(lang);
			book.setNumberOfPages(numOfPages);
			return bookRepository.save(book);
		} else {
			return null;
		}
	}
}