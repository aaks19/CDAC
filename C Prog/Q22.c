// 10. Program to display 3 students grades with marks>75 with Grade =A+ marks<75 and marks>65 grade = A and marks <60 grade=B 
#include <stdio.h>

int main() {
    int marks1, marks2, marks3;
    printf("Enter marks for student 1: ");
    scanf("%d", &marks1);
    printf("Enter marks for student 2: ");
    scanf("%d", &marks2);
    printf("Enter marks for student 3: ");
    scanf("%d", &marks3);

    printf("Grades:\n");

    // Student 1
    if (marks1 > 75) {
        printf("Student 1: A+\n");
    } else if (marks1 > 65) {
        printf("Student 1: A\n");
    } else {
        printf("Student 1: B\n");
    }

    // Student 2
    if (marks2 > 75) {
        printf("Student 2: A+\n");
    } else if (marks2 > 65) {
        printf("Student 2: A\n");
    } else {
        printf("Student 2: B\n");
    }

    // Student 3
    if (marks3 > 75) {
        printf("Student 3: A+\n");
    } else if (marks3 > 65) {
        printf("Student 3: A\n");
    } else {
        printf("Student 3: B\n");
    }

    return 0;
}
