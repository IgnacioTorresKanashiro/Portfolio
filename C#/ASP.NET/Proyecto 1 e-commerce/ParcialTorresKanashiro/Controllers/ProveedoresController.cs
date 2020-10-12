using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Entity;
using System.Linq;
using System.Net;
using System.Web;
using System.Web.Mvc;
using ParcialTorresKanashiro.Models;

namespace ParcialTorresKanashiro.Controllers
{
    public class ProveedoresController : Controller
    {
        private ParcialTorresKanashiroEntities db = new ParcialTorresKanashiroEntities();

        // GET: Proveedores
        [Filtros.FiltroAdmin]
        public ActionResult Index()
        {
            return View(db.Proveedor.ToList());
        }


        // GET: Proveedores/Details/5
        [Filtros.FiltroAdmin]
        public ActionResult Details(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Proveedor proveedor = db.Proveedor.Find(id);
            if (proveedor == null)
            {
                return HttpNotFound();
            }
            return View(proveedor);
        }

        // GET: Proveedores/Create
        [Filtros.FiltroAdmin]
        public ActionResult Create()
        {
            return View();
        }

        // POST: Proveedores/Create
        // Para protegerse de ataques de publicación excesiva, habilite las propiedades específicas a las que desea enlazarse. Para obtener 
        // más información vea https://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        [Filtros.FiltroAdmin]
        public ActionResult Create([Bind(Include = "id,nombre,direccion,nacionalidad")] Proveedor proveedor)
        {
            if (ModelState.IsValid)
            {
                db.Proveedor.Add(proveedor);
                db.SaveChanges();
                return RedirectToAction("Index");
            }

            return View(proveedor);
        }

        // GET: Proveedores/Edit/5
        [Filtros.FiltroAdmin]
        public ActionResult Edit(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Proveedor proveedor = db.Proveedor.Find(id);
            if (proveedor == null)
            {
                return HttpNotFound();
            }
            return View(proveedor);
        }

        // POST: Proveedores/Edit/5
        // Para protegerse de ataques de publicación excesiva, habilite las propiedades específicas a las que desea enlazarse. Para obtener 
        // más información vea https://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        [Filtros.FiltroAdmin]
        public ActionResult Edit([Bind(Include = "id,nombre,direccion,nacionalidad")] Proveedor proveedor)
        {
            if (ModelState.IsValid)
            {
                db.Entry(proveedor).State = EntityState.Modified;
                db.SaveChanges();
                return RedirectToAction("Index");
            }
            return View(proveedor);
        }

        // GET: Proveedores/Delete/5
        [Filtros.FiltroAdmin]
        public ActionResult Delete(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Proveedor proveedor = db.Proveedor.Find(id);
            if (proveedor == null)
            {
                return HttpNotFound();
            }
            return View(proveedor);
        }

        // POST: Proveedores/Delete/5
        [Filtros.FiltroAdmin]
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public ActionResult DeleteConfirmed(int id)
        {
            Proveedor proveedor = db.Proveedor.Find(id);
            db.Proveedor.Remove(proveedor);
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
    }
}
