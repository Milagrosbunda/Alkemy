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
              <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ml-auto mr-0">
                  <li class="nav-item ">
                    <div class="tm-nav-link-highlight"></div>
                    <a class="nav-link" href="index.jsp"
                      >Home </a
                    >
                  </li>
                  <li class="nav-item">
                    <div class="tm-nav-link-highlight active"></div>
                    <a class="nav-link" href="Adm.jsp">Administradores </a>
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
        

      <section class="row tm-pt-4 tm-mb-3">
        <div class="col-12 tm-page-cols-container">

          <div class="tm-page-col-right tm-form-container">
            <h2 class="tm-text-secondary mb-4">Ingrese al sistema</h2>
            <form
              action="LogIn"
              method="POST"
              id="tm_contact_form"
              >
                
                    <% if(session.getAttribute("txtErrorLin")== null){
                    session.setAttribute("txtErrorLin"," ");
                    } %>
                    <p class="col-12 tm-copyright-text mb-0" name="txtError"> 
                    <%=session.getAttribute("txtErrorLin") %> </p>
                
                
              <div class="form-group">
                <input
                  type="txt"
                  name="id"
                  id="id"
                  class="form-control rounded-0 border-top-0 border-right-0 border-left-0"
                  placeholder="Ingrese su ID"
                  required/> 
              </div>
              <div class="form-group-2">
                <input
                  type="password"
                  name="pass"
                  id="pass"
                  class="form-control rounded-0 border-top-0 border-right-0 border-left-0"
                  placeholder="Ingrese su contraseña"
                  required />
              </div>

              <div class="">
                <button
                    type=submit
                  class="btn btn-secondary tm-btn-submit rounded-0">
                  Log In
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
