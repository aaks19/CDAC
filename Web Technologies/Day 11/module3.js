function f1(){
    console.log("in f1 function");

}

function f2(){
    console.log("in f2 function");

}

var user={num1:12,name:"Revati"}

class Employee{
    constructor(eid,ename,sal){
        this.eid=eid;
        this.ename=ename;
        this.sal=sal;
    }
    display(){
        return `Emp id: ${this.eid} Name: ${this.ename} Salary: ${this.sal}`
    }

}

module.exports={
    function1:f1,
    function2:f2,
    user:user,
    Employee:Employee
}