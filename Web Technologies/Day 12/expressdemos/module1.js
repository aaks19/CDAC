exports.f1=function(){
    console.log("in f1 function")
}

exports.addition=(a,b)=>{
    console.log("in addition function")
    return a+b;
}

exports.factorial=(n)=>{
   var fact=1;
   for(var i=1;i<=n;i++){
     fact=fact*i
   }
   return fact;
}

//console.log("addition: "+addition(10,23));
//f1();