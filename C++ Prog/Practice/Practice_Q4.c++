#include<iostream>
#include<sstream>
using namespace std;

/*
Input: "aabcccccaaa"
Output: a2b1c5a3
*/

string compressString(string &s){
     string result = "";
    int n = s.length();

    for (int i = 0; i < n; ) {
        char current = s[i];
        int count = 0;

        // Count consecutive occurrences
        while (i < n && s[i] == current) {
            count++;
            i++;
        }

        // Add character
        result += current;

        // Convert count to string using stringstream
        stringstream ss;
        ss << count;
        result += ss.str();
    }
    return (result.length()<s.length()) ? result : s;
}


int main(){

    string s;
    cout<<"Enter string"<<endl;
    cin>>s;
    cout<<"Compressed string : "<<compressString(s)<<endl;

    return 0;
}
