<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <head>
        <title>View Words</title>
        <!-- <link href="<c:url value="/css/common.css"/>" rel="stylesheet" type="text/css"> -->
        <link href="../styles/styles.css" rel="stylesheet" type="text/css">
    </head>
    <body class="container">
    <%
                int counter = 0;
            %>
 
        <table>
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Japanese</th>
                    <th>English</th>
                    <th>Readings</th>
                </tr>
            </thead>
            <tbody>
            
            
                <c:forEach items="${vocab}" var="word">
                    <%
                        counter++;
                    %>
                    <tr>
                        <td><%
                            out.println(counter);
                        %></td>
                        <td>${word.japanese}</td>
                        <td>${word.english}</td>
                        <td>${word.readings}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        
    </body>
</html>