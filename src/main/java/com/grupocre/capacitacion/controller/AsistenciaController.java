package com.grupocre.capacitacion.controller;

import com.grupocre.capacitacion.models.Asistencia;
import com.grupocre.capacitacion.repository.AsistenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/asistencia")
@CrossOrigin(origins = "*") // Para que tu frontend (React/HTML) pueda conectar
public class AsistenciaController {

    @Autowired
    private AsistenciaRepository repository;

    @PostMapping("/registrar")
    public ResponseEntity<Asistencia> registrarAsistencia(@RequestBody Map<String, String> payload) {
        String nombre = payload.get("nombre");

        if (nombre == null || nombre.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        Asistencia nuevaAsistencia = new Asistencia();
        nuevaAsistencia.setNombreCompleto(nombre);
        nuevaAsistencia.setFecha(LocalDate.now());
        nuevaAsistencia.setHora(LocalTime.now());

        return ResponseEntity.ok(repository.save(nuevaAsistencia));
    }

    @GetMapping("/listar")
    public List<Asistencia> listarTodo() {
        return repository.findAll();
    }
}