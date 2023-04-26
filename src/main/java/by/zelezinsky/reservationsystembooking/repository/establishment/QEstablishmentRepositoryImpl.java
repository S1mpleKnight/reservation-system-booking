package by.zelezinsky.reservationsystembooking.repository.establishment;

import by.zelezinsky.reservationsystembooking.dto.filter.EstablishmentFilter;
import by.zelezinsky.reservationsystembooking.entity.address.QCity;
import by.zelezinsky.reservationsystembooking.entity.address.QCountry;
import by.zelezinsky.reservationsystembooking.entity.offer.Establishment;
import by.zelezinsky.reservationsystembooking.entity.offer.QEstablishment;
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
public class QEstablishmentRepositoryImpl extends BaseRepository implements QEstablishmentRepository {
    @Override
    public Page<Establishment> findAll(Pageable pageable, EstablishmentFilter filter) {
        JPQLQuery<Establishment> query = getQuery(filter);
        query = applyPagination(Establishment.class, pageable, query);
        return new PageImpl<>(query.fetch(), pageable, query.fetchCount());
    }

    private JPQLQuery<Establishment> getQuery(EstablishmentFilter filter) {
        JPQLQuery<Establishment> query = select(QEstablishment.establishment).from(QEstablishment.establishment);
        addJoin(query);
        query.where(getPredicate(filter));
        return query;
    }

    private Predicate getPredicate(EstablishmentFilter filter) {
        BooleanBuilder builder = new BooleanBuilder();
        if (Objects.nonNull(filter)) {
            if (CollectionUtils.isNotEmpty(filter.getContactIds())) {
                builder.and(QUser.user.id.in(filter.getContactIds()));
            }
            if (CollectionUtils.isNotEmpty(filter.getCountryIds())) {
                builder.and(QCountry.country.id.in(filter.getCountryIds()));
            }
            if (CollectionUtils.isNotEmpty(filter.getCityIds())) {
                builder.and(QCity.city.id.in(filter.getCityIds()));
            }
            if (Objects.nonNull(filter.getHasCity())) {
                builder.and(QEstablishment.establishment.hasCity.eq(filter.getHasCity()));
            }
            if (Objects.nonNull(filter.getHasCountry())) {
                builder.and(QEstablishment.establishment.hasCountry.eq(filter.getHasCountry()));
            }
            if (Objects.nonNull(filter.getHasBuilding())) {
                builder.and(QEstablishment.establishment.hasBuilding.eq(filter.getHasBuilding()));
            }
            if (Objects.nonNull(filter.getHasApartment())) {
                builder.and(QEstablishment.establishment.hasApartment.eq(filter.getHasApartment()));
            }
            if (Objects.nonNull(filter.getHasStreet())) {
                builder.and(QEstablishment.establishment.hasStreet.eq(filter.getHasStreet()));
            }
        }
        return builder.getValue();
    }

    private void addJoin(JPQLQuery<Establishment> query) {
        query.leftJoin(QUser.user)
                .leftJoin(QCity.city)
                .leftJoin(QCountry.country);
    }
}
