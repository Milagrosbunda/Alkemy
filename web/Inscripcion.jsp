<%@page import="Logica.Materia"%>
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
                            <h1 class="text-uppercase tm-brand-name">Inscripciones</h1>
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

            <div class="">
                <ul class="tabs">
                    <li>
                        <a href="TotalMaterias.jsp" >
                            Todas las materias
                        </a>
                    </li>
                    <li>
                        <a href="MateriasXalumno.jsp" >
                            <div class="tm-tab-icon"></div>
                            Materias inscriptas
                        </a>
                    </li>
                </ul>
            </div>


            <section class="row tm-pt-4 tm-mb-3">
                <div class="col-12 tm-page-cols-container">

                    <div class="tm-page-col-right tm-form-container">
                        <h2 class="tm-text-secondary mb-4">Inscripciones</h2>
                        <form
                            action="Inscripciones"
                            method="GET"
                            id="tm_contact_form">


                            <%   Connection con;
                                String url = "jdbc:mysql://localhost:3306/universidad";
                                String Driver = "com.mysql.jdbc.Driver";
                                String user = "ingreso";
                                String clave = "";
                                Class.forName(Driver);
                                con = DriverManager.getConnection(url, user, clave);
                                PreparedStatement ps;
                                ResultSet rs;
                                ps = con.prepareStatement("select * from materia where cupo > 0 ");
                    rs = ps.executeQuery();  %>

                            <% if (session.getAttribute("horaSelec") == null) {
                                    session.setAttribute("horaSelec", "_______");
                                    session.setAttribute("profeSelec", "_______");
                                    session.setAttribute("descSelec", "_______");
                                    session.setAttribute("materiaSelec", "_______");
                                }
                            %>

                            <div class="form-group-2 input-field">
                                <p> Materia: </p>
                                <select required class="tm-select" name="materia">

                                    <% while (rs.next()) {
                                    %>
                                    <option value=<%=rs.getInt("code")%>> <%= rs.getString("nombre")%> </option>   <% }%>
                                </select>
                            </div>  
                            <button type="submit">Mostrar Informacion</button></form>

                        <p class="col-12 tm-copyright-text mb-0" name="txtError" style="font-weight: bold;">  <%=session.getAttribute("txtError")%> </p><br>

                        <form
                            action="Inscripciones"
                            method="POST"
                            id="tm_contact_form"> 
                            <div class="form-group">

                                <p style="font-size: larger; font-weight: bold;"><%= session.getAttribute("materiaSelec")%> </p><br>

                                    <p>Horario: <%=session.getAttribute("horaSelec")%> </p> 
                            </div>

                            <div class="form-group">
                                <p>Profesor: <%= session.getAttribute("profeSelec")%> </p>
                            </div>

                            <div class="form-group">
                                <p>Descripcion:  <%= session.getAttribute("descSelec")%></p>
                            </div>


                            <div class="">
                                <button
                                    type="submit"
                                    class="btn btn-secondary tm-btn-submit rounded-0">
                                    Inscribirse
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </section>



        </div>

        <script src="js/jquery.min.js"></script>
        <script src="js/parallax.min.js"></script>
        <script src="js/bootstrap.min.js"></script>

    </body>
</html>
