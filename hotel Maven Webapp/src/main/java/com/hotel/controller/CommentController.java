package com.hotel.controller;

import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
import com.hotel.models.Comment;
import com.hotel.models.Result;
import com.hotel.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;

@Controller
@RequestMapping("comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "getCommentByUid.json",produces="application/json;charset=utf8")
    public Result getCommentByUid(@RequestBody HashMap<String ,Object> map){
        String uid = (String) map.get("uid");
        Integer page = (Integer)map.get("page");
        Integer rows = (Integer)map.get("rows");
        if (StringUtil.isNotEmpty(uid)&&page>=0&&rows>0){
            PageInfo<Comment> pageInfo = commentService.getCommentByUid(uid, page, rows);
            return Result.success("查询成功",pageInfo.getList());
        }else {
            return Result.fail("传入参数错误");
        }
    }
    @RequestMapping(value = "getCommentBySid.json",produces="application/json;charset=utf8")
    public Result getCommentBySid(@RequestBody HashMap<String,Integer> map){
        Integer sid = (Integer)map.get("sid");
        Integer page = (Integer)map.get("page");
        Integer rows = (Integer)map.get("rows");
        if (sid>=0&&page>=0&&rows>0){
            PageInfo<Comment> pageInfo = commentService.getCommentBySid(sid, page, rows);
            return Result.success("查询成功",pageInfo.getList());
        }else{
            return Result.fail("传递参数错误");
        }
    }
    @RequestMapping(value = "addComment.json",produces="application/json;charset=utf8",method=RequestMethod.POST)
    public Result addComment(@RequestBody Comment comment){
        if (comment != null){
            commentService.addComment(comment);
            return Result.success("添加成功",null);
        }else {
            return Result.fail("传递参数错误");
        }
    }
    @RequestMapping(value = "deleteCommentByIds.json",produces="application/json;charset=utf8")
    public Result deleteCommentByIds(@RequestBody Integer[] ids){
        if (ids!=null){
            commentService.deleteCommentByIds(ids);
            return Result.success("删除成功",null);
        }else {
            return Result.fail("参数传递错误");
        }
    }
    @RequestMapping(value = "getCommentById.json",produces="application/json;charset=utf8")
    public Result getCommentById(@RequestBody Integer id){
        if (id > 0){
            Comment comment = commentService.getCommentById(id);
            return Result.success("查询成功",comment);
        }else {
            return Result.fail("参数传递错误");
        }
    }
}
