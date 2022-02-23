package servlet;

import manager.TaskManager;
import manager.UserManager;
import model.User;
import model.UserType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/changeUserType")
public class ChangeUserTypeServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        int userId = Integer.parseInt(req.getParameter("userId"));
        String userType = req.getParameter("type");

        UserManager userManager = new UserManager();
        userManager.changeUserType(userId, userType);
        resp.sendRedirect("/managerHome");
    }
}
