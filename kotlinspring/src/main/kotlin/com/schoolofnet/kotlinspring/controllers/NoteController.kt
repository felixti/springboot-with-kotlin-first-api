package com.schoolofnet.kotlinspring.controllers

import com.schoolofnet.kotlinspring.model.Note
import com.schoolofnet.kotlinspring.repositories.NoteStore
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/notes")
class NoteController(val noteStore: NoteStore) {

    @GetMapping("/")
    fun findAll() =
            noteStore.findAll()

    @GetMapping("/{id}")
    fun findById(@PathVariable(value = "id") id: Long) =
            noteStore.findById(id).map { note ->
                ResponseEntity.ok(note)
            }.orElse(ResponseEntity.notFound().build())

    @PostMapping("/")
    fun create(@RequestBody note: Note) =
            noteStore.save(note)

    @PutMapping("/{id}")
    fun update(@PathVariable(value = "id") id: Long, @RequestBody note: Note) =
            noteStore.findById(id).map { originalNote ->
                val updated = originalNote.copy(title = note.title, body = note.body)

                ResponseEntity.ok().body(noteStore.save(updated))
            }.orElse(ResponseEntity.notFound().build())

    @DeleteMapping("/{id}")
    fun remove(@PathVariable(value = "id") id: Long) =
            noteStore.deleteById(id)
}