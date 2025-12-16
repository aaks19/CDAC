let employees = [];
for(let i=0; i<6; i++){
    let name = prompt(`Enter name of employee ${i+1}:`);
    employees.push(name);
}

employees.sort();
console.log("Sorted Employee Names:", employees);
