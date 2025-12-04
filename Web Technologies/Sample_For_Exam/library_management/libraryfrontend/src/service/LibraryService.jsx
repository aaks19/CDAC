import axios from 'axios'
const baseUrl = "http://localhost:3333";

class LibraryService{
    getAllBooks(){
        return axios.get(baseUrl+"/library/books");
    }

    addBook(book){
        let myHeader = {'content-Type':'application/json'}
        return axios.post(baseUrl+"/library/books",book,{headers:myHeader})
    }

    updateBook(book){
        let myHeader = {'content-Type':'application/json'}
        return axios.put(baseUrl+"/library/books/"+book.id,book,{headers:myHeader})
    }

    deleteBook(id){
        return axios.delete(baseUrl+"/library/books/"+id);
    }
}

export default new LibraryService();