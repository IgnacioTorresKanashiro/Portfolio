using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Entity;
using System.Linq;
using System.Net;
using System.Web;
using System.Web.Mvc;
using iText.Kernel.Pdf;
using iText.Layout;
using iText.Layout.Element;
using ParcialTorresKanashiro.Models;

namespace ParcialTorresKanashiro.Controllers
{
    public class ProductosController : Controller
    {
        private ParcialTorresKanashiroEntities db = new ParcialTorresKanashiroEntities();
        // GET: Productos
        [Filtros.FiltroSesion]
        public ActionResult Index()
        {
            return View(db.Producto.ToList());
        }
        [Filtros.FiltroSesion]
        public ActionResult _partialCarrito()
        {
            ClaseCarrito carrito = Session["carrito"] as ClaseCarrito;
            return PartialView(carrito.listaProducto);
        }

        [Filtros.FiltroSesion]
        public ActionResult AgregarAlCarrito(int id, int cant)
        {
            ClaseCarrito carrito = HttpContext.Session["carrito"] as ClaseCarrito;
            Producto prodARemover = null;
            
            foreach (KeyValuePair<Producto, int> p in carrito.ListaProducto)
            {
                if (p.Key.id.Equals(id))
                {
                    prodARemover = p.Key;
                }
            }

            if (prodARemover != null)
            {
                carrito.ListaProducto.Remove(prodARemover);
            }

            carrito.ListaProducto.Add(db.Producto.Find(id), cant);

            HttpContext.Session["carrito"] = carrito;

            return RedirectToAction("Index");
        }


        // GET: Productos/Details/5
        [Filtros.FiltroSesion]
        public ActionResult Details(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Producto producto = db.Producto.Find(id);
            if (producto == null)
            {
                return HttpNotFound();
            }
            return View(producto);
        }

        // GET: Productos/Create
        [Filtros.FiltroAdmin]
        public ActionResult Create()
        {
            return View();
        }

        // POST: Productos/Create
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see https://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        [Filtros.FiltroAdmin]
        public ActionResult Create([Bind(Include = "id,nombre,editorial,precio,cantidad,foto")] Producto producto)
        {
            String filename = DateTime.Now.Ticks.ToString() + "." + Request.Files["archivo"].ContentType.Split('/')[1];
            GuardarFoto(filename);
            producto.foto = filename;

            if (ModelState.IsValid)
            {
                db.Producto.Add(producto);
                db.SaveChanges();
                return RedirectToAction("Index");
            }

            return View(producto);
        }

        // GET: Productos/Edit/5
        [Filtros.FiltroAdmin]
        public ActionResult Edit(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Producto producto = db.Producto.Find(id);
            if (producto == null)
            {
                return HttpNotFound();
            }
            return View(producto);
        }

        // POST: Productos/Edit/5
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see https://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        [Filtros.FiltroAdmin]
        public ActionResult Edit([Bind(Include = "id,nombre,editorial,precio,cantidad,foto")] Producto producto)
        {
            if (System.IO.File.Exists(Server.MapPath("~/Img/") + producto.foto))
            {
                GuardarFoto(producto.foto);
            }
            else
            {
                String filename = DateTime.Now.Ticks.ToString() + "." + Request.Files["archivo"].ContentType.Split('/')[1];
                GuardarFoto(filename);
                producto.foto = filename;
            }

            if (ModelState.IsValid)
            {
                db.Entry(producto).State = EntityState.Modified;
                db.SaveChanges();
                return RedirectToAction("Index");
            }
            return View(producto);
        }

        // GET: Productos/Delete/5
        [Filtros.FiltroAdmin]
        public ActionResult Delete(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Producto producto = db.Producto.Find(id);
            if (producto == null)
            {
                return HttpNotFound();
            }
            return View(producto);
        }

        // POST: Productos/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public ActionResult DeleteConfirmed(int id)
        {
            Producto producto = db.Producto.Find(id);
            db.Producto.Remove(producto);
            db.SaveChanges();
            return RedirectToAction("Index");
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private void GuardarFoto(String filename)
        {
            HttpPostedFileBase archivoSubido = Request.Files["archivo"];

            if (archivoSubido != null && archivoSubido.ContentLength > 0)
            {
                String ruta = Server.MapPath("~/Img/") + filename;
                archivoSubido.SaveAs(ruta);
            }
        }

        public ActionResult Comprar()
        {
            ClaseCarrito carrito = HttpContext.Session["carrito"] as ClaseCarrito;

            foreach (KeyValuePair<Producto, int> p in carrito.listaProducto)
            {
                Compra c = new Compra();
                
                c.id_producto = p.Key.id;
                c.id_usuario = (int)HttpContext.Session["id"];
                c.monto_total = p.Key.precio * p.Value;
                db.Producto.Find(p.Key.id).cantidad = db.Producto.Find(p.Key.id).cantidad - p.Value;
                db.Compra.Add(c);
            }
            ExportarPDF();
            carrito.listaProducto.Clear();
            HttpContext.Session["carrito"] = carrito;
            db.SaveChanges();
            return RedirectToAction("Index");
        }

        public ActionResult EliminarProductoCarrito(int id)
        {
            ClaseCarrito carrito = HttpContext.Session["carrito"] as ClaseCarrito;
            Producto prodEliminar = null;
            
            foreach (KeyValuePair<Producto, int> p in carrito.listaProducto)
            {
                if (p.Key.id.Equals(id))
                {
                    prodEliminar = p.Key;
                }
            }

            if (prodEliminar != null)
            {
                carrito.listaProducto.Remove(prodEliminar);
            }

            
            return RedirectToAction("Index");
        }

        public void  ExportarPDF()
        {
            ClaseCarrito carrito = HttpContext.Session["carrito"] as ClaseCarrito;

            var exportFolder = Server.MapPath("~/Whatever");
            var exportFile = Server.MapPath("~/prueba.pdf");

            using (var writer = new PdfWriter(exportFile)) {
                using (var pdf = new PdfDocument(writer))
                {
                    var doc = new Document(pdf);

                    doc.Add(new Paragraph("Detalle de compra"));

                    doc.Add(new Paragraph("Nombre - Cantidad - Precio unitario"));

                    foreach (KeyValuePair<Producto, int> p in carrito.listaProducto)
                    {
                        doc.Add(new Paragraph(p.Key.nombre + " - " + p.Value + " - " + p.Key.precio));
                    }

                    doc.Add(new Paragraph("Monto total:"));

                    doc.Add(new Paragraph("$"+ClaseCarrito.calcularTotal(carrito.listaProducto).ToString()));

                }
            
            }
        }

    }
}
