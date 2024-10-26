//BUSCADOR DE ZIPS RAR Y 7Z. Con aicodeconvert.com


#include <stdio.h>
#include <dirent.h>
#include <string.h>

void searchFiles(const char *path) {
    struct dirent *entry;
    DIR *dir = opendir(path);

    if (dir == NULL) {
        return;
    }

    while ((entry = readdir(dir)) != NULL) {
        if (entry->d_type == DT_REG) {
            if (strstr(entry->d_name, ".zip") || strstr(entry->d_name, ".rar") || strstr(entry->d_name, ".7z")) {
                printf("%s\n", entry->d_name);
            }
        } else if (entry->d_type == DT_DIR && strcmp(entry->d_name, ".") != 0 && strcmp(entry->d_name, "..") != 0) {
            char fullPath[1024];
            snprintf(fullPath, sizeof(fullPath), "%s/%s", path, entry->d_name);
            searchFiles(fullPath); // Recursive call for subdirectories
        }
    }

    closedir(dir);
}

int main() {
    searchFiles("/"); // Start searching from the root directory
    return 0;
}
```