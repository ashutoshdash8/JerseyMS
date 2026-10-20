<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Jersey Form - Jersey MS</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div class="container small">
        <h2>
            <c:choose>
                <c:when test="${jersey != null}">Edit Jersey</c:when>
                <c:otherwise>Add New Jersey</c:otherwise>
            </c:choose>
        </h2>

        <form action="jerseys" method="post">
            <input type="hidden" name="id" value="${jersey.id}">

            <label>Team Name</label>
            <input type="text" name="teamName" value="${jersey.teamName}" required>

            <label>Player Name</label>
            <input type="text" name="playerName" value="${jersey.playerName}" required>

            <label>Jersey Number</label>
            <input type="number" name="jerseyNumber" min="1" max="99" value="${jersey.jerseyNumber}" required>

            <label>Size</label>
            <select name="size">
                <c:forEach var="s" items="S,M,L,XL,XXL">
                    <option value="${s}" ${jersey.size == s ? 'selected' : ''}>${s}</option>
                </c:forEach>
            </select>

            <label>Price (&#8377;)</label>
            <input type="number" step="0.01" min="0" name="price" value="${jersey.price}" required>

            <label>Quantity</label>
            <input type="number" min="0" name="quantity" value="${jersey.quantity}" required>

            <button type="submit" class="btn">Save</button>
            <a class="btn grey" href="jerseys">Cancel</a>
        </form>
    </div>
</body>
</html>