// #include<iostream>
// using namespace std;

// int main(){
//     string str = "hello123";
//     int n = str.length();
//     char arr[n];
//     int result = 0;
//     int subNum = 0;
//     string subnumstr = "";
//     for(int i=0;i<str.length();i++){
//         // arr[i] = str[i];
//         int count = 0;
//         switch(str[i]){
//             // if(subnumstr!="") subNum*=10;
//             case '0': if(subnumstr!="") subNum*=10; subNum += 0; break;
//             case '1': if(subnumstr!="") subNum*=10; subNum += 1; break;
//             case '2': if(subnumstr!="") subNum*=10; subNum += 2; break;
//             case '3': if(subnumstr!="") subNum*=10; subNum += 3; break;
//             case '4': if(subnumstr!="") subNum*=10; subNum += 4; break;
//             case '5': if(subnumstr!="") subNum*=10; subNum += 5; break;
//             case '6': if(subnumstr!="") subNum*=10; subNum += 6; break;
//             case '7': if(subnumstr!="") subNum*=10; subNum += 7; break;
//             case '8': if(subnumstr!="") subNum*=10; subNum += 8; break;
//             case '9': if(subnumstr!="") subNum*=10; subNum += 9; break;
//             default:{
//                 if(subnumstr!="")
//                 {
//                     cout<<subNum<<endl;
//                     result += subNum;
//                     subNum=0;
//                     subnumstr = "";
//                 }
//             }
//             cout<<"Result: "<< result;
//         }
//     }

//     // for(int i=0;i<n;i++){
//     //     cout<<arr[i]<<endl;
//     //     //add the digit in the array
//     //       -
//     // }
// }


#include <iostream>
using namespace std;

int main() {
    string str = "hello123world45";
    int result = 0;    // total sum of numbers in string
    int subNum = 0;    // current number being formed

    for (int i = 0; i < str.length(); i++) {
        if (isdigit(str[i])) {
            subNum = subNum * 10 + (str[i] - '0');  // build number
        } else {
            result += subNum;  // add previous number
            if (subNum != 0) {
                cout << "Found number: " << subNum << endl;
            }
            subNum = 0;         // reset for next number
        }
    }

    // add last number if string ends with digits
    if (subNum != 0) {
        cout << "Found number: " << subNum << endl;
        result += subNum;
    }

    cout << "Total sum of numbers: " << result << endl;

    return 0;
}
