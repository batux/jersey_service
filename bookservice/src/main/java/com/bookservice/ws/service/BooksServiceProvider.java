package com.bookservice.ws.service;

import java.util.ArrayList;
import java.util.List;

import com.bookservice.ws.model.Book;

public class BooksServiceProvider {
	
	private List<Book> books;
	
	public BooksServiceProvider() {
		this.books = new ArrayList<Book>();
	}

	public List<Book> getBooks() {
		
		if(this.books.size() == 0) {
			
			Book book1 = new Book(0, "Alex de Souza");
			Book book2 = new Book(1, "Çankaya");
			Book book3 = new Book(2, "Aklýn akýl ile Savaþý");
			Book book4 = new Book(3, "Nutuk");
			Book book5 = new Book(4, "Anne ben Afrika'ya taþýnýyorum");
			
			books.add(book1);
			books.add(book2);
			books.add(book3);
			books.add(book4);
			books.add(book5);
		}
		
		return books;
	}
	
	public Book findBookById(long bookId) {
		
		Book requestedBook = null;
		
		if(bookId < 0) {
			return requestedBook;
		}
		
		for(Book book : this.getBooks()) {
			if(bookId == book.getId()) {
				requestedBook = book;
				break;
			}
		}
		
		return requestedBook;
	}
	
	public int findBookIndexById(long bookId) {
		
		int requestedBookIndex = -1;
		
		if(bookId < 0) {
			return requestedBookIndex;
		}
		
		for(int i=0; i < this.getBooks().size(); i++) {
			
			Book book = this.getBooks().get(i);
			
			if(bookId == book.getId()) {
				requestedBookIndex = i;
				break;
			}
		}
		
		return requestedBookIndex;
	}
	
	public Book saveBook(Book book) {
		
		long nextBookId = this.getBooks().size();
		
		book.setId(nextBookId);
		
		this.getBooks().add(book);
		
		return book;
	}
	
	public Book updateBook(long bookId, Book modifiedBook) {
		
		int requestedBookIndexInList = findBookIndexById(bookId);
		
		modifiedBook.setId(bookId);
		
		this.getBooks().set(requestedBookIndexInList, modifiedBook);
		
		return modifiedBook;
	}
	
	public void deleteBook(long bookId) {
		
		int requestedBookIndexInList = findBookIndexById(bookId);
		
		this.getBooks().remove(requestedBookIndexInList);
	}
}
