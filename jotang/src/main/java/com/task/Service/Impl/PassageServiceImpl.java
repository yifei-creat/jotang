package com.task.Service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.task.Service.PassageService;
import com.task.mapper.PassageMapper;
import com.task.pojo.PageBean;
import com.task.pojo.Passage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PassageServiceImpl implements PassageService {
    @Autowired
    PassageMapper passageMapper;


    //删除文章
    @Override
    public void delete(Integer id) {
        passageMapper.delete(id);
    }

    //根据id查询
    @Override
    public Passage selectById(Integer id) {
        return passageMapper.selectById(id);
    }

    //增加文章
    @Override
    public void add(Passage passage) {
        passage.setCreateTime(LocalDateTime.now());
        passage.setUpdateTime(LocalDateTime.now());
        passageMapper.add(passage);
    }

    //修改数据
    @Override
    public void update(Passage passage) {
        passage.setUpdateTime(LocalDateTime.now());
        passageMapper.update(passage);
    }

    //分页查询
    @Override
    public PageBean page(Integer page, Integer pageSize,String author,String title, LocalDate begin, LocalDate end) {
        //设置分页
        PageHelper.startPage(page,pageSize);

        //执行查询
        List<Passage> passgelist = passageMapper.page(author,title,begin,end);
        Page<Passage> p=(Page<Passage>) passgelist;

        //包装返回数据
        PageBean pageBean=new PageBean(p.getTotal(),p.getResult());
        return pageBean;
    }
}
