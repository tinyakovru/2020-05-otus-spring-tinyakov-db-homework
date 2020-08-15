package ru.otus.library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.library.models.Comment;

public interface CommentRepository extends JpaRepository<Comment,Long> {}
