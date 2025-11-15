//allocate memory
var buff1=Buffer.alloc(156);
//write data to buffer
var length=buff1.write("this is testing buffer")
console.log("length: "+length);
console.log(buff1.toString("utf-8"));

var buff11=Buffer.alloc(10);
var buff22=Buffer.alloc(14);
var buff33=Buffer.alloc(20);
var totallength=buff11.length+buff22.length+buff33.length;
buff11.write("testing");
buff22.write("nodejs")
buff33.write("enjoyable");
console.log("length: "+totallength);

const buffa=Buffer.concat([buff11,buff22,buff33]);
console.log(buffa.toString())


//const buffb=Buffer.from("testing");
const buffc=Buffer.from("2345");
const buffd=Buffer.from("012345");
const arr=[buffc,buffd];

console.log(arr.sort(Buffer.compare).toString())


