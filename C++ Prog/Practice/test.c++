// Write a C++ program to read a string and calculate the sum of all digits present in the string. For example, if the input is hello1234world, the output should be 10 (1+2+3+4).

// #include<iostream>
// using namespace std;

// int main()
// {

//     string str="hello1234world";

//     int result=0;
//     int sum=0;
//     for(int i=0; i<str.length();i++)
//     {
//         if(isdigit(str[i]))
//         {
//             result = str[i]-'0';
//             sum =sum + result;
//         }
        
//     }
//     cout<<sum;
// }


// Write a C++ program to input a string and count the number of vowels and consonants in it.

// #include<iostream>
// using namespace std;

// int main(){
//     string str = "helloworld";
//     int vowels = 0;
//     int consonents = 0;

//     for(int i=0;i<str.length();i++){
//         char ch = str[i];
//         if(isalpha(ch)){
//             if(ch == 'a'||ch == 'e'||ch == 'i'||ch == 'o'||ch == 'u'||ch == 'A'||ch == 'E'||ch == 'I'||ch == 'O'||ch == 'U'){
//                 vowels++;
//             }else{
//                 consonents++;
//             }
//         }
//     }
//     cout<<"Vowels :"<<vowels<<endl;
//     cout<<"Consonents :"<<consonents<<endl;
// }


//Write a C++ program to reverse a given string without using any built-in function.

// #include<iostream>
// using namespace std;

// int main(){
//     string str = "hello";
//     string reversed = "";

//     for(int i=str.length()-1;i>=0;i--){
//         reversed += str[i];
//     }
//     cout<<"Reversed = "<<reversed<<endl;
// }


// Write a C++ program to check whether a given string is a palindrome or not.

// #include<iostream>
// using namespace std;

// int main(){
//     string str = "malaymalam";
//     string rev = "";

//     for(int i=str.length()-1; i>=0; i--){
//         rev += str[i];
//     }

//     if(str == rev){
//         cout<<"palindrom..."<<endl;
//     }else{
//         cout<<"Not palindrom..."<<endl;
//     }
// }


// Frequency of Elements in Array

#include<iostream>
#include<array>
#include<vector>
#include<unordered_map>
using namespace std;

int main(){
    // int arr[] = {1,2,2,3,4,5,5,6,5,5};
    // int n = sizeof(arr)/sizeof(arr[0]);

    // for(int i=0;i<n;i++){
    //     int count = 1;
    //     bool visited = false;
    //     for(int j=0;j<i;j++){
    //         if(arr[i] == arr[j]){
    //             visited = true;
    //         }
    //     }

    //     if(!visited){
    //         for(int k = i+1;k<n;k++){
    //             if(arr[i]==arr[k]){
    //                 count++;
    //             }
    //         }
    //         cout<<arr[i]<<"->"<<count<<endl;
    //     }
    // }

    //using vector and unordered map;

    vector<int> arr = {1,2,2,3,2,4,3,5,5,5,6,5,7};

    unordered_map<int,int> freq;

    for(auto n : arr){
        freq[n]++;
    }

    //display frequency
    for(auto &pair:freq){
        cout<<pair.first<<"--->"<<pair.second<<endl;
    }



}