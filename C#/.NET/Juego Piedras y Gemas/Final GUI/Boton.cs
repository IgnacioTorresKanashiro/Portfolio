using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Final_GUI
{
    public class Boton : System.Windows.Forms.Button
    {
        private char id;

       

        public Boton()
        {
            this.Location = new System.Drawing.Point(165, 200);
            this.Name = "boton";
            this.Size = new System.Drawing.Size(50, 50);
            this.TabIndex = 0;
            this.UseVisualStyleBackColor = true;
            id = 'v';
        }

        public char Id
        {
            get
            {
                return id;
            }

            set
            {
                id = value;
            }
        }
    }
}
