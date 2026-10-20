<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Jerseys - Jersey MS</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div class="container">
        <div class="topbar">
            <h2>&#9917; Jersey Stock</h2>
            <span>Welcome, ${sessionScope.user} | <a href="logout">Logout</a></span>
        </div>

        <form action="jerseys" method="get" class="searchbar">
            <input type="text" name="search" placeholder="Search by team or player" value="${param.search}">
            <button type="submit" class="btn">Search</button>
            <a class="btn" href="jerseys?action=new">+ Add Jersey</a>
        </form>

        <table>
            <tr>
                <th>ID</th><th>Team</th><th>Player</th><th>No.</th>
                <th>Size</th><th>Price (&#8377;)</th><th>Qty</th><th>Actions</th>
            </tr>
            <c:forEach var="j" items="${jerseyList}">
                <tr>
                    <td>${j.id}</td>
                    <td>${j.teamName}</td>
                    <td>${j.playerName}</td>
                    <td>${j.jerseyNumber}</td>
                    <td>${j.size}</td>
                    <td>${j.price}</td>
                    <td>${j.quantity}</td>
                    <td>
                        <a href="jerseys?action=edit&id=${j.id}">Edit</a> |
                        <a href="jerseys?action=delete&id=${j.id}"
                           onclick="return confirm('Delete this jersey?');">Delete</a>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty jerseyList}">
                <tr><td colspan="8" class="center">No jerseys found.</td></tr>
            </c:if>
        </table>
    </div>
</body>
</html>