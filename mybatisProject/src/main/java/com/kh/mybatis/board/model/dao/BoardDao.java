package com.kh.mybatis.board.model.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.kh.mybatis.board.model.vo.Board;
import com.kh.mybatis.board.model.vo.Reply;
import com.kh.mybatis.common.template.PageInfo;

public class BoardDao {

	public int selectListCount(SqlSession sqlSession) {
		return sqlSession.selectOne("boardMapper.selectListCount");
	}

	public ArrayList<Board> selectBoardList(SqlSession sqlSession, PageInfo pi) {
		// mybatis 에서는 페이징 처리를 위해 rowBounds 라는 클래스 제공
		// offset : 몇 개의 게시글을 건너뛰고 조회할 것인지에 대한 값
		/*
		 * currentPage : 1		1~5		0
		 * currentPage : 2		6~10	5
		 * currentPage : 3		11~15	10
		 */
		
		int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();
		
		int limit = pi.getBoardLimit();
		
		RowBounds rb = new RowBounds(offset, limit);
		
		ArrayList<Board> boards = (ArrayList)sqlSession.selectList("boardMapper.selectBoardList", null, rb);
		// list 로 반환되기 때문에 ArrayList 로 다운캐스팅해줘야함
		return boards;
	}

	public int selectSearchCount(SqlSession sqlSession, HashMap<String, String> map) {
		return  sqlSession.selectOne("boardMapper.selectSearchCount", map) ;
	}

	public ArrayList<Board> selectSearchList(SqlSession sqlSession, HashMap<String, String> map, PageInfo pi) {
		int limit = pi.getBoardLimit();
		int offset = (pi.getCurrentPage() - 1) * limit;
		
		RowBounds rbs = new RowBounds(offset, limit);
		
		
		return (ArrayList)sqlSession.selectList("boardMapper.selectSearchList", map, rbs);
	}

	public int increaseCount(SqlSession sqlSession, int boardNo) {
		return sqlSession.update("boardMapper.increaseCount", boardNo);
	}

	public Board selectBoard(SqlSession sqlSession, int boardNo) {
		return sqlSession.selectOne("boardMapper.selectBoard", boardNo);
	}

	public ArrayList<Reply> selectReplyList(SqlSession sqlSession, int boardNo) {
		return (ArrayList)sqlSession.selectList("boardMapper.selectReplyList", boardNo);
	}
	
	

}
