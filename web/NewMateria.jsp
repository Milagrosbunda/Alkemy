<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Logica.Profesor"%>
<%@page import="java.util.List"%>
<%@page import="Logica.Controladora"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>Nueva Materia</title>
<!--
Next Level CSS Template
https://templatemo.com/tm-532-next-level
-->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Open+Sans:400,600" />
    <link rel="stylesheet" href="css/all.min.css" />
    <link rel="stylesheet" href="css/bootstrap.min.css" />
    <link rel="stylesheet" href="css/templatemo-style.css" />
  </head>
  <body>
    <div class="container-fluid">
      <div class="row tm-brand-row">
        <div class="col-lg-4 col-10">
          <div class="tm-brand-container">
            <div class="tm-brand-texts">
              <h1 class="text-uppercase tm-brand-name">Next Level</h1>
            </div>
          </div>
        </div>
 
          
                  <div class="col-lg-8 col-2 tm-nav-col">
          <div class="tm-nav">
            <nav class="navbar navbar-expand-lg navbar-light tm-navbar">
              <button
                class="navbar-toggler"
                type="button"
                data-toggle="collapse"
                data-target="#navbarNav"
                aria-controls="navbarNav"
                aria-expanded="false"
                aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
              </button>
              <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ml-auto mr-0">
                  <li class="nav-item active">
                    <div class="tm-nav-link-highlight"></div>
                    <a class="nav-link" href="index.jsp"
                      >Home </a
                    >
                  </li>
                  <li class="nav-item">
                    <div class="tm-nav-link-highlight"></div>
                    <a class="nav-link" href="Adm.jsp">Administradores</a>
                  </li>
                  <li class="nav-item">
                    <div class="tm-nav-link-highlight"></div>
                    <a class="nav-link" href="Alumnos.jsp">Alumnos</a>
                  </li>
                </ul>
              </div>
            </nav>
          </div>
        </div>
          
          
      </div>

        
 <p class="col-12 tm-copyright-text mb-0" name="txtError">  <%=session.getAttribute("txtError") %> </p>
 
      <section class="row tm-pt-4 tm-mb-3">
        <div class="col-12 tm-page-cols-container">
            
          <div class="tm-page-col-right tm-form-container">
            <h2 class="tm-text-secondary mb-4">Nuevo registro de materia.</h2>
            <form
              action="NuevoRegistro"
              method="POST"
              id="tm_contact_form">
                
              <div class="form-group">
                <input
                  type="text"
                  name="nombre"
                  class="form-control rounded-0 border-top-0 border-right-0 border-left-0"
                  placeholder="Nombre de la materia."
                  required="" />
              </div>
                
                   <div class="form-group">
                <input
                  type="text"
                  name="desc"
                  class="form-control rounded-0 border-top-0 border-right-0 border-left-0"
                  placeholder="Breve descripcion."
                  required="" />
              </div>
                
                 <div class="form-group">
                <input
                  type="number"
                  name="hora"
                  class="form-control rounded-0 border-top-0 border-right-0 border-left-0"
                  placeholder="Horario de comienzo de la clase."
                  min="7"
                  max="20"
                  required
                  />
              </div>
                
                <div class="form-group">
                <input
                  type="number"
                  name="cupo"
                  class="form-control rounded-0 border-top-0 border-right-0 border-left-0"
                  placeholder="Cupo maximo del curso."
                  min="0"
                  max="50"
                  required
                  />
              </div>
                
                
        <%   Connection con;
               String url = "jdbc:mysql://localhost:3306/universidad";
               String Driver = "com.mysql.jdbc.Driver";
               String user= "ingreso" ;
               String clave="";
               Class.forName(Driver);
               con = DriverManager.getConnection(url, user, clave);
               PreparedStatement ps;
               ResultSet rs;
               ps = con.prepareStatement("select * from profesor where activo = 0 ");
               rs = ps.executeQuery();  %>
                
               <div class="form-group-2 input-field">
                <p> Profesor a cargo: </p>
                <select class="tm-select" name="profe">
                <% while(rs.next()){ 
                %>
                <option value=<%=rs.getInt("dni")%>> <%= rs.getString("nombre") + " " + rs.getString("Apellido") %> </option> <% }%>  
                </select>
              </div>   
                
               


              <div class="">
                <button
                  type="submit"
                  class="btn btn-secondary tm-btn-submit rounded-0">
                  Registrar
                </button>
              </div>
            </form>
          </div>
        </div>
      </section>


      <footer class="row tm-page-footer">
        <p class="col-12 tm-copyright-text mb-0">
          Copyright &copy; 2019 Next Level Company... Designed by Template Mo
        </p>
      </footer>
    </div>

    <script src="js/jquery.min.js"></script>
    <script src="js/parallax.min.js"></script>
    <script src="js/bootstrap.min.js"></script>

  </body>
</html>
