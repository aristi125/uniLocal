package co.org.uniquindio.unilocal.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FilePermissionChecker {

    public static void checkWritePermission(String directoryPath) throws IOException {
        File dir = new File(directoryPath);
        if (!dir.exists()) {
            throw new IOException("La ruta especificada no existe: " + directoryPath);
        }
        if (!dir.canWrite()) {
            throw new IOException("No se tienen permisos de escritura en la ruta: " + directoryPath);
        }

        // Crear un archivo de prueba para verificar permisos de escritura
        File testFile = new File(directoryPath, "test.txt");
        try (FileWriter writer = new FileWriter(testFile)) {
            writer.write("This is a test.");
        }
        if (!testFile.delete()) {
            throw new IOException("Falló al eliminar el archivo de prueba.");
        }
    }
}
