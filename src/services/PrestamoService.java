package services;

import dao.PrestamoDAO;
import models.Prestamo;
import java.util.List;

public class PrestamoService {
    private PrestamoDAO prestamoDAO = new PrestamoDAO();

    public void registrarPrestamo(Prestamo prestamo) {
        prestamoDAO.registrarPrestamo(prestamo);
        System.out.println("Préstamo registrado con éxito.");
    }

    public List<Prestamo> listarPrestamos() {
        return prestamoDAO.obtenerPrestamos();
    }
}
