package org.example.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.Default;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class Category {
    @NotNull(groups = Update.class)
    private Integer id;//主键ID
    //(groups = {Add.class,Update.class}) 不写就默认都有 但是要extends Default
//    @NotEmpty(groups = {Add.class,Update.class})
    @NotEmpty
    private String categoryName;//分类名称
    @NotEmpty(groups = {Add.class,Update.class})
    private String categoryAlias;//分类别名
    private Integer createUser;//创建人ID
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;//创建时间
    private LocalDateTime updateTime;//更新时间
    //分组校验 内部接口
    public interface Add extends Default {

    }

    public interface Update extends Default{

    }

}
