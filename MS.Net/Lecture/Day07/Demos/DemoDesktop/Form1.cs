using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace DemoDesktop
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();

            //Button button1 = new Button();
            //button1.Text = "Click Me";

            //EventHandler pointer =
            //    new EventHandler(SayHi);

            ////pointer(null, null); //We dont want to call pointer like this

            //button1.Click += pointer;

            //this.Controls.Add(button1);
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            // TODO: This line of code loads data into the 'kDACDBDataSet.Emp' table. You can move, or remove it, as needed.
            this.empTableAdapter.Fill(this.kDACDBDataSet.Emp);

        }

        //public void SayHi(object obj, EventArgs e)
        //{
        //    MessageBox.Show("Hi");
        //}
    }
}
