package com.hotel.dao;

import java.util.List;
import java.util.Map;

public interface ServiceDao extends ItemsDao {
	public List<Integer> selectSericeIdByUserIdAndNoComment(String uid);

	public List<Map<String, String>> selectSericeIdAllNoComment();
}
