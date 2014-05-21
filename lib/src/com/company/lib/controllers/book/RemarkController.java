package com.company.lib.controllers.book;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.company.lib.controllers.LoginRequired;
import com.company.lib.dao.RemarkDAO;
import com.company.lib.entity.Remark;

import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.Param;
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Delete;
import net.paoding.rose.web.annotation.rest.Get;
import net.paoding.rose.web.annotation.rest.Post;

@LoginRequired
@Path("")
public class RemarkController {
	@Autowired
	private RemarkDAO remarkDAO;

	// 7)
	@Delete
	public String clear(@Param("bookId") long bookId) {
		return "r:/lib/book/" + bookId;
	}

	// 8)
	@Get("deleteRemark")
	public String delete(@Param("bookId") long bookId,Invocation inv,
			@Param("remarkId") String remarkId) {
		inv.addModel("bookId", bookId);
		//System.out.println("bookId = "+bookId);
		//System.out.println("remarkId = "+remarkId);
		if(remarkId!=null) remarkDAO.delete(Long.parseLong(remarkId));
		else remarkDAO.deleteByBook(bookId);
		return "r:/lib/book/listRemark?bookId="+bookId;
	}
	

	@Get("listRemark")
	public String listRemark(@Param("bookId") long bookId,Invocation inv) {
		System.out.println("find book  remark ,bookId=" + bookId);
		List<Remark> remarks = remarkDAO.findByBook(bookId);
		if(remarks.isEmpty()) inv.addModel("msg", "not remark about this book ");
		inv.addModel("remarks", remarks);
		inv.addModel("bookId", bookId);
		return "book-remark";
	}
	@Post("addRemark")
	public String add(Remark remark){
		System.out.println("add ....");
		remarkDAO.save(remark);
		return "r:/lib/book/listRemark?bookId="+remark.getBookId();
	}
	
}