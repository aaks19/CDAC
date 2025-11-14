function f1(){
    console.log("in f1 function")
}

function f2(){
    console.log("in f2 before setTimeout")
    //this will execute callback function after 500ms
    setTimeout(() => {
       console.log("in f2 function") 
    }, 500);
    console.log("in f2 after setTimeout")
    
}

function f3(){
    console.log("in f3 function")
}


setImmediate(()=>{
    console.log("in setImediate")
})
console.log("in nodejs first file")
f1()
for(var v=1;v<10;v++){
    console.log(v)
}
f2();
f1();
//if you pass a function as a parameter to another function, then
//we call the function which is passed as a parameter a callback function
//f3 is a callback function
var st1=setInterval(f3,1000);

var st2=setTimeout(()=>{
   clearInterval(st1);
},5000)

console.log("in nodejs last line file")

