package com.KoreaIT.java.AM.service;

import com.KoreaIT.java.AM.dao.ArticleDao;
import com.KoreaIT.java.AM.dto.Article;
import com.KoreaIT.java.container.Container;

public class ArticleService {
	private ArticleDao articleDao;
	
	public ArticleService() {
		articleDao = Container.articleDao;
	}

	public void add(Article article) {
		articleDao.add(article);
	}

	public void remove(Article foundArticle) {
		articleDao.remove(foundArticle);
	}

	public Article getArticleById(int id) {
		return articleDao.getArticleById(id);
	}

	public int setNewId() {
		return articleDao.setNewId();
	}

}
