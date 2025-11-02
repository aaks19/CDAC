// 8. Write a program to accept 3 numbers and compute minimum and maximum from them. 

#include <stdio.h>

int main() {
    int num1, num2, num3;
    printf("Enter three numbers: ");
    scanf("%d %d %d", &num1, &num2, &num3);
    if(num1>num2){
        if(num1>num3){
            printf("Maximum: %d\n", num1);
        }else{
            printf("Maximum: %d\n", num3);
        }
    }else{
        if(num2>num3){
            printf("Maximum: %d\n", num2);
        }else{
            printf("Maximum: %d\n", num3);
        }
    }
    if(num1<num2){
        if(num1<num3){
            printf("Minimum: %d\n", num1);
        }else{
            printf("Minimum: %d\n", num3);
        }
    }else{
        if(num2<num3){
            printf("Minimum: %d\n", num2);
        }else{
            printf("Minimum: %d\n", num3);
        }
    }
    return 0;
}