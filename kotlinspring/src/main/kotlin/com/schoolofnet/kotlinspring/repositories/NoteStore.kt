package com.schoolofnet.kotlinspring.repositories

import com.schoolofnet.kotlinspring.model.Note
import org.springframework.data.repository.CrudRepository

interface NoteStore : CrudRepository<Note, Long> {}