package com.example.apirestaurantemysql.categoria;


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
@Controller // Indetifica que a classe é um controlador REST. 
@RequestMapping(path = "/categorias") //Especifica o nome do recurso REST
public class CategoriaController {

    // Instância do repositório para usarmos os métodos CRUD
    @Autowired
    private CategoriaRepository categoriaRepository;

    // GET: Retorna todas asa categorias
    @GetMapping
    public @ResponseBody Iterable<Categoria> consultarCategorias() {
        return categoriaRepository.findAll();
    }


    //POST: Incluir uma categoria
    @PostMapping
    public @ResponseBody Categoria incluirCategoria(@RequestBody Categoria categoria) {
        Categoria novaCategoria = new Categoria();
        novaCategoria.setNome(categoria.getNome());
        return categoriaRepository.save(novaCategoria);
    } 

    // GET: Obter uma categoria por seu id/codigo
   @GetMapping(value = "{id}")
   public @ResponseBody Optional<Categoria> obterCategoriaPorId(@PathVariable Integer id){
    return categoriaRepository.findById(id);
}

    // DELETE: Excluir uma categoria por id/código
    @DeleteMapping(value = "{id}")
    public ResponseEntity<Object>excluirCategoria(@PathVariable Integer id) {
    Optional<Categoria> categoria = categoriaRepository.findById(id);
    if(categoria.isPresent()) {
        categoriaRepository.deleteById(id);
        return ResponseEntity.status(200).build();
    }else{
        return ResponseEntity.notFound().build();
    }

}
    // PUT: Alteração dos dados de uma categoria
    @PutMapping(value = "{id}")
    public ResponseEntity<Object> alterarCategoria(@PathVariable Integer id,
        @RequestBody Categoria categoria){
            Optional<Categoria>cat = categoriaRepository.findById(id);
            if(cat.isPresent()) {
                categoria.setCodigo(id);
                categoriaRepository.save(categoria);
            }else{
                categoriaRepository.save(categoria);
            }
            return ResponseEntity.status(201).build();


    }
}

