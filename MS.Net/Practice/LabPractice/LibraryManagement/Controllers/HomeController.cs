using LibraryManagement.Models;
using Microsoft.AspNetCore.Mvc;

namespace LibraryManagement.Controllers
{
    public class HomeController : Controller
    {
        LibraryDAL libraryDal = new LibraryDAL();
        public IActionResult Index()
        {
            List<Book> books = libraryDal.GetBooks();
            return View("Index",books);
        }

        public IActionResult Create()
        {
            return View();
        }

        public IActionResult AfterCreate(Book book)
        {
            if (!ModelState.IsValid) {
                return View("Create");
            }
            libraryDal.AddBook(book);
            return Redirect("/Home/Index");
        }

        public IActionResult Edit(int id)
        {
            Book book =  libraryDal.GetBook(id);
            return View("Edit",book);
        }

        public IActionResult AfterEdit(Book book)
        {
            Book updateBook = libraryDal.GetBook(book.id);
            updateBook.bname = book.bname;
            updateBook.author = book.author;
            updateBook.qty = book.qty;
            updateBook.price = book.price;

            libraryDal.UpdateBook(updateBook);
            return Redirect("/Home/Index");
        }

        public IActionResult DeleteBook(int id)
        {
            libraryDal.DeleteBook(id);
            return Redirect("/Home/Index");
        }
    }
}
