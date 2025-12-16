function myMin(...args) {
    if (args.length === 0) return null;
    let min = args[0];
    for (let i = 1; i < args.length; i++) {
        if (args[i] < min) min = args[i];
    }
    return min;
}

alert("Minimum value: " + myMin(10, 4, 7, 2, 8));
