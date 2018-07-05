package com.hotel.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
import com.hotel.dao.CommentDao;
import com.hotel.dao.ServiceDao;
import com.hotel.models.Comment;
import com.hotel.models.Items;
import com.hotel.redis.RedisService;
import com.hotel.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
private ServiceDao serviceDao;
	@Autowired
private CommentDao commentDao;

    @Autowired
	private RedisService redisService;
    @Value("${REDIS_COMMENT_PRE}")
    private String REDIS_COMMENT_PRE;
    @Value("${REDIS_ITEMS_ITME_OUT}")
    private Long REDIS_ITEMS_ITME_OUT;

    @Override
    public PageInfo<Comment> getAllCommentByHotelId(String hotelId,int page,int rows) {

        String key = REDIS_COMMENT_PRE+":"+hotelId+","+page+","+rows;
        String json = (String) redisService.get(key);
        if (StringUtil.isNotEmpty(json)){
            PageInfo pageInfo = JSON.parseObject(json, PageInfo.class);
            return pageInfo;
        }
        PageHelper.startPage(page,rows);
        List<Comment> list = commentDao.getAllCommentByHotelId(hotelId);
        PageInfo<Comment> pageInfo = new PageInfo<>(list);
        redisService.set(key,JSON.toJSONString(pageInfo),REDIS_ITEMS_ITME_OUT);
        return pageInfo;
    }

    @Override
    public PageInfo<Comment> getCommentByHotelIdAndScore(String hotelId, Integer score,int page,int rows) {
        String key = REDIS_COMMENT_PRE+":"+hotelId+","+score+","+page+","+rows;
        String json = (String) redisService.get(key);
        if (StringUtil.isNotEmpty(json)){
            PageInfo pageInfo = JSON.parseObject(json, PageInfo.class);
            return pageInfo;
        }
        PageHelper.startPage(page,rows);
        List<Comment> list = commentDao.getCommentByHotelIdAndScore(hotelId, score);
        PageInfo<Comment> pageInfo = new PageInfo<>(list);
        redisService.set(key,JSON.toJSONString(pageInfo),REDIS_ITEMS_ITME_OUT);
        return pageInfo;
    }

    @Override
    public PageInfo<Comment> getCommentByHotelIdAndState(String hotelId, Integer state,int page,int rows) {
        String key = REDIS_COMMENT_PRE+":"+hotelId+","+state+","+page+","+rows;
        String json = (String) redisService.get(key);
        if (StringUtil.isNotEmpty(json)){
            PageInfo pageInfo = JSON.parseObject(json, PageInfo.class);
            return pageInfo;
        }
        PageHelper.startPage(page,rows);
        List<Comment> list = commentDao.getCommentByHotelIdAndState(hotelId,state);
        PageInfo<Comment> pageInfo = new PageInfo<>(list);
        redisService.set(key,JSON.toJSONString(pageInfo),REDIS_ITEMS_ITME_OUT);
        return pageInfo;
    }

    @Override
    public PageInfo<Comment> getCommentByUid(String uid,int page,int rows) {
        String key = REDIS_COMMENT_PRE+":"+uid+","+page+","+rows;
        String json = (String) redisService.get(key);
        if (StringUtil.isNotEmpty(json)){
            PageInfo pageInfo = JSON.parseObject(json, PageInfo.class);
            return pageInfo;
        }
        PageHelper.startPage(page,rows);
        List<Comment> list = commentDao.getCommentByUid(uid);
        PageInfo<Comment> pageInfo = new PageInfo<>(list);
        redisService.set(key,JSON.toJSONString(pageInfo),REDIS_ITEMS_ITME_OUT);
        return pageInfo;
    }

    @Override
    public PageInfo<Comment> getCommentBySid(Integer sid,int page,int rows) {
        String key = REDIS_COMMENT_PRE+":"+sid+","+page+","+rows;
        String json = (String) redisService.get(key);
        if (StringUtil.isNotEmpty(json)){
            PageInfo pageInfo = JSON.parseObject(json, PageInfo.class);
            return pageInfo;
        }
        PageHelper.startPage(page,rows);
        List<Comment> list = commentDao.getCommentBySid(sid);
        PageInfo<Comment> pageInfo = new PageInfo<>(list);
        redisService.set(key,JSON.toJSONString(pageInfo),REDIS_ITEMS_ITME_OUT);
        return pageInfo;
    }

	@Override
	public boolean autoComment(String uid) {
		if(uid!=null&&!uid.trim().equals("")){
			List<Integer> list= serviceDao.selectSericeIdByUserIdAndNoComment(uid);
			for( Integer in:list){
				Comment comment=new Comment();
				comment.setContent("此用户没有评价，系统默认好评");
				comment.setScore(5);
				comment.setIsanonymous(0);
				comment.setSid(in);
				comment.setTime(new Date());
				comment.setState(1);
				comment.setUid(uid);
				commentDao.addComment(comment);
			}
			return true;
		}else{
			return false;
		}
	}

	@Override
	public void addComment(Comment comment){
        Items items = serviceDao.getItemsById(comment.getSid());
        redisService.removePattern(REDIS_COMMENT_PRE+":"+items.getHid()+"*");
        redisService.removePattern(REDIS_COMMENT_PRE+":"+comment.getUid()+"*");
        redisService.removePattern(REDIS_COMMENT_PRE+":"+comment.getSid()+"*");
        commentDao.addComment(comment);
    }

    @Override
    public void deleteCommentByIds(Integer[] ids){
        Comment c = commentDao.getCommentById(ids[0]);
        Items items = serviceDao.getItemsById(c.getSid());
        redisService.removePattern(REDIS_COMMENT_PRE+":"+items.getHid()+"*");
        for (int id : ids){
            Comment comment = commentDao.getCommentById(id);
            redisService.removePattern(REDIS_COMMENT_PRE+":"+comment.getUid()+"*");
            redisService.removePattern(REDIS_COMMENT_PRE+":"+comment.getSid()+"*");
            commentDao.delteCommentById(id);
        }
    }

    @Override
    public Comment getCommentById(Integer id) {
        return commentDao.getCommentById(id);
    }
}
