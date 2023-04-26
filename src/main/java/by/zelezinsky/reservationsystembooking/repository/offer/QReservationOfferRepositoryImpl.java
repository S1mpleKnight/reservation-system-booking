package by.zelezinsky.reservationsystembooking.repository.offer;

import by.zelezinsky.reservationsystembooking.dto.filter.ReservationOfferFilter;
import by.zelezinsky.reservationsystembooking.entity.offer.*;
import by.zelezinsky.reservationsystembooking.entity.reservation.QReservationUnit;
import by.zelezinsky.reservationsystembooking.entity.reservation.QReservationUnitType;
import by.zelezinsky.reservationsystembooking.entity.reservation.QReservationUnitedPart;
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
public class QReservationOfferRepositoryImpl extends BaseRepository implements QReservationOfferRepository {

    @Override
    public Page<ReservationOffer> findAll(Pageable pageable, ReservationOfferFilter filter) {
        JPQLQuery<ReservationOffer> query = getQuery(filter);
        query = applyPagination(ReservationOffer.class, pageable, query);
        return new PageImpl<>(query.fetch(), pageable, query.fetchCount());
    }

    private JPQLQuery<ReservationOffer> getQuery(ReservationOfferFilter filter) {
        JPQLQuery<ReservationOffer> query = select(QReservationOffer.reservationOffer)
                .from(QReservationOffer.reservationOffer);
        addJoin(query);
        query.where(getPredicate(filter));
        return query;
    }

    private void addJoin(JPQLQuery<ReservationOffer> query) {
        query.leftJoin(QEvent.event)
                .leftJoin(QReservationUnit.reservationUnit)
                .leftJoin(QReservationUnitedPart.reservationUnitedPart)
                .leftJoin(QOfferCategory.offerCategory)
                .leftJoin(QUser.user)
                .leftJoin(QEstablishment.establishment)
                .leftJoin(QAdditionalOfferInfo.additionalOfferInfo)
                .leftJoin(QReservationUnitType.reservationUnitType);
    }

    private Predicate getPredicate(ReservationOfferFilter filter) {
        BooleanBuilder builder = new BooleanBuilder();

        if (Objects.nonNull(filter)) {
            if (Boolean.TRUE.equals(filter.getHasEvent())) {
                builder.and(QReservationOffer.reservationOffer.hasEvent.eq(filter.getHasEvent()));
            }
            if (CollectionUtils.isNotEmpty(filter.getEventIds())) {
                builder.and(QEvent.event.id.in(filter.getEventIds()));
            }
            if (Objects.nonNull(filter.getDateFrom())) {
                builder.and(QReservationOffer.reservationOffer.reservationDate.goe(filter.getDateFrom()));
            }
            if (Objects.nonNull(filter.getDateTo())) {
                builder.and(QReservationOffer.reservationOffer.reservationDate.loe(filter.getDateTo()));
            }
            if (Objects.nonNull(filter.getHasTime())) {
                builder.and(QReservationOffer.reservationOffer.hasTime.eq(filter.getHasTime()));
            }
            if (Objects.nonNull(filter.getTimeFrom())) {
                builder.and(QReservationOffer.reservationOffer.reservationTime.goe(filter.getTimeFrom()));
            }
            if (Objects.nonNull(filter.getTimeTo())) {
                builder.and(QReservationOffer.reservationOffer.reservationTime.loe(filter.getTimeTo()));
            }
            if (CollectionUtils.isNotEmpty(filter.getCategoryIds())) {
                builder.and(QOfferCategory.offerCategory.id.in(filter.getCategoryIds()));
            }
            if (CollectionUtils.isNotEmpty(filter.getReservationTypes())) {
                builder.and(QReservationOffer.reservationOffer.reservationType.in(filter.getReservationTypes()));
            }
            if (CollectionUtils.isNotEmpty(filter.getOrderReservationTypes())) {
                builder.and(QReservationOffer.reservationOffer.orderType.in(filter.getOrderReservationTypes()));
            }
            if (CollectionUtils.isNotEmpty(filter.getContactIds())) {
                builder.and(QUser.user.id.in(filter.getContactIds()));
            }
            if (Objects.nonNull(filter.getHasEstablishment())) {
                builder.and(QReservationOffer.reservationOffer.hasEstablishment.eq(filter.getHasEstablishment()));
            }
            if (CollectionUtils.isNotEmpty(filter.getEstablishmentIds())) {
                builder.and(QEstablishment.establishment.id.in(filter.getEstablishmentIds()));
            }
            if (CollectionUtils.isNotEmpty(filter.getStatuses())) {
                builder.and(QReservationOffer.reservationOffer.offerStatus.in(filter.getStatuses()));
            }
        }
        return builder.getValue();
    }
}
