package org.example.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.example.pojo.Article;

import java.util.List;

@Mapper
public interface ArticleMapper {
    @Insert("insert into " +
            "article(title,content,cover_img,state,category_Id,create_user,create_time,update_time) " +
            "values (#{title},#{content},#{coverImg},#{state},#{categoryId},#{createUser}," +
            "#{createTime},#{updateTime})"
    )
    void add(Article article);

    //用映射配置文件写动态sql
    List<Article> list(Integer userId, Integer categoryId, String state);
}
