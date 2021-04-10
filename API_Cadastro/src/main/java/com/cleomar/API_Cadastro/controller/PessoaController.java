package com.cleomar.API_Cadastro.controller;

import com.cleomar.API_Cadastro.Entity.Pessoa;
import com.cleomar.API_Cadastro.repository.PessoaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PessoaController {

    private PessoaRepository pessoaRepository;

    @RequestMapping(value = "/cadastro", method =  RequestMethod.POST)
    public ResponseEntity<Pessoa> AdicionarPessoa( @RequestBody Pessoa pessoa){
        pessoaRepository.save(pessoa);
        return new ResponseEntity<>(pessoa, HttpStatus.OK);
    }

    //Metodo Buscar ou get
    @RequestMapping(value = "/cadastro", method = RequestMethod.GET)
    public List<Pessoa> Buscar() {
        return pessoaRepository.findAll();
    }
  //Buscar por id
    @RequestMapping(value = "/cadastro/{id}", method = RequestMethod.GET)
    public ResponseEntity<Pessoa> BuscarPorId(@PathVariable(value ="id") long id)
    {
        Optional<Pessoa> pessoa = pessoaRepository.findById(id);
        if(pessoa.isPresent())
            //return ResponseEntity.ok("Pessoa Encontado");
            return new ResponseEntity<Pessoa>(pessoa.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    //DELETE
    @RequestMapping(value = "/cadastro/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> Deletar(@PathVariable(value ="id") long id)
    {
        Optional<Pessoa> pessoa = pessoaRepository.findById(id);
        if(pessoa.isPresent()){
            pessoaRepository.delete(pessoa.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //Atualizar
    // PUT
    @RequestMapping(value = "/cadastro/{id}", method =  RequestMethod.PUT)
    public ResponseEntity<Pessoa> Atualizar(@PathVariable(value = "id") long id, @RequestBody Pessoa newPessoa)
    {
        Optional<Pessoa> pessoaAnterior = pessoaRepository.findById(id);
        if(pessoaAnterior.isPresent()){
            Pessoa pessoa = pessoaAnterior.get();
            pessoa.setNome(newPessoa.getNome());
            pessoaRepository.save(pessoa);
            return new ResponseEntity<Pessoa>(pessoa, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }



}
