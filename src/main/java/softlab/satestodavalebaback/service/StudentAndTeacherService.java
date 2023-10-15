package softlab.satestodavalebaback.service;

import softlab.satestodavalebaback.DTO.SearchParams;

import java.util.List;

public interface StudentAndTeacherService<T> {
    T add (T t);

    T update (T t, int id);

    String delete (int id);

    T  getById (int id);

    List<T> getAll (SearchParams params);
}




