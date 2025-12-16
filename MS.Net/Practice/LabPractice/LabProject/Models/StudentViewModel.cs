using LabProject.Models;
using Microsoft.Data.SqlClient;

namespace LabProject.Models
{
    public class StudentViewModel
    {
        string connectonString = "Data Source=(LocalDB)\\MSSQLLocalDB;Initial Catalog=PracticeDB;Integrated Security=True;Pooling=False;";
        public List<Student> GetStudents()
        {
            List<Student> students = new List<Student>();

            SqlConnection connection = new SqlConnection(connectonString);

            connection.Open();
            SqlCommand cmd = new SqlCommand("select * from Student", connection);

            SqlDataReader reader = cmd.ExecuteReader();

            while (reader.Read())
            {
                Student student = new Student();
                student.No = Convert.ToInt32(reader["No"]);
                student.Name = reader["Name"].ToString();
                student.Address = reader["Address"].ToString();
                student.Email = reader["Email"].ToString();
                student.Age = Convert.ToInt32(reader["Age"]);

                students.Add(student);
            }
            connection.Close();

            return students;
        }

        public Student GetStudent(int No)
        {
            List<Student> students = GetStudents();
            Student filterStudent = (from student in students
                                     where student.No == No
                                     select student).First();

            return filterStudent;
        }

        // inserting into database
        public int AddStudent(Student student)
        {
            SqlConnection connection = new SqlConnection(connectonString);
            connection.Open();
            string queryFormat = " insert into Student(Name,Address,Age,Email,IsEmailVerified) values('{0}','{1}',{2},'{3}','{4}')";
            string query = string.Format(queryFormat, student.Name, student.Address, student.Age, student.Email, false);

            SqlCommand cmd = new SqlCommand(query, connection);
            int rowAffested = cmd.ExecuteNonQuery();
           
            connection.Close();
            
            return rowAffested;
        }

        public int UpdateStudent(Student student)
        {
            SqlConnection connection = new SqlConnection(connectonString);
            connection.Open();
            string queryFormat = " update Student set Name='{0}', Address='{1}', Age={2}, Email='{3}' where No={4}";

            string query = string.Format(queryFormat, student.Name, student.Address, student.Age, student.Email, student.No);

            SqlCommand cmd = new SqlCommand(query, connection);
            int rowAffested = cmd.ExecuteNonQuery();

            connection.Close();

            return rowAffested;
        }

        public int DeleteStudent(int No)
        {
            SqlConnection connection = new SqlConnection(connectonString);
            connection.Open();
            string queryFormat = " delete from Student where No = {0} ";
            string query = string.Format(queryFormat, No);

            SqlCommand cmd = new SqlCommand(query, connection);
            int rowAffested = cmd.ExecuteNonQuery();

            connection.Close();

            return rowAffested;
        }
    }
}
