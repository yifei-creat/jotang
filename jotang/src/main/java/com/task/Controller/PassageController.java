package com.task.Controller;

import com.task.Service.PassageService;
import com.task.mapper.PassageMapper;
import com.task.pojo.PageBean;
import com.task.pojo.Passage;
import com.task.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
public class PassageController {
    @Autowired
    private PassageService passageService;

    //查询文章
    @GetMapping("/passage")
    public Result page(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer pageSize,
                       String author,String title,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                       @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate end){
        PageBean pageBean=passageService.page(page,pageSize,author,title,begin,end);

        return Result.success(pageBean);
    }

    //删除文章
    @DeleteMapping("/passage/{id}")
    public Result deleteById(@PathVariable Integer id){
        log.info("所删除的文章:{}",id);
        passageService.delete(id);
        return Result.success();
    }

    //根据id查询位置
    @GetMapping("/passage/{id}")
    public Result selectById(@PathVariable Integer id){
        log.info("查询的文章是:{}",id);
        Passage passage=passageService.selectById(id);
        return Result.success(passage);
    }

    //修改文章
    @PutMapping("/passage")
    public Result update(@RequestBody Passage passage){
        log.info("修改的数据为:{}",passage);
        passageService.update(passage);
        return Result.success();
    }
    //增加文章
    @PostMapping("/passage")
    public Result add(@RequestBody Passage passage){
        log.info("增加的文章是:{}",passage);
        passageService.add(passage);
        return Result.success(passage);
    }
}
