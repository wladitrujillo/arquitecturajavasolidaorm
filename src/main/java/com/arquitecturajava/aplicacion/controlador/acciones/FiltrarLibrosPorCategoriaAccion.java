package com.arquitecturajava.aplicacion.controlador.acciones;

import com.arquitecturajava.aplicacion.bo.Categoria;
import com.arquitecturajava.aplicacion.bo.Libro;
import com.arquitecturajava.aplicacion.dao.CategoriaDao;
import com.arquitecturajava.aplicacion.dao.LibroDao;
import com.arquitecturajava.aplicacion.factory.DaoAbstractFactory;
import com.arquitecturajava.aplicacion.factory.DaoFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author cecilio alvarez caules contacto@arquitecturajava.com
 * @version 1.0
 */
public class FiltrarLibrosPorCategoriaAccion extends Accion {

    @Override
    public String ejecutar(HttpServletRequest request,
                           HttpServletResponse response) {
        DaoFactory factory = DaoAbstractFactory.getInstance();
        LibroDao libroDao = factory.getLibroDao();
        CategoriaDao categoriaDao = factory.getCategoriaDao();

        List<Libro> listaDeLibros = null;
        List<Categoria> listaDeCategorias = categoriaDao.buscarTodos();

        if (request.getParameter("categoria") == null
                || request.getParameter("categoria").equals("seleccionar")) {

            listaDeLibros = libroDao.buscarTodos();

        } else {

            Categoria categoria = categoriaDao.buscarPorClave(Integer.parseInt(request
                    .getParameter("categoria")));
            listaDeLibros = libroDao.buscarPorCategoria(categoria);

        }
        request.setAttribute("listaDeLibros", listaDeLibros);
        request.setAttribute("listaDeCategorias", listaDeCategorias);

        return "MostrarLibros.jsp";
    }

}
