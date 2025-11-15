var m1=require("./module3");

m1.function1();
console.log(m1.user.name);
m1.function2();
var e1=new m1.Employee(12,"Ashu",4346454);
console.log(e1);
console.log(JSON.stringify(e1))