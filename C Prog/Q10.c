// Accept program for Area and Perimeter Of Rectangle. 

#include <stdio.h>

int main(){
    int length, breadth;
    printf("Enter length: ");
    scanf("%d", &length);
    printf("Enter breadth: ");
    scanf("%d", &breadth);

    int area = length * breadth;
    printf("Area of rectangle = %d\n", area);

    int perimeter = 2 * (length + breadth);
    printf("Perimeter of rectangle = %d", perimeter);

}