// Q1 Write a program to accept three positive numbers and output the largest of them.
function maxOfThreeNumber(num1, num2, num3) {
  //   var num1 = parseInt(prompt("Enter number 1"));
  //   var num2 = parseInt(prompt("Enter number 2"));
  //   var num3 = parseInt(prompt("Enter number 3"));

  var num1 = parseInt(document.getElementById("q1_num1").value);
  var num2 = parseInt(document.getElementById("q1_num2").value);
  var num3 = parseInt(document.getElementById("q1_num3").value);
  var result;
  if (num1 >= num2 && num1 >= num3) {
    // console.log(num1 + " is greater");
    // alert(num1 + " is greater");
    result = num1;
  } else if (num2 >= num1 && num2 >= num3) {
    // console.log(num2 + " is greater");
    // alert(num2 + " is greater");
    result = num2;
  } else {
    // console.log(num3 + " is greater");
    // alert(num3 + " is greater");
    result = num3;
  }

  document.getElementById("q1_result").value = result;
}

//=================================================================================

//Q2 Accept an integer value and a message from user and print the message that many number of times.

function printMessageNtimes() {
  var str = document.getElementById("q2_str").value;
  var num = parseInt(document.getElementById("q2_num").value);
  result = "";
  for (var i = 0; i < num; i++) {
    result = result.concat(str);
  }
  console.log(result);
  document.getElementById("q2_result").value = result;
}

//======================================================================================

// Q3 Write a function to list all even numbers less than or equal to the number n. Take the value of n as input from user. Use while loop
function listEvenNumber() {
  var num = document.getElementById("q3_num").value;

  var result = [];
  while (num >= 2) {
    if (num % 2 === 0) {
      result.push(num);
    }
    num = num - 1;
  }

  result.reverse();

  // var i = 1;
  // while (i <= num) {
  //   if (i % 2 === 0) {
  //     result = result.push(i);
  //   }
  //   i = i + 1;
  // }
  document.getElementById("q3_result").value = result;
}

//============================================================================================

// Q4 Write a function that accepts two numbers and a operator like (+,-,*, /) from user and performs the appropriate operation indicated by the operator.

function calculate() {
  var num1 = parseInt(document.getElementById("q4_num1").value);
  var num2 = parseInt(document.getElementById("q4_num2").value);
  var operator = document.getElementById("q4_op").value;
  var result;

  if (isNaN(num1) || isNaN(num2)) {
    result = "Error: Invalid input";
  } else {
    switch (operator) {
      case "+":
        result = num1 + num2;
        break;
      case "-":
        result = num1 - num2;
        break;
      case "*":
        result = num1 * num2;
        break;
      case "/":
        if (num2 !== 0) {
          result = num1 / num2;
        } else {
          result = "Error: Division by zero";
        }
        break;
      default:
        result = "Invalid operator";
    }
  }

  document.getElementById("q4_result").value = result;
}
