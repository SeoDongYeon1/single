package com.KoreaIT.java.AM.dao;

import java.util.ArrayList;
import java.util.List;

import com.KoreaIT.java.AM.dto.Article;

public class ArticleDao extends Dao{
	public List<Article> articles;
	
	public ArticleDao() {
		articles = new ArrayList<>();
	}
	
	public int setNewId() {
		return lastId + 1;
	}

	public void add(Article article) {
		articles.add(article);
		lastId++;
	}

	public void remove(Article foundArticle) {
		articles.remove(foundArticle);
	}
	
	public Article getArticleById(int id) {
		int i = 0;
		for(Article article : articles) {
			if(article.id==id) {
				return articles.get(i);
			}
			i++;
		}
		return null;
	}

}
