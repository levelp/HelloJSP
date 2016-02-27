<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="style.css"/>
    <title>Моё первое Web-приложение</title>
</head>
<body>

<h2>Таблица умножения</h2>

<%
    // Размер таблицы
    int size = 10; // 10 - значение по-умолчанию
    String sizeStr = request.getParameter("size");
    if (sizeStr != null) {
        try {
            size = Integer.parseInt(sizeStr);
        } catch (Exception e) {
            out.write("<div class='error'> Ошибка в параметре 'size': " + e.toString() + "</div>");
        }
    } else {
        out.write("Параметр 'size' не указан, использую значение по-умолчанию: size = " + size);
    }
%>

<table border="1">
    <%
        // Цикл по строчкам
        for (int i = 1; i <= size; i++) { %>
    <tr>
        <%
            // Цикл по столбцам
            for (int j = 1; j <= size; j++) { %>
        <td class="cell">
            <%=   i * j  %>
        </td>
        <% } %>
    </tr>
    <% } %>
</table>

<!-- Дата создания страницы: <%= new java.util.Date() %> -->
<hr>
Имя вашего хоста: <%= request.getRemoteHost() %>

</body>
</html>
