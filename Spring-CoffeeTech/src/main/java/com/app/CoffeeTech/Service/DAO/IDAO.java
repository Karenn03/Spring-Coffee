package com.app.CoffeeTech.Service.DAO;

import java.util.List;

public interface IDAO<T, ID> {

    // Método para crear una nueva instancia de la entidad en la base de datos (puede ser redundante con save dependiendo del uso).
    void create(T entidad);

    // Método para obtener una instancia específica de la entidad basada en un identificador único.
    T buscarPorId (ID id);

    // Método para obtener o retornar una lista de todas las instancias de una entidad en la base de datos.
    List<T> buscarTodos();

    // Método para guardar una instancia de la entidad en la base de datos, ya sea creando una nueva entrada o actualizando una existente, devuelve la entidad guardada
    void guardar (T entidad);

    // Método para actualizar una instancia existente de la entidad en la base de datos.
    void actualizar (T entidad);

    // Método para eliminar una instancia existente de la entidad de la base de datos.
    void eliminar (ID id);

}