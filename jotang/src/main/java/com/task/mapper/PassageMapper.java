package com.task.mapper;

import com.task.pojo.Passage;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface PassageMapper {
    //查询所有文章
    //@Select("select * from jotang.passage")
    public List<Passage> page(String author, String title, LocalDate begin, LocalDate end);


    @Delete("delete from jotang.passage where id=#{id}")
    void delete(Integer id);

    //根据id查询
    @Select("select * from jotang.passage where id=#{id}")
    Passage selectById(Integer id);

    //增加文章
    void add(Passage passage);

    //修改文章
    void update(Passage passage);


}
