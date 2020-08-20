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
import modelo.Cliente;
import modelo.Joyeria;

/**
 *
 * @author dell
 */
@Stateless
@Path("modelo.cliente")
public class ClienteFacadeREST extends AbstractFacade<Cliente> {

    @PersistenceContext(unitName = "SistemaVentasWebPU")
    private EntityManager em;

    public ClienteFacadeREST() {
        super(Cliente.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Cliente entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Cliente entity) {
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
    public Cliente find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
    public List<Cliente> findAll() {
        return super.findAll();
    }

    @POST
    @Path("create")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
    public String create(@FormParam("id_cliente") int id_cliente, @FormParam("nombre") String nombre, @FormParam("direccion") String direccion,
            @FormParam("correo") String correo, @FormParam("telefono") String telefono, @FormParam("ciudad") String ciudad, @FormParam("edad") int edad,
            @FormParam("joyeria_preferencia") int joyeria_preferencia) {

        Cliente cliente = new Cliente(id_cliente, nombre, direccion, correo, telefono, ciudad, edad, joyeria_preferencia);

        return "Datos ingresados correctamente";
    }

    @POST
    @Path("editar")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
    public String Editar(@FormParam("id_cliente") int id_cliente, @FormParam("nombre") String nombre, @FormParam("direccion") String direccion,
            @FormParam("correo") String correo, @FormParam("telefono") String telefono, @FormParam("ciudad") String ciudad, @FormParam("edad") int edad,
            @FormParam("joyeria_preferencia") int joyeria_prreferencia) {

        Cliente c = super.find(id_cliente);
        c.setNombre(nombre);
        c.setDireccion(direccion);
        c.setCorreo(correo);
        c.setTelefono(telefono);
        c.setCiudad(ciudad);
        c.setEdad(edad);
        c.setJoyeriaPreferencia(joyeria_prreferencia);
        super.edit(c);
        return "Datos editados exitosamente";
    }

    @POST
    @Path("Delete")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
    public int Eliminar(@FormParam("id_cliente") int id_cliente) {
        Cliente c = super.find(id_cliente);
        super.remove(c);
        return id_cliente;
    }

    @POST
    @Path("clienteJoyeria")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
    public List<Cliente> clienteJoyeria(@FormParam("nombre") String nombre) {
        TypedQuery consulte = getEntityManager().createQuery("SELECT c FROM Cliente c WHERE c.nombre = :nombre", Cliente.class);
        consulte.setParameter("nombre", nombre);
        return consulte.getResultList();
    }

    @POST
    @Path("mayorEdad")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
    public int Edad(@FormParam("edad") int edad) {
        Cliente c = super.find(edad);
        if (edad > 20) {
            return edad;
        } else {
            return 0;
        }
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Cliente> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
