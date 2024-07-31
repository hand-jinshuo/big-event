package org.example.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.example.mapper.ArticleMapper;
import org.example.pojo.Article;
import org.example.pojo.PageBean;
import org.example.service.ArticleService;
import org.example.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public void add(Article article) {
        article.setCreateTime(LocalDateTime.now());
        article.setUpdateTime(LocalDateTime.now());
        Map<String,Object> map =  ThreadLocalUtil.get();
        Integer userId = (Integer)map.get("id");
        article.setCreateUser(userId);
        articleMapper.add(article);
    }

    @Override
    public PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state) {
        //1创建pagebean对象
        PageBean<Article> pb = new PageBean<>();
        //2开启分页查询 pagehelper  mybatis插件
        PageHelper.startPage(pageNum,pageSize);
        //3调用mapper完成查询  pageNum,pageSize不用传 会拼接到sql后面 通过limit
        Map<String,Object> map =  ThreadLocalUtil.get();
        Integer userId = (Integer)map.get("id");
        List<Article> as= articleMapper.list(userId,categoryId,state);
        //pageHelper最终完成分页查询后会返回一个page对象 这个page对象是mybatis中的
        Page<Article> p = (Page<Article>) as;
        //然后把数据填充到pagebean中
        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());
        return pb;
    }
}
