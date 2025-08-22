// 1. Write a program to accept an integer and check if it is even or odd. 

#include <stdio.h>

int main(){
    int num;
    printf("Enter an integer: ");
    scanf("%d", &num);

    if (num % 2 == 0) {
        printf("%d is even.\n", num);
    } else {
        printf("%d is odd.\n", num);
    }

    return 0;
}