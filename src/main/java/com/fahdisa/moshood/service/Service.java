package com.fahdisa.moshood.service;

import com.fahdisa.moshood.entity.Book;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Objects;

@RequestScoped
public class Service {

    @PersistenceContext
    private EntityManager entityManager;

    public Book createBook(Book book){
        if(Objects.isNull(book)){
            return null;
        }else{
            entityManager.persist(book);
            return book;
        }
    }

    public Book updateBook(Book book){
        if(Objects.isNull(book) || Objects.isNull(book.getId())){
            return null;
        }else{
            entityManager.merge(book);
            return book;
        }
    }

    public Book deleteBook(Long id){
        if(Objects.isNull(id)){
            return null;
        }else{
            Book book = entityManager.find(Book.class, id);
            entityManager.remove(book);
            return book;
        }
    }
}
