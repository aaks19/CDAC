// 5. Write a program having menu that has three options - add, subtract or multiply two fractions. The two 
// fractions and the options are taken as input and the result is displayed as output. Each fraction is read as 
// two integers, numerator and denominator. 

#include <stdio.h>

int main() {
    int num1, den1, num2, den2, choice;
    printf("Enter numerator and denominator for first fraction: ");
    scanf("%d %d", &num1, &den1);
    printf("Enter numerator and denominator for second fraction: ");
    scanf("%d %d", &num2, &den2);

    printf("Options:\n");
    printf("1. Add\n");
    printf("2. Subtract\n");
    printf("3. Multiply\n");
    printf("Enter your choice (1-3): ");
    scanf("%d", &choice);

    switch (choice) {
        case 1:
            printf("Result: %d/%d\n", (num1 * den2) + (num2 * den1), den1 * den2);
            break;
        case 2:
            printf("Result: %d/%d\n", (num1 * den2) - (num2 * den1), den1 * den2);
            break;
        case 3:
            printf("Result: %d/%d\n", num1 * num2, den1 * den2);
            break;
        default:
            printf("Invalid choice\n");
    }

    return 0;
}