package com.example.readinglist.controllers;

import com.example.readinglist.domain.Book;
import com.example.readinglist.repository.ReadingListRepository;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 书接口
 */

@RestController
@RequestMapping(value = "/")
public class BooksController {
    private ReadingListRepository readingListRepository;
    @Autowired
    public BooksController(ReadingListRepository readingListRepository)
    {
        this.readingListRepository = readingListRepository;
    }

    @ApiOperation(value="查询一条图书信息", notes="根据主键id,查询一条图书信息")
    @ApiImplicitParam(name = "id", value = "图书表主键", required = true, dataType = "integer",paramType = "path")
    @GetMapping(value = "/books/{id}")
    public Book girlFindOne(@PathVariable("id") Integer id)
    {
        return readingListRepository.findOne(id);
    }

    @ApiOperation(value="查询所有图书信息", notes="查询所有图书信息")
    @GetMapping(value = "/booksAll")
    public List<Book> getAllBook()
    {
        return readingListRepository.findAll();
    }
    /**
     * 查询图书列表
     * @param reader
     * @return
     */
    @ApiOperation(value="通过reader查询图书", notes="通过reader查询图书,==方式查询")
    @ApiImplicitParam(name = "reader", value = "book表的reader字段", required = true, dataType = "string")
    @RequestMapping(value="/booksByReader/{reader}", method= RequestMethod.GET)
    public List<Book> readersBooks(
            @PathVariable("reader") String reader) {
        List<Book> readingList =
                readingListRepository.findByReader(reader);
        return readingList;
    }

    /**
     * 新增图书
     * @param reader
     * @param book
     * @return
     */
    @ApiOperation(value="创建图书", notes="根据Book对象创建图书")
    @ApiImplicitParam(name = "book", value = "用户详细实体book", required = true, dataType = "Book")
    @RequestMapping(value="/books/{reader}", method=RequestMethod.POST)
    public Book addToReadingList(
            @PathVariable("reader") String reader, @RequestBody Book book) {
        System.out.println("param reader = "+reader+",book obj = "+book.toString());
        book.setReader(reader);
        return readingListRepository.save(book);
    }

    /**
     * 修改图书
     */
    @ApiOperation(value="修改图书", notes="根据主键修改")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "图书表主键", required = true, dataType = "integer",paramType = "path"),
            @ApiImplicitParam(name = "book", value = "Book对象", required = true, dataType = "Book")}
    )
    @PutMapping(value = "/books/{id}")
    public Book updateBook(@PathVariable("id") Integer id, @RequestBody Book book)
    {
        Book bk = readingListRepository.findOne(id);
        if(book.getReader() != null)
        {
            bk.setReader(book.getReader());
        }
        if(book.getAuthor() != null)
        {
            bk.setAuthor(book.getAuthor());
        }
        return readingListRepository.save(bk);
    }
    /**
     * 删除图书
     */
    @ApiOperation(value = "删除图书",notes = "该接口是删除图书接口，参数是数据表主键")
    @ApiImplicitParam(name = "id", value = "图书表主键", required = true, dataType = "integer",paramType = "path")
    @DeleteMapping(value = "/books/{id}")
    public void deleteBook(@PathVariable("id") Integer id)
    {
        try{
            readingListRepository.delete(id);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
