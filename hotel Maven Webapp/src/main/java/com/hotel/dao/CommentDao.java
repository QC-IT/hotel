package com.hotel.dao;

import com.hotel.models.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *  评论相关
 */
public interface CommentDao {
    /**
     * 根据酒店ID查看所有评论
     * @param hotelId 酒店id
     * @return
     */
    public List<Comment> getAllCommentByHotelId(@Param("hotelId")String hotelId);

    /**
     * 根据酒店ID，评分查看评论
     * @param hotelId
     * @param score
     * @return
     */
    public List<Comment> getCommentByHotelIdAndScore(@Param("hotelId")String hotelId,@Param("score")Integer score);

    /**
     * 根据酒店ID，评论状态查看评论
     * @param hotelId
     * @param state
     * @return
     */
    public List<Comment> getCommentByHotelIdAndState(@Param("hotelId")String hotelId,@Param("state")Integer state);

    /**
     * 根据用户id查看评论
     * @param uid
     * @return
     */
    public List<Comment> getCommentByUid(@Param("uid")String uid);

    /**
     * 根据服务ID查看评论
     * @param sid
     * @return
     */
    public List<Comment> getCommentBySid(@Param("sid") Integer sid);

    public void addComment(Comment comment);

    public Comment getCommentById(@Param("id") Integer id);

    public void delteCommentById(@Param("id") Integer id);
}
