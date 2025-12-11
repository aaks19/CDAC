using Microsoft.AspNetCore.Mvc;
using MovieTicket.Models;

namespace MovieTicket.Controllers
{
    public class HomeController : Controller
    {
        MovieDAL dbObj = new MovieDAL();
        public IActionResult Index()
        {
            List<Movie> movies = dbObj.GetMovies();
            return View("Index",movies);
        }

        public IActionResult Create()
        {
            return View();
        }

        public IActionResult AfterCreate(Movie movie)
        {
            dbObj.AddMovie(movie);
            return Redirect("/Home/Index");
        }

        public IActionResult Edit(int id)
        {
            Movie movie = dbObj.GetMovie(id);
            return View("Edit", movie);
        }
        public IActionResult AfterEdit(Movie movie)
        {
            Movie movieUpdate = dbObj.GetMovie(movie.id);
            movieUpdate.name = movie.name;
            movieUpdate.qty = movie.qty;
            movieUpdate.price = movie.price;

            dbObj.UpdateMovie(movieUpdate);

            return Redirect("/Home/Index");
        }

        public IActionResult Delete(int id)
        {
            dbObj.Delete(id);
            return Redirect("/Home/Index");
        }
    }
}
