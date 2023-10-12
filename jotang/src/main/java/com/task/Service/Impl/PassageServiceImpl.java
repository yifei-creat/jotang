package com.task.Service.Impl;

import com.task.Service.PassageService;
import com.task.mapper.PassageMapper;
import com.task.pojo.Passage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PassageServiceImpl implements PassageService {
    @Autowired
    PassageMapper passageMapper;
    @Override
    public List<Passage> list() {
        return passageMapper.list();
    }

    @Override
    public void delete(Integer id) {
        passageMapper.delete(id);
    }

    @Override
    public Passage selectById(Integer id) {
        return passageMapper.selectById(id);
    }

    @Override
    public void add(Passage passage) {
        passage.setCreateTime(LocalDateTime.now());
        passage.setUpdateTime(LocalDateTime.now());
        passageMapper.add(passage);
    }

    @Override
    public void update(Passage passage) {
        passageMapper.update(passage);
    }
}
