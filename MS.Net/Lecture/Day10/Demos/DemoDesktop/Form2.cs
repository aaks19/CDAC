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
    public partial class Form2 : Form
    {
        public Form2()
        {
            InitializeComponent();
            //this.button1.Click += new System.EventHandler(this.button1_Click);


            //Anonymous Method Linking with Event
            //this.button1.Click += delegate (object sender, EventArgs e)
            //                        {
            //                            MessageBox.Show("Hello");
            //                        };


            //Lambada Expression Linking with Event
            this.button1.Click += (sender, e)=>
                                    {
                                        //MessageBox.Show("Hello");
                                    };
        }




        //private void button1_Click(object sender, EventArgs e)
        //{
        //    MessageBox.Show("Hello");
        //}
    }
}
