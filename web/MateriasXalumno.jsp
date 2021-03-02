

<%@page import="java.util.List"%>
<%@page import="Logica.Alumno"%>
<%@page import="Logica.Materia"%>
<%@page import="Logica.Controladora"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <meta http-equiv="X-UA-Compatible" content="ie=edge" />
        <title>Universidad Alkemy</title>
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
                            <h1 class="text-uppercase tm-brand-name">Bienvenidos</h1>
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
                                                       >Home</a
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
                        </nav>
                    </div>
                </div>
            </div>

            <div class="row tm-welcome-row">
                <div class="col-12">
                    <div
                        class="tm-welcome-parallax tm-center-child"
                        data-parallax="scroll"
                        data-image-src="univ.jpg"
                        >
                        <div class="tm-bg-black-transparent tm-parallax-overlay">
                            <h2>Materias</h2>
                        </div>
                    </div>
                </div>
            </div>

            <section class="row tm-pt-4">

                <div class="tm-page-col-left">
                    <ul class="tabs">
                        <li>
                            <a href="TotalMaterias.jsp" >
                                <div class="tm-tab-icon"></div>
                                Todas las materias
                            </a>
                        </li>
                        <li>
                            <a href="MateriasXalumno.jsp" >
                                <div class="tm-tab-icon active"></div>
                                Materias inscriptas
                            </a>
                        </li>
                        <li>
                            <a href="Inscripcion.jsp"  >
                                <div class="tm-tab-icon"></div>
                                Inscripciones
                            </a>
                        </li>
                    </ul>
                </div>




                <!-- CONEXION--------------------------------- -->

                <%
                    Controladora c = new Controladora();
                    int legajo = (int) session.getAttribute("legajoIn");
                    Alumno alumno = c.traerAlumno(legajo);
                    List<Materia> materias = alumno.getMaterias();
                    Iterator<Materia> it = materias.iterator();
                %>

                <table class="table">
                    <thead>
                        <tr>
                            <th scope="col"></th>
                            <th scope="col"></th>
                            <th scope="col"></th></tr>

                    </thead> <tbody>

                        <% while (it.hasNext()) {
                                Materia m = it.next();
                        %>              

                        <tr> 
                            <th> <div class="card" >                    
                                    <div class="card-body">
                                        <h4 class="titulo-materia"><%= m.getNombre()%></h4>
                                        <p class="card-text"><%= m.getDescripcion()%> <br> 
                                            Horario: <%= m.getHorario()%>hs.</p>
                                    </div>
                                </div>          </th> 

                        </tr>
                        <% }%>  
                    </tbody>
                </table>


            </section>
        </div>   



        <script src="js/jquery.min.js"></script>
        <script src="js/parallax.min.js"></script>
        <script src="js/imagesloaded.pkgd.min.js"></script>
        <script src="js/isotope.pkgd.min.js"></script>
        <script src="js/bootstrap.min.js"></script>

    </body>
</html>

