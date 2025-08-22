// Accept marks of 5 subjects (out of 100) of a student and display total marks and compute the percentage also. 

#include <stdio.h>

int main() {
    float marks1 , marks2, marks3, marks4, marks5;
    printf("Enter marks for 1st subjects (out of 100):");
    scanf("%f", &marks1);
    printf("Enter marks for 2nd subjects (out of 100):");
    scanf("%f", &marks2);
    printf("Enter marks for 3rd subjects (out of 100):");
    scanf("%f", &marks3);
    printf("Enter marks for 4th subjects (out of 100):");
    scanf("%f", &marks4);
    printf("Enter marks for 5th subjects (out of 100):");
    scanf("%f", &marks5);

    float sum;
    sum = marks1 + marks2 + marks3 + marks4 + marks5;
    printf("Total marks = %.2f\n", sum);
    printf("Percentage = %.2f%%\n", (sum / 500) * 100);
}

    
