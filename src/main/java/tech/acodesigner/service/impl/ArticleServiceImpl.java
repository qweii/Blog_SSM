package tech.acodesigner.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.acodesigner.dao.ArticleDao;
import tech.acodesigner.dto.ArticleDto;
import tech.acodesigner.dto.ArticleLiteDto;
import tech.acodesigner.dto.OperationResult;
import tech.acodesigner.entity.Article;
import tech.acodesigner.service.ArticleService;
import tech.acodesigner.util.PageUtil;

import java.util.List;

/**
 * Created by 77239 on 2017/4/2/0002.
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleDao articleDao;

    public List<ArticleDto> searchArticles(String key) {
        return articleDao.getArticlesByKey(key);
    }

    public List<ArticleDto> pagination(PageUtil pageUtil) {
        return articleDao.getArticlesByRange(pageUtil);
    }

    public ArticleLiteDto getPreArticle(int articleId) {
        ArticleLiteDto article = articleDao.getPreArticle(articleId);
        if (article == null) {
            article = new ArticleLiteDto(0, "无");
        }
        return article;
    }

    public ArticleLiteDto getNextArticle(int articleId) {
        ArticleLiteDto article = articleDao.getNextArticle(articleId);
        if (article == null) {
            article = new ArticleLiteDto(0, "无");
        }
        return article;
    }

    public OperationResult<ArticleDto> getArticleById(int articleId) {
        ArticleDto article = articleDao.getArticleById(articleId);
        OperationResult<ArticleDto> or = new OperationResult<ArticleDto>();
        if (article == null) {
            or.setSuccess(false);
            or.setInfo("该文章不存在");
        } else {
            or.setSuccess(true);
            or.setData(article);
        }
        return or;
    }

    public List<ArticleDto> getArticles() {
        return articleDao.getArticles();
    }

    public List<ArticleLiteDto> getArticlesByCategoryId(int categoryId) {
        return articleDao.getArticlesByCategoryId(categoryId);
    }

    public List<ArticleLiteDto> getRecentArticles() {
        return articleDao.getRecentArticlesTitle();
    }

    public OperationResult updateArticle(Article article) {
        OperationResult or = new OperationResult();
        int result = articleDao.updateArticle(article);
        if (result <= 0) {
            or.setSuccess(false);
            or.setInfo("修改失败");
        } else {
            or.setSuccess(true);
            or.setInfo("修改成功");
        }
        return or;
    }

    public OperationResult saveArticle(Article article) {
        OperationResult or = new OperationResult();
        int result = articleDao.saveArticle(article);
        if (result <= 0) {
            or.setSuccess(false);
            or.setInfo("保存失败");
        } else {
            or.setSuccess(true);
            or.setInfo("保存成功");
        }
        return or;
    }

    public OperationResult deleteArticle(int articleId) {
        OperationResult or = new OperationResult();
        int result = articleDao.deleteArticle(articleId);
        if (result <= 0) {
            or.setSuccess(false);
            or.setInfo("删除失败");
        } else {
            or.setSuccess(true);
            or.setInfo("删除成功");
        }
        return or;
    }

    public OperationResult addClicks(int articleId) {
        OperationResult or = new OperationResult();
        int result = articleDao.addClicks(articleId);
        if (result <= 0) {
            or.setSuccess(false);
            or.setInfo("操作失败");
        } else {
            or.setSuccess(true);
            or.setInfo("操作成功");
        }
        return or;
    }

    public int countArticleNum() {
        return articleDao.countArticleNum();
    }

}
