package by.zelezinsky.reservationsystembooking.repository;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.core.types.dsl.PathBuilderFactory;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.Querydsl;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.Objects;

@Repository
public abstract class BaseRepository {

    @PersistenceContext
    private EntityManager entityManager;

    private EntityManager em() {
        return entityManager;
    }

    private Querydsl querydsl(PathBuilder<?> builder) {
        return new Querydsl(em(), builder);
    }

    public <U> JPQLQuery<U> select(Expression<U> expression) {
        return new JPAQuery<>(em()).select(expression);
    }

    public <U> JPQLQuery<U> applyPagination(Class<U> clazz, Pageable pageable, JPQLQuery<U> query) {
        if (Objects.isNull(pageable)) {
            return query;
        }
        PathBuilder<U> builder = (new PathBuilderFactory()).create(clazz);
        return querydsl(builder).applyPagination(pageable, query);
    }

    public JPQLQuery<Tuple> applyTuplePagination(Class<?> clazz, Pageable pageable, JPQLQuery<Tuple> query) {
        if (Objects.isNull(pageable)) {
            return query;
        }
        PathBuilder<?> builder = (new PathBuilderFactory()).create(clazz);
        return querydsl(builder).applyPagination(pageable, query);
    }

    public JPQLQuery<Tuple> applyPagination(JPQLQuery<Tuple> query, Pageable pageable) {
        return query.limit(pageable.getPageSize())
                .offset(pageable.getOffset());
    }

    public JPQLQuery<Tuple> select(Expression<?>... expressions) {
        return new JPAQuery<>(em()).select(expressions);
    }

}