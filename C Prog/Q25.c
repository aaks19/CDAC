// 3. Accept two numbers in variables x and y from the user and perform the following operations 
// Options               Actions 
// 1. Equality           Check if x is equal to y 
// 2. Less Than          Check if x is less than y 
// 3. Greater Than       Check if x is greater than y 
// 4. Quotient and Remainder Divide x by y and display the quotient and remainder 
// 5. Range : Accept a number and check if it lies between x and y (both inclusive) 
// 6. Swap : Interchange x and y

#include <stdio.h>

int main() {
    int x, y, num;

    printf("Enter two numbers (x and y): ");
    scanf("%d %d", &x, &y);

    printf("Options:\n");
    printf("1. Equality\n");
    printf("2. Less Than\n");
    printf("3. Greater Than\n");
    printf("4. Quotient and Remainder\n");
    printf("5. Range\n");
    printf("6. Swap\n");

    int choice;
    printf("Enter your choice (1-6): ");
    scanf("%d", &choice);

    switch (choice) {
        case 1:
            if (x == y) {
                printf("x is equal to y\n");
            } else {
                printf("x is not equal to y\n");
            }
            break;
        case 2:
            if (x < y) {
                printf("x is less than y\n");
            } else {
                printf("x is not less than y\n");
            }
            break;
        case 3:
            if (x > y) {
                printf("x is greater than y\n");
            } else {
                printf("x is not greater than y\n");
            }
            break;
        case 4:
            if (y != 0) {
                printf("Quotient: %d, Remainder: %d\n", x / y, x % y);
            } else {
                printf("Division by zero error\n");
            }
            break;
        case 5:
            printf("Enter a number to check its range: ");
            scanf("%d", &num);
            if (num >= x && num <= y) {
                printf("%d lies between %d and %d\n", num, x, y);
            } else {
                printf("%d does not lie between %d and %d\n", num, x, y);
            }
            break;
        case 6:
            int temp = x;
            x = y;
            y = temp;
            printf("After swapping: x = %d, y = %d\n", x, y);
            break;
        default:
            printf("Invalid choice\n");
    }

    return 0;
}