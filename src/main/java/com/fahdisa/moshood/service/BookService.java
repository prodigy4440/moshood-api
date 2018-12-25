package com.fahdisa.moshood.service;

import com.fahdisa.moshood.entity.Book;
import com.fahdisa.moshood.entity.Review;
import com.fahdisa.moshood.util.BaseResponse;
import com.fahdisa.moshood.util.Codes;
import com.fahdisa.moshood.util.Page;
import com.sun.tools.javac.jvm.Code;

import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Objects;

@Stateless
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
            book.setId(null);
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
            if (Objects.isNull(book)) {
                return new BaseResponse.Builder<>()
                        .setCode(Codes.NO_RECORD)
                        .setDescription("Book not found")
                        .build();
            }else {
                return new BaseResponse.Builder<>()
                        .setCode(Codes.SUCCESS)
                        .setDescription("Success")
                        .setData(book)
                        .build();
            }
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

    public BaseResponse createReview(Long bookId, Review review){
        if(Objects.isNull(bookId) || Objects.isNull(review)){
            return new BaseResponse.Builder<>()
                    .setCode(Codes.INVALID_INPUT)
                    .setDescription("Invalid review")
                    .build();
        }else{
            Book book = entityManager.find(Book.class, bookId);
            if(Objects.isNull(book)){
                return new BaseResponse.Builder<>()
                        .setCode(Codes.NO_RECORD)
                        .setDescription("Book not found")
                        .build();
            }else{
                review.setBook(bookId);
                entityManager.persist(review);
                return new BaseResponse.Builder<>()
                        .setCode(Codes.SUCCESS)
                        .setDescription("Success")
                        .setData(review)
                        .build();
            }
        }
    }

    public BaseResponse getReviews(Long bookId, Long lastId, Integer page, Integer size){
        if(Objects.isNull(bookId)){
            return new BaseResponse.Builder<>()
                    .setCode(Codes.INVALID_INPUT)
                    .setDescription("Invalid Book")
                    .build();
        }else{
            if(Objects.isNull(lastId) || (lastId < 1)){
                lastId = Page.DEFAULT_LAST_ID;
            }

            if(Objects.isNull(page) || (page < 0)){
                page = Page.DEFAULT_PAGE;
            }

            if(Objects.isNull(size) || (size < 1)){
                size = Page.DEFAULT_SIZE;
            }

            List<Review> reviews = entityManager
                    .createQuery("SELECT r FROM Review r WHERE r.book = :book AND r.id < :id ORDER BY r.id DESC",
                            Review.class).setParameter("book", bookId)
                    .setParameter("id", lastId).setFirstResult(page * size)
                    .setMaxResults(size).getResultList();
            if(reviews.isEmpty()){
                return new BaseResponse.Builder<>()
                        .setCode(Codes.NO_RECORD)
                        .setDescription("No Review found")
                        .build();
            }else{
                return new BaseResponse.Builder<>()
                        .setCode(Codes.SUCCESS)
                        .setDescription("Success")
                        .setData(reviews)
                        .build();
            }
        }
    }

}
