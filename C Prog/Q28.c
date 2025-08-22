// 6. Write the menu driven program with even, odd,positive, negative numbers. 

#include <stdio.h>

int main() {
    int num, choice;

    printf("Enter a number: ");
    scanf("%d", &num);

    printf("Options:\n");
    printf("1. Check Even or Odd\n");
    printf("2. Check Positive or Negative\n");
    printf("Enter your choice (1-2): ");
    scanf("%d", &choice);

    switch (choice) {
        case 1:
            if (num % 2 == 0) {
                printf("%d is even\n", num);
            } else {
                printf("%d is odd\n", num);
            }
            break;
        case 2:
            if (num > 0) {
                printf("%d is positive\n", num);
            } else if (num < 0) {
                printf("%d is negative\n", num);
            } else {
                printf("%d is zero\n", num);
            }
            break;
        default:
            printf("Invalid choice\n");
    }

    return 0;
}