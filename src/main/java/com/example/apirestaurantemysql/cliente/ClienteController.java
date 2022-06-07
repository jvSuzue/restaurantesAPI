package com.example.apirestaurantemysql.cliente;

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
@RequestMapping(path = "/clientes")
public class ClienteController {
    
    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    public @ResponseBody Iterable<Cliente> consultarClientes() {
        return clienteRepository.findAll();
    }

    @GetMapping(value = "{id}")
    public @ResponseBody Optional<Cliente> consultarClientes(@PathVariable int id){
        return clienteRepository.findById(id);
    }

    @PostMapping
    public @ResponseBody Cliente incluirCliente(@RequestBody Cliente cliente){
        Cliente novoCliente = new Cliente();
        novoCliente.setNome(cliente.getNome());
        novoCliente.setEmail(cliente.getEmail());
        novoCliente.setTelefone(cliente.getTelefone());
        return clienteRepository.save(novoCliente);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Object> excluirCliente(@PathVariable Integer id) {
        Optional<Cliente> cliente =  clienteRepository.findById(id);
        if (cliente.isPresent()){
            clienteRepository.deleteById(id);
            return ResponseEntity.status(200).build();
        }else{
            return ResponseEntity.notFound().build();
        }
     }

     @PutMapping(value = "{id}")
     public ResponseEntity<Object> alterarCliente(@PathVariable Integer id,
     @RequestBody Cliente cliente){
         Optional<Cliente> cli = clienteRepository.findById(id);
         if (cli.isPresent()){
             cliente.setCodigo(id);
         }else{
             clienteRepository.save(cliente);
         }
         return ResponseEntity.status(201).build();
         
     }
    
}
