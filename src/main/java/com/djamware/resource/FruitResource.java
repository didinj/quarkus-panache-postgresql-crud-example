package com.djamware.resource;

import java.util.List;

import com.djamware.model.Fruit;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/fruits")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FruitResource {

    @GET
    public List<Fruit> getAll() {
        return Fruit.listAll();
    }

    @GET
    @Path("/{id}")
    public Fruit getById(@PathParam("id") Long id) {
        return Fruit.findById(id);
    }

    @POST
    @Transactional
    public void create(Fruit fruit) {
        fruit.persist();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Fruit update(@PathParam("id") Long id, Fruit updatedFruit) {
        Fruit fruit = Fruit.findById(id);
        if (fruit == null) {
            throw new NotFoundException();
        }
        fruit.name = updatedFruit.name;
        fruit.color = updatedFruit.color;
        return fruit;
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void delete(@PathParam("id") Long id) {
        Fruit fruit = Fruit.findById(id);
        if (fruit != null) {
            fruit.delete();
        }
    }
}
