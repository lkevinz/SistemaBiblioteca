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
    
    // Si se requiere implementar la devolución, se puede agregar un método:
    public boolean devolverPrestamo(int idPrestamo) {
        // Aquí se debería actualizar la base de datos para marcar la devolución
        // Por ejemplo, actualizar el registro para cambiar el estado del préstamo o del libro.
        // Se deja la implementación según la lógica de negocio.
        return true;
    }
}
