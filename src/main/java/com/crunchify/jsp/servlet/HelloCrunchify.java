package com.crunchify.jsp.servlet;
 
import edu.co.sergio.mundo.dao.ObrasDeArteDao;
import edu.co.sergio.mundo.vo.ObraArte;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import javax.servlet.RequestDispatcher;
 
/**
 * @author Crunchify.com
 */
 
public class HelloCrunchify extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // reading the user input
        
        
        String nombreO = request.getParameter("nombreO");
        String descrip = request.getParameter("descripcion");
        String estilo = request.getParameter("estilo");
        int valor = Integer.valueOf(request.getParameter("valor"));
        String nombreA = request.getParameter("nombreA");
        
        //Se debe incluir validaciones - Lo recuerda: Gestion de Excepciones.
        
        ObrasDeArteDao ADAO = new ObrasDeArteDao();
        
        ObraArte obra = new ObraArte();
        obra.setNombreO(nombreO);
        obra.setDescripcion(descrip);
        obra.setEstilo(estilo);
        obra.setValor(valor);
        obra.setNombreA(nombreA);
        
        ADAO.InsertarObraDeArte(obra);
        
        //Listando la informacion  
        List<ObraArte> obras =  ADAO.findAll();
        request.setAttribute("obras", obras);
       
       
        //Redireccionando la informacion
        RequestDispatcher redireccion = request.getRequestDispatcher("index.jsp");
        redireccion.forward(request, response);
        
        
        }
}
