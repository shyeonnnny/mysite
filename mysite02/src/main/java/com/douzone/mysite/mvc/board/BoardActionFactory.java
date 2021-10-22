package com.douzone.mysite.mvc.board;

import com.douzone.web.mvc.Action;
import com.douzone.web.mvc.ActionFactory;

public class BoardActionFactory extends ActionFactory{
	
	
	public Action getAction(String actionName) {
		Action action = null;
		if("write".equals(actionName)) {
			action = new WriteAction();
		} else if("writeform".equals(actionName)) {
			action = new WriteFormAction();
		} else if("delete".equals(actionName)) {
			action = new DeleteAction();
		} else if("deleteform".equals(actionName)) {
			action = new DeleteFormAction();
		} else if("view".equals(actionName)) {
			action = new ViewAction();
		} else if("viewform".equals(actionName)) {
			action = new ViewFormAction();
		} else if("modifyform".equals(actionName)) { // 글내용보기
			action = new ModifyformAction();
		} else if("modify".equals(actionName)) { // 글내용보기
			action = new ModifyAction();
		} else if("replyform".equals(actionName)) { // 글내용보기
			action = new ReplyFormAction();
		} else if("reply".equals(actionName)) { // 글내용보기
			action = new ReplyAction();
		} else {
			action = new BoardAction();
		}
		return action;
	}

}
