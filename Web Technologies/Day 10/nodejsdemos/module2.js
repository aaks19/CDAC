function factorial(n){
    var fact=1;
    for(var i=1;i<=n;i++){
        fact=fact*i;
    }
    return fact;
}

exports.combination=(n,r)=>{
   var ans=factorial(n)/factorial(r);
   return ans;
}