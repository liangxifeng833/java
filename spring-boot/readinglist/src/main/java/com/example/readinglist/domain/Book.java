package com.example.readinglist.domain;

import javax.persistence.*;

@Entity
@Table(name="t_book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String author;
    private String isbn;

    private String title;

    private String reader;

    private String description;

    public Book(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setReader(String reader) {
        this.reader = reader;
    }

    public String getReader() {
        return reader;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getIsbn() {
        return isbn;
    }

    @Override
    public String toString() {
        return "author="+author+": reader="+reader;
    }
}
