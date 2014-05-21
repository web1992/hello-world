package com.company.lib.controllers;

import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;

//@Path是Rose提供的注解
@LoginRequired
@Path("")
public class HomeController {
 // 1)
 // 这个"1)"表示这个对应于《URI设计》表格中的序号
 @Get
 public String redirect() {
      return "r:/lib/login";
 }
}