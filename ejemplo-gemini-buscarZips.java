import java.io.File;

public class BuscadorArchivosSinLibrerias {

    public static void main(String[] args) {
        String directorio = "C:\\mis_archivos";
        String[] extensiones = {".zip", ".rar", ".7z"};

        buscarArchivos(new File(directorio), extensiones);
    }

    private static void buscarArchivos(File directorio, String[] extensiones) {
        File[] archivos = directorio.listFiles();
        if (archivos != null) {
            for (File archivo : archivos) {
                if (archivo.isFile()) {
                    for (String extension : extensiones) {
                        if (archivo.getName().endsWith(extension)) {
                            System.out.println("Archivo encontrado: " + archivo.getAbsolutePath());
                        }
                    }
                } else if (archivo.isDirectory()) {
                    buscarArchivos(archivo, extensiones);
                }
            }
        }
    }
}