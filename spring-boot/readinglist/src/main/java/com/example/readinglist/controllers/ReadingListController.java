package com.example.readinglist.controllers;

import com.example.readinglist.domain.Book;
import com.example.readinglist.repository.ReadingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * 获取读书列表controller
 */

//@RestController
//@RequestMapping(value = "/")
public class ReadingListController {
    private ReadingListRepository readingListRepository;
    @Autowired
    public ReadingListController(ReadingListRepository readingListRepository)
    {
        this.readingListRepository = readingListRepository;
    }

    /**
     * 查询图书列表
     * @param reader
     * @param model
     * @return
     */
    @RequestMapping(value="/{reader}", method= RequestMethod.GET)
    public String readersBooks(
            @PathVariable("reader") String reader,
            Model model) {
        List<Book> readingList =
                readingListRepository.findByReader(reader);
        if(readingList != null){
            model.addAttribute("books",readingList);
        }
        return "readingList";
    }

    /**
     * 新增图书
     * @param reader
     * @param book
     * @return
     */
    @RequestMapping(value="/{reader}", method=RequestMethod.POST)
    public String addToReadingList(
            @PathVariable("reader") String reader, Book book) {
        System.out.println(reader);
        book.setReader(reader);
        //return readingListRepository.save(book);
        return "redirect:/{reader}";
    }
}
