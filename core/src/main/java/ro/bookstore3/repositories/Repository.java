package ro.bookstore3.repositories;

import org.springframework.transaction.annotation.Transactional;
import ro.bookstore3.models.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
@Transactional
public interface Repository
        <T extends BaseEntity<ID>,ID extends Serializable>
        extends JpaRepository<T, ID>
{

}
