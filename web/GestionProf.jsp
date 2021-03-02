<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>Next Level - Contact</title>
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

      <div class="row tm-welcome-row">
        <div class="col-12 tm-page-cols-container">

            <h2>Nuevo registro</h2>
        </div>
      </div>

      <section class="row tm-pt-4 tm-mb-3">
        <div class="col-12 tm-page-cols-container">

          <div class="tm-page-col-right tm-form-container">
            <h2 class="tm-text-secondary mb-4">Gestion de profesores</h2>
            <form
              action="GestionProfesores"
              method="GET"
              id="tm_contact_form">
                
                   <% Connection con;
               String url = "jdbc:mysql://localhost:3306/universidad";
               String Driver = "com.mysql.jdbc.Driver";
               String user= "ingreso" ;
               String clave="";
               Class.forName(Driver);
               con = DriverManager.getConnection(url, user, clave);
               PreparedStatement ps;
               ResultSet rs;
               ps = con.prepareStatement("select * from profesor");
               rs = ps.executeQuery();  %>
               
                <% if(session.getAttribute("nombreP") == null){
                 session.setAttribute("nombreP", "_______");
                 session.setAttribute("apellidoP", "_______");
                 session.setAttribute("dniP", "_______");
                 session.setAttribute("activoP", "_______");
                }
                %>
                
               <div class="form-group-2 input-field">
                <p> Profesores: </p>
                <select class="tm-select" name="profesores">
                <% while(rs.next()){ 
                %>
                <option value=<%=rs.getInt("DNI")%> > <%= rs.getString("nombre")+ " " + rs.getString("apellido") %> </option>   <% }%>
                </select>
              </div>  
                <button class="btn btn-secondary tm-btn-submit rounded-0" type="submit">Mostrar Informacion</button></form>
                
                <form action="GestionProfesores"
              method="POST" id="tm_contact_form">
                    
                    <h2 class="tm-text-secondary mb-4">Modificar datos:</h2>
             
                    
                 <div class="form-group">
                <p
                  class="form-control rounded-0 border-top-0 border-right-0 border-left-0" style="font-weight: bold; color: #10707f;">
                  Nombre actual: <%= session.getAttribute("nombreP")  %></p>
                <input
                  type="text"
                  name="nombreN"
                  class="form-control rounded-0 border-top-0 border-right-0 border-left-0"
                  placeholder="Nuevo nombre del docente."
                   />
              </div>
                
                  <div class="form-group">
                <p
                  class="form-control rounded-0 border-top-0 border-right-0 border-left-0" style="font-weight: bold; color: #10707f;" 
                    >Apellido actual: <%= session.getAttribute("apellidoP")  %></p>
                <input
                  type="text"
                  name="apellidoN"
                  class="form-control rounded-0 border-top-0 border-right-0 border-left-0"
                  placeholder="Nuevo apellido del docente."
                   />
              </div>
                
                  <div class="form-group">
                <p
                  class="form-control rounded-0 border-top-0 border-right-0 border-left-0" style="font-weight: bold; color: #10707f;"
                  >DNI actual: <%= session.getAttribute("dniP")  %></p>
                <input
                  type="text"
                  name="dniN"
                  class="form-control rounded-0 border-top-0 border-right-0 border-left-0"
                  placeholder="Nuevo DNI del docente."
                  />
              </div>
                
                <div class="form-group">
                <p
                  name="nombre"
                  class="form-control rounded-0 border-top-0 border-right-0 border-left-0" style="font-weight: bold; color: #10707f;"
                  >Estado actual: <%= session.getAttribute("activoP")  %></p>
                <div class="form-group-2 input-field">
                <select class="tm-select" name="estadoN" >
                <option value="2" selected>Nuevo estado</option>
                <option value="0">Activo</option>  
                <option value="1">Inactivo</option>
                </select>
              </div> 
              </div>
            
              <div class="">
                <button
                  type="submit"
                  class="btn btn-secondary tm-btn-submit rounded-0">
                  Modificar
                </button>
              </div>
            </form>
                  <p name="txtError"><%= session.getAttribute("txtError") %></p>
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
    <script>
      $(document).on("change", ":file", function() {
        var input = $(this),
          numFiles = input.get(0).files ? input.get(0).files.length : 1,
          label = input
            .val()
            .replace(/\\/g, "/")
            .replace(/.*\//, "");
        input.trigger("fileselect", [numFiles, label]);
      });

      $(document).ready(function() {
        $(":file").on("fileselect", function(event, numFiles, label) {
          $("#file_name_label").attr("placeholder", label);
        });
      });
    </script>
  </body>
</html>
