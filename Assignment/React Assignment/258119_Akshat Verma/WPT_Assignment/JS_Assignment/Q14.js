let sites = ["www.google.com", "www.msn.com", "www.amazon.co.in", "in.answers.yahoo.com", "en.m.wikipedia.com", "codehs.gitbooks.io", "www.coderanch.com"];
let count = sites.filter(site => site.startsWith("www")).length;
console.log("Total websites starting with 'www':", count);
