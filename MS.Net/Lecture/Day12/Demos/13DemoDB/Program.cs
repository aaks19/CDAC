using Microsoft.Data.SqlClient;
using System.Data;

namespace _13DemoDB
{
    internal class Program
    {
        static void Main(string[] args)
        {
            #region Simple DataSet + DataTable

            //DataSet dataSet = new DataSet();

            //DataTable table = new DataTable("Emp");

            //DataColumn column1 = new DataColumn("No", typeof(int));
            //DataColumn column2 = new DataColumn("Name", typeof(string));
            //DataColumn column3 = 
            //    new DataColumn("Address", typeof(string));

            //table.Columns.Add(column1);
            //table.Columns.Add(column2);
            //table.Columns.Add(column3);

            //table.PrimaryKey = new DataColumn[] { column1 };


            //DataRow row1 = table.NewRow();
            //row1["No"] = 1;
            //row1["Name"] = "abc";
            //row1["Address"] = "pune";

            //DataRow row2 = table.NewRow();
            //row2["No"] = 2;
            //row2["Name"] = "xyz";
            //row2["Address"] = "pune";

            //DataRow row3 = table.NewRow();
            //row3["No"] = 3;
            //row3["Name"] = "pqr";
            //row3["Address"] = "pune";

            //table.Rows.Add(row1);
            //table.Rows.Add(row2);
            //table.Rows.Add(row3);

            //dataSet.Tables.Add(table);

            //dataSet.WriteXml("D:\\IACSD\\IACSDDemos\\data.xml");
            //dataSet.WriteXmlSchema("D:\\IACSD\\IACSDDemos\\schema.xml");
            #endregion

            #region Read XMl Schema , XML
            //DataSet dataSet = new DataSet();
            //dataSet.ReadXmlSchema("D:\\IACSD\\IACSDDemos\\schema.xml");
            //dataSet.ReadXml("D:\\IACSD\\IACSDDemos\\data.xml");
            #endregion

            #region Simple DataSet + DataTable + From DB

            //DataSet dataSet = new DataSet();

            //DataTable table = new DataTable("Emp");

            //DataColumn column1 = new DataColumn("No", typeof(int));
            //DataColumn column2 = new DataColumn("Name", typeof(string));
            //DataColumn column3 =
            //    new DataColumn("Address", typeof(string));

            //table.Columns.Add(column1);
            //table.Columns.Add(column2);
            //table.Columns.Add(column3);

            //table.PrimaryKey = new DataColumn[] { column1 };

            //SqlConnection connection =
            //    new SqlConnection("Data Source=(LocalDB)\\MSSQLLocalDB;Initial Catalog=IACSDDB;Integrated Security=True");

            //SqlCommand command = new SqlCommand("select * from Emp", connection);

            //connection.Open();
            //SqlDataReader reader = command.ExecuteReader();

            //while (reader.Read())
            //{
            //    DataRow row = table.NewRow();
            //    row["No"] = Convert.ToInt32(reader["No"]);
            //    row["Name"] = reader["Name"].ToString();
            //    row["Address"] = reader["Address"].ToString();

            //    table.Rows.Add(row);
            //}

            //connection.Close();

            //dataSet.Tables.Add(table);

            #endregion

            #region Using Disconnected Architecture 
            //DataSet dataSet = new DataSet();

            //SqlConnection connection =
            //    new SqlConnection("Data Source=(LocalDB)\\MSSQLLocalDB;Initial Catalog=IACSDDB;Integrated Security=True");

            //SqlDataAdapter adapter =
            //    new SqlDataAdapter("select * from Emp", connection);

            //adapter.MissingSchemaAction = MissingSchemaAction.AddWithKey;

            //SqlCommandBuilder commandsBuilder = 
            //    new SqlCommandBuilder(adapter);

            //adapter.Fill(dataSet,"Emp");
            #endregion

            #region Add Record in DS
            //DataRow row = dataSet.Tables["Emp"].NewRow();
            ////row["No"] = 10;
            //row["Name"] = "Mitesh";
            //row["Address"] = "Pune";

            //dataSet.Tables["Emp"].Rows.Add(row);
            #endregion

            #region Update Record in DS
            //DataRow row = dataSet.Tables["Emp"].Rows.Find(13);
            //row["Name"] = "Chinu";
            //row["Address"] = "Pondi";
            #endregion

            #region Update in DB
            //adapter.Update(dataSet, "Emp");
            #endregion

        }
    }
}
