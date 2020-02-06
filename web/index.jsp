<%@ page pageEncoding="UTF-8" language="java" import="java.sql.*"%>

<%@ page import = "bab.Kapcsolat"%>
<%@ page import = "bab.Register" %>
<%@ page import = "bab.Login" %>
<%@ page import = "bab.Bicikli" %>
<%@ page import = "bab.Kirandulas" %>

<jsp:useBean id="dbKapcs" class="bab.Kapcsolat" scope="session"/>
<jsp:useBean id="userReg" class="bab.Register" scope="session"/>
<jsp:useBean id="login" class="bab.Login" scope="session"/>
<jsp:useBean id="main" class="bab.Bicikli" scope="session"/>
<jsp:useBean id="trip" class="bab.Kirandulas" scope="session"/>

<jsp:setProperty name="userReg" property="*"/>
<jsp:setProperty name="login" property="*"/>
<jsp:setProperty name="main" property="*"/>
<jsp:setProperty name="trip" property="*"/>

<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="bootstrap.min.css">
    </head>
    <body>
        <div class="container">
            <div>
                <%@include file="WEB-INF/jspf/menu.jspf" %>
            </div>
            <div>
               
              
            <div>
                <%
                    
                    if(login.isLoggedIn())
                    {
                        
                %>
                 
                 
                
                <%
                       
                        if(request.getParameter("page") != null)
                        {
                            String showPage = request.getParameter("page");
                           
                            if (showPage.equals("allNotes")) {%>
                                <%@include file="WEB-INF/jspf/trips.jspf" %>
                                <%
                                }

                                 if (showPage.equals("index")) {%>
                                <div>
                Ismerősök:
                </div>
                            <%    
                                login.barataim(login.getId());
                                ResultSet c = login.listaLeker();
                                while (c.next())
                                { 
                            %> 
                            <div class="card" style="width: 25rem;" >
                            <div class="card-body">

                              <h5 class="card-title"><%out.print(c.getString("nev"));%></h5>
                              <h6 class="card-subtitle mb-2 text-muted"><%out.print(c.getString("felhasz"));%></h6>
                              <p class="card-text"><%out.print(c.getString("email"));%></p>
                            </div>
                            </div>

                            <br>
                    <% } 
       %>



                
                 <div>
                Akik elérhetőek:
                </div>
                   <%     login.mindenki();
                        ResultSet b = login.listaLeker();
                        while (b.next())
                        { 
                if(request.getParameter("jelol") == null)
                {
            %> 
                            <div class="card" style="width: 25rem;" style="display: inline;">
  <div class="card-body">
      <form action="index.jsp" method="POST">
    <h5 class="card-title"><%out.print(b.getString("nev"));%></h5>
    <h6 class="card-subtitle mb-2 text-muted"><%out.print(b.getString("felhasz"));%></h6>
    <p class="card-text"><%out.print(b.getString("email"));%></p>
    <input type="hidden" name="id" value="<%out.print(b.getInt("id"));%>" />
    <button class="btn btn-primary" name="jelol" type="submit" value="<%out.print(login.getId());%>">Barátnak jelölés</button>
         </form>
  </div>
</div>
                    
                        
                        
               
                    <br>
                    <%} 
                else
                    {
                   //login.baratnakJelol(id, login.getId() );
                    }




}
                    
                    %>
                    
                    
                <div>
                Közelgő túrák:
                </div>
                                <%
                                }

                            if (showPage.equals("newTrip")) {%>
                                <%@include file="WEB-INF/jspf/newTrip.jspf" %>
                                <%
                                }
                                
                            if (showPage.equals("newTrip2")) {%>
                                <%@include file="WEB-INF/jspf/newTrip2.jspf" %>
                                <%
                                }

                            if (showPage.equals("pd")) {%>
                                <%@include file="WEB-INF/jspf/szemelyesAdatok.jspf" %>
                                <%
                                }

                            if (showPage.equals("pw")) {%>
                                <%@include file="WEB-INF/jspf/passwordForgotten.jspf" %>
                                <%
                                }

                            if (showPage.equals("bicikli")) {%>
                                <%@include file="WEB-INF/jspf/bicikliatiras.jspf" %>
                                <%
                                }
                           
                        }
                    }
                    else if(request.getParameter("page") != null)
                    {
                        String showPage = request.getParameter("page");
                        
                        if (showPage.equals("login")) {%>
                                <%@include file="WEB-INF/jspf/login.jspf" %>
                                <%
                                }
                            if (showPage.equals("register")) {%>
                                <%@include file="WEB-INF/jspf/regist.jspf" %>
                                <%
                                }
                            if (showPage.equals("index")) {%>
                                
                                 <h1>Üdvözlöm a Bicikli igen webalkalmazásban!</h1>
            </div>
                <div>
                    <h3>Kiket ismerhetek:</h3>
                
                <p>Ha szeretnéd követni ezeket az embereket regisztrálj/jelentkezz be!!</p>
                
                 <%     login.mindenki();
                        ResultSet a = login.listaLeker();
                        while (a.next())
                        { 
            %> 
            
            <div class="card col-sm-3" style="width: 28rem;" style="display: inline;">
  <div class="card-body">
    <h5 class="card-title"><%out.print(a.getString("nev"));%></h5>
    <h6 class="card-subtitle mb-2 text-muted"><%out.print(a.getString("felhasz"));%></h6>
    <p class="card-text"><%out.print(a.getString("email"));%></p>
  </div>
</div>
                           <%}%>
                </div>
                                <%
                                }
                    }

                    
                %>
                 
            </div>
        </div>            
    </body>
</html>
