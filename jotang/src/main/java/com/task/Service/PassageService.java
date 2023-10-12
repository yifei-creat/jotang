package com.task.Service;

import com.task.pojo.Passage;

import java.util.List;

public interface PassageService {
    //查询位置
    List<Passage> list();

    //删除文章
    void delete(Integer id);

    //根据id删除文章
    Passage selectById(Integer id);

    //增加文章
    void add(Passage passage);

    //修改文章
    void update(Passage passage);
}
