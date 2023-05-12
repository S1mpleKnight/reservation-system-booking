CREATE TABLE public.additional_offer_info
(
    id                uuid NOT NULL,
    establishment_url character varying(255),
    event_url         character varying(255),
    image_url         character varying(255),
    offer_id          uuid NOT NULL
);


ALTER TABLE public.additional_offer_info
    OWNER TO postgres;

CREATE TABLE public.city
(
    id         uuid                   NOT NULL,
    name       character varying(255) NOT NULL,
    country_id uuid                   NOT NULL
);


ALTER TABLE public.city
    OWNER TO postgres;

CREATE TABLE public.country
(
    id   uuid                   NOT NULL,
    name character varying(255) NOT NULL
);


ALTER TABLE public.country
    OWNER TO postgres;

CREATE TABLE public.databasechangeloglock
(
    id          integer NOT NULL,
    locked      boolean NOT NULL,
    lockgranted timestamp without time zone,
    lockedby    character varying(255)
);


ALTER TABLE public.databasechangeloglock
    OWNER TO postgres;

CREATE TABLE public.establishments
(
    id            uuid                   NOT NULL,
    apartment     character varying(255),
    building      character varying(255),
    user_id       uuid                   NOT NULL,
    has_apartment boolean                NOT NULL,
    has_building  character varying(255) NOT NULL,
    has_city      boolean                NOT NULL,
    has_country   boolean                NOT NULL,
    has_street    character varying(255) NOT NULL,
    street        character varying(255),
    city_id       uuid,
    country_id    uuid
);


ALTER TABLE public.establishments
    OWNER TO postgres;

CREATE TABLE public.events
(
    id           uuid                   NOT NULL,
    user_id      uuid,
    description  character varying(255) NOT NULL,
    end_date     date,
    has_end_date boolean                NOT NULL,
    has_time     boolean                NOT NULL,
    start_date   date                   NOT NULL,
    event_time   time without time zone,
    title        character varying(255) NOT NULL
);


ALTER TABLE public.events
    OWNER TO postgres;

CREATE TABLE public.offer_categories
(
    id   uuid                   NOT NULL,
    name character varying(255) NOT NULL
);


ALTER TABLE public.offer_categories
    OWNER TO postgres;

CREATE TABLE public.permission
(
    id      uuid                   NOT NULL,
    name    character varying(255) NOT NULL,
    role_id uuid                   NOT NULL
);


ALTER TABLE public.permission
    OWNER TO postgres;

CREATE TABLE public.reservation_offer_offer_category
(
    reservation_offer_id uuid NOT NULL,
    offer_category_id    uuid NOT NULL
);


ALTER TABLE public.reservation_offer_offer_category
    OWNER TO postgres;



CREATE TABLE public.reservation_offers
(
    id                  uuid                   NOT NULL,
    user_id             uuid                   NOT NULL,
    establishment_id    uuid,
    event_id            uuid,
    has_additional_info boolean                NOT NULL,
    has_establishment   boolean                NOT NULL,
    has_event           boolean                NOT NULL,
    has_time            boolean                NOT NULL,
    name                character varying(255) NOT NULL,
    offer_status        character varying(255) NOT NULL,
    order_type          character varying(255) NOT NULL,
    reservation_date    date                   NOT NULL,
    reservation_time    time without time zone,
    reservation_type    character varying(255) NOT NULL
);


ALTER TABLE public.reservation_offers
    OWNER TO postgres;



CREATE TABLE public.reservation_unit_types
(
    id                   uuid                   NOT NULL,
    name                 character varying(255) NOT NULL,
    reservation_offer_id uuid                   NOT NULL
);


ALTER TABLE public.reservation_unit_types
    OWNER TO postgres;



CREATE TABLE public.reservation_united_parts
(
    id                   uuid    NOT NULL,
    has_parent           boolean NOT NULL,
    name                 character varying(255),
    reservation_offer_id uuid,
    parent_id            uuid
);


ALTER TABLE public.reservation_united_parts
    OWNER TO postgres;



CREATE TABLE public.reservation_units
(
    id                         uuid                   NOT NULL,
    description                character varying(255),
    has_order_number           boolean                NOT NULL,
    has_reservation_unit_type  boolean                NOT NULL,
    has_time                   boolean                NOT NULL,
    has_united_part            boolean                NOT NULL,
    name                       character varying(255) NOT NULL,
    reservation_offer_id       uuid                   NOT NULL,
    order_number               integer,
    reservation_id             uuid,
    reservation_unit_type_id   uuid,
    reservation_united_part_id uuid,
    reservation_time           time without time zone
);


ALTER TABLE public.reservation_units
    OWNER TO postgres;



CREATE TABLE public.reservations
(
    id               uuid                   NOT NULL,
    reservation_date date                   NOT NULL,
    reservation_time time without time zone NOT NULL,
    status           character varying(255) NOT NULL,
    user_id          uuid                   NOT NULL
);


ALTER TABLE public.reservations
    OWNER TO postgres;



CREATE TABLE public.roles
(
    id   uuid                   NOT NULL,
    name character varying(255) NOT NULL
);


ALTER TABLE public.roles
    OWNER TO postgres;



CREATE TABLE public.users
(
    id        uuid                   NOT NULL,
    birthday  date                   NOT NULL,
    email     character varying(255) NOT NULL,
    firstname character varying(255) NOT NULL,
    is_male   boolean                NOT NULL,
    lastname  character varying(255) NOT NULL,
    password  character varying(255) NOT NULL,
    username  character varying(255) NOT NULL,
    role_id   uuid                   NOT NULL
);


ALTER TABLE public.users
    OWNER TO postgres;

ALTER TABLE ONLY public.additional_offer_info
    ADD CONSTRAINT additional_offer_info_pkey PRIMARY KEY (id);



ALTER TABLE ONLY public.city
    ADD CONSTRAINT city_pkey PRIMARY KEY (id);



ALTER TABLE ONLY public.country
    ADD CONSTRAINT country_pkey PRIMARY KEY (id);



ALTER TABLE ONLY public.databasechangeloglock
    ADD CONSTRAINT databasechangeloglock_pkey PRIMARY KEY (id);



ALTER TABLE ONLY public.establishments
    ADD CONSTRAINT establishments_pkey PRIMARY KEY (id);



ALTER TABLE ONLY public.events
    ADD CONSTRAINT events_pkey PRIMARY KEY (id);



ALTER TABLE ONLY public.offer_categories
    ADD CONSTRAINT offer_categories_pkey PRIMARY KEY (id);



ALTER TABLE ONLY public.permission
    ADD CONSTRAINT permission_pkey PRIMARY KEY (id);



ALTER TABLE ONLY public.reservation_offers
    ADD CONSTRAINT reservation_offers_pkey PRIMARY KEY (id);



ALTER TABLE ONLY public.reservation_unit_types
    ADD CONSTRAINT reservation_unit_types_pkey PRIMARY KEY (id);



ALTER TABLE ONLY public.reservation_united_parts
    ADD CONSTRAINT reservation_united_parts_pkey PRIMARY KEY (id);



ALTER TABLE ONLY public.reservation_units
    ADD CONSTRAINT reservation_units_pkey PRIMARY KEY (id);



ALTER TABLE ONLY public.reservations
    ADD CONSTRAINT reservations_pkey PRIMARY KEY (id);



ALTER TABLE ONLY public.roles
    ADD CONSTRAINT roles_pkey PRIMARY KEY (id);



ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);



ALTER TABLE ONLY public.reservation_unit_types
    ADD CONSTRAINT fk39lc6m63gi332kuou2slfw708 FOREIGN KEY (reservation_offer_id) REFERENCES public.reservation_offers (id);



ALTER TABLE ONLY public.establishments
    ADD CONSTRAINT fk3rut9r3blvnq1fj2ijmauliea FOREIGN KEY (city_id) REFERENCES public.city (id);



ALTER TABLE ONLY public.reservation_offers
    ADD CONSTRAINT fk41i178a44gfivb5885e6tcpq8 FOREIGN KEY (event_id) REFERENCES public.events (id);



ALTER TABLE ONLY public.reservation_units
    ADD CONSTRAINT fk4qmce2fsk7y4c7u4wa77udmqs FOREIGN KEY (reservation_id) REFERENCES public.reservations (id);



ALTER TABLE ONLY public.reservation_units
    ADD CONSTRAINT fk77t7rjeu1qy6sunt9rbd823p FOREIGN KEY (reservation_offer_id) REFERENCES public.reservation_offers (id);



ALTER TABLE ONLY public.reservation_united_parts
    ADD CONSTRAINT fkaoiar0bnavmygory2snm7e9b4 FOREIGN KEY (reservation_offer_id) REFERENCES public.reservation_offers (id);



ALTER TABLE ONLY public.reservation_offers
    ADD CONSTRAINT fkasologckwnwcl41hjdbd0pjmx FOREIGN KEY (user_id) REFERENCES public.users (id);



ALTER TABLE ONLY public.events
    ADD CONSTRAINT fkat8p3s7yjcp57lny4udqvqncq FOREIGN KEY (user_id) REFERENCES public.users (id);



ALTER TABLE ONLY public.reservations
    ADD CONSTRAINT fkb5g9io5h54iwl2inkno50ppln FOREIGN KEY (user_id) REFERENCES public.users (id);



ALTER TABLE ONLY public.establishments
    ADD CONSTRAINT fkch0mmqppqj0h0dsr43lhufaxf FOREIGN KEY (user_id) REFERENCES public.users (id);



ALTER TABLE ONLY public.reservation_units
    ADD CONSTRAINT fkepf2dkqrtyn67tn7wa2wgvk8l FOREIGN KEY (reservation_unit_type_id) REFERENCES public.reservation_unit_types (id);



ALTER TABLE ONLY public.reservation_offers
    ADD CONSTRAINT fkim2kqn9qbmmp3i05xe715q42f FOREIGN KEY (establishment_id) REFERENCES public.establishments (id);



ALTER TABLE ONLY public.permission
    ADD CONSTRAINT fkkljbup8ulb1xvc6inep7tr5rt FOREIGN KEY (role_id) REFERENCES public.roles (id);



ALTER TABLE ONLY public.reservation_united_parts
    ADD CONSTRAINT fkm5ixo694ayvp004qvlaawvy40 FOREIGN KEY (parent_id) REFERENCES public.reservation_united_parts (id);



ALTER TABLE ONLY public.establishments
    ADD CONSTRAINT fkmecx58svwt6ftv0b2de6ork0n FOREIGN KEY (country_id) REFERENCES public.country (id);



ALTER TABLE ONLY public.additional_offer_info
    ADD CONSTRAINT fkn3c42abl3996et6s1ihwqusrm FOREIGN KEY (offer_id) REFERENCES public.reservation_offers (id);



ALTER TABLE ONLY public.reservation_offer_offer_category
    ADD CONSTRAINT fkolhykwq0trsr8q5byyu4bs3d4 FOREIGN KEY (reservation_offer_id) REFERENCES public.reservation_offers (id);



ALTER TABLE ONLY public.users
    ADD CONSTRAINT fkp56c1712k691lhsyewcssf40f FOREIGN KEY (role_id) REFERENCES public.roles (id);



ALTER TABLE ONLY public.reservation_offer_offer_category
    ADD CONSTRAINT fkp8hpy3jva20a0ffxrx39cvbps FOREIGN KEY (offer_category_id) REFERENCES public.offer_categories (id);



ALTER TABLE ONLY public.city
    ADD CONSTRAINT fkrpd7j1p7yxr784adkx4pyepba FOREIGN KEY (country_id) REFERENCES public.country (id);



ALTER TABLE ONLY public.reservation_units
    ADD CONSTRAINT fkssu7uo5fge5n4bl9g0a8i721f FOREIGN KEY (reservation_united_part_id) REFERENCES public.reservation_united_parts (id);

GRANT ALL ON SCHEMA public TO PUBLIC;






