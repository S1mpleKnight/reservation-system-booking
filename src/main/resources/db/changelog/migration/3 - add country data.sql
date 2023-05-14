INSERT INTO country
VALUES ('7b0f0d16-595c-4b8f-bb6f-35defc6e809d', 'Belarus');
INSERT INTO country
VALUES ('ed965dd2-bfa9-4998-b98d-d7dc95bbcc92', 'Russia');
INSERT INTO country
VALUES ('b973af17-1759-465e-a0bd-d844964829ca', 'USA');
INSERT INTO country
VALUES ('1f492030-dff1-42ae-94ab-c9885f5d966d', 'Poland');
INSERT INTO country
VALUES ('fbab9d64-4754-487b-9e14-2edd12998d94', 'Brazil');

INSERT INTO city
VALUES ('947c15f5-1c65-4e1e-aff7-f6891eb77a19', 'Pinsk', '7b0f0d16-595c-4b8f-bb6f-35defc6e809d');
INSERT INTO city
VALUES ('46268491-18a8-42e8-b98c-c2fdc5acf7cf', 'Minsk', '7b0f0d16-595c-4b8f-bb6f-35defc6e809d');
INSERT INTO city
VALUES ('b2af3302-8bdd-4bec-91f6-bcf13bdf6866', 'Mozyr', '7b0f0d16-595c-4b8f-bb6f-35defc6e809d');
INSERT INTO city
VALUES ('a33f41c3-ce5b-452e-adde-3be425428cd5', 'Moscow', 'ed965dd2-bfa9-4998-b98d-d7dc95bbcc92');
INSERT INTO city
VALUES ('b160e1b6-1bbc-4884-abb6-254f0534633a', 'Tomsk', 'ed965dd2-bfa9-4998-b98d-d7dc95bbcc92');
INSERT INTO city
VALUES ('0e028caf-e249-47e5-b022-7e75f61d4956', 'Smolensk', 'ed965dd2-bfa9-4998-b98d-d7dc95bbcc92');

INSERT INTO users
VALUES ('46ffd4fc-4452-4ee8-8192-96fe46f89b4b', '2002-05-29', 's1mpleknigh@mail.ru', 'Vanya', true, 'Zelezinsky',
        '$2a$12$Jj1et9eg0Z7z/PjEuDbyUup64sGUGyEQuedhaBfUfJPFD1frgTXti', 's1mpleknight',
        'd7c14ad6-0382-4ee0-8ab1-189aa666c6ba');

INSERT INTO establishments
VALUES ('a5123302-0486-40e1-bada-b1d361ab94dc', NULL, NULL, '46ffd4fc-4452-4ee8-8192-96fe46f89b4b', false, 'false',
        true, true, 'false', NULL, NULL, NULL);

INSERT INTO events
VALUES ('af66067a-a121-4e84-b3cb-47914f6badcf', '46ffd4fc-4452-4ee8-8192-96fe46f89b4b', 'Postman testing', '2023-05-15',
        true, false, '2023-05-14', NULL, 'First test event');

INSERT INTO offer_categories
VALUES ('b049e188-81c1-4eb6-aefd-abb2fd2b9278', 'restaurant');

INSERT INTO reservation_offers
VALUES ('f4abd7b8-9238-4e8b-8e9c-b102c15ed02e', '46ffd4fc-4452-4ee8-8192-96fe46f89b4b', NULL, NULL, false, false, false,
        false, 'Restaraunt places', 'NOT_OPEN', 'NONE', '2023-05-15', NULL, 'PLACE');

INSERT INTO reservation_offer_offer_category
VALUES ('f4abd7b8-9238-4e8b-8e9c-b102c15ed02e', 'b049e188-81c1-4eb6-aefd-abb2fd2b9278');
