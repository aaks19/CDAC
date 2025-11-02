// 2. Write a program to accept a number and check if it is divisible by 5 and 7. 

#include <stdio.h>

int main(){
    int num;
    printf("Enter an integer: ");
    scanf("%d", &num);

    if (num % 5 == 0 && num % 7 == 0) {
        printf("%d is divisible by both 5 and 7.\n", num);
    } else {
        printf("%d is not divisible by both 5 and 7.\n", num);
    }

    return 0;
}