package fr.uga.im2ag.l3.miage.db.repository;

import fr.uga.im2ag.l3.miage.db.repository.api.GradeRepository;
import fr.uga.im2ag.l3.miage.db.repository.api.GraduationClassRepository;
import fr.uga.im2ag.l3.miage.db.repository.api.StudentRepository;
import fr.uga.im2ag.l3.miage.db.repository.api.SubjectRepository;
import fr.uga.im2ag.l3.miage.db.repository.api.TeacherRepository;
import fr.uga.im2ag.l3.miage.db.repository.impl.GradeRepositoryImpl;
import fr.uga.im2ag.l3.miage.db.repository.impl.GraduationClassRepositoryImpl;
import fr.uga.im2ag.l3.miage.db.repository.impl.StudentRepositoryImpl;
import fr.uga.im2ag.l3.miage.db.repository.impl.SubjectRepositoryImpl;
import fr.uga.im2ag.l3.miage.db.repository.impl.TeacherRepositoryImpl;

import javax.persistence.EntityManager;

public class RepositoryFactory {

    public SubjectRepository newSubjectRepository(EntityManager entityManager) {
        return new SubjectRepositoryImpl(entityManager);
    }

    public StudentRepository newStudentRepository(EntityManager entityManager) {
        return new StudentRepositoryImpl(entityManager);
    }

    public GradeRepository newGradeRepository(EntityManager entityManager) {
        return new GradeRepositoryImpl(entityManager);
    }

    public GraduationClassRepository newGraduationClassRepository(EntityManager entityManager) {
        return new GraduationClassRepositoryImpl(entityManager);
    }

    public TeacherRepository newTeacherRepository(EntityManager entityManager) {
        return new TeacherRepositoryImpl(entityManager);
    }

}
