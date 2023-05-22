package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.medico.DatosDeRegistroMedico;
import med.voll.api.medico.DatosListadoMedico;
import med.voll.api.medico.Medico;
import med.voll.api.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    /* Con la anotación @Autowired le estamos diciendo a Spring que el componente es un punto donde se debe inyectar una dependencia,
    en otras palabras, el componente se inyecta en la clase que lo posee, estableciendo una colaboración entre componentes.*/
    @Autowired
    private MedicoRepository medicoRepository;

    @PostMapping
    public void registrarMedico(@RequestBody @Valid DatosDeRegistroMedico registro) {
        System.out.println("El request llega correctamente!");
        System.out.println(registro);
        medicoRepository.save(new Medico(registro));
    }

    // Listar
    /*@GetMapping
    public List<DatosListadoMedico> listadoMedicos() {
        return medicoRepository.findAll().stream().map(DatosListadoMedico::new).toList();
    }*/

    // Estrategia de paginación
    @GetMapping
    public Page<DatosListadoMedico> listadoMedicos(@PageableDefault(size = 2) Pageable paginacion) {
        return medicoRepository.findAll(paginacion).map(DatosListadoMedico::new);
    }

    @PutMapping
    public void actualizarMedico() {

    }
}
