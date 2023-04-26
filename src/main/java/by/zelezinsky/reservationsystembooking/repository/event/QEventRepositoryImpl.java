package by.zelezinsky.reservationsystembooking.repository.event;

import by.zelezinsky.reservationsystembooking.dto.filter.EventFilter;
import by.zelezinsky.reservationsystembooking.entity.offer.Event;
import by.zelezinsky.reservationsystembooking.entity.offer.QEvent;
import by.zelezinsky.reservationsystembooking.entity.user.QUser;
import by.zelezinsky.reservationsystembooking.repository.BaseRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.JPQLQuery;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Objects;

@Repository
public class QEventRepositoryImpl extends BaseRepository implements QEventRepository {

    @Override
    public Page<Event> findAll(Pageable pageable, EventFilter filter) {
        JPQLQuery<Event> query = getQuery(filter);
        query = applyPagination(Event.class, pageable, query);
        return new PageImpl<>(query.fetch(), pageable, query.fetchCount());
    }

    private JPQLQuery<Event> getQuery(EventFilter filter) {
        JPQLQuery<Event> query = select(QEvent.event).from(QEvent.event);
        query.leftJoin(QUser.user);
        query.where(getPredicate(filter));
        return query;
    }

    private Predicate getPredicate(EventFilter filter) {
        BooleanBuilder builder = new BooleanBuilder();
        if (Objects.nonNull(filter)) {
            if (CollectionUtils.isNotEmpty(filter.getContactIds())) {
                builder.and(QUser.user.id.in(filter.getContactIds()));
            }
            if (Objects.nonNull(filter.getDateFrom())) {
                builder.and(QEvent.event.startDate.goe(filter.getDateFrom()));
            }
            if (Objects.nonNull(filter.getHasEndDate())) {
                builder.and(QEvent.event.hasEndDate.eq(filter.getHasEndDate()));
            }
            if (Objects.nonNull(filter.getDateTo())) {
                builder.and(QEvent.event.startDate.loe(filter.getDateTo()));
            }
            if (Objects.nonNull(filter.getHasTime())) {
                builder.and(QEvent.event.hasTime.eq(filter.getHasTime()));
            }
            if (Objects.nonNull(filter.getTimeFrom())) {
                builder.and(QEvent.event.time.goe(filter.getTimeFrom()));
            }
            if (Objects.nonNull(filter.getTimeTo())) {
                builder.and(QEvent.event.time.loe(filter.getTimeTo()));
            }
        }
        return builder.getValue();
    }
}