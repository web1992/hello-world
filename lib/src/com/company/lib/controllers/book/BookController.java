package com.company.lib.controllers.book;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.company.lib.controllers.LoginRequired;
import com.company.lib.dao.BookDAO;
import com.company.lib.entity.Book;
import com.company.lib.entity.User;

import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.Param;
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;
import net.paoding.rose.web.annotation.rest.Post;
@LoginRequired
@Path("")
public class BookController {

    // 推荐使用bookDAO作为字段名，但这不是必须的，如果要以其它名称作为名字也不需要另外的配置
    // 如果使用多个DAO，则需要写多个@Autowired在每个DAO声明前
    @Autowired
    private BookDAO bookDAO;

    // 2)
    @Get
    public String list(Invocation inv, @Param("byBookId") long byBookId) {
        int limit = 30;
        List<Book> books = (byBookId <= 0) ? bookDAO.find(limit) : bookDAO.find(byBookId, limit);
        inv.addModel("books", books);
        HttpSession session = inv.getRequest().getSession(true);
        User userInfo=(User)session.getAttribute("userInfo");
        if(userInfo.getName().equals("admin")) inv.addModel("isEdit", true);
        return "book-list";
    }

    // 3)
    @Post
    public String add(Book book) {
    	//System.out.println("save book...");
        bookDAO.save(book);
        return "r:/lib/book";
    }

    // 4)
    @Get("add")
    public String showAdd() {
        return "book-add";
    }

    // 5)
    @Get("{bookId}")
    public String show(Invocation inv, @Param("bookId") long bookId, @Param("edit") boolean isEdit) {
    	System.out.println("get book by id ...");
        Book book = bookDAO.get(bookId);
        inv.addModel("book", book);
        if (isEdit) {
            return "@book-edit";
        }
        return "@book-page";
    }

    // 6)
    @Post("update")
    public String update(@Param("bookId") long bookId, Book book) {
    	//System.out.println("update book by id ...id= "+bookId);
        //book.setId(bookId);
        bookDAO.update(book);
        return "r:/lib/book/";
    }
    @Get("delete")
    public String delete(@Param("bookId") long bookId,@Param("edit") boolean isEdit){
    	if(!isEdit){
    		return "book-page";
    	}
    	
    	try {
			bookDAO.delete(bookId);
			return "r:/lib/book/";
		} catch (Exception e) {
			e.printStackTrace();
			return "r:/lib/book/";
		}
    	
    	
    }
    
    @Get("get")
    public String get(@Param("bookId") long bookId,Invocation inv){
    	Book book = bookDAO.get(bookId);
    	inv.addModel("book", book);
    	return "book-edit";
    }
}