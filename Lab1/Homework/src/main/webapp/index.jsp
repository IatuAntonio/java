<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    response.setHeader("Cache-Control","no-cache, no-store, must-revalidate");
    response.setHeader("Pragma","no-cache");
    response.setDateHeader ("Expires", 0);
%>

<!DOCTYPE html>
<html>
<head>
    <title>Welcome</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
    <h1>Welcome</h1>
    <br/>

    <form action="welcome" method="get">
        <label>What page do you want to see??</label>
        <br />
        <label>
            <input type="radio" name="page" value="1" required> Page 1
        </label>
        <br />
        <label>
            <input type="radio" name="page" value="2" required> Page 2
        </label>
        <br />
        <button type="submit">Go</button>
    </form>

</body>
</html>