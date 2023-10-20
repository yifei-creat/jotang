package com.task.Service;

import com.task.pojo.PageBean;
import com.task.pojo.Passage;

import java.time.LocalDate;
import java.util.List;

public interface PassageService {
    //查询位置


    //删除文章
    void delete(Integer id);

    //根据id查询文章
    Passage selectById(Integer id);

    //增加文章
    void add(Passage passage);

    //修改文章
    void update(Passage passage);
    //分页查询
    PageBean page(Integer page, Integer pageSize, String author,String title, LocalDate begin, LocalDate end);
}
