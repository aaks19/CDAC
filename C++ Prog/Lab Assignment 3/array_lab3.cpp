/**


1:Write a program to create an array of integers and perform following operations on that array like 
finding the sum, average, maximum and minimum number in that array. Accept the numbers of the 
array from user. 

2: Write a program to Accept a number and display its sum of digits.:ex 568    5+6+8

3:. Write a  program to find sum of all even and odd numbers between 1 to n. 

4:. Write a  program to print all Prime numbers between 1 to n. 

5:Write a program to accept array  from user .Accept number from user and search number is present in array or not.

6:Write a program to print following pattern.
*
* *
* * *
* * * *
* * * * *

7:Write a program to create student class with data members rollno, marks1,mark2,mark3.
Accept data (acceptInfo()) and display  using display member function.
Also display total,percentage and grade.



 **/



//  1:Write a program to create an array of integers and perform following operations on that array like 
// finding the sum, average, maximum and minimum number in that array. Accept the numbers of the 
// array from user. 

// #include <iostream>
// using namespace std;

// int main() {
//     int n;
//     cout << "Enter the number of elements in the array: ";
//     cin >> n;

//     int arr[n];
//     cout << "Enter " << n << " integers:" << endl;
//     for (int i = 0; i < n; i++) {
//         cin >> arr[i];
//     }

//     int sum = 0, max = arr[0], min = arr[0];
//     for (int i = 0; i < n; i++) {
//         sum += arr[i];
//         if (arr[i] > max) {
//             max = arr[i];
//         }
//         if (arr[i] < min) {
//             min = arr[i];
//         }
//     }

//     double average = static_cast<double>(sum) / n;

//     cout << "Sum: " << sum << endl;
//     cout << "Average: " << average << endl;
//     cout << "Maximum: " << max << endl;
//     cout << "Minimum: " << min << endl;

//     return 0;
// }



// 2: Write a program to Accept a number and display its sum of digits.:ex 568    5+6+8


// #include <iostream>
// using namespace std;

// int main(){
//     int num,sum=0, digit;
//     cout<<"Enter a number: ";
//     cin>>num;
//     while(num>0){
//         digit = num% 10;
//         sum = sum + digit;
//         num = num / 10;
//     }
//     cout<<"Sum of digits is: "<<sum<<endl;
//     return 0;
// }

// 3:. Write a  program to find sum of all even and odd numbers between 1 to n. 

// #include<iostream>
// using namespace std;
// int main(){
//     int n, evenSum=0, oddSum=0;
//     cout<<"Enter a number: ";
//     cin>>n;
//     for(int i=1; i<=n; i++){
//         if(i%2==0){
//             evenSum += i;
//         } else {
//             oddSum += i;
//         }
//     }
//     cout<<"Sum of even numbers: "<<evenSum<<endl;
//     cout<<"Sum of odd numbers: "<<oddSum<<endl;
//     return 0;
// }

// 4:. Write a  program to print all Prime numbers between 1 to n.

// #include <iostream>
// using namespace std;

// int main() {
//     int n;
//     cout << "Enter a number: ";
//     cin >> n;
//     cout << "Prime numbers between 1 and " << n << " are:" << endl;
//     for (int i = 2; i <= n; i++) {
//         bool isPrime = true;
//         for (int j = 2; j * j <= i; j++) {
//             if (i % j == 0) {
//                 isPrime = false;
//                 break;
//             }
//         }
//         if (isPrime) {
//             cout << i << " ";
//         }
//     }
//     cout << endl;
//     return 0;
// }


// 5:Write a program to accept array  from user .Accept number from user and search number is present in array or not.

// #include <iostream>
// using namespace std;
// int main() {
//     int n, searchNum;
//     cout << "Enter the number of elements in the array: ";
//     cin >> n;

//     int arr[n];
//     cout << "Enter " << n << " integers:" << endl;
//     for (int i = 0; i < n; i++) {
//         cin >> arr[i];
//     }

//     cout << "Enter a number to search: ";
//     cin >> searchNum;

//     bool found = false;
//     for (int i = 0; i < n; i++) {
//         if (arr[i] == searchNum) {
//             found = true;
//             break;
//         }
//     }

//     if (found) {
//         cout << searchNum << " is present in the array." << endl;
//     } else {
//         cout << searchNum << " is not present in the array." << endl;
//     }

//     return 0;
// }




// 6:Write a program to print following pattern.

// *
// * *
// * * *
// * * * *
// * * * * *

#include <iostream>
using namespace std;

int main() {
    int n;
    cout << "Enter the number of rows: ";
    cin >> n;

    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= i; j++) {
            cout << "* ";
        }
        cout << endl;
    }

    return 0;
}