```c
#include <stdio.h>
#include <stdlib.h>

int main() {
    FILE *listTemp;
    FILE *salida;
    char buffer[256];

    listTemp = fopen("ListadoNuevos.txt", "r");
    if (listTemp == NULL) {
        perror("Error opening ListadoNuevos.txt");
        return -1;
    }

    salida = fopen("ListadoFullZips.txt", "a");
    if (salida == NULL) {
        perror("Error opening ListadoFullZips.txt");
        fclose(listTemp);
        return -1;
    }

    printf("Procesando zips y obteniendo los contenidos\n\n");
    printf("Fase 0.1 - OBTENER DATOS DE ZIPS\n\n");

    while (fgets(buffer, sizeof(buffer), listTemp) != NULL) {
        printf("NOMBRE:%s", buffer);
        char command[512];
        sprintf(command, "\"C:\\Program Files\\7-Zip\\7z.exe\" l -sccWIN -- \"%s\" >> ListadoFullZips.txt", buffer);
        system(command);
    }

    fclose(listTemp);
    fclose(salida);

    return 0;
}
