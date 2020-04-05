package com.example.readinglist.repository;

import com.example.readinglist.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReadingListRepository extends JpaRepository<Book,Integer> {
    /**
     * @param reader
     * @return
     */
    List<Book> findByReader(String reader);

}
