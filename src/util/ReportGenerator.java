package util;

import net.sf.jasperreports.engine.*;
import java.sql.Connection;

public class ReportGenerator {
    public static void generarReporte(String reportPath, Connection connection) {
        try {
            JasperReport report = JasperCompileManager.compileReport(reportPath);
            JasperPrint print = JasperFillManager.fillReport(report, null, connection);
            JasperExportManager.exportReportToPdfFile(print, "reporte.pdf");
            System.out.println("Reporte generado correctamente.");
        } catch (JRException e) {
            e.printStackTrace();
        }
    }
}
