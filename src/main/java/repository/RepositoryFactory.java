package repository;

import repository.api.GradeRepository;
import repository.api.GraduationClassRepository;
import repository.api.StudentRepository;
import repository.api.SubjectRepository;
import repository.api.TeacherRepository;
import repository.impl.GradeRepositoryImpl;
import repository.impl.GraduationClassRepositoryImpl;
import repository.impl.StudentRepositoryImpl;
import repository.impl.SubjectRepositoryImpl;
import repository.impl.TeacherRepositoryImpl;

import javax.persistence.EntityManager;

public class RepositoryFactory {

    public SubjectRepository newSubjectRepository(EntityManager entityManager) {
        return new SubjectRepositoryImpl(entityManager);
    }


}
