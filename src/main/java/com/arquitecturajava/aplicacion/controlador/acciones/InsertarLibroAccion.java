package com.arquitecturajava.aplicacion.controlador.acciones;

import com.arquitecturajava.aplicacion.bo.Categoria;
import com.arquitecturajava.aplicacion.bo.Libro;
import com.arquitecturajava.aplicacion.dao.LibroDao;
import com.arquitecturajava.aplicacion.factory.DaoAbstractFactory;
import com.arquitecturajava.aplicacion.factory.DaoFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author cecilio alvarez caules contacto@arquitecturajava.com
 * @version 1.0
 */
public class InsertarLibroAccion extends Accion {

    @Override
    public String ejecutar(HttpServletRequest request,
                           HttpServletResponse response) {
        DaoFactory factory = DaoAbstractFactory.getInstance();
        LibroDao libroDao = factory.getLibroDao();


        String isbn = request.getParameter("isbn");
        String titulo = request.getParameter("titulo");
        String categoria = request.getParameter("categoria");
        Categoria objetoCategoria = new Categoria(Integer.parseInt(categoria));

        Libro libro = new Libro(isbn, titulo, objetoCategoria);
        libroDao.insertar(libro);


        return "MostrarLibros.do";
    }


}
