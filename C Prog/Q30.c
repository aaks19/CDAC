// 8. Write the program having menu to display student information with accept employee_name,emp_id,Employee salary,display employee details.

#include <stdio.h>

int main() {
    char name[50];
    int emp_id;
    float salary;
    int choice;

    do {
        printf("\n--- Employee Menu ---\n");
        printf("1. Accept Employee Details\n");
        printf("2. Display Employee Details\n");
        printf("3. Exit\n");
        printf("Enter your choice: ");
        scanf("%d", &choice);

        switch (choice) {
            case 1:
                printf("Enter Employee ID: ");
                scanf("%d", &emp_id);

                printf("Enter Employee Name (no spaces): ");
                scanf("%s", name); // spaces not allowed in beginner version

                printf("Enter Employee Salary: ");
                scanf("%f", &salary);
                break;

            case 2:
                printf("\n--- Employee Details ---\n");
                printf("ID     : %d\n", emp_id);
                printf("Name   : %s\n", name);
                printf("Salary : %.2f\n", salary);
                break;

            case 3:
                printf("Exiting program...\n");
                break;

            default:
                printf("Invalid choice! Please try again.\n");
        }
    } while (choice != 3);

    return 0;
}