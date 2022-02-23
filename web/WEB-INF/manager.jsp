<%@ page import="model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Task" %>
<%@ page import="java.text.SimpleDateFormat" %><%--
  Created by IntelliJ IDEA.
  User: NZP
  Date: 17.02.2022
  Time: 15:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ManagerHome</title>
</head>
<body>
<%
    List<User> users = (List<User>) request.getAttribute("users");
    List<Task> tasks = (List<Task>) request.getAttribute("tasks");
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
%>
<div style="width: 1000px; height: 1000px; margin: 0 auto">

    <div style="width: 1000px; height: 15px; margin: 0 auto; text-align: right">
        <a href="/logout" style="color: red"><h5>logout</h5></a>
    </div>

    <div style="margin: auto; width: 1000px;height: 220px">

        <div style="width: 450px; float: left">

            <h2>Add User:</h2>

            <form action="/userRegister" method="post" enctype="multipart/form-data">
                <input type="text" name="name" placeholder="name"><br>
                <input type="text" name="surname" placeholder="surname"><br>
                <input type="text" name="email" placeholder="email"><br>
                <input type="password" name="password" placeholder="password"><br>
                <input type="file" name="image"><br>
                <select name="type">
                    <option value="USER">USER</option>
                    <option value="MANAGER">MANAGER</option>
                </select><br>
                <input type="submit" value="Register">
            </form>
        </div>

        <div style="width: 450px; float: left">

            <h2>Add task:</h2>

            <form action="/addTask" method="post">
                <input type="text" name="name" placeholder="name"><br>
                <textarea name="description" placeholder="description"></textarea><br>
                <input type="date" name="date"><br>
                <select name="status">
                    <option value="NEW">NEW</option>
                    <option value="IN_PROGRESS">IN_PROGRESS</option>
                    <option value="FINISHED">FINISHED</option>
                </select><br>
                <select name="user_id">
                    <%
                        for (User user : users) { %>
                    <option value="<%=user.getId()%>"><%=user.getName()%> <%=user.getSurname()%>
                    </option>
                    <% }
                    %>
                </select><br>
                <input type="submit" value="AddTask">
            </form>
        </div>

    </div>

    <div>

        <div style="width: 1000px; float: left">

            <h2>All Users:</h2>

            <table border="1">
                <tr>
                    <th>name</th>
                    <th>surname</th>
                    <th>email</th>
                    <th>type</th>
                    <th>changeType</th>
                    <th>delete</th>
                </tr>
                <%
                    for (User user : users) {

                %>
                <tr>
                    <td><%=user.getName()%>
                    </td>
                    <td><%=user.getSurname()%>
                    </td>
                    <td><%=user.getEmail()%>
                    </td>
                    <td><%=user.getUserType().name()%>
                    </td>
                    <td>
                        <form action="/changeUserType" method="post">
                            <input type="hidden" name="userId" value="<%=user.getId()%>">
                            <select name="type">
                                <option value="USER">USER</option>
                                <option value="MANAGER">MANAGER</option>
                            </select> <input type="submit" value="OK">
                        </form>
                    </td>
                    <td><a href="/deleteUser?id=<%=user.getId()%>"> Delete</a></td>
                </tr>
                <%
                    }
                %>
            </table>
        </div>

        <div style="width: 1200px;float: left">

            <h2>All Tasks:</h2>

            <table border="1">
                <tr>
                    <th>name</th>
                    <th>description</th>
                    <th>deadline</th>
                    <th>status</th>
                    <th>Update status</th>
                    <th>user</th>
                    <th>changeUser</th>
                    <th>delete</th>
                </tr>
                <%
                    for (Task task : tasks) {

                %>
                <tr>
                    <td><%=task.getName()%>
                    </td>
                    <td><%=task.getDescription()%>
                    </td>
                    <td><%=sdf.format(task.getDeadline())%>
                    </td>
                    <td><%=task.getTaskStatus()%>
                    </td>
                    <td>
                        <form action="/changeTaskStatus" method="post">
                            <input type="hidden" name="taskId" value="<%=task.getId()%>">
                            <select name="status">
                                <option value="NEW">NEW</option>
                                <option value="IN_PROGRESS">IN_PROGRESS</option>
                                <option value="FINISHED">FINISHED</option>
                            </select> <input type="submit" value="OK">
                        </form>
                    </td>
                    <td><%=task.getUser().getName() + " " + task.getUser().getSurname()%>
                    </td>
                    <td>
                        <form action="/changeTaskUser" method="post">
                            <input type="hidden" name="taskId" value="<%=task.getId()%>">
                            <select name="userId">
                                <%
                                    for (User user : users) { %>
                                <option value="<%=user.getId()%>"><%=user.getName()%> <%=user.getSurname()%>
                                </option>
                                <% }
                                %>
                            </select><input type="submit" value="OK">
                        </form>
                    </td>
                    <td><a href="/deleteTask?id=<%=task.getId()%>"> Delete</a></td>
                </tr>
                <%
                    }
                %>
            </table>
        </div>
    </div>
</div>
</body>
</html>
