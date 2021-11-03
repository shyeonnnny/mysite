package com.douzone.mysite.mvc.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class SearchAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<BoardVo> boardVo = null;
		
		String kwd = request.getParameter("kwd");
		Long p = Long.parseLong(request.getParameter("p"));
		Long startBoard = (p*10)-9;
		
		Long boardLength = new BoardRepository().countBoardNum();
		
		Long startPageNum = ((((p-1)/5)*5)+1);
		Long endPageNum = startPageNum+4;
		Long maxPageNum = ((boardLength/10)+((boardLength%10)==0?0:1));
		
		System.out.println(startPageNum + "@@@@@" + endPageNum + "@@@@" + maxPageNum + "@@@" + boardLength);
		System.out.println(kwd);
		if(endPageNum > maxPageNum) {
			endPageNum=maxPageNum;
		}
		
		boardVo = new BoardRepository().searchFile(kwd,p);
		
		
		request.setAttribute("p", p);
		request.setAttribute("startBoard", startBoard);
		request.setAttribute("maxPageNum", maxPageNum);
		request.setAttribute("maxBoardNum", boardLength);
		request.setAttribute("startPageNum", startPageNum);
		request.setAttribute("endPageNum", endPageNum);
		request.setAttribute("boardList", boardVo);
		
		
		
		MvcUtil.forward("board/list", request, response);

	}

}
