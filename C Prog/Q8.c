// Write the program to display 5 employee details with their Employee_Name, Employee_id and department. 

// q8_employees.c
#include <stdio.h>
#include <string.h>

#define MAX_EMP 5
#define MAX_NAME 50
#define MAX_DEPT 30

int main() {
    char name[MAX_EMP][MAX_NAME];
    char dept[MAX_EMP][MAX_DEPT];
    int id[MAX_EMP];

    for (int i = 0; i < MAX_EMP; ++i) {
        printf("Enter details for Employee %d:\n", i+1);
        printf("ID: ");
        if (scanf("%d", &id[i]) != 1) return 0;
        getchar(); // consume newline

        printf("Name: ");
        fgets(name[i], MAX_NAME, stdin);
        name[i][strcspn(name[i], "\n")] = '\0'; // remove newline

        printf("Department: ");
        fgets(dept[i], MAX_DEPT, stdin);
        dept[i][strcspn(dept[i], "\n")] = '\0';
        printf("\n");
    }

    printf("Displaying employee details:\n");
    for (int i = 0; i < MAX_EMP; ++i) {
        printf("Employee %d -- ID: %d, Name: %s, Department: %s\n", i+1, id[i], name[i], dept[i]);
    }
    return 0;
}
