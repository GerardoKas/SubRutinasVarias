import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ListZipFiles {
    public static void main(String[] args) {
        String zipDir = "D:";
        String listTemp = "ListadoSimple.txt";

        System.out.println("[unidad:\\ruta\\a\\los\\zip] o solo [unidad:]");
        System.out.print("Escribe: ");
        // Code for user input can be added here

        System.out.println("Fase 0.0 Listando zips de: " + zipDir);

        System.out.println("Preparados para hacer un Listado de Zips (tardara maximo 10 minutos dependiendo de la cantidad de ficheros)...");
        System.out.println("No vas a ver nada pero debes tener paciencia");
        System.out.println();
        System.out.println("ListaZips: " + listTemp);

        File listTempFile = new File(listTemp);
        if (listTempFile.exists()) {
            if (listTempFile.delete()) {
                System.out.println("Deleted existing " + listTemp);
            } else {
                System.out.println("Failed to delete existing " + listTemp);
            }
        }

        try (FileWriter writer = new FileWriter(listTempFile)) {
            listFilesInDirectory(new File(zipDir), writer, "zip");
            listFilesInDirectory(new File(zipDir), writer, "rar");
            listFilesInDirectory(new File(zipDir), writer, "7z");
            // Add more file extensions if needed
        } catch (IOException e) {
            System.err.println("An error occurred while listing files: " + e.getMessage());
        }
    }

    private static void listFilesInDirectory(File directory, FileWriter writer, String extension) throws IOException {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.getName().endsWith("." + extension)) {
                    writer.write(file.getAbsolutePath() + "\n");
                } else if (file.isDirectory()) {
                    listFilesInDirectory(file, writer, extension);
                }
            }
        }
    }
}