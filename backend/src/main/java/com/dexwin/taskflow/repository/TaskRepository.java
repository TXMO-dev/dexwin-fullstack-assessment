package com.dexwin.taskflow.repository;

import com.dexwin.taskflow.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByProjectId(Long projectId);

    List<Task> findByTitleContainingIgnoreCase(String query);

    @Query("""
            select distinct t from Task t
            left join fetch t.assignee a
            left join fetch t.comments c
            left join fetch c.author ca
            where t.project.id = :projectId
            order by t.id
            """)
    List<Task> findByProjectIdWithAssigneeAndComments(@Param("projectId") Long projectId);
}
