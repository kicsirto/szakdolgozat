<%-- 
    Document   : admin
    Created on : 2019.12.19., 17:27:46
    Author     : besse
--%>

<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="login" scope="session" class="bab.Bicikli" />
<jsp:useBean id="con" scope="session" class="bab.Kapcsolat" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div>
            <h1>Jelenlegi felhasználók</h1>
            <table class="table">
                <tr>
                    <td>Id</td><td>nev</td><td>email</td><td>felhasznalonev</td><td>jog</td><td>bicikli</td>   
                </tr>
                <%
                    try 
                    {
                        login.adatok();
                        ResultSet temp = login.listaLeker();
                        while (temp.next()) 
                        {   %>                             
                         <tr> 
                             <td><%out.print(temp.getInt("id"));%></td>
                             <td><%out.print(temp.getString("nev"));%></td>
                             <td><%out.print(temp.getString("email"));%></td>
                             <td><%out.print(temp.getString("felhasz"));%></td>
                             <td><%out.print(temp.getString("jog"));%></td>
                             <td><%out.print(temp.getInt("bicikliId"));%></td>
                             <td><%out.print(temp.getInt("id"));%><a href="modofy.jsp"><button class="btn btn-success">Elfelejtett</button></a></td>
                         </tr>   
                        <%  }

                    }
                    catch (Exception e) 
                    {
                        out.print("Hiba az adatok lekérésénél az oldalon" +e);
                    }
                %>
            </table>
        </div>
        
    </body>
</html>
