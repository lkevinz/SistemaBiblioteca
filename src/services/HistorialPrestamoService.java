package services;

import dao.HistorialPrestamoDAO;
import models.HistorialPrestamo;
import java.util.List;

public class HistorialPrestamoService {
    private HistorialPrestamoDAO historialDAO = new HistorialPrestamoDAO();

    public void registrarPrestamo(HistorialPrestamo prestamo) {
        historialDAO.registrarPrestamo(prestamo);
        System.out.println("Préstamo registrado con éxito.");
    }

    public List<HistorialPrestamo> listarHistorial() {
        return historialDAO.obtenerHistorial();
    }
}
