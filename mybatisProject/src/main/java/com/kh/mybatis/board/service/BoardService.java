package com.kh.mybatis.board.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.kh.mybatis.board.model.vo.Board;
import com.kh.mybatis.board.model.vo.Reply;
import com.kh.mybatis.common.template.PageInfo;

public interface BoardService {
	
	/**
	 * 게시판 리스트 조회
	 * @return
	 */
	public int selectListCount();
	
	public ArrayList<Board> selectBoardList(PageInfo pi);
	
	/**
	 * 게시글 검색
	 * @param map
	 * @return
	 */
	public int selectSearchCount(HashMap<String, String> map);
	
	/**
	 * 검색한 게시글 리스트 조회
	 * @param map
	 * @param pi
	 * @return
	 */
	public ArrayList<Board> selectSearchList(HashMap<String, String> map, PageInfo pi);
	
	/**
	 * 게시글 상세 조회
	 * @param boardNo
	 * @return
	 */
	public Board increaseCount(int boardNo);
	
	/**
	 * 댓글 조회
	 * @param boardNo
	 * @return
	 */
	public ArrayList<Reply> selectReplyList(int boardNo);
}
