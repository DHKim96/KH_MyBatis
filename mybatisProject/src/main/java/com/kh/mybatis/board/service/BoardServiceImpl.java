package com.kh.mybatis.board.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;

import com.kh.mybatis.board.model.dao.BoardDao;
import com.kh.mybatis.board.model.vo.Board;
import com.kh.mybatis.board.model.vo.Reply;
import com.kh.mybatis.common.template.PageInfo;
import com.kh.mybatis.common.template.Template;

public class BoardServiceImpl implements BoardService{

	private BoardDao bDao = new BoardDao(); 
	
	/**
	 * 게시판 리스트 조회하는 추상메소드 구현
	 */
	@Override
	public int selectListCount() {
		SqlSession sqlSession = Template.getSqlSession();
		
		int listCount = bDao.selectListCount(sqlSession);
		
		sqlSession.close();
		
		return listCount;
	}

	@Override
	public ArrayList<Board> selectBoardList(PageInfo pi) {
		SqlSession sqlSession = Template.getSqlSession();
		
		ArrayList<Board> boards = bDao.selectBoardList(sqlSession, pi);
		
		sqlSession.close();
		
		return boards;
	}

	@Override
	public int selectSearchCount(HashMap<String, String> map) {
		SqlSession sqlSession = Template.getSqlSession();
		
		int searchCount = bDao.selectSearchCount(sqlSession, map);
		
		sqlSession.close();
		
		return searchCount;
	}

	@Override
	public ArrayList<Board> selectSearchList(HashMap<String, String> map, PageInfo pi) {
		SqlSession sqlSession = Template.getSqlSession();
		
		ArrayList<Board> boards = bDao.selectSearchList(sqlSession, map, pi);
		
		sqlSession.close();
		
		return boards;
	}
	

	@Override
	public Board increaseCount(int boardNo) {
		SqlSession sqlSession = Template.getSqlSession();
		
		Board b = null;
		
		int result = bDao.increaseCount(sqlSession, boardNo);
		
		if(result > 0) {
			sqlSession.commit();
			b = bDao.selectBoard(sqlSession, boardNo);
		} else {
			sqlSession.rollback();
		}
		
		sqlSession.close();
		
		return b;
	}

	@Override
	public ArrayList<Reply> selectReplyList(int boardNo) {
		SqlSession sqlSession = Template.getSqlSession();
		
		ArrayList<Reply> replies = bDao.selectReplyList(sqlSession, boardNo);
		
		sqlSession.close();
		
		return replies;
	}

	


}
