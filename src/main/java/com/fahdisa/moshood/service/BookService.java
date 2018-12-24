package com.fahdisa.moshood.service;

import com.fahdisa.moshood.entity.Book;
import com.fahdisa.moshood.util.BaseResponse;
import com.fahdisa.moshood.util.Codes;
import com.fahdisa.moshood.util.Page;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Objects;

@RequestScoped
public class BookService {

    @PersistenceContext
    private EntityManager entityManager;

    public BaseResponse createBook(Book book) {
        if (Objects.isNull(book)) {
            return new BaseResponse.Builder<>()
                    .setCode(Codes.INVALID_INPUT)
                    .setDescription("Invalid book")
                    .build();
        } else {
            entityManager.persist(book);
            return new BaseResponse.Builder<>()
                    .setCode(Codes.SUCCESS)
                    .setDescription("Success")
                    .setData(book)
                    .build();
        }
    }

    public BaseResponse updateBook(Book book) {
        if (Objects.isNull(book) || Objects.isNull(book.getId())) {
            return new BaseResponse.Builder<>()
                    .setCode(Codes.INVALID_INPUT)
                    .setDescription("Invalid book")
                    .build();
        } else {
            entityManager.merge(book);
            return new BaseResponse.Builder<>()
                    .setCode(Codes.SUCCESS)
                    .setDescription("Success")
                    .setData(book)
                    .build();
        }
    }

    public BaseResponse deleteBook(Long id) {
        if (Objects.isNull(id)) {
            return new BaseResponse.Builder<>()
                    .setCode(Codes.INVALID_INPUT)
                    .setDescription("Invalid book id")
                    .build();
        } else {
            Book book = entityManager.find(Book.class, id);
            entityManager.remove(book);
            return new BaseResponse.Builder<>()
                    .setCode(Codes.INVALID_INPUT)
                    .setDescription("Success")
                    .setData(book)
                    .build();
        }
    }

    public BaseResponse getBook(Long id) {
        if (Objects.isNull(id)) {
            return new BaseResponse.Builder<>()
                    .setCode(Codes.INVALID_INPUT)
                    .setDescription("Invalid book id")
                    .build();
        } else {
            Book book = entityManager.find(Book.class, id);
            return new BaseResponse.Builder<>()
                    .setCode(Codes.INVALID_INPUT)
                    .setDescription("Success")
                    .setData(book)
                    .build();
        }
    }

    public BaseResponse getBooks(Long lastId, Integer page, Integer size) {
        if (Objects.isNull(lastId)) {
            lastId = Page.DEFAULT_LAST_ID;
        }

        if (Objects.isNull(page)) {
            page = Page.DEFAULT_PAGE;
        }

        if (Objects.isNull(size)) {
            size = Page.DEFAULT_SIZE;
        }

        List<Book> books = entityManager
                .createQuery("SELECT b FROM Book b WHERE b.id < :id ORDER BY b.createdDate",
                        Book.class).setParameter("id", lastId).setFirstResult(size * page)
                .setMaxResults(size).getResultList();
        if (books.isEmpty()) {
            return new BaseResponse.Builder<>()
                    .setCode(Codes.NO_RECORD)
                    .setDescription("Books not found")
                    .build();
        } else {
            return new BaseResponse.Builder<>()
                    .setCode(Codes.INVALID_INPUT)
                    .setDescription("Success")
                    .setData(books)
                    .build();
        }

    }

}
