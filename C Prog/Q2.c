//  Accept a character from user and display ASCII value of it. 

#include <stdio.h>
int main(){
    char ch;
    printf("enter a character: ");
    scanf("%c", &ch);
    printf("ASCII value of %c is %d\n", ch, ch);
}