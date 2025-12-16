let empArr = [
    { empid: 101, empname: "Alice", salary: 5000 },
    { empid: 102, empname: "Bob", salary: 6000 },
    { empid: 103, empname: "Charlie", salary: 5500 },
    { empid: 104, empname: "David", salary: 7000 }
];

console.table(empArr);

empArr.forEach(emp => {
    console.log(`Employee ID: ${emp.empid}, Name: ${emp.empname}, Salary: ${emp.salary}`);
});
