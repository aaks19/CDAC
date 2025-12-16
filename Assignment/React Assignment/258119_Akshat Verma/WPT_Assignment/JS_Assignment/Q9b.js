function getWeekDay(date) {
    let days = ['Sun','Mon','Tue','Wed','Thu','Fri','Sat'];
    return days[date.getDay()];
}

let date = new Date(2012, 0, 3); // Jan 3, 2012
alert(getWeekDay(date)); // Tue
