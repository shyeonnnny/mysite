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
		} else if("boardview".equals(actionName)) {
			action = new BoardViewAction();
		} else if("update".equals(actionName)) {
			action = new UpdateAction();
		} else if("delete".equals(actionName)) {
			action = new DeleteAction();
		} else if("deleteform".equals(actionName)) {
			action = new DeleteFormAction();
		}else {
			action = new BoardAction();
		}
		return action;
	}

}
