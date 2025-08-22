// 7. Write a C program to accept a character and invert the case of it.

#include <stdio.h>
#include <ctype.h>

int main() {
    char ch;
    printf("Enter a character: ");
    scanf("%c", &ch);

    if (ch >= 'A' && ch <= 'Z') {
        // Convert uppercase to lowercase
        char lower_case = tolower(ch);
        printf("Inverted case: %c\n", lower_case);
    } else if (ch >= 'a' && ch <= 'z') {
        // Convert lowercase to uppercase
        char upper_case = toupper(ch);
        printf("Inverted case: %c\n", upper_case);
    } else {
        printf("Invalid input. Please enter an alphabetic character.\n");
    }

    return 0;

}
