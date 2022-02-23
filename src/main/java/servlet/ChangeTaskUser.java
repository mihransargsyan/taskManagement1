package servlet;

import manager.TaskManager;
import model.User;
import model.UserType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/changeTaskUser")
public class ChangeTaskUser extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int taskUserId = Integer.parseInt(req.getParameter("userId"));
        int taskId = Integer.parseInt(req.getParameter("taskId"));
        TaskManager taskManager = new TaskManager();
        taskManager.upDateTaskUser(taskUserId,taskId);
        resp.sendRedirect("/managerHome");
    }
}
