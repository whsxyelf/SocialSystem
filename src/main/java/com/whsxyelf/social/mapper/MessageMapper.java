package com.whsxyelf.social.mapper;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;

import com.whsxyelf.social.bean.Message;

@Mapper
public interface MessageMapper {
	/*
	 *	双方相互关注，私信才能发送成功。 
	 */
	
	//1.发送一条消息
	@InsertProvider(method = "sendMessage",type = MessageDaoProvider.class)
	public int sendMessage(Message message);
	//2.展示消息
	@SelectProvider(method = "showMessage",type = MessageDaoProvider.class)
	public Message showMessage(@Param("userNo")int userNo,@Param("concernedNo")int concernedNo);
	
	class MessageDaoProvider{
		public String sendMessage(Message message) {
			return new SQL() {{
				INSERT_INTO("message");
				VALUES("user_no", "#{userNo}");
				VALUES("concerned_no", "#{concernedNo}");
				VALUES("content", "#{content}");
				VALUES("create_time","#{createTime}");
			}}.toString(); 
		}
		
		public String showMessage(int userNo,int concernedNo) {
			return new SQL() {{
				SELECT("user_no");
				SELECT("concerned_no");
				SELECT("content");
				FROM("message");
				WHERE("user_no=#{userNo}");
				WHERE("concerned_no=#{concernedNo}");
			}}.toString();
			 
		}
		
		
	}
	

}
