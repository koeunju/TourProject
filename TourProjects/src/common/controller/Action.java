package common.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {

    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception;
}
