package softlab.satestodavalebaback.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import softlab.satestodavalebaback.DTO.SearchParams;

public interface StudentAndTeacherService<T> {
    T add (T t);

    T update (T t, int id);

    String delete (int id);

    T  getById (int id);

    Page<T> getAll (SearchParams params, Pageable pageable);
}




