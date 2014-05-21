package com.company.lib.dao;
// dao类必须在xxx.dao或子包下
// 必须是接口，并且以大写DAO结尾
// 必须标注@DAO，DAO中有一个catalog属性，对于大部分人来说，这个都是没用的

import java.util.List;

import com.company.lib.entity.Book;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;
import net.paoding.rose.jade.core.Identity;

@DAO
public interface BookDAO {

    @SQL("select id, name, price, author, create_time from book where id = :1")
    public Book get(long bookId);

    @SQL("select id, name, price, author, create_time from book limit :1 ")
    public List<Book>find(int limit);

    @SQL("select id, name, price, author, create_time from book where id < :1 limit :2 ")
    public List<Book> find(long bookId, int limit);

    @SQL("update book set name=:1.name, price=:1.price, author=:1.author where id=:1.id")
    public void update(Book book);

    @SQL("insert into book (name, price, author) values (:1.name, :1.price, :1.author)")
    public Identity save(Book book);
    
    @SQL("delete from book where id=:1")
    public void delete(long bookid) throws Exception;
    

}