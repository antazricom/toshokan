INSERT INTO public.category
    (id, name, type, uuid)
VALUES
    (1, 'Littérature japonaise', 'book', 'dcf4fa13-c420-4060-bf8f-44d95f046a65'),
    (2, 'Littérature française', 'book', '0d3bed1c-641d-4893-8bf9-f9a2645cf2f6'),
    (3, 'Littérature russe', 'book', '6d761fd5-4298-4d91-bf6e-00e694f69381'),
    (4, 'Littérature américaine', 'book', '36478908-a36b-4879-aebe-673a7a4a3fe9'),
    (5, 'Fantastique - Fantasy', 'book', 'e60ef8ea-695b-4eb6-8793-9f04cacec1e2'),
    (6, 'Rap', 'vinyl', '930c457d-3023-497e-92f3-e989ed90ae15'),
    (7, 'Pop - Rock', 'vinyl', '4de81d2d-88b0-4d27-a170-5c81e05af82e'),
    (8, 'Electro', 'vinyl', '62f983ee-9ccf-4fce-9464-344784502e0e'),
    (9, 'Metal', 'vinyl', '0209ffbe-0a9c-4c7d-aff8-b073dcde08ac');

ALTER SEQUENCE category_id_seq RESTART 6;

INSERT INTO public.author
    (id, firstname, lastname, uuid)
VALUES
    (1, 'Haruki', 'Murakami', '1555717d-a80e-4c50-ab30-3ee401628eb5'),
    (2, 'Risa', 'Wataya', 'ec8278e5-ccdf-4079-b706-439e2d111c43'),
    (3, 'Ernest', 'Hemingway', 'c06513e2-b7b6-41bd-9f65-a59e4088d033'),
    (4, 'Leon', 'Tolstoï', '21a37923-a8b8-4a6b-b05d-8bdccc8b3ccc'),
    (5, 'John Ronald Reuel', 'Tolkien', '5e5f1181-2a10-4e96-9b73-ce4c7ba2b978');

ALTER SEQUENCE author_id_seq RESTART 6;

INSERT INTO public.book
    (id, isbn, publication_year, title, uuid, author_id, category_id)
VALUES
    (1, '2714447074', '2009', '1Q84 Tome 1', '9cef070b-ccdf-44ce-a355-b68eb509d946', 1, 1),
    (2, '2714449840', '2009', '1Q84 Tome 2', '5f4d67ee-5274-44fa-98b3-e7f12a0f75d3', 1, 1),
    (3, '2714449859', '2010', '1Q84 Tome 3', 'c75a4ae6-f797-4536-868f-a19b10912c3c', 1, 1),
    (4, '2253049417', '1937', 'Le Hobbit', 'e0116867-ee11-4ca0-8909-542aac74e5b2', 5, 5),
    (5, '2266154117', '1954', 'Le Seigneur des Anneaux, Tome 1 : La Communauté de l''Anneau', '8b9d3896-cb72-49f3-940a-82d6e3a2a4cd', 5, 5),
    (6, '2070364550', '1940', 'Pour qui sonne le glas', '2dac057a-08cd-45dc-b818-7fa09a2f5d81', 3, 4),
    (7, '207036027X', '1929', 'L''adieu aux armes', '72dcfbfd-2a22-4064-8c69-fe56a20edc93', 3, 4),
    (8, '2809700168', '2008', 'Appel du pied', '996f1c08-0fa0-4892-a6f7-28dd553ff4df', 2, 1),
    (9, 'B074X5JZFJ', '2017', 'Guerre et Paix: Intégral', '92adb27f-cdee-4907-8304-e59c29036518', 4, 3);

ALTER SEQUENCE book_id_seq RESTART 10;

INSERT INTO public.artist
(id, name, uuid)
VALUES
       (1, 'U2', 'f154392e-7d08-47ed-8d4c-5483f08f8acc'),
       (2, 'IAM', '49fafd7f-e360-4870-938a-f5959884d93c'),
       (3, 'John Mayer', 'f25116a9-525e-4984-ad21-3826a6b79814'),
       (4, 'Deftones', '1138b916-cb7d-48a0-abed-0f62ea7c76a8'),
       (5, 'Linkin Park', '2250f3c8-a03e-4141-ac6c-0e8b94c7a079');

ALTER SEQUENCE artist_id_seq RESTART 6;

INSERT INTO public.vinyl
(id, title, publication_year, uuid, artist_id, category_id)
VALUES
       (1, 'The Joshua Tree', '2017', '75c14359-5fa1-4c35-aa88-604fb0dc037e', 1, 7),
       (2, 'Minutes to Midnight', '2007', 'e8b28f1b-bbec-44e1-a1b1-ca1958b09640', 5, 7),
       (3, 'Koi No Yokan', '2008', 'b3f29041-33ee-444e-838e-f90274b68a16', 4, 9),
       (4, 'Born and Raised', '2011', '3b2a4b71-fce5-43c0-be39-6905306ece5f', 3, 7),
       (5, 'Yasuke', '2017', 'dcd671ae-9154-4deb-bd0c-bc4c5e44217e', 2, 6);

ALTER SEQUENCE vinyl_id_seq RESTART 6;