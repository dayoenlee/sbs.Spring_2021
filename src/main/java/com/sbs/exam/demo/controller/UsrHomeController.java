package com.sbs.exam.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Controller
public class UsrHomeController {
	@RequestMapping("/usr/home/test1")
	@ResponseBody
	public String getString() {
		return "Hi";
	}
	@RequestMapping("/usr/home/getMap")
	@ResponseBody
	public Map getMap() {
		Map<String,Object>map = new HashMap<>();
		
		map.put("철수의 나이",22);
		map.put("영희의 나이",32);
		return map;
	}
	@RequestMapping("/usr/home/getList")
	@ResponseBody
	public List<String> getList() {
		List<String> list = new ArrayList<>();
		
		list.add("철수의 나이");
		list.add("영희의 나이");
		
		return list;
	}
	@RequestMapping("/usr/home/getArticle")
	@ResponseBody
	public Article getArticle() {
		Article article = new Article(1,"제목");
		Article article1 = new Article();
		
	
		return article;
	}
	@RequestMapping("/usr/home/getArticles")
	@ResponseBody
	public List<Article> getArticles() {
		Article article = new Article(1,"제목1");
		Article article1 = new Article(2,"제목2");
		
		List<Article> list = new ArrayList<>();
		
		list.add(article);
		list.add(article1);
		
		return list;
	}
}	
@Data
@AllArgsConstructor
@NoArgsConstructor
class Article{
	
	private int id;
	private String title;
	
	
}
