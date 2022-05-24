package com.example.apirestaurantemysql;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@CrossOrigin(maxAge = 3600)
@Controller
@RequestMapping(path = "/restaurantes")
public class RestauranteController {
    
    @Autowired
    private RestauranteRepository restauranteRepository;

    @GetMapping
    public @ResponseBody Iterable<Restaurante> consultarRestaurantes() {
        return restauranteRepository.findAll();
    }

    @GetMapping(value = "{id}")
    public @ResponseBody Optional<Restaurante> consultarRestaurante(@PathVariable int id){
        return restauranteRepository.findById(id);
    }

    @PostMapping
    public @ResponseBody Restaurante incluirRestaurante(@RequestBody Restaurante restaurante){
        Restaurante novoRestaurante = new Restaurante();
        novoRestaurante.setNomeRestaurante(restaurante.getNomeRestaurante());
        novoRestaurante.setTipoRestaurante(restaurante.getTipoRestaurante());
        novoRestaurante.setHomePage(restaurante.getHomePage());
        novoRestaurante.setCidade(restaurante.getCidade());
        novoRestaurante.setTelefone(restaurante.getTelefone());
        novoRestaurante.setEndereco(restaurante.getEndereco());
        return restauranteRepository.save(novoRestaurante);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Object> excluirRestaurante(@PathVariable Integer id) {
        Optional<Restaurante> restaurante =  restauranteRepository.findById(id);
        if (restaurante.isPresent()){
            restauranteRepository.deleteById(id);
            return ResponseEntity.status(200).build();
        }else{
            return ResponseEntity.notFound().build();
        }
     }

     @PutMapping(value = "{id}")
     public ResponseEntity<Object> alterarRestaurante(@PathVariable Integer id,
     @RequestBody Restaurante restaurante){
         Optional<Restaurante> res = restauranteRepository.findById(id);
         if (res.isPresent()){
            restaurante.setCodigo(id);
         }else{
             restauranteRepository.save(restaurante);
         }
         return ResponseEntity.status(201).build();
         
     }
    
}