/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import modelo.Joyeria;

/**
 *
 * @author dell
 */
@Stateless
@Path("modelo.joyeria")
public class JoyeriaFacadeREST extends AbstractFacade<Joyeria> {

    @PersistenceContext(unitName = "SistemaVentasWebPU")
    private EntityManager em;

    public JoyeriaFacadeREST() {
        super(Joyeria.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Joyeria entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Joyeria entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Joyeria find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
    public List<Joyeria> findAll() {
        return super.findAll();
    }

    @POST
    @Path("create")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
    public String Crear(@FormParam("id_joyeria") int id_joyeria, @FormParam("nombre") String nombre, @FormParam("ubicacion") String ubicacion,
            @FormParam("direccion") String direccion, @FormParam("nombre_encargado") String nombre_encargado, @FormParam("dimension_local") String diemension_local,
            @FormParam("ciudad") String ciudad) {

        Joyeria j = new Joyeria(id_joyeria, nombre, ubicacion, direccion, nombre_encargado, diemension_local, ciudad);

        return "Dato ingresado correctamente";
    }

    @POST
    @Path("editar")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
    public String Editar(@FormParam("id_joyeria") int id_joyeria, @FormParam("nombre") String nombre, @FormParam("Ubicacion") String Ubicacion,
            @FormParam("direccion") String direccion, @FormParam("nombre_encargado") String nombre_encargado, @FormParam("dimension_local") String dimension_local,
            @FormParam("ciudad") String ciudad) {
        Joyeria jo = new Joyeria(id_joyeria);
        jo.setNombre(nombre);
        jo.setUbicacion(Ubicacion);
        jo.setDireccion(direccion);
        jo.setNombreEncargado(nombre_encargado);
        jo.setDimensionLocal(dimension_local);
        jo.setCiudad(ciudad);
        super.edit(jo);
        return "Datos editados correctamente";
    }

    @POST
    @Path("delete")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
    public int Eliminar(@FormParam("id_joyeria") int id_joyeria) {

        Joyeria joyeria = super.find(id_joyeria);
        super.remove(joyeria);
        return id_joyeria;
    }

    @POST
    @Path("joyeriaCiudad")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
    public List<Joyeria> joCliente(@FormParam("nombre") String nombre) {
        TypedQuery consulte = getEntityManager().createQuery("SELECT j FROM Joyeria j WHERE j.nombre = :nombre", Joyeria.class);
        consulte.setParameter("nombre", nombre);
        return consulte.getResultList();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Joyeria> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
